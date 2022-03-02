package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import nf.*;


public class RequetesBD {

    //Test pour la BD
    //VALIDE
    public static void afficherNomPrenom(Connection conn, String IPP) throws SQLException {
        // java.math.BigDecimal
        Statement stmt = conn.createStatement();
        String requete = "SELECT * FROM DPI "
                + "WHERE IPP = '" + IPP + "' ";
        ResultSet rs = stmt.executeQuery(requete);

        if (rs.next()) {
            System.out.println("Nom du patient : " + rs.getString("nom"));
            System.out.println("Prénom du patient : " + rs.getString("prenom"));
        } else {
            System.out.print("Ce patient n'existe pas.");
        }

        rs.close();
        stmt.close();
    }

    //Test pour la date
    //VALIDE
    public static void afficherDateNaissanceDPI(Connection conn, String IPP) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT * FROM DPI "
                + "WHERE IPP = '" + IPP + "' ";
        ResultSet rs = stmt.executeQuery(requete);

        if (rs.next()) {
            System.out.println("Nom du patient : " + rs.getString("nom"));
            System.out.println("Prénom du patient : " + rs.getString("prenom"));
            System.out.println("Date de naissance du patient : " + rs.getDate("date_de_naissance"));
            System.out.println("Date de naissance du patient string : " + rs.getDate("date_de_naissance").toString());
        } else {
            System.out.print("Ce patient n'existe pas.");
        }

