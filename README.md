# Psychotherapy Practice Management System

> A JavaFX desktop application for managing a psychotherapy practice — enabling therapists to log in, view their session history, track client payments, manage client applications, and control session data disclosure — backed by a MySQL relational database accessed via JDBC.

---

## Overview

This application serves as a **management portal for certified psychotherapists**. After authenticating, a therapist gains access to a personalized dashboard from which they can navigate to different functional areas: their profile, their client list, past and upcoming sessions, payment status, and the ability to formally publish session data to authorized parties.

The system models the real-world structure of a psychotherapy practice: therapists hold sessions with clients, sessions have associated costs, clients make payments (potentially in installments), and session data can be formally disclosed to third parties with a recorded reason. Sessions can also be held by therapist candidates under supervision, which is reflected in the database schema and query logic.

---

## Features

- **Secure login** by email and password with database authentication
- **Therapist registration** via a stored procedure call
- **Profile view**: name, email, phone, certification date, area of specialization
- **Past sessions** (before today): date, time, duration, client name, description, disclosure status
- **Upcoming sessions** (from today): future appointment schedule
- **Session detail view**: full session info including psychological tests administered
- **Session data disclosure**: publish session data to a specified party with a logged reason and timestamp
- **Payment overview**: per-session pricing, total paid, and outstanding debt (calculated via SQL aggregation)
- **Client list**: all clients who have had at least one session with the logged-in therapist
- **New client applications**: view incoming client registration requests

---

## Architecture

The project follows a layered **MVC (Model-View-Controller)** architecture:

```
View (JavaFX)          Controller (EventHandlers)       Model / Data Layer
─────────────────      ──────────────────────────       ──────────────────
LogIn.java        ──►  LogInPs.java               ──►  JDBCUtils.logPsychotherapist()
GlavniView.java         (EventHandler<ActionEvent>)      Server.getInstance()
SeanseDoDanasView.java  InfoPs.java                      MySQL Database
SeanseOdDanasView.java  InsertPs.java
PsychotherapistInfo.java ImePrezimePs.java
DugovanjeView.java      SviKlijenti.java
PrijaveKlijenata.java
ObjaviView.java
PregledView.java
```

### `Server` — Application State (Singleton)
`Server` acts as the in-memory application state. It is a **Singleton** that loads all therapist records and outstanding payments from the database on first instantiation. It holds the currently logged-in `Psychotherapist` object (`selected`), which is read by all views to filter data for the correct user.

### `JDBCUtils` — Database Access Layer
All SQL interactions are centralized in `JDBCUtils` as static methods. This class manages the JDBC connection and exposes methods for every database operation the application needs. Parameterized queries (`PreparedStatement`) are used throughout to prevent SQL injection.

### Views — JavaFX Scene Graph
Each screen is a custom JavaFX component (extending `VBox`, `GridPane`, etc.) that builds its own layout programmatically. Navigation between screens is handled by opening a new `Stage` and closing the current one. Table views use `TableView<T>` with `TableColumn` and `PropertyValueFactory` for structured data display.

---

## Database Schema

The MySQL database (`novi_pocetak200`) models a psychotherapy practice. Key tables:

| Table | Description |
|---|---|
| `psihoterapeut` | Certified therapists with credentials, area of practice, and training center |
| `kandidat` | Therapist candidates in training (can hold supervised sessions) |
| `klijent` | Clients registered in the system |
| `seansa` | Individual therapy sessions linked to a client and a therapist/candidate |
| `drziSeansu` | Junction table: who holds a session (therapist or candidate) |
| `sertifikacija` | Supervision link: which therapist supervises which candidate |
| `psiholoski_test` | Psychological tests administered during a session |
| `cena_seanse` | Pricing history for sessions |
| `placanje` | Payment records per session (supports partial/installment payments) |
| `oblast_psihoterapije` | Areas of psychotherapy specialization |
| `centar_za_obuku` | Training centers that certify candidates |

The schema uses **composite primary keys**, **unique constraints**, and **indexes** on frequently queried columns for performance.

---

## Key Queries

