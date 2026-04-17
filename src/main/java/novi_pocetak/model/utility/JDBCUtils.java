package novi_pocetak.model.utility;

import novi_pocetak.model.*;
import novi_pocetak.model.base.Server;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCUtils {
    public static Connection connection = null;

    public static void connect(){
        Properties properties = new Properties();
        properties.put("user", "root");
        properties.put("password", "");
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novi_pocetak200", properties);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Psychotherapist> selectAllFromPsihoterapeut(){
        List<Psychotherapist> psychotherapists = new ArrayList<>();
        String query = "select * from psihoterapeut";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int psihijatar_id=resultSet.getInt(1);
                String ime=resultSet.getString(2);
                String prezime=resultSet.getString(3);
                String jmbg=resultSet.getString(4);
                LocalDate datum=resultSet.getDate(5).toLocalDate();
                String prebivaliste=resultSet.getString(6);
                String brojTelefona=resultSet.getString(7);
                String email=resultSet.getString(8);
                LocalDate datumSertifikacije=resultSet.getDate(9).toLocalDate();
                String lozinka=resultSet.getString(10);
                int oblast_psihoterapije_oblast_id=resultSet.getInt(11);
                int centar_za_obuku_centar_id=resultSet.getInt(12);
                Psychotherapist psychotherapist = new Psychotherapist(psihijatar_id,ime,prezime,jmbg,datum,prebivaliste,brojTelefona,email,datumSertifikacije,lozinka,oblast_psihoterapije_oblast_id,centar_za_obuku_centar_id);
                psychotherapists.add(psychotherapist);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return psychotherapists;
    }
    public static List<Seansa> izvuciSeanseDoDanas(int idUlogovanog){
        List<Seansa> seanse = new ArrayList<>();
        String query = "SELECT \n" +
                "    s.seansa_id,\n" +
                "    s.dan,\n" +
                "    s.vreme,\n" +
                "    s.trajanje,\n" +
                "\ts.opis_seanse,\n" +
                "    s.objavljivanje_podataka,\n" +
                "    s.razlog_objavljivanja,\n" +
                "    s.kome_su_objavljeni,\n" +
                "    s.vreme_objavljivanja,\n" +
                "    k.ime AS klijent_ime,\n" +
                "    k.prezime AS klijent_prezime\n" +
                "FROM \n" +
                "    seansa s\n" +
                "INNER JOIN \n" +
                "    klijent k ON s.klijent_klijent_id = k.klijent_id\n" +
                "INNER JOIN \n" +
                "    drziSeansu d ON s.drziSeansu_drzi_id = d.drzi_id\n" +
                "LEFT JOIN \n" +
                "    sertifikacija cert ON d.kandidat_kandidat_id = cert.kandidat_kandidat_id\n" +
                "WHERE \n" +
                "    ((d.psihoterapeut_psihoterapeut_id = ? AND d.kandidat_kandidat_id IS NULL)\n" +
                "    OR\n" +
                "    (cert.psihoterapeut_psihoterapeut_id = ?)) \n" +
                "    AND s.dan < CURRENT_DATE\n" +
                "ORDER BY \n" +
                "    s.dan DESC;";

        try{
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idUlogovanog);
            statement.setInt(2, idUlogovanog);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int seansaId = resultSet.getInt(1);
                LocalDate dan = resultSet.getDate(2).toLocalDate();
                Time vreme = resultSet.getTime(3);
                Time trajanje = resultSet.getTime(4);
                String opis = resultSet.getString(5);
                Boolean objavljivanje = resultSet.getBoolean(6);
                String razlog = resultSet.getString(7);
                String kome = resultSet.getString(8);
                LocalDate vremeObj = null;
                Date pom = resultSet.getDate(9);
                if(pom != null){
                    vremeObj = pom.toLocalDate();
                }
                String klijentIme = resultSet.getString(10);
                String klijentPrezime = resultSet.getString(11);
                Seansa s = new Seansa(seansaId,dan,vreme,trajanje, klijentIme, klijentPrezime, opis, objavljivanje, razlog, kome, vremeObj);
                seanse.add(s);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return seanse;
    }

    public static List<Seansa> izvuciSeanseOdDanas(Psychotherapist psychotherapist){
        List<Seansa> seanse = new ArrayList<>();
        try{
            String query="SELECT\n" +
                    "    s.seansa_id,\n" +
                    "    s.dan,\n" +
                    "    s.vreme,\n" +
                    "    s.trajanje,\n" +
                    "    s.opis_seanse,\n" +
                    "    s.objavljivanje_podataka,\n" +
                    "    s.razlog_objavljivanja,\n" +
                    "    s.kome_su_objavljeni,\n" +
                    "    k.ime AS klijent_ime,\n" +
                    "    k.prezime AS klijent_prezime\n" +
                    "FROM\n" +
                    "    seansa s\n" +
                    "INNER JOIN\n" +
                    "    klijent k ON s.klijent_klijent_id = k.klijent_id\n" +
                    "INNER JOIN\n" +
                    "    drziSeansu d ON s.drziSeansu_drzi_id = d.drzi_id\n" +
                    "LEFT JOIN\n" +
                    "    sertifikacija cert ON d.kandidat_kandidat_id = cert.kandidat_kandidat_id\n" +
                    "WHERE\n" +
                    "    ((d.psihoterapeut_psihoterapeut_id = ? AND d.kandidat_kandidat_id IS NULL)\n" +
                    "    OR\n" +
                    "    (cert.psihoterapeut_psihoterapeut_id = ?))\n" +
                    "    AND s.dan >= CURRENT_DATE\n" +
                    "ORDER BY\n" +
                    "    s.dan ASC;\n";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, psychotherapist.getPsihijatarId());
            preparedStatement.setInt(2, psychotherapist.getPsihijatarId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int seansaId = resultSet.getInt(1);
                LocalDate dan = resultSet.getDate(2).toLocalDate();
                Time vreme = resultSet.getTime(3);
                Time trajanje = resultSet.getTime(4);
                String opis = resultSet.getString(5);
                Boolean objavljivanje = resultSet.getBoolean(6);
                String razlog = resultSet.getString(7);
                String kome = resultSet.getString(8);
                LocalDate vremeObj = null;
                String klijentIme = resultSet.getString(9);
                String klijentPrezime = resultSet.getString(10);
                Seansa s = new Seansa(seansaId,dan,vreme,trajanje, klijentIme, klijentPrezime, opis, objavljivanje, razlog, kome, vremeObj);
                seanse.add(s);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return seanse;
    }

    public static Seansa izvuciPregledSeanse(int idSeanse){
        String sql = "SELECT \n" +
                "    s.seansa_id,\n" +
                "    s.dan,\n" +
                "    s.vreme,\n" +
                "    s.trajanje,\n" +
                "\ts.opis_seanse,\n" +
                "    s.objavljivanje_podataka,\n" +
                "    s.razlog_objavljivanja,\n" +
                "    s.kome_su_objavljeni,\n" +
                "    s.vreme_objavljivanja,\n" +
                "    k.ime AS klijent_ime,\n" +
                "    k.prezime AS klijent_prezime\n" +
                "FROM \n" +
                "    seansa s\n" +
                "INNER JOIN \n" +
                "    klijent k ON s.klijent_klijent_id = k.klijent_id\n" +
                "INNER JOIN \n" +
                "    drziSeansu d ON s.drziSeansu_drzi_id = d.drzi_id\n" +
                "LEFT JOIN \n" +
                "    sertifikacija cert ON d.kandidat_kandidat_id = cert.kandidat_kandidat_id\n" +
                "WHERE \n" +
                "\t s.seansa_id = ?;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, idSeanse);
            ResultSet resultSet = ps.executeQuery();
            while(resultSet.next()){
                int seansaId = resultSet.getInt(1);
                LocalDate dan = resultSet.getDate(2).toLocalDate();
                Time vreme = resultSet.getTime(3);
                Time trajanje = resultSet.getTime(4);
                String opis = resultSet.getString(5);
                Boolean objavljivanje = resultSet.getBoolean(6);
                String razlog = resultSet.getString(7);
                String kome = resultSet.getString(8);
                LocalDate vremeObj = null;
                Date pom = resultSet.getDate(9);
                if(pom != null){
                    vremeObj = pom.toLocalDate();
                }
                String klijentIme = resultSet.getString(10);
                String klijentPrezime = resultSet.getString(11);
                Seansa s = new Seansa(seansaId,dan,vreme,trajanje, klijentIme, klijentPrezime, opis, objavljivanje, razlog, kome, vremeObj);
                return s;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    public static List<Dugovanja> izvuciDugovanja(){
        List<Dugovanja> dugovanja = new ArrayList<>();
        String query = "SELECT \n" +
                "    s.seansa_id,\n" +
                "    s.klijent_klijent_id,\n" +
                "    s.dan AS datum_seanse,\n" +
                "    cs.cena AS cena_seanse,\n" +
                "    COALESCE(SUM(p.uplaceno), 0) AS ukupno_placeno,\n" +
                "    cs.cena - COALESCE(SUM(p.uplaceno), 0) AS dug\n" +
                "FROM \n" +
                "    seansa s\n" +
                "INNER JOIN \n" +
                "    cena_seanse cs ON s.cena_seanse_cena_id = cs.cena_id\n" +
                "LEFT JOIN \n" +
                "    placanje p ON s.seansa_id = p.seansa_seansa_id\n" +
                "GROUP BY \n" +
                "    s.seansa_id, s.klijent_klijent_id, s.dan, cs.cena\n" +
                "ORDER BY \n" +
                "    s.dan DESC;";
        try{
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                int seansa_id=resultSet.getInt(1);
                int klijent_id=resultSet.getInt(2);
                LocalDate datum = resultSet.getDate(3).toLocalDate();
                int cena = resultSet.getInt(4);
                int uplaceno = resultSet.getInt(5);
                int dug = resultSet.getInt(6);
                Dugovanja dugovanje = new Dugovanja(seansa_id, klijent_id, datum, cena, uplaceno, dug);
                dugovanja.add(dugovanje);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return dugovanja;
    }

    public static void insertPsychotherapist(String ime, String prezime, String jmbg, Date datumRodjenja, String prebivaliste,
                       String brojTelefona, String email, Date datumSertifikacije, String lozinka,
                       int oblastId, int centarId){
        try{
            int psihijatarId= Server.getInstance().getBrPsihoterapeuta()+1;
            CallableStatement callableStatement = connection.prepareCall(
                    "{CALL insert_psihoterapeut(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}"
            );
            //str_to_date(?,'%m/%d/%Y')
            //connection.setAutoCommit(false);
            callableStatement.setInt(1,psihijatarId);
            callableStatement.setString(2, ime);
            callableStatement.setString(3, prezime);
            callableStatement.setString(4, jmbg);
            callableStatement.setDate(5, datumRodjenja);
            callableStatement.setString(6, prebivaliste);
            callableStatement.setString(7, brojTelefona);
            callableStatement.setString(8, email);
            callableStatement.setDate(9, datumSertifikacije);
            callableStatement.setString(10, lozinka);
            callableStatement.setInt(11, oblastId);
            callableStatement.setInt(12, centarId);

            callableStatement.executeUpdate();
            //connection.commit();
            Server.getInstance().setBrPsihoterapeuta(psihijatarId);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static void objavaPodataka(Seansa s, String razlog, String kome){
        try {
            CallableStatement cs = connection.prepareCall("{CALL objavi_podatke_o_seansi(?,?,?)}");
            cs.setInt(1, s.getSeansaId());
            cs.setString(2, kome);
            cs.setString(3, razlog);
            cs.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static Psychotherapist logPsychotherapist(String em, String loz){
        try{
            String query = "select * from psihoterapeut WHERE mejl = ? AND lozinka = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            //connection.setAutoCommit(false);
            preparedStatement.setString(1, em);
            preparedStatement.setString(2, loz);
            ResultSet resultSet = preparedStatement.executeQuery();
            //connection.commit();
            while (resultSet.next()){
                int psihijatar_id=resultSet.getInt(1);
                String ime=resultSet.getString(2);
                String prezime=resultSet.getString(3);
                String jmbg=resultSet.getString(4);
                LocalDate datum=resultSet.getDate(5).toLocalDate();
                String prebivaliste=resultSet.getString(6);
                String brojTelefona=resultSet.getString(7);
                String email=resultSet.getString(8);
                LocalDate datumSertifikacije=resultSet.getDate(9).toLocalDate();
                String lozinka=resultSet.getString(10);
                int oblast_psihoterapije_oblast_id=resultSet.getInt(11);
                int centar_za_obuku_centar_id=resultSet.getInt(12);
                Psychotherapist psychotherapist = new Psychotherapist(psihijatar_id,ime,prezime,jmbg,datum,prebivaliste,brojTelefona,email,datumSertifikacije,lozinka,oblast_psihoterapije_oblast_id,centar_za_obuku_centar_id);
                return psychotherapist;
            }
            return null;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static Psychotherapist infoPs(Psychotherapist psychotherapist){
        try{
            String query="SELECT p.psihoterapeut_id, p.ime, p.prezime, p.mejl, p.brojtelefona, p.datumSertifikacije, o.naziv" +
                    " FROM psihoterapeut p JOIN oblast_psihoterapije o ON p.oblast_psihoterapije_oblast_id = o.oblast_id " +
                    "WHERE p.psihoterapeut_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, psychotherapist.getPsihijatarId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int psihijatar_id = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String email = resultSet.getString(4);
                String brojTelefona = resultSet.getString(5);
                LocalDate datumSertifikacije = resultSet.getDate(6).toLocalDate();
                String nazivOblasti = resultSet.getString(7);
                Psychotherapist psiho = new Psychotherapist(psihijatar_id, ime, prezime, email, brojTelefona, datumSertifikacije, nazivOblasti);
                return psiho;
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    public static List<Test> izvuciTestoveZaSeansu(int idSeanse){
        List<Test> testovi = new ArrayList<>();
        String query = "select\n" +
                "\tpsiholoski_test_id,\n" +
                "\toblast,\n" +
                "    naziv,\n" +
                "\tcena,\n" +
                "    rezultat\n" +
                "from\n" +
                "\tpsiholoski_test\n" +
                "where\n" +
                "\tseansa_seansa_id = ?;";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            //connection.setAutoCommit(false);
            preparedStatement.setInt(1, idSeanse);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int testId = resultSet.getInt(1);
                String oblast = resultSet.getString(2);
                String naziv = resultSet.getString(3);
                int cena = resultSet.getInt(4);
                int rez = resultSet.getInt(5);
                Test t = new Test(testId, oblast, naziv, cena ,rez, idSeanse);
                testovi.add(t);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return testovi;
    }

    public static List<Klijent> dobijKlijente(Psychotherapist psychotherapist){
        List<Klijent> klijentiLista = new ArrayList<>();
        try{
            String query="SELECT DISTINCT\n" +
                    "    k.klijent_id,\n" +
                    "    k.ime,\n" +
                    "    k.prezime,\n" +
                    "    k.mejl,\n" +
                    "    k.brojtelefona\n" +
                    "FROM\n" +
                    "    seansa s\n" +
                    "INNER JOIN\n" +
                    "    klijent k ON s.klijent_klijent_id = k.klijent_id\n" +
                    "INNER JOIN\n" +
                    "    drziSeansu d ON s.drziSeansu_drzi_id = d.drzi_id\n" +
                    "LEFT JOIN\n" +
                    "    sertifikacija cert ON d.kandidat_kandidat_id = cert.kandidat_kandidat_id\n" +
                    "WHERE\n" +
                    "    (d.psihoterapeut_psihoterapeut_id = ? AND d.kandidat_kandidat_id IS NULL)\n" +
                    "    OR\n" +
                    "    (cert.psihoterapeut_psihoterapeut_id = ?);";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, psychotherapist.getPsihijatarId());
            preparedStatement.setInt(2, psychotherapist.getPsihijatarId());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int klijentId = resultSet.getInt(1);
                String ime = resultSet.getString(2);
                String prezime = resultSet.getString(3);
                String email = resultSet.getString(4);
                String brojTelefona = resultSet.getString(5);
                Klijent klijent=new Klijent(klijentId,ime,prezime,email,brojTelefona);
                klijentiLista.add(klijent);
            }
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        return klijentiLista;
    }

    public static Connection getConnection() {
        if(connection == null){
            connect();
        }
        return connection;
    }
}