        rs.close();
        stmt.close();
    }
    
    //Convertir une date sql en date java correctement
    //VALIDE
    public static Date convertDateSQLenJava(java.sql.Date date){
        Date dateReelle = new Date(date.getYear(), date.getMonth() - 1, date.getDate());
        return dateReelle;
    }
    
    //Convertir une date heure sql en dateheure java correctement
    //VALIDE
    public static DateHeure convertTimestampSQLenJava(java.sql.Timestamp timestamp){
        DateHeure dh = new DateHeure(timestamp.getYear()+1900, timestamp.getMonth()+1, timestamp.getDate(), timestamp.getHours(), timestamp.getMinutes());
        return dh;
    }
    
    //Convertir une date Java en Date SQL correctement
    //VALIDE
    public static java.sql.Date convertDateJavaEnSQL(Date d){
        Date dateReelle = new Date(d.getYear()-1900, d.getMonth()-1, d.getDate());
        java.sql.Date dateSQL = new java.sql.Date(dateReelle.getTime());
        return dateSQL;
    }
    
    //Convertir une date Java en Date SQL correctement
    //VALIDE
    public static java.sql.Timestamp convertDateHeureJavaEnTimestampSQL(DateHeure dh){
        DateHeure dateHeureReelle = new DateHeure(dh.getAnnee()-1900, dh.getMois()-1, dh.getJour(), dh.getHeure(), dh.getMinutes());
        java.sql.Timestamp timestampSQL = new java.sql.Timestamp(dateHeureReelle.getAnnee(),dateHeureReelle.getMois(),dateHeureReelle.getJour(), dateHeureReelle.getHeure(), dateHeureReelle.getMinutes(), 0, 0);
        return timestampSQL;
    }
    
    //Renvoie en string le timestamp
    //
    public static String toStringTimestamp(java.sql.Timestamp ts){
        int year = ts.getYear();
        int month = ts.getMonth();
        int day = ts.getDate();
        int hour = ts.getHours();
        int min = ts.getMinutes();
        int sec = ts.getSeconds();
        String s = day + "-" + month + "-" + year + " " + hour + ":" + min + ":" + sec;
        return s;
    }

    ////////////////////////////////////////////////////////////////////////////
    //Fonctions PH
    
    //Renvoie la liste des PH
    //VALIDE
    public static List<PH> getListePH(Connection conn) throws SQLException {
        List<PH> listePH = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }

    //Renvoie le vecteur des PH
    //VALIDE
    public static Vector getVectPH(Connection conn) throws SQLException {
        Vector vPHTotal = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH");

        while (rs.next()) {
            Vector vParPH = new Vector();
            vParPH.add(rs.getString("nom_PH"));
            vParPH.add(rs.getString("prenom_PH"));
            vParPH.add(rs.getString("service_PH"));
            vPHTotal.add(vParPH);
        }

        rs.close();
        stmt.close();
        return vPHTotal;
    }

    //Renvoie la liste des PH en fonction du nom
    //VALIDE
    public static List<PH> getListePH(Connection conn, String nom) throws SQLException {
        List<PH> listePH = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE nom_PH = '" + nom + "'");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }

    //Renvoie le vecteur des PH en fonction du nom
    //VALIDE
    public static Vector getVectPHNom(Connection conn, String nom) throws SQLException {
        Vector vPHTotal = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE nom_PH = '" + nom + "'");

        while (rs.next()) {
            Vector vParPH = new Vector();
            vParPH.add(rs.getString("nom_PH"));
            vParPH.add(rs.getString("prenom_PH"));
            vParPH.add(rs.getString("service_PH"));
            vPHTotal.add(vParPH);
        }

        rs.close();
        stmt.close();
        return vPHTotal;
    }

    //Renvoie la liste des PH en fonction du service
    //VALIDE
    public static List<PH> getListePHService(Connection conn, String service) throws SQLException {
        List<PH> listePH = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection de tous les PH
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE service_PH = '" + service + "'");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }

    //Renvoie le vecteur des PH en fonction du service
    //VALIDE
    public static Vector getVectPHService(Connection conn, String service) throws SQLException {
        Vector vPHTotal = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection de tous les PH
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE service_PH = '" + service + "'");

        while (rs.next()) {
            Vector vParPH = new Vector();
            vParPH.add(rs.getString("nom_PH"));
            vParPH.add(rs.getString("prenom_PH"));
            vParPH.add(rs.getString("service_PH"));
            vPHTotal.add(vParPH);
        }

        rs.close();
        stmt.close();
        return vPHTotal;
    }

    
    ////////////////////////////////////////////////////////////////////////////
    //Fonctions médecin traitant
    
    //Renvoie la liste des médecins traitants
    //VALIDE
    public static List<MedecinTraitant> getListeMT(Connection conn) throws SQLException {
        List<MedecinTraitant> listeMT = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT nom_medecin_traitant, prenom_medecin_traitant, mail, telephone_medecin_traitant "
                + "FROM Medecin_traitant");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            listeMT.add(m);
        }

        rs.close();
        stmt.close();
        return listeMT;
    }
    
    //Renvoie la liste des médecins traitants en fonction du nom donné
    //VALIDE
    public static List<MedecinTraitant> getListeMTNom(Connection conn, String nom) throws SQLException {
        List<MedecinTraitant> listeMT = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT nom_medecin_traitant, prenom_medecin_traitant, mail, telephone_medecin_traitant "
                + "FROM Medecin_traitant "
                + "WHERE nom_medecin_traitant = '" + nom + "'");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            listeMT.add(m);
        }

        rs.close();
        stmt.close();
        return listeMT;
    }
    
    //Renvoie le vecteur des Médecins traitants
    //VALIDE
    public static Vector getVectMT(Connection conn) throws SQLException {
        Vector vMTtotal = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT nom_medecin_traitant, prenom_medecin_traitant, mail, telephone_medecin_traitant "
                + "FROM Medecin_traitant");

        while (rs.next()) {
            Vector vParMT = new Vector();
            vParMT.add(rs.getString("nom_medecin_traitant"));
            vParMT.add(rs.getString("prenom_medecin_traitant"));
            vParMT.add(rs.getString("mail"));
            vParMT.add(rs.getString("telephone_medecin_traitant"));
            vMTtotal.add(vParMT);
        }

        rs.close();
        stmt.close();
        return vMTtotal;
    }
    
    //Renvoie le vecteur des médecins traitants avec un nom donné
    //VALIDE
    public static Vector getVectMTNom(Connection conn, String nom) throws SQLException {
        Vector vMTtotal = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT nom_medecin_traitant, prenom_medecin_traitant, mail, telephone_medecin_traitant "
                + "FROM Medecin_traitant "
                + "WHERE nom_medecin_traitant = '" + nom + "'");

        while (rs.next()) {
            Vector vParMT = new Vector();
            vParMT.add(rs.getString("nom_medecin_traitant"));
            vParMT.add(rs.getString("prenom_medecin_traitant"));
            vParMT.add(rs.getString("mail"));
            vParMT.add(rs.getString("telephone_medecin_traitant"));
            vMTtotal.add(vParMT);
        }

        rs.close();
        stmt.close();
        return vMTtotal;
    }
    
    
    ////////////////////////////////////////////////////////////////////////////
    //Fonctions DPI
    
    //Renvoie true si l'ipp existe sinon renvoie false
    //VALIDE
    public static boolean IPPexistant(Connection conn, String ipp) throws SQLException{
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT IPP FROM DPI "
                + "WHERE IPP = '" + ipp + "'");
        boolean ippExiste = false;

        if(rs.next()){
            ippExiste = true;
        }
        
        rs.close();
        stmt.close();
        return ippExiste;
    }
    
    //Renvoie la liste des DPI fermés -> patients PAS dans le CHU
    //VALIDE
    public static List<DPI> getListeDPIFerme(Connection conn) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI " +
        "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) " +
        "WHERE IPP NOT IN (SELECT IPP FROM Localisation)");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
            listeDPI.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPI;
    }
    
    //Renvoie le vecteur des DPI ouverts
    //VALIDE
    public static Vector getVectorDPIFerme(Connection conn) throws SQLException {
        Vector vDPI = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI " +
        "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) " +
        "WHERE IPP NOT IN (SELECT IPP FROM Localisation)");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            vParDPI.add(rs.getString("nom_DPI"));
            vParDPI.add(rs.getString("prenom_DPI"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("sexe_DPI"));
            vDPI.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPI;
    }
    
    //Renvoie la liste des DPI ouverts ou fermés -> patients dans le CHU ou non
    //VALIDE
    public static List<DPI> getListeTousDPI(Connection conn) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) ");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
            listeDPI.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPI;
    }
    
    //Renvoie le vecteur des DPI ouverts
    //VALIDE
    public static Vector getVectorTousDPI(Connection conn) throws SQLException {
        Vector vDPI = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) ");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            vParDPI.add(rs.getString("nom_DPI"));
            vParDPI.add(rs.getString("prenom_DPI"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("sexe_DPI"));
            vDPI.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPI;
    }

    //Renvoie la liste des DPI ouverts -> patients dans le CHU
    //VALIDE
    public static List<DPI> getListeDPI(Connection conn) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI " +
        "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) " +
        "WHERE IPP IN (SELECT IPP FROM Localisation)");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
            listeDPIOuvert.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPIOuvert;
    }
    
    //Renvoie le vecteur des DPI ouverts
    //VALIDE
    public static Vector getVectorDPI(Connection conn) throws SQLException {
        Vector vDPIOuvert = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "NATURAL JOIN Localisation");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            vParDPI.add(rs.getString("nom_DPI"));
            vParDPI.add(rs.getString("prenom_DPI"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("sexe_DPI"));
            vDPIOuvert.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPIOuvert;
    }

    //Renvoie la liste des DPI ouverts en fonction du nom
    //VALIDE
    public static List<DPI> getListeDPI(Connection conn, String nom) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI " 
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) " 
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE (nom_DPI = '" + nom + "'))");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
            listeDPIOuvert.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPIOuvert;
    }

    //Renvoie le vecteur des DPI ouverts en fonction du nom
    //VALIDE
    public static Vector getVectorDPI(Connection conn, String nom) throws SQLException {
        Vector vDPIOuvert = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "NATURAL JOIN Localisation "
                + "WHERE (nom_DPI = '" + nom + "')");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            vParDPI.add(rs.getString("nom_DPI"));
            vParDPI.add(rs.getString("prenom_DPI"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("sexe_DPI"));
            vDPIOuvert.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPIOuvert;
    }

    //Renvoie la liste des DPI ouverts en fonction du nom et du service
    //VALIDE
    public static List<DPI> getListeDPIService(Connection conn, String service) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI " 
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) " 
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE service_responsable = '" + service + "')");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
            listeDPIOuvert.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPIOuvert;
    }

    //Renvoie le vecteur des DPI ouverts en fonction du nom et du service
    //VALIDE
    public static Vector getVectorDPIService(Connection conn, String service) throws SQLException {
        Vector vDPIOuvert = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "NATURAL JOIN Localisation "
                + "WHERE service_responsable = '" + service + "'");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            vParDPI.add(rs.getString("nom_DPI"));
            vParDPI.add(rs.getString("prenom_DPI"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("sexe_DPI"));
            vDPIOuvert.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPIOuvert;
    }

    //Renvoie la liste des DPI ouverts en fonction du nom et du service
    //VALIDE
    public static List<DPI> getListeDPI(Connection conn, String nom, String service) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI " 
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE service_responsable = '" + service + "') AND (nom_DPI = '" + nom + "')");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
            listeDPIOuvert.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPIOuvert;
    }

    //Renvoie le vecteur des DPI ouverts en fonction du nom et du service
    //VALIDE
    public static Vector getVectorDPI(Connection conn, String nom, String service) throws SQLException {
        Vector vDPIOuvert = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "NATURAL JOIN Localisation "
                + "WHERE (service_responsable = '" + service + "') AND (nom_DPI = '" + nom + "')");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            vParDPI.add(rs.getString("nom_DPI"));
            vParDPI.add(rs.getString("prenom_DPI"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("sexe_DPI"));
            vDPIOuvert.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPIOuvert;
    }

    //Creer un patient et l'ajouter dans la base de données
    //VALIDE
    public static void creerNouveauDPI(Connection conn, String id, String nom_DPI, String prenom_DPI, Date date_de_naissance, String sexe_DPI, String telephone_DPI, String adresse_DPI, String telephone_medecin_traitant) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "WHERE IPP = '" + id + "'");

        if (rs.next()) {
            System.out.println("Ce patient est déjà enregistré dans la base de données.");
        } else {
            Date dateNaissanceReelle = new Date(date_de_naissance.getYear(), date_de_naissance.getMonth() - 1, date_de_naissance.getDate());
            Statement stmt2 = conn.createStatement();
            stmt2.executeUpdate("INSERT INTO DPI(IPP, nom_DPI, prenom_DPI, date_de_naissance, sexe_DPI, adresse_DPI, telephone_DPI, telephone_medecin_traitant) "
                    + "VALUES ('" + id + "', '" + nom_DPI + "', '" + prenom_DPI + "', TO_DATE('" + new java.sql.Date(dateNaissanceReelle.getTime()).toString() + "'"
                            + ",'yyyy-MM-dd')" + ", '" + sexe_DPI + "', '" + adresse_DPI + "', '" + telephone_DPI + "', '" + telephone_medecin_traitant + "')");
            System.out.println("Ce patient a été inséré dans la base de données.");
            stmt2.close();
        }
        rs.close();
        stmt.close();
    }

    //Renvoie la liste des rendez-vous pour un patient donné
    //VALIDE
    public static List<RendezVous> listeRendezVous(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM RendezVous "
                + "WHERE IPP = '" + ipp + "'");//Tous les RDV du patient
        List<RendezVous> listeRDV = new ArrayList();

        //Création de la liste de RDV
        while (rs.next()) { //Tant qu'il y a des RDV pour le patient
            RendezVous rdv = new RendezVous(convertTimestampSQLenJava(rs.getTimestamp("dateHeure_RDV")), rs.getString("remarque"));
            //Création du PH
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + rs.getString("idPH") + "'");//Les infos du PH associé au RDV
            if(rs2.next()){
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                rdv.setpH(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if(rs3.next()){
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                rdv.setDPI(dpi);
            }
            rs2.close();
            rs3.close();
            stmt2.close();
            stmt3.close();
            listeRDV.add(rdv);
        }
        rs.close();
        stmt.close();
        return listeRDV;
    }

    //Renvoie la liste des examens pour un patient donné
    //VALIDE
    public static List<Examen> listeExamens(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Examen "
                + "WHERE IPP = '" + ipp + "'");//Tous les examens du patient
        List<Examen> listeExams = new ArrayList();

        //Création de la liste d'examens
        while (rs.next()) { //Tant qu'il y a des examens pour le patient
            Examen exam = new Examen(TypeExamen.valueOf(rs.getString("nom_Examen")), convertTimestampSQLenJava(rs.getTimestamp("dateHeure_Examen")));
            Statement stmt2 = conn.createStatement();
            //Création du PH
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + rs.getString("idPH") + "'");//Selection du PH qui réalise l'examen
            if(rs2.next()){
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                exam.setPh(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if(rs3.next()){
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                exam.setDPI(dpi);
            }
            //Résultat ou pas
            if(rs.getString("resultat") != null){
                exam.setResultats(rs.getString("resultat"));
            }
            rs2.close();
            rs3.close();
            stmt2.close();
            stmt3.close();
            listeExams.add(exam);
        }

        rs.close();
        stmt.close();
        return listeExams;
    }

    //Renvoie la liste des lettres de sortie pour un patient donné
    //VALIDE
    public static List<LettreDeSortie> listeLettreSortie(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LettreDeSortie "
                + "WHERE IPP = '" + ipp + "'");//Toutes les lettre des sorties du patient
        List<LettreDeSortie> listeLettres = new ArrayList();

        //Création de la liste de lettres de sortie
        while (rs.next()) {
            LettreDeSortie ls = new LettreDeSortie(rs.getString("texte"), convertTimestampSQLenJava(rs.getTimestamp("dateHeure_LettreDeSortie")));
            //Création du PH
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + rs.getString("idPH") + "'");//Avoir le PH associé à la lettre de sortie
            if(rs2.next()){
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                ls.setPh(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if(rs3.next()){
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                ls.setDPI(dpi);
            }
            rs2.close();
            rs3.close();
            stmt2.close();
            stmt3.close();
            listeLettres.add(ls);
        }
        
        rs.close();
        stmt.close();
        return listeLettres;
    }

    //Renvoie la liste des fiches de soins pour un patient donné
    //
    public static List<FicheDeSoins> listeFichesDeSoins(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "WHERE IPP = '" + ipp + "'");
        ResultSet rs2 = stmt2.executeQuery("SELECT DISTINCT IPP, dateHeure_FichesDeSoins FROM FichesDeSoins "
                + "WHERE IPP = '" + ipp + "'");//Toutes les fiches de soins du patient, fiches n'apparaissent pas en double
        List<FicheDeSoins> listeFiches = new ArrayList();

        if (rs.next()) { //Si y'a un DPI qui correspond à l'identifiant
            //Création de la liste de fiches de soins
            //System.out.println("ok if");
            while (rs2.next()) { //Tant que y'a des fiches de soins pour ce patient (sans doublons)
                //System.out.println("ok while");
                FicheDeSoins fiche = new FicheDeSoins(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                //System.out.println("bow?");marche jusque la
                Statement stmt3 = conn.createStatement();
                System.out.println("avant rs3");
                System.out.println(rs2.getTimestamp("dateHeure_FichesDeSoins").toGMTString());
                System.out.println(rs2.getTimestamp("dateHeure_FichesDeSoins").toString());
                System.out.println(toStringTimestamp(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                //System.out.println(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                //System.out.println(convertDateJavaenSQL('" + rs2.getTimestamp("dateHeure_FichesDeSoins") + "', 'DD-MON-YYYY HH:MI" + ":00 AM');
                ResultSet rs3 = stmt3.executeQuery("SELECT * FROM FichesDeSoins " //Sélection de la fiche de soins et tous ses actes
                        + "JOIN Acte USING (idActe)"
                        + "WHERE (IPP = '" + ipp + "') AND (dateHeure_FichesDeSoins = '" + rs2.getTimestamp("dateHeure_FichesDeSoins").toString() + "')");//pb ici a régler
                //convertDateHeureJavaEnTimestampSQL(fiche.getDateHeure())
                System.out.println("avant while 2");
                while (rs3.next()) { //Tant qu'il y a des actes pour cette fiche
                    System.out.println("ok while 2");
                    Code c = Code.valueOf(rs3.getString("libelle") + "," + (double) rs3.getFloat("cout"));
                    Acte acte = new Acte(rs3.getString("nom_Acte"), Type.valueOf(rs3.getString("type")), c, (int) rs3.getFloat("coeff"), rs3.getString("observation"));
                    //coeff int ou float??? est ce que ca va poser pb?
                    fiche.ajouterActe(acte);
                }
                //Création du professionnel de santé qui réalise la fiche de soins
                if (rs3.getString("idPH") != null) { //Si l'éditeur/celui qui réalise les actes est un PH
                    Statement stmt4 = conn.createStatement();
                    ResultSet rs4 = stmt4.executeQuery("SELECT * FROM PH "
                            + "WHERE idPH = '" + rs2.getString("idPH") + "'");//Avoir les infos du PH pour cette fiche de soins
                    System.out.println("ok if 2");
                    if (rs4.next()) {//Si y'a bien un PH qui correspond -> en principe toujours oui mais on sait jamais
                        System.out.println("ok if 3");
                        PH ph = new PH(rs4.getString("idPH"), rs4.getString("nom_PH"), rs4.getString("prenom_PH"), Service.valueOf(rs4.getString("service_PH")), rs4.getString("mdp_PH"), rs4.getString("telephone_PH"), rs4.getString("specialite_PH"));
                        fiche.setpH(ph);
                    }
                    rs4.close();
                    stmt4.close();
                } else if (rs3.getString("idInfirmier") != null) { //L'éditeur/celui qui réalise les actes est un infirmier
                    System.out.println("ok else if");
                    Statement stmt5 = conn.createStatement();
                    ResultSet rs5 = stmt5.executeQuery("SELECT * FROM Infirmier "
                            + "WHERE idInfirmier = '" + rs2.getString("idInfirmier") + "'");//Avoir les infos de l'infirmier pour cette fiche de soins
                    if (rs5.next()) {//Si y'a bien un infirmier qui correspond -> en principe toujours oui mais on sait jamais
                        System.out.println("ok else if 2");
                        Infirmier inf = new Infirmier(rs5.getString("idInfirmier"), rs5.getString("nom_Infirmier"), rs5.getString("prenom_Infirmier"), Service.valueOf(rs5.getString("service_Infirmier")), rs5.getString("mdp_Infirmier"));
                        fiche.setInfirmier(inf);
                    }
                    rs5.close();
                    stmt5.close();
                }
                rs3.close();
                stmt3.close();
                listeFiches.add(fiche);
            }
        }
        rs.close();
        rs2.close();
        stmt.close();
        stmt2.close();
        return listeFiches;
    }

    
   
    ////////////////////////////////////////////////////////////////////////////
    //Fonctions pour connexion
    
    //Renvoie true si le mdp est correct, sinon false
    //VALIDE
    public static boolean verifyConnexion(Connection conn, String id, String mdp, String statut) throws SQLException {
        boolean correct = false;
        Statement stmt = conn.createStatement();
        //Requete ci dessous juste pour initialiser le resultset et pas faire buger la suite du programme, pas utilisée
        ResultSet rs = stmt.executeQuery("SELECT idPH from PH");

        if (statut == "Medecin") {
            rs = stmt.executeQuery("SELECT idPH, mdp_PH FROM PH "
                    + "WHERE (idPH = '" + id + "') AND (mdp_PH = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        } else if (statut == "Secretaire Administrative") {
            rs = stmt.executeQuery("SELECT idSecretaireAd, mdp_SA FROM Secretaire_administrative "
                    + "WHERE (idSecretaireAd = '" + id + "') AND (mdp_SA = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        } else if (statut == "Secretaire Medicale") {
            rs = stmt.executeQuery("SELECT idSecretaireMed, mdp_SM FROM Secretaire_medicale "
                    + "WHERE (idSecretaireMed = '" + id + "') AND (mdp_SM = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        } else if (statut == "Infirmier") {
            rs = stmt.executeQuery("SELECT idInfirmier, mdp_Infirmier FROM Infirmier "
                    + "WHERE (idInfirmier = '" + id + "') AND (mdp_Infirmier = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        }

        rs.close();
        stmt.close();
        return correct;
    }

    //Renvoie le PH dont on donne l'identifiant
    //VALIDE
    public static PH userPH(Connection conn, String id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE idPH = '" + id + "'");

        if (rs.next()) { //Si c'est un PH
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            rs.close();
            stmt.close();
            return ph;

        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    //Renvoie l'infirmier dont on donne l'identifiant
    //VALIDE
    public static Infirmier userInf(Connection conn, String id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Infirmier "
                + "WHERE idInfirmier = '" + id + "'");

        if (rs.next()) { //Si c'est un PH
            Infirmier inf = new Infirmier(rs.getString("idInfirmier"), rs.getString("nom_Infirmier"), rs.getString("prenom_Infirmier"), Service.valueOf(rs.getString("service_Infirmier")), rs.getString("mdp_Infirmier"));
            rs.close();
            stmt.close();
            return inf;

        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    //Renvoie la secretaire medicale dont on donne l'identifiant
    //VALIDE
    public static SecretaireMedicale userSM(Connection conn, String id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Secretaire_medicale "
                + "WHERE idSecretaireMed = '" + id + "'");

        if (rs.next()) { //Si c'est un PH
            SecretaireMedicale sm = new SecretaireMedicale(rs.getString("idSecretaireMed"), rs.getString("nom_SM"), rs.getString("prenom_SM"), Service.valueOf(rs.getString("service_SM")), rs.getString("mdp_SM"));
            rs.close();
            stmt.close();
            return sm;

        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }

    //Renvoie a secretaire administrative dont on donne l'identifiant
    //VALIDE
    public static SecretaireAdministrative userSA(Connection conn, String id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Secretaire_administrative "
                + "WHERE idSecretaireAd = '" + id + "'");

        if (rs.next()) { //Si c'est un PH
            SecretaireAdministrative sa = new SecretaireAdministrative(rs.getString("idSecretaireAd"), rs.getString("nom_SA"), rs.getString("prenom_SA"), rs.getString("mdp_SA"));
            rs.close();
            stmt.close();
            return sa;

        } else {
            rs.close();
            stmt.close();
            return null;
        }
    }
    
    //Renvoie le statut du professionnel de santé
    //VALIDE
    public static String getStatut(Connection conn, String id) throws SQLException{
        Statement stmt1 = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        Statement stmt3 = conn.createStatement();
        Statement stmt4 = conn.createStatement();
        ResultSet rsSA = stmt1.executeQuery("SELECT * FROM Secretaire_administrative "
                + "WHERE idSecretaireAd = '" + id + "'");
        ResultSet rsSM = stmt2.executeQuery("SELECT * FROM Secretaire_medicale "
                + "WHERE idSecretaireMed = '" + id + "'");
        ResultSet rsInf = stmt3.executeQuery("SELECT * FROM Infirmier "
                + "WHERE idInfirmier = '" + id + "'");
        ResultSet rsPH = stmt4.executeQuery("SELECT * FROM PH "
                + "WHERE idPH = '" + id + "'");
        String statut = "";
        
        if(rsSA.next()){
            statut = "SA";
        }
        else if(rsSM.next()){
            statut = "SM";
        }
        else if(rsInf.next()){
            statut = "Inf";
        }
        else if(rsPH.next()){
            statut = "PH";
        }
        
        rsSA.close();
        rsSM.close();
        rsInf.close();
        rsPH.close();
        stmt1.close();
        stmt2.close();
        stmt3.close();
        stmt4.close();
        return statut;
    }
}
