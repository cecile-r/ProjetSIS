/*
Fonctions relatives aux DPI
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nf.Acte;
import nf.Code;
import nf.DPI;
import nf.Examen;
import nf.FicheDeSoins;
import nf.Infirmier;
import nf.LettreDeSortie;
import nf.MedecinTraitant;
import nf.PH;
import nf.RendezVous;
import nf.Service;
import nf.Sexe;
import nf.Type;
import nf.TypeExamen;
import static database.RequetesBDConversion.convertDateHeureJavaEnTimestampSQL;
import static database.RequetesBDConversion.convertDateJavaEnSQL;
import static database.RequetesBDConversion.convertDateJavaEnTimestampJavaMax;
import static database.RequetesBDConversion.convertDateJavaEnTimestampJavaMin;
import static database.RequetesBDConversion.convertDateSQLenJava;
import static database.RequetesBDConversion.convertLocalDateEnDate;
import static database.RequetesBDConversion.convertTimestampSQLenJava;
import static database.RequetesBDConversion.toStringTimestampJAVA;
import static java.lang.String.valueOf;
import java.time.LocalDate;
import nf.DM;
import nf.DMA;
import nf.DateHeure;
import nf.Lit;
import nf.Localisation;
import nf.Prescription;
import nf.SoinsQuotidien;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDDPI {

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp pour l'identifiant
     * @return boolean pour vérifier si l'ipp existe ou non
     * @throws java.sql.SQLException
     */
    public static boolean IPPexistant(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT IPP FROM DPI "
                + "WHERE IPP = '" + ipp + "'");
        boolean ippExiste = false;

        if (rs.next()) {
            ippExiste = true;
        }

        rs.close();
        stmt.close();
        return ippExiste;
    }
    
    /**
     * @param conn la connection établie pour la base de données
     * @param nom pour le nom du patient
     * @param prenom pour le prenom du patient
     * @param date_naissance pour la date de naissance du patient
     * @return la chaine de caractère de l'ipp
     * @throws java.sql.SQLException
     */
    public static String getIPPPatient(Connection conn, String nom, String prenom, Date date_naissance) throws SQLException{
        String ipp = "";
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT IPP FROM DPI "
                + "WHERE UPPER(nom_DPI) = UPPER(?) AND UPPER(prenom_DPI) = UPPER(?) AND date_de_naissance = ?");
        stmt.setString(1, nom);
        stmt.setString(2, prenom);
        stmt.setDate(3, convertDateJavaEnSQL(date_naissance));
        ResultSet rs = stmt.executeQuery();
        if(rs.next()){
            ipp = rs.getString("IPP");
        }
        stmt.close();
        return ipp;
    }

    ////////////////////////////////////////////////////////////////////////////
    //Récupération d'éléments
    
    /**
     * @param conn la connection établie pour la base de données
     * @return la liste des DPI fermés : patients PAS dans le CHU
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIFerme(Connection conn) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP NOT IN (SELECT IPP FROM Localisation) AND IPP NOT IN (SELECT IPP FROM Archive)");

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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom du patient
     * @return la liste des DPI fermés en fonction du nom
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIFermeNom(Connection conn, String nom) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP NOT IN (SELECT IPP FROM Localisation) AND IPP NOT IN (SELECT IPP FROM Archive) AND UPPER(nom_DPI) LIKE UPPER('" + nom + "%')");

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

    /**
     * @param conn la connection établie pour la base de données
     * @return la liste des DPI ouverts ou fermés : patients dans le CHU ou non
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeTousDPI(Connection conn) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) WHERE IPP NOT IN (SELECT IPP FROM Archive)");

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

    /**
     * @param conn la connection établie pour la base de données
     * @return la liste des DPI ouverts : patients dans le CHU
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPI(Connection conn) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation)");

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

    /**
     * @param conn la connection établie pour la base de données
     * @return la liste des DPI ayant un début de localisation : patient passé seulement au secrétariat administratif
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIEntrant(Connection conn) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE service_geographique IS NULL)");

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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param service le service responsable 
     * @return la liste des DPI ayant un début de localisation selon le service entré : patient passé seulement au secrétariat administratif
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIEntrantService(Connection conn, Service service) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE service_geographique IS NULL AND UPPER(service_responsable) LIKE UPPER('" + service.name() + "%'))");

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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom du patient
     * @return la liste des DPI ayant un début de localisation selon le nom entré : patient passé seulement au secrétariat administratif
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIEntrantNom(Connection conn, String nom) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE service_geographique IS NULL) AND UPPER(nom_DPI) LIKE UPPER('" + nom + "%')");

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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param service le service responsable 
     * @param nom le nom du patient
     * @return la liste des DPI ayant un début de localisation : patient passé seulement au secrétariat administratif
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIEntrantServiceNom(Connection conn, Service service, String nom) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN "
                + "(SELECT IPP FROM Localisation "
                + "WHERE service_geographique IS NULL AND UPPER(service_responsable) LIKE UPPER('" + service.name() + "%')) "
                        + "AND UPPER(nom_DPI) LIKE UPPER('" + nom + "%')");

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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom du patient
     * @return la liste des DPI ouverts en fonction du nom
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPI(Connection conn, String nom) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE (UPPER(nom_DPI) LIKE UPPER('" + nom + "%')))");

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

    /**
     * @param conn la connection établie pour la base de données
     * @param service le service responsable
     * @return la liste des DPI ouverts en fonction du service
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPIService(Connection conn, String service) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE UPPER(service_responsable) LIKE UPPER('" + service + "%'))");

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

    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom du patient
     * @param service le service responsable
     * @return la liste des DPI ouverts en fonction du nom et du service
     * @throws java.sql.SQLException
     */
    public static List<DPI> getListeDPI(Connection conn, String nom, String service) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP IN (SELECT IPP FROM Localisation WHERE UPPER(service_responsable) LIKE UPPER('" + service + "%') AND UPPER(nom_DPI) LIKE UPPER('" + nom + "%'))");

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

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des rendez-vous pour un patient donné
     * @throws java.sql.SQLException
     */
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
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                rdv.setpH(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
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

    /**
     * @param conn la connection établie pour la base de données
     * @param date la date du rendez-vous
     * @param ph l'instance du PH réalisant le rendez-vous
     * @return la liste des rendez-vous pour un PH et une date donnés
     * @throws java.sql.SQLException
     */
    public static List<RendezVous> listeRendezVous(Connection conn, Date date, PH ph) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM RendezVous "
                + "WHERE idPH = '" + ph.getIdPH() + "' AND (dateHeure_RDV BETWEEN ? AND ?)");
        stmt.setTimestamp(1, convertDateJavaEnTimestampJavaMin(date));
        stmt.setTimestamp(2, convertDateJavaEnTimestampJavaMax(date));
        ResultSet rs = stmt.executeQuery();
        List<RendezVous> listeRDV = new ArrayList();

        //Création de la liste de RDV
        while (rs.next()) {
            RendezVous rdv = new RendezVous(convertTimestampSQLenJava(rs.getTimestamp("dateHeure_RDV")), rs.getString("remarque"));
            rdv.setpH(ph);
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + rs.getString("IPP") + "'");//Les infos du DPI du patient qui a le RDV
            if (rs3.next()) {
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                rdv.setDPI(dpi);
            }
            rs3.close();
            stmt3.close();
            listeRDV.add(rdv);
        }
        rs.close();
        stmt.close();
        return listeRDV;
    }
    
    /**
     * @param conn la connection établie pour la base de données
     * @param date la date du rendez-vous
     * @param service le service responsable
     * @return la liste des rendez-vous pour un service et une date donnés
     * @throws java.sql.SQLException
     */
    public static List<RendezVous> listeRendezVous(Connection conn, Date date, Service service) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM RendezVous "
                + "JOIN PH USING(idPH)"
                + "WHERE UPPER(service_PH) LIKE UPPER('" + service.name() + "%') AND (dateHeure_RDV BETWEEN ? AND ?)");
        stmt.setTimestamp(1, convertDateJavaEnTimestampJavaMin(date));
        stmt.setTimestamp(2, convertDateJavaEnTimestampJavaMax(date));
        ResultSet rs = stmt.executeQuery();
        List<RendezVous> listeRDV = new ArrayList();

        //Création de la liste de RDV
        while (rs.next()) { //Tant qu'il y a des RDV pour le patient
            RendezVous rdv = new RendezVous(convertTimestampSQLenJava(rs.getTimestamp("dateHeure_RDV")), rs.getString("remarque"));
            //Création du PH
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + rs.getString("idPH") + "'");//Les infos du PH associé au RDV
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                rdv.setpH(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + rs.getString("IPP") + "'");//Les infos du DPI du patient
            if (rs3.next()) {
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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des examens pour un patient donné
     * @throws java.sql.SQLException
     */
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
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                exam.setPh(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                exam.setDPI(dpi);
            }
            //Résultat ou pas
            if (rs.getString("resultat") != null) {
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

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des lettres de sortie pour un patient donné
     * @throws java.sql.SQLException
     */
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
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                ls.setPh(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
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

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des soins quotidiens pour un patient donné
     * @throws java.sql.SQLException
     */
    public static List<SoinsQuotidien> listeSoinQuotidien(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SoinsQuotidien "
                + "WHERE IPP = '" + ipp + "'");//Tous les soins quotidiens du patient
        List<SoinsQuotidien> listeSQ = new ArrayList();

        while (rs.next()) {
            SoinsQuotidien sq = new SoinsQuotidien((double) rs.getFloat("temperature"), rs.getInt("saturation_o2"), (double) rs.getFloat("tension"), rs.getString("remarque"), convertTimestampSQLenJava(rs.getTimestamp("dateHeure_SoinsQuotidien")));

            //Création de l'infirmier
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM Infirmier "
                    + "WHERE idInfirmier = '" + rs.getString("idInfirmier") + "'");//Avoir les infos de l'infirmier pour cette fiche de soins
            if (rs2.next()) {//Si y'a bien un infirmier qui correspond -> en principe toujours oui mais on sait jamais
                Infirmier inf = new Infirmier(rs2.getString("idInfirmier"), rs2.getString("nom_Infirmier"), rs2.getString("prenom_Infirmier"), Service.valueOf(rs2.getString("service_Infirmier")), rs2.getString("mdp_Infirmier"));
                sq.setInfirmier(inf);
            }
            rs2.close();
            stmt2.close();

            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                sq.setDPI(dpi);
            }
            rs3.close();
            stmt3.close();
            listeSQ.add(sq);
        }

        rs.close();
        stmt.close();
        return listeSQ;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des prescriptions pour un patient donné
     * @throws java.sql.SQLException
     */
    public static List<Prescription> listePrescription(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Prescription "
                + "WHERE IPP = '" + ipp + "'");//Toutes les prescriptions du patient
        List<Prescription> listeP = new ArrayList();

        //Création de la liste de prescriptions
        while (rs.next()) { //Tant qu'il y a des prescriptions pour le patient
            String obs;
            TypeExamen te;
            if (rs.getString("observation") == null) {//Si il n'y a pas d'observation -> null
                obs = null;
                if (rs.getString("examen") == null) {//Si l'examen est null -> c'est une prescription de medicament
                    te = null;
                } else {//Si il y a un examen
                    te = TypeExamen.valueOf(rs.getString("examen"));
                }
            } else {//Si il y a une observation
                obs = rs.getString("observation");
                if (rs.getString("examen") == null) {//Si l'examen est null -> c'est une prescription de medicament
                    te = null;
                } else {//Si il y a un examen
                    te = TypeExamen.valueOf(rs.getString("examen"));
                }
            }
            Prescription p = new Prescription(convertTimestampSQLenJava(rs.getTimestamp("dateHeure_Prescription")), obs, te, rs.getString("medicament"));
            //Création du PH
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + rs.getString("idPH") + "'");//Selection du PH qui a créé la prescription
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                p.setpH(ph);
            }
            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                p.setDPI(dpi);
            }

            rs2.close();
            rs3.close();
            stmt2.close();
            stmt3.close();
            listeP.add(p);
        }

        rs.close();
        stmt.close();
        return listeP;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des fiches de soins pour un patient donné
     * @throws java.sql.SQLException
     */
    public static List<FicheDeSoins> listeFichesDeSoins(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "WHERE IPP = '" + ipp + "'");//Les infos du patient
        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM FichesDeSoins JOIN Acte USING (idActe)"
                + "WHERE IPP = '" + ipp + "'");//Toutes les fiches de soins du patient, fiches n'apparaissent pas en double
        List<FicheDeSoins> listeFiches = new ArrayList();
        List<DateHeure> listeDateHeure = new ArrayList();

        if (rs.next()) { //Si y'a un DPI qui correspond à l'identifiant
            //Création de la liste de fiches de soins avec doublons
            while (rs2.next()) { //Tant que y'a des fiches de soins pour ce patient

                if (!listeDateHeure.contains(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")))) {//Si c'est la 1er fois qu'on voit la fiche
                    listeDateHeure.add(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                    FicheDeSoins fiche = new FicheDeSoins(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                    Code c = Code.valueOf(rs2.getString("libelle"));
                    Acte acte = new Acte(rs2.getString("nom_Acte"), Type.valueOf(rs2.getString("type")), c, (int) rs2.getFloat("coeff"), rs2.getString("observation"));
                    fiche.ajouterActe(acte);

                    //Création du professionnel de santé qui réalise la fiche de soins
                    if (rs2.getString("idPH") != null) { //Si l'éditeur/celui qui réalise les actes est un PH
                        Statement stmt4 = conn.createStatement();
                        ResultSet rs4 = stmt4.executeQuery("SELECT * FROM PH "
                                + "WHERE idPH = '" + rs2.getString("idPH") + "'");//Avoir les infos du PH pour cette fiche de soins
                        if (rs4.next()) {//Si y'a bien un PH qui correspond -> en principe toujours oui mais on sait jamais
                            PH ph = new PH(rs4.getString("idPH"), rs4.getString("nom_PH"), rs4.getString("prenom_PH"), Service.valueOf(rs4.getString("service_PH")), rs4.getString("mdp_PH"), rs4.getString("telephone_PH"), rs4.getString("specialite_PH"));
                            fiche.setpH(ph);
                        }
                        rs4.close();
                        stmt4.close();
                    } else if (rs2.getString("idInfirmier") != null) { //L'éditeur/celui qui réalise les actes est un infirmier
                        Statement stmt5 = conn.createStatement();
                        ResultSet rs5 = stmt5.executeQuery("SELECT * FROM Infirmier "
                                + "WHERE idInfirmier = '" + rs2.getString("idInfirmier") + "'");//Avoir les infos de l'infirmier pour cette fiche de soins
                        if (rs5.next()) {//Si y'a bien un infirmier qui correspond -> en principe toujours oui mais on sait jamais
                            Infirmier inf = new Infirmier(rs5.getString("idInfirmier"), rs5.getString("nom_Infirmier"), rs5.getString("prenom_Infirmier"), Service.valueOf(rs5.getString("service_Infirmier")), rs5.getString("mdp_Infirmier"));
                            fiche.setInfirmier(inf);
                        }
                        rs5.close();
                        stmt5.close();
                    }

                    //Création du DPI
                    Statement stmt3 = conn.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                            + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                            + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
                    if (rs3.next()) {
                        MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                        DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                        fiche.setDPI(dpi);
                    }
                    rs3.close();
                    stmt3.close();
                    listeFiches.add(fiche);
                } else {//Si y'a deja eu cette fiche
                    Code c = Code.valueOf(rs2.getString("libelle"));
                    Acte acte = new Acte(rs2.getString("nom_Acte"), Type.valueOf(rs2.getString("type")), c, (int) rs2.getFloat("coeff"), rs2.getString("observation"));
                    int j = 0;
                    while (j < listeFiches.size() && listeFiches.get(j).getDateHeure().compareTo(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins"))) != 0) {
                        j++;
                    }
                    listeFiches.get(j).ajouterActe(acte);
                }

            }
            rs2.close();
            stmt2.close();
        }
        rs.close();
        stmt.close();

        return listeFiches;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return le DPI associé à l'ipp donnée, ainsi que ses DM et DMA associés
     * @throws java.sql.SQLException
     */
    public static DPI getDPI(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                + "JOIN Localisation USING(IPP) "
                + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient

        if (rs.next()) { //Si y'a un DPI qui correspond à l'identifiant
            //Création des instances pour créer le DPI (pour le moment sans les DM et DMA
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            Date d = new Date(rs.getDate("date_de_naissance").getTime());
            DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);

            //Création du DMA
            Localisation loc;
            if(rs.getString("service_geographique") == null){
                loc = new Localisation(Service.valueOf(rs.getString("service_responsable")), null, 0, null);
            }
            else{
                loc = new Localisation(Service.valueOf(rs.getString("service_responsable")), Lit.valueOf(rs.getString("lit")), rs.getInt("nchambre"), Service.valueOf(rs.getString("service_geographique")));
            }
            DMA dma = new DMA(loc);
            //Remplissage du DMA
            List<FicheDeSoins> listeFiches = listeFichesDeSoins(conn, ipp);
            for (int i = 0; i < listeFiches.size(); i++) { //parcours de la liste de fiches de soins
                dma.ajouterFicheDeSoins(listeFiches.get(i));
            }
            List<RendezVous> listeRDV = listeRendezVous(conn, ipp);
            for (int i = 0; i < listeRDV.size(); i++) { //parcours de la liste de rdv
                dma.ajouterRendezVous(listeRDV.get(i));
            }
            List<Examen> listeExam = listeExamens(conn, ipp);
            for (int i = 0; i < listeExam.size(); i++) { //parcours de la liste des examens
                dma.ajouterExamen(listeExam.get(i));
            }
            List<LettreDeSortie> listeLS = listeLettreSortie(conn, ipp);
            for (int i = 0; i < listeLS.size(); i++) { //parcours de la liste des lettres de sortie
                dma.ajouterLettreDeSortie(listeLS.get(i));
            }

            //Création du DM
            DM dm = new DM();
            //Remplissage du DM
            for (int i = 0; i < listeFiches.size(); i++) { //parcours de la liste de fiches de soins définie au dessus
                dm.ajouterFicheDeSoins(listeFiches.get(i));
            }
            List<Prescription> listeP = listePrescription(conn, ipp);
            for (int i = 0; i < listeP.size(); i++) { //parcours des prescriptions
                dm.ajouterPrescription(listeP.get(i));
            }
            for (int i = 0; i < listeLS.size(); i++) { //parcours de la liste des lettres de sortie définie au dessus
                dm.ajouterLettreDeSortie(listeLS.get(i));
            }
            List<SoinsQuotidien> listeSQ = listeSoinQuotidien(conn, ipp);
            for (int i = 0; i < listeSQ.size(); i++) { //parcours de la liste des lettres de sortie
                dm.ajouterSoinsQuotidien(listeSQ.get(i));
            }
            for (int i = 0; i < listeExam.size(); i++) { //parcours de la liste des examens définie au dessus
                dm.ajouterExamen(listeExam.get(i));
            }

            dpi.setdMA(dma);
            dpi.setdM(dm);
            return dpi;
        }
        return null;
    }

    ////////////////////////////////////////////////////////////////////////////
    //Création d'éléments
    
    /**
     * @param conn la connection établie pour la base de données
     * @param id l'ipp du patient
     * @param nom_DPI le nom du patient
     * @param prenom_DPI le prenom du patient
     * @param date_de_naissance la date de naissance du patient
     * @param sexe_DPI le sexe du patient
     * @param telephone_DPI le numéro de téléphone du patient
     * @param adresse_DPI l'adresse postale du patient
     * @param m le médecin traitant du patient
     * @return le DPI crée, et crée ce nouveau DPI dans la base de données
     * @throws java.sql.SQLException
     */
    public static DPI creerNouveauDPI(Connection conn, String id, String nom_DPI, String prenom_DPI, Date date_de_naissance, String sexe_DPI, String telephone_DPI, String adresse_DPI, MedecinTraitant m) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "WHERE IPP = '" + id + "' AND IPP IN (SELECT IPP FROM Archive)");
        DPI dpi = new DPI(id, nom_DPI, prenom_DPI, date_de_naissance, Sexe.valueOf(sexe_DPI), adresse_DPI, telephone_DPI, m);
        if (rs.next()) {
            System.out.println("Ce patient est déjà enregistré dans la base de données.");
        } else {
            //Ajout dans la table DPI
            Date dateNaissanceReelle = new Date(date_de_naissance.getYear()-1900, date_de_naissance.getMonth() - 1, date_de_naissance.getDate());
            Statement stmt2 = conn.createStatement();
            stmt2.executeUpdate("INSERT INTO DPI(IPP, nom_DPI, prenom_DPI, date_de_naissance, sexe_DPI, adresse_DPI, telephone_DPI, telephone_medecin_traitant) "
                    + "VALUES ('" + id + "', '" + nom_DPI + "', '" + prenom_DPI + "', TO_DATE('" + new java.sql.Date(dateNaissanceReelle.getTime()).toString() + "'"
                    + ",'yyyy-MM-dd')" + ", '" + sexe_DPI + "', '" + adresse_DPI + "', '" + telephone_DPI + "', '" + m.getTelephoneMedecinTraitant() + "')");
            //Ajout dans la table Medecin_traitant
            PreparedStatement stmt3 = null;
            stmt3 = conn.prepareStatement("INSERT INTO Medecin_traitant VALUES (?,?,?,?,?)");
            stmt3.setString(1, m.getNomMedecinTraitant());
            stmt3.setString(2, m.getPrenomMedecinTraitant());
            stmt3.setString(3, m.getMail());
            stmt3.setString(4, m.getTelephoneMedecinTraitant());
            stmt3.setString(5, id);
            stmt3.executeUpdate();
            stmt3.close();
            
            System.out.println("Ce patient a été inséré dans la base de données.");
            stmt2.close();
        }
        rs.close();
        stmt.close();
        return dpi;
    }

    /**
     * Crée une fiche de soins pour un patient et l'ajoute dans la base de donnée
     * @param conn la connection établie pour la base de données
     * @param fiche la fiche de soin a ajoutée au patient
     * @throws java.sql.SQLException
     */
    public static void creerFicheDeSoins(Connection conn, FicheDeSoins fiche) throws SQLException {
        
        for (int i = 0; i < fiche.getActe().size(); i++) {
            PreparedStatement stmt2 = null;
            String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(fiche.getDateHeure()));
            Timestamp t = Timestamp.valueOf(ts);
            int a;//idActe

            if (fiche.getInfirmier() != null) {
                //Insertion dans la table Acte
                a = creerActe(conn, fiche.getActe().get(i));
                //Insertion dans la table FichesDeSoins
                stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins values(?,?,?,?,?)");
                stmt2.setString(1, fiche.getDPI().getIPP());
                stmt2.setTimestamp(2, t);
                stmt2.setInt(3, a);//idActe
                stmt2.setString(4, null);
                stmt2.setString(5, fiche.getInfirmier().getIdInfirmiere());
            }
            else if (fiche.getpH() != null) {
                //Insertion dans la table Acte
                a = creerActe(conn, fiche.getActe().get(i));
                //Insertion dans la table FichesDeSoins
                stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins values(?,?,?,?,?)");
                stmt2.setString(1, fiche.getDPI().getIPP());
                stmt2.setTimestamp(2, t);
                stmt2.setInt(3, a);//idActe
                stmt2.setString(4, fiche.getpH().getIdPH());
                stmt2.setString(5, null);
            } 
            else {
                //Insertion dans la table Acte
                a = creerActe(conn, fiche.getActe().get(i));
                
                //Insertion dans la table FichesDeSoins
                stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins values(?,?,?,?,?)");
                stmt2.setString(1, fiche.getDPI().getIPP());
                stmt2.setTimestamp(2, t);
                stmt2.setInt(3, a);
                stmt2.setString(4, fiche.getpH().getIdPH());
                stmt2.setString(5, fiche.getInfirmier().getIdInfirmiere());
            }
            stmt2.executeUpdate();
            stmt2.close();
        }
    }
    
    /**
     * Crée un acte pour un patient et l'ajoute dans la base de donnée
     * @param conn la connection établie pour la base de données
     * @param acte l'acte ajouté au patient
     * @return un entier qui représente l'identifiant de l'acte
     * @throws java.sql.SQLException
     */
    public static int creerActe(Connection conn, Acte acte) throws SQLException {
        //Calcul du nombre d'élément dans la table Acte pour trouver l'id
        PreparedStatement stmt2 = null;
        int rowCount = 0;
        stmt2 = conn.prepareStatement("SELECT COUNT(idActe) AS rowcount FROM Acte");
        ResultSet rs2 = stmt2.executeQuery();
        if(rs2.next()){
            rowCount = rs2.getInt("rowcount");
        }
        
        //Insertion de l'acte
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("INSERT INTO Acte values(?,?,?,?,?,?)");
        stmt.setInt(1, rowCount+2);
        stmt.setString(2, acte.getCode().name()); 
        stmt.setString(3, acte.getType().name()); 
        stmt.setString(4, acte.getNomA()); 
        stmt.setFloat(5, acte.getCoeff()); 
        stmt.setString(6, acte.getObservation());
        stmt.executeUpdate();
        stmt.close();
        
        rs2.close();
        stmt2.close();
        return (rowCount+2);
    }
    
    /**
     * Crée une prescription pour un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param p la prescription ajoutée au patient
     * @throws java.sql.SQLException
     */
    public static void creerPrescription(Connection conn, Prescription p) throws SQLException{
        PreparedStatement stmt2 = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(p.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt2 = conn.prepareStatement("INSERT INTO Prescription values(?,?,?,?,?,?)");
        stmt2.setString(1, p.getDPI().getIPP());
        stmt2.setString(2, p.getpH().getIdPH());
        stmt2.setTimestamp(3, t);
        
        if(p.getTypeExamen() == null){
            stmt2.setString(4, p.getMedicament());
            stmt2.setString(5, null);
        }
        else if(p.getMedicament() == null){
            stmt2.setString(4, null);
            stmt2.setString(5, p.getTypeExamen().toString());
        }
        
        stmt2.setString(6, p.getObservation());
        
        stmt2.executeUpdate();
        stmt2.close();
    }
    
    /**
     * Crée une lettre de sortie pour un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param ls la lettre de sortie ajoutée au patient
     * @throws java.sql.SQLException
     */
    public static void creerLettreSortie(Connection conn, LettreDeSortie ls) throws SQLException{
        PreparedStatement stmt = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(ls.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt = conn.prepareStatement("INSERT INTO LettreDeSortie values(?,?,?,?)");
        stmt.setString(1, ls.getDPI().getIPP());
        stmt.setString(2, ls.getPh().getIdPH());
        stmt.setTimestamp(3, t);
        stmt.setString(4, ls.getTexte());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    /**
     * Crée un soin quotidien pour un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param soin le soin quotidien ajouté au patient
     * @throws java.sql.SQLException
     */
    public static void creerSoinQuotidien(Connection conn, SoinsQuotidien soin) throws SQLException{
        PreparedStatement stmt = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(soin.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt = conn.prepareStatement("INSERT INTO SoinsQuotidien values(?,?,?,?,?,?,?)");
        stmt.setString(1, soin.getDPI().getIPP());
        stmt.setTimestamp(2, t);
        stmt.setString(3, soin.getInfirmier().getIdInfirmiere());
        stmt.setFloat(4, (float) soin.getTemperature());
        stmt.setInt(5, soin.getSaturationO2());
        stmt.setFloat(6, (float) soin.getTension());
        stmt.setString(7, soin.getObservations());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    /**
     * Crée un rendez-vous pour un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param rdv le rendez-vous ajouté au patient
     * @throws java.sql.SQLException
     */
    public static void creerRendezVous(Connection conn, RendezVous rdv) throws SQLException{
        PreparedStatement stmt = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(rdv.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt = conn.prepareStatement("SELECT * FROM RendezVous WHERE IPP=? AND idPH=? AND dateHeure_RDV=?");
        stmt.setString(1, rdv.getDPI().getIPP());
        stmt.setString(2, rdv.getpH().getIdPH());
        stmt.setTimestamp(3, t);
        ResultSet rs = stmt.executeQuery();
        
        if (rs.next()) {
            System.out.println("Ce rendez-vous est déjà ajouté.");
        } else {
            System.out.println("ok else");
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO RendezVous values(?,?,?,?)");
            stmt2.setString(1, rdv.getDPI().getIPP());
            stmt2.setString(2, rdv.getpH().getIdPH());
            stmt2.setTimestamp(3, t);
            stmt2.setString(4, rdv.getRemarque());
        
            stmt2.executeUpdate();
            stmt2.close();
        }
        //rs.close();
        stmt.close();
    }
    
    /**
     * Crée un examen pour un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param exam l'exam ajouté au patient
     * @throws java.sql.SQLException
     */
    public static void creerExamen(Connection conn, Examen exam) throws SQLException{
        PreparedStatement stmt = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(exam.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt = conn.prepareStatement("INSERT INTO Examen values(?,?,?,?,?)");
        stmt.setString(1, exam.getDPI().getIPP());
        stmt.setString(2, exam.getPh().getIdPH());
        stmt.setTimestamp(3, t);
        stmt.setString(4, exam.getType_examen().toString());
        stmt.setString(5, exam.getResultats());
        
        stmt.executeUpdate();
        stmt.close();
    }
    
    /**
     * Crée la localisation d'un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @param loc la localisation où le patient sera
     * @throws java.sql.SQLException
     */
    public static void creerLocalisation(Connection conn, String ipp, Localisation loc) throws SQLException{
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM Archive WHERE IPP = ?");
        stmt.setString(1, ipp);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            System.out.println("Le patient est archivé et ne peut pas être localisé dans le CHU.");
        }
        else {
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO Localisation values(?,?,?,?,?)");
            stmt2.setString(1, ipp);
            stmt2.setString(2, loc.getService_responsable().toString());
            stmt2.setString(3, loc.getService_geographique().toString());
            stmt2.setString(4, loc.getLit().toString());
            stmt2.setString(5, valueOf(loc.getNchambre()));
        
            stmt2.executeUpdate();
            stmt2.close();
        }
        
        rs.close();
        stmt.close();
    }
    
    /**
     * Crée la localisation à l'étape du secrétariat administratif d'un patient et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @param service_respo le service responsable du patient
     * @throws java.sql.SQLException
     */
    public static void creerLocalisationSA(Connection conn, String ipp, Service service_respo) throws SQLException{
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM Archive WHERE IPP = ?");
        stmt.setString(1, ipp);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            System.out.println("Le patient est archivé et ne peut pas être localisé dans le CHU.");
        }
        else {
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO Localisation values(?,?,?,?,?)");
            stmt2.setString(1, ipp);
            stmt2.setString(2, service_respo.name());
            stmt2.setString(3, null);
            stmt2.setString(4, null);
            stmt2.setString(5, null);
        
            stmt2.executeUpdate();
            stmt2.close();
        }
        
        rs.close();
        stmt.close();
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //Modification d'éléments
    
    /**
     * Modifie la localisation à l'étape du secrétariat médical d'un patient et la modifier dans la base de données
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @param numero_chambre le numéro de la nouvelle chambre du patient
     * @param lit le nouveau lit du patient
     * @param service_geo le service géographique où sera le patient
     * @throws java.sql.SQLException
     */
    public static void modifierLocalisationSM(Connection conn, String ipp, int numero_chambre, Lit lit, Service service_geo) throws SQLException{
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM Archive WHERE IPP = ?");
        stmt.setString(1, ipp);
        ResultSet rs = stmt.executeQuery();
        
        if(rs.next()){
            System.out.println("Le patient est archivé et ne peut pas être localisé dans le CHU.");
        }
        else {
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("UPDATE Localisation SET lit = ?, nchambre = ?, service_geographique = ?  WHERE IPP = ?");
            stmt2.setString(1, lit.name());
            stmt2.setInt(2, numero_chambre);
            stmt2.setString(3, service_geo.name());
            stmt2.setString(4, ipp);
            stmt2.executeUpdate();
            stmt2.close();
        }
        
        rs.close();
        stmt.close();
    }
    
    /**
     * Modifie certaines informations dans le DPI d'un patient et le modifier dans la base de données
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @param telephone le nouveau numéro de téléphone du patient
     * @param adresse la nouvelle adresse postale du patient
     * @param m le médecin traitant du patient
     * @throws java.sql.SQLException
     */
    public static void modifierDPI(Connection conn, String ipp, String telephone, String adresse, MedecinTraitant m) throws SQLException{
        //Modification dans la table DPI
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("UPDATE DPI SET adresse_DPI = ?, telephone_DPI = ?, telephone_medecin_traitant = ? WHERE IPP = ?");
        stmt.setString(1, adresse);
        stmt.setString(2, telephone);
        stmt.setString(3, m.getTelephoneMedecinTraitant());
        stmt.setString(4, ipp);
        stmt.executeUpdate();
        stmt.close();
        
        //Modification dans la table Medecin_traitant
        PreparedStatement stmt2 = null;
        stmt2 = conn.prepareStatement("UPDATE Medecin_traitant SET nom_medecin_traitant = ?, prenom_medecin_traitant = ?, mail = ?, telephone_medecin_traitant = ? WHERE IPP = ?");
        stmt2.setString(1, m.getNomMedecinTraitant());
        stmt2.setString(2, m.getPrenomMedecinTraitant());
        stmt2.setString(3, m.getMail());
        stmt2.setString(4, m.getTelephoneMedecinTraitant());
        stmt2.setString(5, ipp);
        stmt2.executeUpdate();
        stmt2.close();
    }
    
    /**
     * Archivage d'un dossier quand le patient est décédé
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @param date_deces la date de décès du patient
     * @throws java.sql.SQLException
     */
    public static void archiverDPI(Connection conn, String ipp, Date date_deces) throws SQLException{
        PreparedStatement stmt = null;
        //Ajout du DPI dans les archives
        stmt = conn.prepareStatement("INSERT INTO Archive values(?,?,?)");
        stmt.setString(1, ipp);
        stmt.setDate(2, convertDateJavaEnSQL(date_deces));
        stmt.setDate(3, convertDateJavaEnSQL(convertLocalDateEnDate(LocalDate.now())));
        stmt.executeUpdate();
        stmt.close();
        
        //Fermeture du DPI 
        fermerDPI(conn, ipp);
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //Suppression d'éléments
    
    /**
     * Suppression de la localisation d'un patient lorsqu'on ferme son dossier
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @throws java.sql.SQLException
     */
    public static void fermerDPI(Connection conn, String ipp) throws SQLException{
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM Localisation WHERE IPP = ?");
        stmt.setString(1, ipp);
        stmt.executeUpdate();
        stmt.close();
    }
}
