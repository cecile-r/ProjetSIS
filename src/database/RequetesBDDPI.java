/*
Fonctions relatives aux DPI
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
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
import database.RequetesBDConversion;
import static database.RequetesBDConversion.convertDateSQLenJava;
import static database.RequetesBDConversion.convertTimestampSQLenJava;
import static database.RequetesBDConversion.toStringTimestamp;
import nf.DM;
import nf.DMA;
import nf.Lit;
import nf.Localisation;
import nf.Prescription;
import nf.SoinsQuotidien;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDDPI {

    //Renvoie true si l'ipp existe sinon renvoie false
    //VALIDE
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

    //Renvoie la liste des DPI fermés -> patients PAS dans le CHU
    //VALIDE
    public static List<DPI> getListeDPIFerme(Connection conn) throws SQLException {
        List<DPI> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP NOT IN (SELECT IPP FROM Localisation)");

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
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING (telephone_medecin_traitant, IPP) "
                + "WHERE IPP NOT IN (SELECT IPP FROM Localisation)");

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

    //Renvoie la liste des soins quotidiens pour un patient donné
    //
    /*public static List<SoinsQuotidien> listeSoinQuotidien(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM SoinsQuotidien "
                + "WHERE IPP = '" + ipp + "'");//Tous les soins quotidiens du patient
        List<SoinsQuotidien> listeSQ = new ArrayList();

        while (rs.next()) {
            SoinsQuotidien sq = new SoinsQuotidien((double) rs.getFloat("temperature"), (int) rs.getFloat("saturation_o2"), (double) rs.getFloat("tension"), rs.getString("remarque"), convertTimestampSQLEnJava(rs.getTimestamp("dateHeure_SoinsQuotidien")));

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
    }*/

    //Renvoie la liste des prescriptions pour un patient donné
    //VALIDE
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
                //System.out.println(rs2.getTimestamp("dateHeure_FichesDeSoins").toGMTString());
                System.out.println(rs2.getTimestamp("dateHeure_FichesDeSoins"));
                //System.out.println(toStringTimestamp(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                //System.out.println(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                //System.out.println(convertDateJavaenSQL('" + rs2.getTimestamp("dateHeure_FichesDeSoins") + "', 'DD-MON-YYYY HH:MI" + ":00v AM');
                ResultSet rs3 = stmt3.executeQuery("SELECT dateHeure_FichesDeSoins, idActe, libelle, cout, type, nom_Acte, coeff, observation FROM FichesDeSoins " //Sélection de la fiche de soins et tous ses actes
                        + "JOIN Acte USING (idActe) "
                        + "WHERE (IPP = '" + ipp + "') AND (TO_CHAR(dateHeure_FichesDeSoins, 'DD-MON-YYYY HH12:MI:SS.FF') = '" + toStringTimestamp(rs2.getTimestamp("dateHeure_FichesDeSoins")) + "')");
                //+ "WHERE (diff = 0)");  
                //+ "WHERE (IPP = '" + ipp + "') AND (dateHeure_FichesDeSoins - " + rs2.getTimestamp("dateHeure_FichesDeSoins") + " = 0)");
                //convertDateHeureJavaEnTimestampSQL(fiche.getDateHeure())
                System.out.println("avec fct tostringtimestamp : " + toStringTimestamp(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                System.out.println("avant while 2");//marche jusque la

                while (rs3.next()) { //Tant qu'il y a des actes pour cette fiche MAIS ne rentre pas dans la boucle PB
                    System.out.println("ok while 2");
                    Code c = Code.valueOf(rs3.getString("libelle") + "," + (double) rs3.getFloat("cout"));
                    Acte acte = new Acte(rs3.getString("nom_Acte"), Type.valueOf(rs3.getString("type")), c, (int) rs3.getFloat("coeff"), rs3.getString("observation"));
                    //coeff int ou float??? est ce que ca va poser pb?
                    fiche.ajouterActe(acte);
                }
                System.out.println("ok fin while 2");
                //Création du professionnel de santé qui réalise la fiche de soins
                if (rs2.getString("idPH") != null) { //Si l'éditeur/celui qui réalise les actes est un PH
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
                } else if (rs2.getString("idInfirmier") != null) { //L'éditeur/celui qui réalise les actes est un infirmier
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
            rs2.close();
            stmt2.close();
        }
        rs.close();

        stmt.close();

        return listeFiches;
    }

    //Renvoie la liste des fiches de soins pour un patient donné
    //
    public static List<FicheDeSoins> listeFichesDeSoinsBis(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI "
                + "WHERE IPP = '" + ipp + "'");//Les infos du patient
        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM FichesDeSoins NATURAL JOIN Acte "
                + "WHERE IPP = '" + ipp + "'");//Toutes les fiches de soins du patient, fiches n'apparaissent pas en double
        List<FicheDeSoins> listeFiches = new ArrayList();

        if (rs.next()) { //Si y'a un DPI qui correspond à l'identifiant
            int compteur = 0;
            //Création de la liste de fiches de soins avec doublons
            while (rs2.next()) { //Tant que y'a des fiches de soins pour ce patient
                if (listeFiches.size() != 0 && listeFiches.get(listeFiches.size() - 1).getDateHeure().compareTo(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins"))) == 0) {
                    compteur++;
                } else {
                    compteur = 0;
                }

                if (compteur == 0) {//Si c'est la 1er fois qu'on voit la fiche
                    FicheDeSoins fiche = new FicheDeSoins(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                    Code c = Code.valueOf(rs2.getString("libelle"));
                    Acte acte = new Acte(rs2.getString("nom_Acte"), Type.valueOf(rs2.getString("type")), c, (int) rs2.getFloat("coeff"), rs2.getString("observation"));
                    fiche.ajouterActe(acte);

                    //Création du professionnel de santé qui réalise la fiche de soins
                    if (rs2.getString("idPH") != null) { //Si l'éditeur/celui qui réalise les actes est un PH
                        System.out.println("ph ok");
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
                        System.out.println("inf ok");
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
                }

                if (compteur > 0) {//Si y'a deja eu cette fiche
                    Code c = Code.valueOf(rs2.getString("libelle"));
                    Acte acte = new Acte(rs2.getString("nom_Acte"), Type.valueOf(rs2.getString("type")), c, (int) rs2.getFloat("coeff"), rs2.getString("observation"));
                    listeFiches.get(listeFiches.size() - 1).ajouterActe(acte);
                }

            }
            rs2.close();
            stmt2.close();
        }
        rs.close();
        stmt.close();

        return listeFiches;
    }

    //Renvoie le DPI associé à l'ipp donnée, ainsi que ses DM et DMA associés
    //
    /*public static DPI getDPI(Connection conn, String ipp) throws SQLException {
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
            Localisation loc = new Localisation(Service.valueOf(rs.getString("service_responsable")), Lit.valueOf(rs.getString("lit")), rs.getInt("nchambre"), Service.valueOf(rs.getString("service_geographique")));
            DMA dma = new DMA(loc);
            //Remplissage du DMA
            List<FicheDeSoins> listeFiches = listeFichesDeSoins(conn, ipp);
            for (int i = 0; i < listeFiches.size(); i++){ //parcours de la liste de fiches de soins
                dma.ajouterFicheDeSoins(listeFiches.get(i));
            }
            List<RendezVous> listeRDV = listeRendezVous(conn, ipp);
            for (int i = 0; i < listeRDV.size(); i++){ //parcours de la liste de rdv
                dma.ajouterRendezVous(listeRDV.get(i));
            }
            List<Examen> listeExam = listeExamens(conn, ipp);
            for (int i = 0; i < listeExam.size(); i++){ //parcours de la liste des examens
                dma.ajouterExamen(listeExam.get(i));
            }
            List<LettreDeSortie> listeLS = listeLettreSortie(conn, ipp);
            for (int i = 0; i < listeLS.size(); i++){ //parcours de la liste des lettres de sortie
                dma.ajouterLettreDeSortie(listeLS.get(i));
            }
            
            //Création du DM
            DM dm = new DM();
            //Remplissage du DM
            for (int i = 0; i < listeFiches.size(); i++){ //parcours de la liste de fiches de soins définie au dessus
                dm.ajouterFicheDeSoins(listeFiches.get(i));
            }
            List<Prescription> listeP = listePrescription(conn, ipp);
            for (int i = 0; i < listeP.size(); i++){ //parcours des prescriptions
                dm.ajouterPrescription(listeP.get(i));
            }
            for (int i = 0; i < listeLS.size(); i++){ //parcours de la liste des lettres de sortie définie au dessus
                dm.ajouterLettreDeSortie(listeLS.get(i));
            }
            //List<SoinsQuotidien> listeSQ = listeSoinQuotidien(conn, ipp);
            /*for (int i = 0; i < listeSQ.size(); i++){ //parcours de la liste des lettres de sortie
                dm.ajouterSoinsQuotidien(listeSQ.get(i));
            }
            for (int i = 0; i < listeExam.size(); i++){ //parcours de la liste des examens définie au dessus
                dm.ajouterExamen(listeExam.get(i));
            }/*
            
        dpi.setdMA(dma);
        dpi.setdM(dm);
        return dpi;
    }
    return dpi;
    }*/
}
