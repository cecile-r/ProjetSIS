package database;

import java.beans.Statement;
import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import nf.*;
import database.*;

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
            //afficherNomPrenom(conn, "5555888800");
            //afficherDateNaissanceDPI(conn,"5555888800");
            
          
            //TESTS DES REQUETES : définition des paramètres
            Service service = Service.valueOf("Anesthesie");
            String serviceS = "Anesthesie";
            Date d = new Date(2000, 12, 20);
            Date date2 = new Date(2021, 12, 14);
            Date date3 = new Date(2021, 12, 13);
            DateHeure dh = new DateHeure(2020, 12, 6, 11, 30);
            
            MedecinTraitant m = new MedecinTraitant("bruce.batman@yahoo.com", "Batman", "Bruce", "0696471245");
            MedecinTraitant m1 = new MedecinTraitant("doctor.octopus@gmail.com", "Docteur", "Octopus", "0696874523");
            Infirmier inf1 = new Infirmier("totorocec","Totoro","Cecile",Service.Unite_de_soins_intensifs_respiratoires,"dessin");
            PH ph1 = new PH("roussecha","Rousse","Charlotte",Service.Addictologie,"cannabis","0433322233","Specialiste en substances psychoactives");
            PH ph2 = new PH("nadalraf","Nadal","Rafael",Service.Neurologie,"grosbras","0611111111","Neurologue");
            
            DM dm1 = new DM();
            Localisation l1 = new Localisation(Service.Unite_de_soins_intensifs_respiratoires,Lit.P,3,Service.Addictologie);
            DMA dma1 = new DMA(l1);
            
            Acte a1 = new Acte("prise de sang",Type.diagnostic,Code.CS,2, "RAS");
            //a1.setIdActe(666);
            Acte a2 = new Acte("changement pansement",Type.therapeutique,Code.FP,1, "Cicatrisation normale");
            //a2.setIdActe(252);
          
            DateHeure d1 = new DateHeure(2022,02,10,10,00);
            FicheDeSoins fs1 = new FicheDeSoins(d1);
            //fs1.setpH(ph1);
            //fs1.ajouterActe(a1);
            //fs1.ajouterActe(a2);
            
            DateHeure d2 = new DateHeure(2021,06,27,17,40);
            FicheDeSoins fs2 = new FicheDeSoins(d1);
            //fs2.setInfirmier(inf1);
            //fs2.ajouterActe(a2);
            
            Prescription p1 = new Prescription(d2,"a prendre 2 fois par jour pendant 7 jours",null,"Doliprane");
            Prescription p2 = new Prescription(d1,"Faire ultrasons au patient, attention epaule droite",TypeExamen.ultrasons,null);
            
            LettreDeSortie ls = new LettreDeSortie("J'autorise le patient à retourner à son domicile, tout est ok.", d2);
            
            SoinsQuotidien sq = new SoinsQuotidien(39.8, 82, 12.2, "Une température légèrement élevée", d1);
            
            DateHeure d3 = new DateHeure(2022,04,2,11,50);
            DateHeure d4 = new DateHeure(2022,03,14,15,30);
            RendezVous rdv1 = new RendezVous(d3,"Consultation de controle");
            RendezVous rdv2 = new RendezVous(d4,"Bien surveiller la reaction aux substances psychoactives");
            
            Examen exam = new Examen(TypeExamen.imagerie_par_resonance_magnetique, "tout va bien, rien a signaler", d2);
            
            Date dn1 = new Date(1977,07,30);
            DPI dpi1 = new DPI("1314532074","Lampe","uv",dn1,Sexe.femme,"Rue chambre, Lit","0635674533",m1,dma1,dm1);
            
            //ph1.ajouterFicheDeSoins(fs1);
            //fs1.setDPI(dpi1);
            //p1.setDPI(dpi1);
            //p2.setDPI(dpi1);
            //p1.setpH(ph1);
            //p2.setpH(ph1);
            //ls.setDPI(dpi1);
            //ls.setPh(ph1);
            //sq.setDPI(dpi1);
            //sq.setInfirmier(inf1);
            //rdv1.setDPI(dpi1);
            //rdv1.setpH(ph1);
            //rdv2.setDPI(dpi1);
            //rdv2.setpH(ph1);
            //exam.setDPI(dpi1);
            //exam.setPh(ph1);
            
            //dm1.ajouterFicheDeSoins(fs1);
            //dma1.ajouterFicheDeSoins(fs1);
            //dm1.ajouterPrescription(p1);
            //dm1.ajouterPrescription(p2);
            //dm1.ajouterLettreDeSortie(ls);
            //dma1.ajouterLettreDeSortie(ls);
            //dm1.ajouterSoinsQuotidien(sq);
            //dma1.ajouterRendezVous(rdv1);
            //dma1.ajouterRendezVous(rdv2);
            //dma1.ajouterExamen(exam);
            //dm1.ajouterExamen(exam);
            
            // Test getListePH() -> VALIDE
            /*System.out.println(getListePH(conn));
            List<PH> lph = getListePH(conn);
            for (int i = 0; i < lph.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lph.get(i).toString());
                System.out.println(lph.get(i).getIdPH());
                System.out.println(lph.get(i).getMdp());
                System.out.println(lph.get(i).getTelephone());
            }*/
            
            
            // Test getVectPH() -> VALIDE
            /*Vector lph = getVectPH(conn);
            System.out.println(lph);*/
            
            
            // Test getListePH(nom) -> VALIDE
            /*System.out.println(getListePH(conn, "Wawrinka"));
            List<PH> lphn = getListePH(conn, "Wawrinka");
            for (int i = 0; i < lphn.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lphn.get(i).toString());
                System.out.println(lphn.get(i).getIdPH());
                System.out.println(lphn.get(i).getMdp());
                System.out.println(lphn.get(i).getTelephone());
            }*/
            
            
            // Test getVectPHNom(nom) -> VALIDE
            /*Vector lph = getVectPHNom(conn, "Wawrinka");
            System.out.println(lph);*/
            
            
            // Test getListePHService(service) -> VALIDE
            /*System.out.println(getListePHService(conn, serviceS));
            List<PH> lph = getListePHService(conn, serviceS);
            for (int i = 0; i < lph.size(); i++){
                System.out.println("\n Praticien hospitalier " + i + " :\n");
                System.out.println(lph.get(i).toString());
                System.out.println(lph.get(i).getIdPH());
                System.out.println(lph.get(i).getMdp());
                System.out.println(lph.get(i).getTelephone());
            }*/
            
            
            // Test getVectPHService(service) -> VALIDE
            /*Vector lph = getVectPHService(conn, serviceS);
            System.out.println(lph);*/
            
            
            //Test getListeDPI() -> VALIDE
            /*System.out.println(getListeDPI(conn));
            List<DPI> ldpi = getListeDPI(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPI() -> VALIDE
            /*Vector ldpi = getVectorDPI(conn);
            System.out.println(ldpi);*/
            
            
            //Test getListeDPI(nom) -> VALIDE
            /*System.out.println(getListeDPI(conn, "Garcin"));
            List<DPI> ldpi = getListeDPI(conn, "Garcin");
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPI(nom) -> VALIDE
            /*Vector ldpi = getVectorDPI(conn, "Garcin");
            System.out.println(ldpi);*/
            
            
            //Test getListeDPI(nom, service) -> VALIDE
            /*List<DPI> ldpi = getListeDPI(conn, "Pilard", "Chirurgie_digestive");
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPI(nom, service) -> VALIDE
            /*Vector ldpi = getVectorDPI(conn, "Pilard", "Chirurgie_digestive");
            System.out.println(ldpi);*/
            
            
            //Test getListeDPIService(service) -> VALIDE
            /*System.out.println(getListeDPIService(conn, "Medecine_nucleaire"));
            List<DPI> ldpi = getListeDPIService(conn, "Medecine_nucleaire");
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPIService(service) -> VALIDE
            /*Vector ldpi = getVectorDPIService(conn, "Addictologie");
            System.out.println(ldpi);*/
            
            
            //Test verifyConnexion(id, mdp, statut) -> VALIDE
            /*//Medecins
            System.out.println(verifyConnexion(conn, "1436985621", "facile", "Medecin")); //true
            System.out.println(verifyConnexion(conn, "1436985620", "facile", "Medecin")); //false
            System.out.println(verifyConnexion(conn, "1436985621", "FACILE", "Medecin")); //false
            System.out.println(verifyConnexion(conn, "1045236987", "chanteuse", "Medecin")); //false
            //Infirmiers
            System.out.println(verifyConnexion(conn, "1045236987", "chanteuse", "Infirmier")); //true
            System.out.println(verifyConnexion(conn, "1045236987", "Chanteuse", "Infirmier")); //false
            System.out.println(verifyConnexion(conn, "1436985621", "FACILE", "Infirmier")); //false
            //Secretaire medicale
            System.out.println(verifyConnexion(conn, "9993331234", "polyjeu", "Secretaire Medicale")); //true
            System.out.println(verifyConnexion(conn, "9993331234", "test", "Secretaire Medicale")); //false
            System.out.println(verifyConnexion(conn, "1045236987", "chanteuse", "Secretaire Medicale")); //false
            //Secretaire administrative
            System.out.println(verifyConnexion(conn, "1451251451", "couscous", "Secretaire Administrative")); //true
            System.out.println(verifyConnexion(conn, "1451251450", "couscous", "Secretaire Administrative")); //false
            System.out.println(verifyConnexion(conn, "9993331234", "polyjeu", "Secretaire Administrative")); //false*/
       
            
            //Test userPH(id) -> VALIDE
            /*System.out.println(userPH(conn, "8888888888").getClass().getName()); //PH
            System.out.println(userPH(conn, "8888888888").toString());*/

            //Test userInf(id) -> VALIDE
            /*System.out.println(userInf(conn, "5000050005").getClass().getName()); //infirmier
            System.out.println(userInf(conn, "5000050005").toString());*/
            
            //Test userSM(id) -> VALIDE
            /*System.out.println(userSM(conn, "9992221234").getClass().getName()); //secretaire medicale
            System.out.println(userSM(conn, "9992221234").toString());*/
            
            //Test userSA(id) -> VALIDE
            /*System.out.println(userSA(conn, "1451261452").getClass().getName()); //secretaire administrative
            System.out.println(userSA(conn, "1451261452").toString());*/
            
            
            //Test creerNouveauDPI(infos DPI) -> 
            //creerNouveauDPI(conn, "1926354276", "Pouet", "Goku", d, "autre", "0798142609", "14 avenue des trousses", "0987123516");
            //creerNouveauDPI(conn, "1800511699", "Ronflex", "Pokemon", d, "autre", "0765342609", "3 avenue des Flutes", "0655445544");
        
            
            //Tests conversions dates -> VALIDE
            /*java.sql.Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM DPI WHERE IPP = '4444333322'");
            if(rs.next()){
                System.out.println(rs.getDate("date_de_naissance"));
                System.out.println(rs.getDate("date_de_naissance").getClass());
                System.out.println(convertDateSQLenJava(rs.getDate("date_de_naissance")));
                System.out.println(convertDateSQLenJava(rs.getDate("date_de_naissance")).getClass());
            }
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM RendezVous WHERE IPP = '5555888800'");
            if(rs2.next()){
                System.out.println(rs2.getTimestamp("dateHeure_RDV"));
                System.out.println(rs2.getTimestamp("dateHeure_RDV").getClass());
                System.out.println(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_RDV")));
                DateHeure dh = convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_RDV"));
                System.out.println(dh.StringDateHeure());
                System.out.println(dh.getClass());
            }*/
            
            
            // Test getListeMT() -> VALIDE
            /*System.out.println(getListeMT(conn));
            List<MedecinTraitant> lmt = getListeMT(conn);
            for (int i = 0; i < lmt.size(); i++){
                System.out.println("\n Médecin traitant " + i + " :\n");
                System.out.println(lmt.get(i).toString2());
            }*/
            
            
            // Test getVectMT() -> VALIDE
            /*Vector lmt = getVectMT(conn);
            System.out.println(lmt);*/
            
            
            // Test getListeMTNom(nom) -> VALIDE
            /*System.out.println(getListeMTNom(conn, "Docteur"));
            List<MedecinTraitant> lmt = getListeMTNom(conn, "Docteur");
            for (int i = 0; i < lmt.size(); i++){
                System.out.println("\n Médecin traitant " + i + " :\n");
                System.out.println(lmt.get(i).toString2());
            }*/
            
            
            // Test getVectMTNom() -> VALIDE
            /*Vector lmt = getVectMTNom(conn, "Osborn");
            System.out.println(lmt);*/
            
            
            //Test listeRendezVous(ipp) -> VALIDE
            /*System.out.println("Identifiant du patient : 5555888800");
            System.out.println(listeRendezVous(conn, "5555888800"));*/
            
            
            //Test listeExamens(ipp) -> VALIDE
            /*System.out.println("Identifiant du patient : 4445556660");
            System.out.println(listeExamens(conn, "4445556660"));*/
            
            
            //Test listeLettreSortie(ipp) -> VALIDE
            /*System.out.println("Identifiant du patient : 1502503500");
            System.out.println(listeLettreSortie(conn, "1502503500"));*/
            
            
            //Test IPPexistant(ipp) -> VALIDE
            //System.out.println(IPPexistant(conn, "4500055544")); //true
            //System.out.println(IPPexistant(conn, "1111111111")); //false
            
            
            //Test getListeTousDPI() -> VALIDE
            /*System.out.println(getListeTousDPI(conn));
            List<DPI> ldpi = getListeTousDPI(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorTousDPI() -> VALIDE
            /*Vector ldpi = getVectorTousDPI(conn);
            System.out.println(ldpi);*/
            
            
            //Test getListeDPIFerme() -> VALIDE
            /*System.out.println(getListeDPIFerme(conn));
            List<DPI> ldpi = getListeDPIFerme(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }*/
            
            
            // Test getVectorDPIFerme() -> VALIDE
            /*Vector ldpif = getVectorDPIFerme(conn);
            System.out.println(ldpif);*/
            
            
            //Test getStatut(id) -> VALIDE
            /*System.out.println(getStatut(conn, "5005400400")); //PH
            System.out.println(getStatut(conn, "9999000099")); //Infirmier
            System.out.println(getStatut(conn, "9993331234")); //Secrétaire médicale
            System.out.println(getStatut(conn, "1451251451")); //Secrétaire administrative
            System.out.println(getStatut(conn, "9290374828")); //Rien*/
            
            
            //Test convertDateJavaEnSQL(date) -> VALIDE
            /*System.out.println(convertDateJavaEnSQL(d));
            System.out.println(convertDateJavaEnSQL(d).getClass());*/
            
            //Test convertDateHeureJavaEnTimestampSQL(date) -> VALIDE
            /*DateHeure dh3 = new DateHeure(2020, 12, 6, 12, 30);
            System.out.println(RequetesBDConversion.convertDateHeureJavaEnTimestampSQL(dh3));
            System.out.println(RequetesBDConversion.convertDateHeureJavaEnTimestampSQL(dh3).getClass());*/
            
            //Test conversions convertDateJavaEnDateHeureMin(date) et convertDateJavaEnDateHeureMax(date) -> VALIDE
            //System.out.println(RequetesBDConversion.convertDateJavaEnTimestampJavaMin(d).toString());
            //System.out.println(RequetesBDConversion.convertDateJavaEnTimestampJavaMax(d).toString());
            
            //Test toStringTimestamp(timestamp) -> VALIDE
            //Timestamp ts = new Timestamp(2022, 3, 12, 12, 30, 0, 0);
            //System.out.println(RequetesBDConversion.toStringTimestamp(ts));
            
            
            //Test listePrescription(ipp) -> VALIDE
            //System.out.println(RequetesBDDPI.listePrescription(conn, "1502503500"));
            
            //Test listeFichesDeSoins(ipp) -> VALIDE
            //System.out.println(RequetesBDDPI.listeFichesDeSoins(conn, "5555888800"));
            
            //Test listeSoinsQuotidien(ipp) -> VALIDE
            //System.out.println(RequetesBDDPI.listeSoinQuotidien(conn, "5500005500"));
            
            
            //Test getDPI(ipp) -> VALIDE
            /*DPI dpi = RequetesBDDPI.getDPI(conn, "5555888800");
            System.out.println(dpi.toString());
            System.out.println("DM");
            System.out.println(dpi.getdM().toString());
            System.out.println();
            System.out.println("DMA");
            System.out.println(dpi.getdMA().toString());*/
            
            
            //Test creerFicheDeSoins(fiche) -> VALIDE
            //RequetesBDDPI.creerFicheDeSoins(conn, fs1);
            
            //Test creerPrescription(p) -> VALIDE
            //RequetesBDDPI.creerPrescription(conn, p1);
            //RequetesBDDPI.creerPrescription(conn, p2);
            
            //Test creerLettreSortie(ls) -> VALIDE
            //RequetesBDDPI.creerLettreSortie(conn, ls);
            
            //Test creerSoinQuotidien(sq) -> VALIDE
            //RequetesBDDPI.creerSoinQuotidien(conn, sq);
            
            //Test creerRendezVous(rdv) -> VALIDE
            //RequetesBDDPI.creerRendezVous(conn, rdv1);
            //RequetesBDDPI.creerRendezVous(conn, rdv2);
            
            //Test creerExamen(exam) -> VALIDE
            //RequetesBDDPI.creerExamen(conn, exam);
            
            
            //Test getListeRDVparJour(ph, date) -> VALIDE
            //System.out.println(RequetesBDProfessionnels.getListeRDVparJour(conn, ph2, date2)); //date où y'a des rdv
            //System.out.println(RequetesBDProfessionnels.getListeRDVparJour(conn, ph2, date3)); //date où y'a pas de rdv
            
            
            
            //Print information about connection warnings
            SQLWarningsExceptions.printWarnings(conn);
            conn.close();
        } 
        catch (SQLException se) {
            // Print information about SQL exceptions
            SQLWarningsExceptions.printExceptions(se);
            return;

        } 
        catch (Exception e) {
            System.err.println("Exception: " + e.getMessage());
            e.printStackTrace();
            return;
        }

    }
}
