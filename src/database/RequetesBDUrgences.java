/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.RequetesBDConversion.convertDateHeureJavaEnTimestampSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nf.DPI;
import nf.DPITemporaire;
import nf.MedecinTraitant;
import nf.PH;
import nf.Service;
import nf.Sexe;
import static database.RequetesBDConversion.convertDateJavaEnSQL;
import static database.RequetesBDConversion.convertDateSQLenJava;
import static database.RequetesBDConversion.convertTimestampSQLenJava;
import static database.RequetesBDConversion.toStringTimestampJAVA;
import static database.RequetesBDDPI.creerExamen;
import static database.RequetesBDDPI.creerFicheDeSoins;
import static database.RequetesBDDPI.creerPrescription;
import static database.RequetesBDDPI.getIPPPatient;
import nf.Acte;
import nf.Code;
import nf.DateHeure;
import nf.Examen;
import nf.ExamenTemp;
import nf.FicheDeSoins;
import nf.FicheDeSoinsTemp;
import nf.Infirmier;
import nf.Prescription;
import nf.PrescriptionTemp;
import nf.Type;
import nf.TypeExamen;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDUrgences {

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du DPI temporaire
     * @return boolean true si l'ipp existe sinon false
     * @throws java.sql.SQLException
     */
    public static boolean IPPTempExistant(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT IPP FROM DPI_temporaire "
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
     * @return le nombre de DPI temporaire
     * @throws java.sql.SQLException
     */
    public static int nbDPITemporaire(Connection conn) throws SQLException {
        PreparedStatement stmt = null;
        int rowCount = 0;
        stmt = conn.prepareStatement("SELECT COUNT(IPP) AS rowcount FROM DPI_temporaire");
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            rowCount = rs.getInt("rowcount");
        }
        return rowCount;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param dpit le dpi temporaire à créer
     * @throws java.sql.SQLException
     */
    public static void creerDPITemporaire(Connection conn, DPITemporaire dpit) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM DPI_temporaire WHERE nom_DPI_temp=? AND prenom_DPI_temp=? AND date_de_naissance_temp=?");
        stmt.setString(1, dpit.getNom());
        stmt.setString(2, dpit.getPrenom());
        stmt.setDate(3, convertDateJavaEnSQL(dpit.getDate_naissance()));
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) { //Si le DPI temporaire n'existe pas encore
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO DPI_temporaire values(?,?,?,?,?)");
            stmt2.setString(1, dpit.getIPP());
            stmt2.setString(2, dpit.getNom());
            stmt2.setString(3, dpit.getPrenom());
            stmt2.setDate(4, convertDateJavaEnSQL(dpit.getDate_naissance()));
            stmt2.setInt(5, 0);
            stmt2.executeUpdate();
            stmt2.close();
        } else {
            System.out.println("Ce patient est déjà dans le service des urgences.");
        }
        rs.close();
        stmt.close();

    }

    /**
     * @param conn la connection établie pour la base de données
     * @return la liste de DPI temporaires : les patients aux urgences
     * @throws java.sql.SQLException
     */
    public static List<DPITemporaire> getListeDPITemporaires(Connection conn) throws SQLException {
        List<DPITemporaire> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI temporaires
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "WHERE attente = 0");

        while (rs.next()) {
            Date d = new Date(rs.getDate("date_de_naissance_temp").getTime());
            DPITemporaire dpi = new DPITemporaire(rs.getString("IPP"), rs.getString("nom_DPI_temp"), rs.getString("prenom_DPI_temp"), d);
            listeDPI.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPI;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param dpit l'ipp du DPI temporaire
     * @return boolean true si le dpit existe sinon false
     * @throws java.sql.SQLException
     */
    public static boolean dpiExiste(Connection conn, DPITemporaire dpit) throws SQLException {
        boolean exist = false;
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM DPI "
                + "WHERE UPPER(nom_DPI) = UPPER(?) AND UPPER(prenom_DPI) = UPPER(?) AND date_de_naissance = ?");
        stmt.setString(1, dpit.getNom());
        stmt.setString(2, dpit.getPrenom());
        stmt.setDate(3, convertDateJavaEnSQL(dpit.getDate_naissance()));
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            exist = true;
        }

        return exist;
    }

    /**
     * Fusionne les DPI si le DPI des urgences existe déjà au CHU
     * @param conn la connection établie pour la base de données
     * @param dpit l'ipp du DPI temporaire
     * @throws java.sql.SQLException
     */
    public static void fusionDPI(Connection conn, DPITemporaire dpit) throws SQLException {
        //FICHES DE SOINS
        for (int i = 0; i < listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).size(); i++) { //Parcours des fiches de soins temporaires
            //Ajout de la fiche de soin dans la table FichesDeSoins
            FicheDeSoins f = new FicheDeSoins(listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i).getDateHeure());
            f.setActe(listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i).getActe());

            //Création DPI avec l'IPP réel du patient dans la table DPI
            PreparedStatement stmt = null;
            stmt = conn.prepareStatement("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = ?");
            stmt.setString(1, getIPPPatient(conn, dpit.getNom(), dpit.getPrenom(), dpit.getDate_naissance()));
            ResultSet rs = stmt.executeQuery();//Les infos du DPI du patient

            if (rs.next()) {
                MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
                Date d = new Date(rs.getDate("date_de_naissance").getTime());
                DPI dpi = new DPI(rs.getString("IPP"), rs.getString("nom_DPI"), rs.getString("prenom_DPI"), d, Sexe.valueOf(rs.getString("sexe_DPI")), rs.getString("adresse_DPI"), rs.getString("telephone_DPI"), m);
                f.setDPI(dpi);
            }
            stmt.close();
            rs.close();

            //Création du professionnel de santé qui réalise la fiche de soins
            if (listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i).getpH() != null) { //Si l'éditeur/celui qui réalise les actes est un PH
                Statement stmt4 = conn.createStatement();
                ResultSet rs4 = stmt4.executeQuery("SELECT * FROM PH "
                        + "WHERE idPH = '" + listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i).getpH().getIdPH() + "'");//Avoir les infos du PH pour cette fiche de soins
                if (rs4.next()) {//Si y'a bien un PH qui correspond -> en principe toujours oui mais on sait jamais
                    PH ph = new PH(rs4.getString("idPH"), rs4.getString("nom_PH"), rs4.getString("prenom_PH"), Service.valueOf(rs4.getString("service_PH")), rs4.getString("mdp_PH"), rs4.getString("telephone_PH"), rs4.getString("specialite_PH"));
                    f.setpH(ph);
                }
                rs4.close();
                stmt4.close();
            } else if (listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i).getInfirmier() != null) { //L'éditeur/celui qui réalise les actes est un infirmier
                Statement stmt5 = conn.createStatement();
                ResultSet rs5 = stmt5.executeQuery("SELECT * FROM Infirmier "
                        + "WHERE idInfirmier = '" + listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i).getInfirmier().getIdInfirmiere() + "'");//Avoir les infos de l'infirmier pour cette fiche de soins
                if (rs5.next()) {//Si y'a bien un infirmier qui correspond -> en principe toujours oui mais on sait jamais
                    Infirmier inf = new Infirmier(rs5.getString("idInfirmier"), rs5.getString("nom_Infirmier"), rs5.getString("prenom_Infirmier"), Service.valueOf(rs5.getString("service_Infirmier")), rs5.getString("mdp_Infirmier"));
                    f.setInfirmier(inf);
                }
                rs5.close();
                stmt5.close();
            }
            creerFicheDeSoins(conn, f);
        }
        //Suppression des fiches dans FichesDeSoins_temporaire
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM FichesDeSoins_temporaire WHERE IPP = ?");
        stmt.setString(1, dpit.getIPP());
        stmt.executeUpdate();
        stmt.close();

        //PRESCRIPTIONS
        for (int i = 0; i < listePrescriptionTemporaire(conn, dpit.getIPP()).size(); i++) { //Parcours des prescriptions temporaires
            //Ajout de la prescription dans la table Prescription
            Prescription p = new Prescription(listePrescriptionTemporaire(conn, dpit.getIPP()).get(i).getDateHeure(), listePrescriptionTemporaire(conn, dpit.getIPP()).get(i).getObservation(), listePrescriptionTemporaire(conn, dpit.getIPP()).get(i).getTypeExamen(), listePrescriptionTemporaire(conn, dpit.getIPP()).get(i).getMedicament());

            //Création PH
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + listePrescriptionTemporaire(conn, dpit.getIPP()).get(i).getpH().getIdPH() + "'");//Selection du PH qui a créé la prescription
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                p.setpH(ph);
            }

            //Création du DPI
            PreparedStatement stmt3 = null;
            stmt3 = conn.prepareStatement("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = ?");
            stmt3.setString(1, getIPPPatient(conn, dpit.getNom(), dpit.getPrenom(), dpit.getDate_naissance()));
            ResultSet rs3 = stmt3.executeQuery();//Les infos du DPI du patient
            if (rs3.next()) {
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                p.setDPI(dpi);
            }
            rs2.close();
            rs3.close();
            stmt2.close();
            stmt3.close();

            creerPrescription(conn, p);
        }
        //Suppression des prescriptions dans Prescription_temporaire
        PreparedStatement stmtp = null;
        stmtp = conn.prepareStatement("DELETE FROM Prescription_temporaire WHERE IPP = ?");
        stmtp.setString(1, dpit.getIPP());
        stmtp.executeUpdate();
        stmtp.close();

        //EXAMENS
        for (int i = 0; i < listeExamensTemporaire(conn, dpit.getIPP()).size(); i++) { //Parcours des prescriptions temporaires
            //Ajout de l'examen dans la table Examen
            Examen e = new Examen(listeExamensTemporaire(conn, dpit.getIPP()).get(i).getType_examen(), listeExamensTemporaire(conn, dpit.getIPP()).get(i).getResultats(), listeExamensTemporaire(conn, dpit.getIPP()).get(i).getDateHeure());

            //Création du PH
            Statement stmte = conn.createStatement();
            ResultSet rs2 = stmte.executeQuery("SELECT * FROM PH "
                    + "WHERE idPH = '" + listeExamensTemporaire(conn, dpit.getIPP()).get(i).getPh().getIdPH() + "'");//Selection du PH qui réalise l'examen
            if (rs2.next()) {
                PH ph = new PH(rs2.getString("idPH"), rs2.getString("nom_PH"), rs2.getString("prenom_PH"), Service.valueOf(rs2.getString("service_PH")), rs2.getString("mdp_PH"), rs2.getString("telephone_PH"), rs2.getString("specialite_PH"));
                e.setPh(ph);
            }

            //Création du DPI
            Statement stmt3 = conn.createStatement();
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI "
                    + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                    + "WHERE IPP = '" + getIPPPatient(conn, dpit.getNom(), dpit.getPrenom(), dpit.getDate_naissance()) + "'");//Les infos du DPI du patient
            if (rs3.next()) {
                MedecinTraitant m = new MedecinTraitant(rs3.getString("mail"), rs3.getString("nom_medecin_traitant"), rs3.getString("prenom_medecin_traitant"), rs3.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs3.getString("IPP"), rs3.getString("nom_DPI"), rs3.getString("prenom_DPI"), convertDateSQLenJava(rs3.getDate("date_de_naissance")), Sexe.valueOf(rs3.getString("sexe_DPI")), rs3.getString("adresse_DPI"), rs3.getString("telephone_DPI"), m);
                e.setDPI(dpi);
            }

            creerExamen(conn, e);
        }
        //Suppression des examens dans Examen_temporaire
        PreparedStatement stmte = null;
        stmte = conn.prepareStatement("DELETE FROM Examen_temporaire WHERE IPP = ?");
        stmte.setString(1, dpit.getIPP());
        stmte.executeUpdate();
        stmte.close();

        //Suppression du DPI temporaire
        PreparedStatement stmt2 = null;
        stmt2 = conn.prepareStatement("DELETE FROM DPI_temporaire WHERE IPP = ?");
        stmt2.setString(1, dpit.getIPP());
        stmt2.executeUpdate();
        stmt2.close();
    }

    /**
     * Créer une fiche de soins d'urgence (temporaire) et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param fiche la fiche de soins temporaire à ajouter
     * @throws java.sql.SQLException
     */
    public static void creerFicheDeSoinsTemp(Connection conn, FicheDeSoinsTemp fiche) throws SQLException {

        for (int i = 0; i < fiche.getActe().size(); i++) {
            String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(fiche.getDateHeure()));
            Timestamp t = Timestamp.valueOf(ts);
            int a;//idActe

            //Insertion dans la table Acte
            a = creerActeTemp(conn, fiche.getActe().get(i));
            //Insertion dans la table FichesDeSoins_temporaire
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins_temporaire values(?,?,?,?,?)");
            stmt2.setString(1, fiche.getDPI().getIPP());
            stmt2.setTimestamp(2, t);
            stmt2.setInt(3, a);//idActe
            stmt2.setString(4, fiche.getpH().getIdPH());
            stmt2.setString(5, null);
            stmt2.executeUpdate();
            stmt2.close();
        }
    }

    /**
     * Créer un acte et l'ajouter dans la base de donnée
     * @param conn la connection établie pour la base de données
     * @param acte l'acte temporaire à ajouter
     * @return l'identifiant de l'acte
     * @throws java.sql.SQLException
     */
    public static int creerActeTemp(Connection conn, Acte acte) throws SQLException {
        //Calcul du nombre d'élément dans la table Acte pour trouver l'id
        PreparedStatement stmt2 = null;
        int rowCount = 0;
        stmt2 = conn.prepareStatement("SELECT COUNT(idActe) AS rowcount FROM Acte_temporaire");
        ResultSet rs2 = stmt2.executeQuery();
        if (rs2.next()) {
            rowCount = rs2.getInt("rowcount");
        }

        //Insertion de l'acte
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("INSERT INTO Acte_temporaire values(?,?,?,?,?,?)");
        stmt.setInt(1, rowCount + 2);
        stmt.setString(2, acte.getCode().name());
        stmt.setString(3, acte.getType().name());
        stmt.setString(4, acte.getNomA());
        stmt.setFloat(5, acte.getCoeff());
        stmt.setString(6, acte.getObservation());
        stmt.executeUpdate();
        stmt.close();

        rs2.close();
        stmt2.close();
        return (rowCount + 2);
    }

    /**
     * Créer une prescription d'urgence (temporaire) et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param p la prescription temporaire à ajouter
     * @throws java.sql.SQLException
     */
    public static void creerPrescriptionTemp(Connection conn, PrescriptionTemp p) throws SQLException {
        PreparedStatement stmt2 = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(p.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt2 = conn.prepareStatement("INSERT INTO Prescription_temporaire values(?,?,?,?,?,?)");
        stmt2.setString(1, p.getDPI().getIPP());
        stmt2.setString(2, p.getpH().getIdPH());
        stmt2.setTimestamp(3, t);

        if (p.getTypeExamen() == null) {
            stmt2.setString(4, p.getMedicament());
            stmt2.setString(5, null);
        } else if (p.getMedicament() == null) {
            stmt2.setString(4, null);
            stmt2.setString(5, p.getTypeExamen().toString());
        }

        stmt2.setString(6, p.getObservation());

        stmt2.executeUpdate();
        stmt2.close();
    }

    /**
     * Créer un examen d'urgence (temporaire) et l'ajouter dans la base de données
     * @param conn la connection établie pour la base de données
     * @param exam l'examen temporaire à ajouter
     * @throws java.sql.SQLException
     */
    public static void creerExamenTemp(Connection conn, ExamenTemp exam) throws SQLException {
        PreparedStatement stmt = null;
        String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(exam.getDateHeure()));
        Timestamp t = Timestamp.valueOf(ts);
        stmt = conn.prepareStatement("INSERT INTO Examen_temporaire values(?,?,?,?,?)");
        stmt.setString(1, exam.getDPI().getIPP());
        stmt.setString(2, exam.getPh().getIdPH());
        stmt.setTimestamp(3, t);
        stmt.setString(4, exam.getType_examen().toString());
        stmt.setString(5, exam.getResultats());

        stmt.executeUpdate();
        stmt.close();
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @return la liste des fiches de soins
     * @throws java.sql.SQLException
     */
    public static List<FicheDeSoinsTemp> listeFichesDeSoinsTemporaire(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        Statement stmt2 = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "WHERE IPP = '" + ipp + "'");//Les infos du patient
        ResultSet rs2 = stmt2.executeQuery("SELECT * FROM FichesDeSoins_temporaire JOIN Acte_temporaire USING (idActe)"
                + "WHERE IPP = '" + ipp + "'");//Toutes les fiches de soins du patient, fiches n'apparaissent pas en double
        List<FicheDeSoinsTemp> listeFiches = new ArrayList();
        List<DateHeure> listeDateHeure = new ArrayList();

        if (rs.next()) { //Si y'a un DPI qui correspond à l'identifiant
            //Création de la liste de fiches de soins avec doublons
            while (rs2.next()) { //Tant que y'a des fiches de soins pour ce patient

                if (!listeDateHeure.contains(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")))) {//Si c'est la 1er fois qu'on voit la fiche
                    listeDateHeure.add(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
                    FicheDeSoinsTemp fiche = new FicheDeSoinsTemp(convertTimestampSQLenJava(rs2.getTimestamp("dateHeure_FichesDeSoins")));
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

                    //Création du DPI temporaire
                    Statement stmt3 = conn.createStatement();
                    ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI_temporaire "
                            + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
                    if (rs3.next()) {
                        DPITemporaire dpi = new DPITemporaire(rs3.getString("IPP"), rs3.getString("nom_DPI_temp"), rs3.getString("prenom_DPI_temp"), convertDateSQLenJava(rs3.getDate("date_de_naissance_temp")));
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
     * @return la liste des prescriptions
     * @throws java.sql.SQLException
     */
    public static List<PrescriptionTemp> listePrescriptionTemporaire(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Prescription_temporaire "
                + "WHERE IPP = '" + ipp + "'");//Toutes les prescriptions du patient
        List<PrescriptionTemp> listeP = new ArrayList();

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
            PrescriptionTemp p = new PrescriptionTemp(convertTimestampSQLenJava(rs.getTimestamp("dateHeure_Prescription")), obs, te, rs.getString("medicament"));
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
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI_temporaire "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
                DPITemporaire dpi = new DPITemporaire(rs3.getString("IPP"), rs3.getString("nom_DPI_temp"), rs3.getString("prenom_DPI_temp"), convertDateSQLenJava(rs3.getDate("date_de_naissance_temp")));
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
     * @return la liste des examens
     * @throws java.sql.SQLException
     */
    public static List<ExamenTemp> listeExamensTemporaire(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM Examen_temporaire "
                + "WHERE IPP = '" + ipp + "'");//Tous les examens du patient
        List<ExamenTemp> listeExams = new ArrayList();

        //Création de la liste d'examens
        while (rs.next()) { //Tant qu'il y a des examens pour le patient
            ExamenTemp exam = new ExamenTemp(TypeExamen.valueOf(rs.getString("nom_Examen")), convertTimestampSQLenJava(rs.getTimestamp("dateHeure_Examen")));
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
            ResultSet rs3 = stmt3.executeQuery("SELECT * FROM DPI_temporaire "
                    + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient
            if (rs3.next()) {
                DPITemporaire dpi = new DPITemporaire(rs3.getString("IPP"), rs3.getString("nom_DPI_temp"), rs3.getString("prenom_DPI_temp"), convertDateSQLenJava(rs3.getDate("date_de_naissance_temp")));
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
     * @return le DPI temporaire
     * @throws java.sql.SQLException
     */
    public static DPITemporaire getDPITemp(Connection conn, String ipp) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "WHERE IPP = '" + ipp + "'");//Les infos du DPI du patient

        if (rs.next()) { //Si y'a un DPI temporaire qui correspond à l'identifiant
            //Création des instances pour créer le DPI
            Date d = new Date(rs.getDate("date_de_naissance_temp").getTime());
            DPITemporaire dpi = new DPITemporaire(rs.getString("IPP"), rs.getString("nom_DPI_temp"), rs.getString("prenom_DPI_temp"), d);

            //Remplissage de la liste de fiches de soins temporaires
            List<FicheDeSoinsTemp> listeFiches = listeFichesDeSoinsTemporaire(conn, ipp);
            dpi.setListe_f(listeFiches);

            //Remplissage de la liste d'examens temporaires
            List<ExamenTemp> listeExam = listeExamensTemporaire(conn, ipp);
            dpi.setListe_e(listeExam);

            //Remplissage de la liste de prescriptions temporaires
            List<PrescriptionTemp> listeP = listePrescriptionTemporaire(conn, ipp);
            dpi.setListe_p(listeP);

            return dpi;
        }
        else{
            return null;
        }
    }

    /**
     * @param conn la connection établie pour la base de données
     * @return la liste des DPI temporaires mis en attente avant leur passage à l'administration
     * @throws java.sql.SQLException
     */
    public static List<DPITemporaire> getListeDPIAttente(Connection conn) throws SQLException {
        List<DPITemporaire> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI temporaires
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "WHERE attente = 1");

        while (rs.next()) {
            Date d = new Date(rs.getDate("date_de_naissance_temp").getTime());
            DPITemporaire dpi = new DPITemporaire(rs.getString("IPP"), rs.getString("nom_DPI_temp"), rs.getString("prenom_DPI_temp"), d);
            listeDPI.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPI;
    }

    /**
     * Met les DPI temporaires en liste attente lorsqu'un patient sort des urgences pour la fusion ou création de DPI
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @throws java.sql.SQLException
     */
    public static void miseAttente(Connection conn, String ipp) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM DPI_temporaire "
                + "WHERE IPP = ?");
        stmt.setString(1, ipp);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("UPDATE DPI_temporaire SET attente = ? "
                    + "WHERE IPP = ?");
            stmt2.setInt(1, 1);
            stmt2.setString(2, ipp);
            stmt2.executeUpdate();
            stmt2.close();
        }
        rs.close();
        stmt.close();
    }

    /**
     * Supprime un DPI temporaire à partir d'un IPP
     * @param conn la connection établie pour la base de données
     * @param ipp l'ipp du patient
     * @throws java.sql.SQLException
     */
    public static void supprimerDPITemp(Connection conn, String ipp) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM DPI_temporaire "
                + "WHERE IPP = ?");
        stmt.setString(1, ipp);
        ResultSet rs = stmt.executeQuery();
        if (rs.next()) {
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("DELETE FROM DPI_temporaire WHERE IPP = ?");
            stmt2.setString(1, ipp);
            stmt2.executeUpdate();
            stmt2.close();
        }
        rs.close();
        stmt.close();
    }
    
    /**
     * Transfert les fiches de soins, prescriptions, examens d'un DPI temporaire dans un DPI et suppression des temporaires
     * @param conn la connection établie pour la base de données
     * @param dpi le dpi du patient
     * @param dpit le dpi temporaire du patient
     * @throws java.sql.SQLException
     */
    public static void transfertDPI(Connection conn, DPI dpi, DPITemporaire dpit) throws SQLException {
        //Récupération des données de dpit
        //FICHES DE SOINS
        for (int i = 0 ; i < listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).size() ; i++){
            FicheDeSoinsTemp ft = listeFichesDeSoinsTemporaire(conn, dpit.getIPP()).get(i);
            FicheDeSoins f = new FicheDeSoins(ft.getDateHeure());
            f.setActe(ft.getActe());
            f.setDPI(dpi);
            if(ft.getpH() != null){ //si c'est un PH sur la fiche de soins
                f.setpH(ft.getpH());
            }
            else if(ft.getInfirmier() != null){ //si c'est un infirmier sur la fiche de soins
                f.setInfirmier(ft.getInfirmier());
            }
            creerFicheDeSoins(conn, f);
        }
        //PRESCRIPTIONS
        for (int i = 0 ; i < listePrescriptionTemporaire(conn, dpit.getIPP()).size() ; i++){
            PrescriptionTemp pt = listePrescriptionTemporaire(conn, dpit.getIPP()).get(i);
            Prescription p = new Prescription(pt.getDateHeure(), pt.getObservation(), pt.getTypeExamen(), pt.getMedicament());
            p.setDPI(dpi);
            p.setpH(pt.getpH());
            creerPrescription(conn, p);
        }
        //EXAMENS
        for (int i = 0 ; i < listeExamensTemporaire(conn, dpit.getIPP()).size() ; i++){
            ExamenTemp et = listeExamensTemporaire(conn, dpit.getIPP()).get(i);
            Examen e = new Examen(et.getType_examen(), et.getResultats(), et.getDateHeure());
            e.setDPI(dpi);
            e.setPh(et.getPh());
            creerExamen(conn, e);
        }
        
        //Suppression des données
        //Suppression des fiches dans FichesDeSoins_temporaire
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("DELETE FROM FichesDeSoins_temporaire WHERE IPP = ?");
        stmt.setString(1, dpit.getIPP());
        stmt.executeUpdate();
        stmt.close();
        //Suppression des prescriptions dans Prescription_temporaire
        PreparedStatement stmtp = null;
        stmtp = conn.prepareStatement("DELETE FROM Prescription_temporaire WHERE IPP = ?");
        stmtp.setString(1, dpit.getIPP());
        stmtp.executeUpdate();
        stmtp.close();
        //Suppression des examens dans Examen_temporaire
        PreparedStatement stmte = null;
        stmte = conn.prepareStatement("DELETE FROM Examen_temporaire WHERE IPP = ?");
        stmte.setString(1, dpit.getIPP());
        stmte.executeUpdate();
        stmte.close();
    }
}       
