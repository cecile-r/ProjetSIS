package database;

import java.math.BigDecimal;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
import nf.*;

class ConnexionSGBD {

    private static final String configurationFile = "src/database/BD.properties";

    public static void main(String args[]) throws SQLException {
        try {
            String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
            String dbUrl = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:ufrima";
            String username;
            String password;

            DatabaseAccessProperties dap = new DatabaseAccessProperties(configurationFile);
            jdbcDriver = dap.getJdbcDriver();
            dbUrl = dap.getDatabaseUrl();
            username = dap.getUsername();
            password = dap.getPassword();

            // Load the database driver
            Class.forName(jdbcDriver);

            // Get a connection to the database
            Connection conn = DriverManager.getConnection(dbUrl, username, password);

            //REQUETES TESTS DE LA BD
            //RequetesBD.afficherNomPrenom(conn, "5555888800");
            //RequetesBD.afficherDateNaissanceDPI(conn,"5555888800");
            
          
            //TESTS DES REQUETES : définition des paramètres
            Service service = Service.valueOf("Anesthesie");
            String serviceS = "Anesthesie";
            Date d = new Date(2000, 12, 20);
            MedecinTraitant m = new MedecinTraitant("bruce.batman@yahoo.com", "Batman", "Bruce", "0696471245");
            //DPI dpi = new DPI("5500055544", "Richard", "Cecile", d, Sexe.valueOf("femme"), "18 chemin de bel air Saint Sauveur", "0782813245", m);
            
            
            // Test getListePH() -> VALIDE
            /*System.out.println(RequetesBD.getListePH(conn));
            List<PH> lph = RequetesBD.getListePH(conn);
            for (int i = 0; i < lph.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lph.get(i).toString());
                System.out.println(lph.get(i).getIdPH());
                System.out.println(lph.get(i).getMdp());
                System.out.println(lph.get(i).getTelephone());
            }*/
            
            
            // Test getListePHSimplifie() -> VALIDE
            /*System.out.println(RequetesBD.getListePHSimplifie(conn));
            List<PHSimplifie> lph = RequetesBD.getListePHSimplifie(conn);
            for (int i = 0; i < lph.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lph.get(i).toString());
            }*/
            
            
            // Test getListePH(nom) -> VALIDE
            /*System.out.println(RequetesBD.getListePH(conn, "Wawrinka"));
            List<PH> lphn = RequetesBD.getListePH(conn, "Wawrinka");
            for (int i = 0; i < lphn.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lphn.get(i).toString());
                System.out.println(lphn.get(i).getIdPH());
                System.out.println(lphn.get(i).getMdp());
                System.out.println(lphn.get(i).getTelephone());
            }*/
            
            
            // Test getListePHSimplifie(nom) -> VALIDE
            /*System.out.println(RequetesBD.getListePHSimplifie(conn, "Wawrinka"));
            List<PHSimplifie> lphn = RequetesBD.getListePHSimplifie(conn, "Wawrinka");
            for (int i = 0; i < lphn.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lphn.get(i).toString());
            }*/
            
            
            // Test getListePHService(service) -> VALIDE
            /*System.out.println(RequetesBD.getListePHService(conn, serviceS));
            List<PH> lph = RequetesBD.getListePHService(conn, serviceS);
            for (int i = 0; i < lph.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lph.get(i).toString());
                System.out.println(lph.get(i).getIdPH());
                System.out.println(lph.get(i).getMdp());
                System.out.println(lph.get(i).getTelephone());
            }*/
            
            
            // Test getListePHServiceSimpl(service) -> VALIDE
            /*System.out.println(RequetesBD.getListePHServiceSimpl(conn, serviceS));
            List<PHSimplifie> lph = RequetesBD.getListePHServiceSimpl(conn, serviceS);
            for (int i = 0; i < lph.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lph.get(i).toString());
            }*/
            
            
            // Print information about connection warnings
            SQLWarningsExceptions.printWarnings(conn);
            conn.close();
        } catch (SQLException se) {
            // Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
            return;

        } catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return;
        }

    }
}
