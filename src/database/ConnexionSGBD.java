
package database;


import java.math.BigDecimal;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


class ConnexionSGBD {

    private static final String configurationFile = "C:/Users/marti/Documents/TIS 4/S2/Projet_SIS/ProjetSIS/src/database/BD.properties";

    public static void main(String args[]) {
        try {
            String jdbcDriver= "oracle.jdbc.driver.OracleDriver";
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
            //requetesbd.nbSpectacles(conn);
            //requetesbd.spectacles(conn);
                //Question 2
            //requetesbd.afficherNomSpectacle(conn);
                //Question 3
            //requetesbd.afficherNumeroSpectacle(conn);
                //Question 4
            //requetesbd.EnsRepSpect(conn);
                //Question 5
            //requetesbd.AjouterDateRep(conn);
                //Question 6
            //requetesbd.EnleverDateRep(conn);
            //Question 7
            //requetesbd.AjouterUnSpectacle(conn);
            //Question 8
            //requetesbd.EnleverSpectacle(conn);
            
            
            //Requêtes Cécile
            //requetesbd.affichageSpectacles(conn);
            //requetesbd.afficherNomSpectacle(conn, 101);
            //requetesbd.afficherNumeroSpectacle(conn, "Metallica");
            //requetesbd.afficherRepresentations(conn, 104);
            /*
java.util.Date newrep = new Date();
            System.out.println(newrep.toString());
*/
            //requetesbd.ajouterRepresentation(conn, 101, java.sql.Date(newrep.getTime()));
            //requetesbd.ajouterRepresentation(conn, 101,newrep);
            
            
            //PROJET_SIS
            RequetesBD.afficherNomPrenom(conn, "5555888800");
            
            
            
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
