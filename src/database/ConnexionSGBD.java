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
import static database.RequetesBDConversion.convertDateJavaEnTimestampJavaMax;
import static database.RequetesBDConversion.convertDateJavaEnTimestampJavaMin;
import static database.RequetesBDDPI.creerLocalisationSA;
import static database.RequetesBDDPI.fermerDPI;
import static database.RequetesBDDPI.getIPPPatient;
import static database.RequetesBDDPI.modifierLocalisationSM;
import static database.RequetesBDDPI.getListeDPI;
import static database.RequetesBDDPI.getListeDPIEntrant;
import static database.RequetesBDDPI.getListeDPIEntrantNom;
import static database.RequetesBDDPI.getListeDPIEntrantService;
import static database.RequetesBDDPI.getListeDPIEntrantServiceNom;
import static database.RequetesBDDPI.getListeDPIFerme;
import static database.RequetesBDDPI.getListeDPIFermeNom;
import static database.RequetesBDDPI.getListeDPIService;
import static database.RequetesBDDPI.getListeTousDPI;
import static database.RequetesBDDPI.getVectorDPI;
import static database.RequetesBDDPI.getVectorDPIService;
import static database.RequetesBDDPI.listeRendezVous;
import static database.RequetesBDProfessionnels.getPH;
import static database.RequetesBDUrgences.creerDPITemporaire;
import static database.RequetesBDUrgences.getListeDPITemporaires;
import static database.RequetesBDUrgences.getVectorDPITemporaires;
import static java.lang.String.valueOf;
import java.time.LocalDate;

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
            Date date6 = new Date(1999, 8, 26);
            Date date2 = new Date(2021, 12, 14);
            Date date3 = new Date(2021, 12, 13);
            Date date4 = new Date(2022, 3, 14);
            Date date5 = new Date(2022, 4, 2);
            DateHeure dh = new DateHeure(2020, 12, 6, 11, 30);
            
            MedecinTraitant m = new MedecinTraitant("bruce.batman@yahoo.com", "Batman", "Bruce", "0696471245");
            MedecinTraitant m1 = new MedecinTraitant("doctor.octopus@gmail.com", "Docteur", "Octopus", "0696874523");
            MedecinTraitant m2 = new MedecinTraitant("paul.morin@gmail.com", "Morin", "Paul", "0688822222");
            Infirmier inf1 = new Infirmier("totorocec","Totoro","Cecile",Service.Unite_de_soins_intensifs_respiratoires,"dessin");
            PH ph1 = new PH("roussecha","Rousse","Charlotte",Service.Addictologie,"cannabis","0433322233","Specialiste en substances psychoactives");
            PH ph2 = new PH("nadalraf","Nadal","Rafael",Service.Neurologie,"grosbras","0611111111","Neurologue");
            PH ph3 = new PH("marechalou","Marecha","Louise",Service.Endoscopie_digestive,"chaussette","0620240400","chirurgienne");
            
            DM dm1 = new DM();
            Localisation l1 = new Localisation(Service.Unite_de_soins_intensifs_respiratoires,Lit.P,3,Service.Addictologie);
            Localisation l2 = new Localisation(Service.Medecine_nucleaire,Lit.P,40,Service.Medecine_nucleaire);
            DMA dma1 = new DMA(l2);
            
            Acte a1 = new Acte("prise de sang",Type.diagnostic,Code.CS,2, "RAS");
            //a1.setIdActe(666);
            Acte a2 = new Acte("changement pansement",Type.therapeutique,Code.FP,1, "Cicatrisation normale");
            //a2.setIdActe(252);
          
            DateHeure d1 = new DateHeure(2022,3,9,11,00);
            FicheDeSoins fs1 = new FicheDeSoins(d1);
            fs1.setpH(ph1);
            fs1.ajouterActe(a1);
            fs1.ajouterActe(a2);
            
            DateHeure d2 = new DateHeure(2022,2,23,14,00);
            FicheDeSoins fs2 = new FicheDeSoins(d2);
            fs2.setInfirmier(inf1);
            fs2.ajouterActe(a2);
            
            DateHeure dT = new DateHeure(2022,3,23,14,00);
            FicheDeSoins fs3 = new FicheDeSoins(dT);
            PH ph_urgence = new PH("alerterou", "Alerte", "Rouge", Service.Urgences, "vite", "1515151515", "Urgentiste");
            fs3.setpH(ph_urgence);
            fs3.ajouterActe(a1);
            
            Prescription p1 = new Prescription(d2,"a prendre 2 fois par jour pendant 7 jours",null,"Doliprane");
            Prescription p2 = new Prescription(d1,"Faire ultrasons au patient, attention epaule droite",TypeExamen.ultrasons,null);
            
            LettreDeSortie ls = new LettreDeSortie("J'autorise le patient à retourner à son domicile, tout est ok.", d2);
            
            SoinsQuotidien sq = new SoinsQuotidien(39.8, 82, 12.2, "Une température légèrement élevée", d1);
            
            DateHeure d3 = new DateHeure(2022,04,2,11,50);
            DateHeure d4 = new DateHeure(2022,03,14,15,30);
            DateHeure d5 = new DateHeure(2022,03,18,9,00);
            DateHeure d6 = new DateHeure(2022,03,18,10,00);
            DateHeure d7 = new DateHeure(2022,03,18,15,00);
            RendezVous rdv1 = new RendezVous(d3,"Consultation de controle");
            RendezVous rdv2 = new RendezVous(d4,"Bien surveiller la reaction aux substances psychoactives");
            
            Examen exam = new Examen(TypeExamen.imagerie_par_resonance_magnetique, "tout va bien, rien a signaler", d2);
            
            Date dn1 = new Date(1975,5,25);
            //DPI dpi1 = new DPI("1314532074","Lampe","uv",dn1,Sexe.femme,"Rue chambre, Lit","0635674533",m1,dma1,dm1);
            DPI dpi1 = new DPI("5555888800","Lespagnol","Mikael",dn1,Sexe.homme,"25 rue de la soif, Madrid","0644445555",m2,dma1,dm1);
            
            //ph1.ajouterFicheDeSoins(fs1);
            //fs1.setDPI(dpi1);
            //fs2.setDPI(dpi1);
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
            //dm1.ajouterFicheDeSoins(fs2);
            //dma1.ajouterFicheDeSoins(fs2);
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
            //System.out.println(getListeDPI(conn));
            /*List<DPI> ldpi = getListeDPI(conn);
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
            
            
            //Test creerNouveauDPI(infos DPI) -> VALIDE
            //1er version de la fonction
            //creerNouveauDPI(conn, "1926354276", "Pouet", "Goku", d, "autre", "0798142609", "14 avenue des trousses", "0987123516");
            //creerNouveauDPI(conn, "1800511699", "Ronflex", "Pokemon", d, "autre", "0765342609", "3 avenue des Flutes", "0655445544");
            //2e version de la fonction
            //RequetesBDDPI.creerNouveauDPI(conn, "9736482923", "Mandalorian", "Grogu", date6, "homme", "0483256789", "33 bis avenue du tonerre", m);
            
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
            System.out.println(getListeTousDPI(conn));
            List<DPI> ldpi = getListeTousDPI(conn);
            for (int i = 0; i < ldpi.size(); i++){
                System.out.println("\n Dossier Patient Informatisé " + i + " :\n");
                System.out.println(ldpi.get(i).toString());
            }
            
            
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
            //RequetesBDDPI.creerFicheDeSoins(conn, fs3);
            //RequetesBDDPI.creerFicheDeSoins(conn, fs2);
            
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
            //RequetesBDDPI.creerRendezVous(conn, rdv1);
            //RequetesBDDPI.creerRendezVous(conn, rdv1);
            //RequetesBDDPI.creerRendezVous(conn, rdv1);
            
            //Test creerExamen(exam) -> VALIDE
            //RequetesBDDPI.creerExamen(conn, exam);
            
            
            //Test getListeRDVparJour(ph, date) -> VALIDE
            //System.out.println(RequetesBDProfessionnels.getListeRDVparJour(conn, ph1, date2)); //date où y'a des rdv
            //System.out.println(RequetesBDProfessionnels.getListeRDVparJour(conn, ph2, date3)); //date où y'a pas de rdv
            //System.out.println(RequetesBDProfessionnels.getListeRDVparJour(conn, ph3, date4)); //aucun rdv
            
            
            //Test modifierDPI(ipp, tel, adresse, tel medecin) -> VALIDE
            //1er version de la fonction
            //RequetesBDDPI.modifierDPI(conn, "1876500989", "0476385426", "12 avenue des Pokemons", m);
            //RequetesBDDPI.modifierDPI(conn, "1876500989", "0476385426", "10 avenue des Pokemons", m);
            //2e version de la fonction
            //RequetesBDDPI.modifierDPI(conn, "9736482920", "0413256790", "3 bis avenue du tonerre", m1);
            
            //Test creerLocalisation(ipp, loc) -> VALIDE
            //Localisation loc = new Localisation(Service.Medecine_nucleaire, Lit.F, 9, Service.Medecine_nucleaire);
            //RequetesBDDPI.creerLocalisation(conn, "9736482920", loc);
            //RequetesBDDPI.creerLocalisation(conn, "1111888811", loc);
            
            //Test fermerDPI(ipp) -> VALIDE
            //System.out.println(getListeDPI(conn));
            //RequetesBDDPI.fermerDPI(conn, "1800511699");
           
            //Test conversion local date en date -> VALIDE
            //System.out.println(RequetesBDConversion.convertLocalDateEnDate(LocalDate.now()));
            
            //Test archive (ipp, date deces) -> VALIDE
            //RequetesBDDPI.archiverDPI(conn, "1111888811", RequetesBDConversion.convertLocalDateEnDate(LocalDate.now()));
            
            
            //Test des conditions de RDV -> VALIDE
            //Date dateRDV = new Date(2022, 3, 18);
            //PH phHouse = new PH("housedoc", "House", "Docteur", Service.valueOf("Medecine_nucleaire"), "tele", "0611199911", "Radiologue");
            //PH phRDV = new PH("alaphilippejul", "Alaphilippe", "Julian", Service.valueOf("Medecine_interne"), "jesaispas", "0655555555", "medecin");
            //System.out.println(listeRendezVous(conn, dateRDV, phHouse));
            //System.out.println(listeRendezVous(conn, dateRDV, Service.Medecine_interne));
            
            
            //Test des conversions min max timestamp -> VALIDE
            /*Timestamp timetime = new Timestamp(2022, 3, 12, 12, 30, 0, 0);
            System.out.println(convertDateJavaEnTimestampJavaMax(timetime));*/
            
            
            //Test des localisations -> VALIDE
            //creerLocalisationSA(conn, "9736482920", Service.Anesthesie);
            //creerLocalisationSA(conn, "2222000011", Service.Chirurgie_vasculaire);
            //fermerDPI(conn, "9736482920");
            //fermerDPI(conn, "2222000011");
            //modifierLocalisationSM(conn, "9736482920", 3, Lit.F, Service.AMP);
            
            //Test getListeDPIEntrant() -> VALIDE
            //System.out.println(getListeDPIEntrant(conn));
            
            //Test getListeDPIEntrantService(service) -> VALIDE
            //System.out.println(getListeDPIEntrantService(conn, Service.Anesthesie));
            
            //Test getListeDPIEntrantNom(nom) -> VALIDE
            //System.out.println(getListeDPIEntrantNom(conn, "ecl"));
            
            //Test getListeDPIEntrantServiceNom(service, nom) -> VALIDE
            //System.out.println(getListeDPIEntrantServiceNom(conn, Service.Anesthesie, "ecl"));
            
            
            //Test nb de DPI temporaires -> VALIDE
            //System.out.println(RequetesBDUrgences.nbDPITemporaire(conn));
            
            
            //Test ajout de DPI temporaire -> VALIDE
            //Date dn = new Date(1997,3,20);
            //PH ph_urgence = new PH("alerterou", "Alerte", "Rouge", Service.Urgences, "vite", "1515151515", "Urgentiste");
            //DPITemporaire dpit = new DPITemporaire("9977554466", "Palmito", "Juan", dn, ph_urgence);//DPI temporaire de quelqu'un existant dans la table DPI temporaire
            //creerDPITemporaire(conn, dpit);
            
            //Date dn2 = new Date(1999,8,26);
            //DPITemporaire dpit2 = new DPITemporaire("9999555566", "Arceus", "Goinfrex", dn2, ph_urgence);//DPI temporaire de quelqu'un déjà aux urgences
            //creerDPITemporaire(conn, dpit2);*/
            
            /*Date dn3 = new Date(1995,6,27);
            DPITemporaire dpit3 = new DPITemporaire("9999555599", "Plante", "Bulbi", dn3, ph_urgence);
            creerDPITemporaire(conn, dpit3);*/
            
            
            //Test getListeDPITemporaires() et getVectorDPITemporaires() -> VALIDE
            //System.out.println(getListeDPITemporaires(conn));
            //System.out.println(getVectorDPITemporaires(conn));
            
            
            //Test getPH(id) -> VALIDE
            //System.out.println(getPH(conn, "alerterou"));
            
            
            //Test getIPPPatient(nom, prenom, dateNaissance) -> VALIDE
            //System.out.println(getIPPPatient(conn, "ronflex", "perle", d));
            
            
            //Test getListeDPIFermeNom(nom) -> VALIDE
            //System.out.println(getListeDPIFermeNom(conn, "man"));
            
            
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
