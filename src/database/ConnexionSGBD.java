package database;

import java.beans.Statement;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
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
            
            
            // Test getVectPH() -> VALIDE
            /*Vector lph = RequetesBD.getVectPH(conn);
            System.out.println(lph);*/
            
            
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
            
            
            // Test getVectPHNom(nom) -> VALIDE
            /*Vector lph = RequetesBD.getVectPHNom(conn, "Wawrinka");
            System.out.println(lph);*/
            
            
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
            
            
            // Test getVectPHService(service) -> VALIDE
            /*Vector lph = RequetesBD.getVectPHService(conn, serviceS);
            System.out.println(lph);*/
            
            
            //Test getListeDPI() -> VALIDE
            /*System.out.println(RequetesBD.getListeDPI(conn));
            List<DPI> ldpi = RequetesBD.getListeDPI(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPI() -> VALIDE
            /*Vector ldpi = RequetesBD.getVectorDPI(conn);
            System.out.println(ldpi);*/
            
            
            //Test getListeDPI(nom) -> VALIDE
            /*System.out.println(RequetesBD.getListeDPI(conn, "Garcin"));
            List<DPI> ldpi = RequetesBD.getListeDPI(conn, "Garcin");
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPI(nom) -> VALIDE
            /*Vector ldpi = RequetesBD.getVectorDPI(conn, "Garcin");
            System.out.println(ldpi);*/
            
            
            //Test getListeDPI(nom, service) -> VALIDE
            /*List<DPI> ldpi = RequetesBD.getListeDPI(conn, "Pilard", "Chirurgie_digestive");
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPI(nom, service) -> VALIDE
            /*Vector ldpi = RequetesBD.getVectorDPI(conn, "Pilard", "Chirurgie_digestive");
            System.out.println(ldpi);*/
            
            
            //Test getListeDPIService(service) -> VALIDE
            /*System.out.println(RequetesBD.getListeDPIService(conn, "Medecine_nucleaire"));
            List<DPI> ldpi = RequetesBD.getListeDPIService(conn, "Medecine_nucleaire");
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPIService(service) -> VALIDE
            /*Vector ldpi = RequetesBD.getVectorDPIService(conn, "Addictologie");
            System.out.println(ldpi);*/
            
            
            //Test verifyConnexion(id, mdp, statut) -> VALIDE
            /*//Medecins
            System.out.println(RequetesBD.verifyConnexion(conn, "1436985621", "facile", "Medecin")); //true
            System.out.println(RequetesBD.verifyConnexion(conn, "1436985620", "facile", "Medecin")); //false
            System.out.println(RequetesBD.verifyConnexion(conn, "1436985621", "FACILE", "Medecin")); //false
            System.out.println(RequetesBD.verifyConnexion(conn, "1045236987", "chanteuse", "Medecin")); //false
            //Infirmiers
            System.out.println(RequetesBD.verifyConnexion(conn, "1045236987", "chanteuse", "Infirmier")); //true
            System.out.println(RequetesBD.verifyConnexion(conn, "1045236987", "Chanteuse", "Infirmier")); //false
            System.out.println(RequetesBD.verifyConnexion(conn, "1436985621", "FACILE", "Infirmier")); //false
            //Secretaire medicale
            System.out.println(RequetesBD.verifyConnexion(conn, "9993331234", "polyjeu", "Secretaire Medicale")); //true
            System.out.println(RequetesBD.verifyConnexion(conn, "9993331234", "test", "Secretaire Medicale")); //false
            System.out.println(RequetesBD.verifyConnexion(conn, "1045236987", "chanteuse", "Secretaire Medicale")); //false
            //Secretaire administrative
            System.out.println(RequetesBD.verifyConnexion(conn, "1451251451", "couscous", "Secretaire Administrative")); //true
            System.out.println(RequetesBD.verifyConnexion(conn, "1451251450", "couscous", "Secretaire Administrative")); //false
            System.out.println(RequetesBD.verifyConnexion(conn, "9993331234", "polyjeu", "Secretaire Administrative")); //false*/
       
            
            //Test userPH(id) -> VALIDE
            /*System.out.println(RequetesBD.userPH(conn, "8888888888").getClass().getName()); //PH
            System.out.println(RequetesBD.userPH(conn, "8888888888").toString());*/

            //Test userInf(id) -> VALIDE
            /*System.out.println(RequetesBD.userInf(conn, "5000050005").getClass().getName()); //infirmier
            System.out.println(RequetesBD.userInf(conn, "5000050005").toString());*/
            
            //Test userSM(id) -> VALIDE
            /*System.out.println(RequetesBD.userSM(conn, "9992221234").getClass().getName()); //secretaire medicale
            System.out.println(RequetesBD.userSM(conn, "9992221234").toString());*/
            
            //Test userSA(id) -> VALIDE
            /*System.out.println(RequetesBD.userSA(conn, "1451261452").getClass().getName()); //secretaire administrative
            System.out.println(RequetesBD.userSA(conn, "1451261452").toString());*/
            
            
            //Test creerNouveauDPI(infos DPI) -> 
            //RequetesBD.creerNouveauDPI(conn, "1926354276", "Pouet", "Goku", d, "autre", "0798142609", "14 avenue des trousses", "0987123516");
            //RequetesBD.creerNouveauDPI(conn, "1800511699", "Ronflex", "Pokemon", d, "autre", "0765342609", "3 avenue des Flutes", "0655445544");
        
            
            //Tests conversions dates -> VALIDE
            /*java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DPI WHERE IPP = '4444333322'");
            if(rs.next()){
                System.out.println(rs.getDate("date_de_naissance"));
                System.out.println(rs.getDate("date_de_naissance").getClass());
                System.out.println(RequetesBD.convertDateSQLenJava(rs.getDate("date_de_naissance")));
                System.out.println(RequetesBD.convertDateSQLenJava(rs.getDate("date_de_naissance")).getClass());
            }
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM RendezVous WHERE IPP = '5555888800'");
            if(rs2.next()){
                System.out.println(rs2.getTimestamp("dateHeure_RDV"));
                System.out.println(rs2.getTimestamp("dateHeure_RDV").getClass());
                System.out.println(RequetesBD.convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_RDV")));
                DateHeure dh = RequetesBD.convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_RDV"));
                System.out.println(dh.StringDateHeure());
                System.out.println(dh.getClass());
            }*/
            
            
            // Test getListeMT() -> VALIDE
            /*System.out.println(RequetesBD.getListeMT(conn));
            List<MedecinTraitant> lmt = RequetesBD.getListeMT(conn);
            for (int i = 0; i < lmt.size(); i++){
                System.out.println("\n Médecin traitant " + i + " :\n");
                System.out.println(lmt.get(i).toString2());
            }*/
            
            
            // Test getVectMT() -> VALIDE
            /*Vector lmt = RequetesBD.getVectMT(conn);
            System.out.println(lmt);*/
            
            
            // Test getListeMTNom(nom) -> VALIDE
            /*System.out.println(RequetesBD.getListeMTNom(conn, "Docteur"));
            List<MedecinTraitant> lmt = RequetesBD.getListeMTNom(conn, "Docteur");
            for (int i = 0; i < lmt.size(); i++){
                System.out.println("\n Médecin traitant " + i + " :\n");
                System.out.println(lmt.get(i).toString2());
            }*/
            
            
            // Test getVectMTNom() -> VALIDE
            /*Vector lmt = RequetesBD.getVectMTNom(conn, "Osborn");
            System.out.println(lmt);*/
            
            
            //Test listeRendezVous(ipp) -> VALIDE
            /*System.out.println("Identifiant du patient : 5555888800");
            System.out.println(RequetesBD.listeRendezVous(conn, "5555888800"));*/
            
            
            //Test listeExamens(ipp) -> VALIDE
            /*System.out.println("Identifiant du patient : 4445556660");
            System.out.println(RequetesBD.listeExamens(conn, "4445556660"));*/
            
            
            //Test listeLettreSortie(ipp) -> VALIDE
            /*System.out.println("Identifiant du patient : 1502503500");
            System.out.println(RequetesBD.listeLettreSortie(conn, "1502503500"));*/
            
            
            //Test IPPexistant(ipp) -> VALIDE
            //System.out.println(RequetesBD.IPPexistant(conn, "4500055544")); //true
            //System.out.println(RequetesBD.IPPexistant(conn, "1111111111")); //false
            
            
            //Test getListeTousDPI() -> VALIDE
            /*System.out.println(RequetesBD.getListeTousDPI(conn));
            List<DPI> ldpi = RequetesBD.getListeTousDPI(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            //Test getListeDPIFerme() -> VALIDE
            /*System.out.println(RequetesBD.getListeDPIFerme(conn));
            List<DPI> ldpi = RequetesBD.getListeDPIFerme(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            //Test listeFichesDeSoins(ipp) ->
            /*System.out.println(RequetesBD.listeFichesDeSoins(conn, "5555888800"));
            List<FicheDeSoins> listeFiches = RequetesBD.listeFichesDeSoins(conn, "5555888800");
            for (int i = 0; i < listeFiches.size(); i++){
                listeFiches.get(i).toString();
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