### Session retrieval (past sessions)
Sessions are fetched with a multi-table join that handles both cases: a session held directly by a therapist, or held by a candidate whose supervising therapist is the logged-in user. This covers the full scope of sessions a therapist is responsible for.

```sql
SELECT s.*, k.ime, k.prezime
FROM seansa s
INNER JOIN klijent k ON s.klijent_klijent_id = k.klijent_id
INNER JOIN drziSeansu d ON s.drziSeansu_drzi_id = d.drzi_id
LEFT JOIN sertifikacija cert ON d.kandidat_kandidat_id = cert.kandidat_kandidat_id
WHERE (
    (d.psihoterapeut_psihoterapeut_id = ? AND d.kandidat_kandidat_id IS NULL)
    OR
    (cert.psihoterapeut_psihoterapeut_id = ?)
)
AND s.dan < CURRENT_DATE
ORDER BY s.dan DESC
```

### Payment / debt overview
Uses `COALESCE` and `SUM` to aggregate all payments per session and compute outstanding debt:

```sql
SELECT s.seansa_id, cs.cena,
       COALESCE(SUM(p.uplaceno), 0) AS ukupno_placeno,
       cs.cena - COALESCE(SUM(p.uplaceno), 0) AS dug
FROM seansa s
INNER JOIN cena_seanse cs ON s.cena_seanse_cena_id = cs.cena_id
LEFT JOIN placanje p ON s.seansa_id = p.seansa_seansa_id
GROUP BY s.seansa_id, s.klijent_klijent_id, s.dan, cs.cena
ORDER BY s.dan DESC
```

### Therapist registration
New therapists are inserted via a **stored procedure** call (`CALL insert_psihoterapeut(...)`), which encapsulates the insert logic on the database side.

### Session data disclosure
Disclosing session data to a third party is handled via a stored procedure (`CALL objavi_podatke_o_seansi(id, kome, razlog)`), which records the recipient, reason, and timestamp atomically.

---

## Data Models

### `Psychotherapist`
Holds full therapist data: ID, name, surname, JMBG, date of birth, address, phone, email, certification date, password, area of practice ID, and training center ID. A second constructor holds a reduced profile for display purposes (name, email, phone, certification date, area name).

### `Seansa` (Session)
Stores session ID, date, start time, duration, client name, session description, data disclosure flag, disclosure reason, recipient, and disclosure timestamp.

### `Klijent` (Client)
Stores client ID, name, surname, email, and phone number.

### `Dugovanja` (Debt Record)
Stores session ID, client ID, session date, session price, total paid, and computed outstanding debt.

### `Test` (Psychological Test)
Stores test ID, area, name, cost, result score, and the associated session ID.

---

## Application Flow

```
App launch
    │
    └── App.java → JDBCUtils.connect() → Server.getInstance()
            │
            ▼
        MainView (choose: Log In / Sign Up)
            │
       ┌────┴────┐
       │         │
    LogIn      SingUp
       │         │
  Authenticate  InsertPs (stored procedure)
       │
       ▼
   GlavniView (dashboard)
    ├── PsychotherapistInfo (profile)
    ├── SeanseDoDanasView (past sessions → detail view → disclose data)
    ├── SeanseOdDanasView (upcoming sessions)
    ├── PrijaveKlijenata (new client applications)
    └── DugovanjeView (payment overview)
```

---

## Technologies

| Technology | Role |
|---|---|
| **Java 17+** | Core language |
| **JavaFX** | Desktop GUI framework |
| **JDBC** | Direct database connectivity |
| **MySQL** | Relational database (stored procedures, views, indexes) |
| **Maven** | Build and dependency management |

---

## Setup & Configuration

**Prerequisites**: Java 17+, Maven, MySQL Server

1. Create the database by running the provided `kreiranje_baze.txt` script:
   ```bash
   mysql -u root -p < kreiranje_baze.txt
   ```

2. Update the JDBC connection string in `JDBCUtils.java` if needed:
   ```java
   connection = DriverManager.getConnection(
       "jdbc:mysql://localhost:3306/novi_pocetak200", properties
   );
   ```

3. Build and run:
   ```bash
   mvn clean javafx:run
   ```
