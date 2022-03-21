/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import static database.RequetesBDConversion.convertDateHeureJavaEnTimestampSQL;
import static database.RequetesBDConversion.convertDateJavaEnSQL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import nf.DPI;
import nf.DPITemporaire;
import nf.Lit;
import nf.Localisation;
import nf.MedecinTraitant;
import nf.PH;
import nf.Service;
import nf.Sexe;
import static database.RequetesBDConversion.convertDateJavaEnSQL;
import static database.RequetesBDConversion.convertTimestampSQLenJava;
import static database.RequetesBDConversion.toStringTimestampJAVA;
import static database.RequetesBDDPI.creerActe;
import static database.RequetesBDDPI.creerExamen;
import static database.RequetesBDDPI.creerFicheDeSoins;
import static database.RequetesBDDPI.creerPrescription;
import nf.Examen;
import nf.ExamenTemp;
import nf.FicheDeSoins;
import nf.FicheDeSoinsTemp;
import nf.Prescription;
import nf.PrescriptionTemp;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDUrgences {

    //Renvoie le nombre de DPI temporaire
    //VALIDE
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

    //Créé un DPI temporaire 
    //VALIDE
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
            stmt2.setString(5, dpit.getPh().getIdPH());
            stmt2.executeUpdate();
            stmt2.close();
        } else {
            System.out.println("Ce patient est déjà dans le service des urgences.");
        }
        rs.close();
        stmt.close();

    }

    //Renvoie la liste des DPI temporaires -> patients aux urgences
    //VALIDE
    public static List<DPITemporaire> getListeDPITemporaires(Connection conn) throws SQLException {
        List<DPITemporaire> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI temporaires
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "LEFT OUTER JOIN PH USING(idPH)");

        while (rs.next()) {
            System.out.println("ok");
            Date d = new Date(rs.getDate("date_de_naissance_temp").getTime());
            System.out.println("ok2");
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            System.out.println("ok3");
            DPITemporaire dpi = new DPITemporaire(rs.getString("IPP"), rs.getString("nom_DPI_temp"), rs.getString("prenom_DPI_temp"), d, ph);
            listeDPI.add(dpi);
            System.out.println("ok4");
        }

        rs.close();
        stmt.close();
        return listeDPI;
    }

    //Renvoie le vecteur des DPI temporaires
    //VALIDE
    public static Vector getVectorDPITemporaires(Connection conn) throws SQLException {
        Vector vDPIOuvert = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection des DPI temporaires
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "LEFT OUTER JOIN PH USING(idPH)");

        while (rs.next()) {
            Vector vParDPI = new Vector();
            Date d = new Date(rs.getDate("date_de_naissance_temp").getTime());
            vParDPI.add(rs.getString("IPP"));
            vParDPI.add(rs.getString("nom_DPI_temp"));
            vParDPI.add(rs.getString("prenom_DPI_temp"));
            vParDPI.add(d);
            vParDPI.add(rs.getString("idPH"));
            vDPIOuvert.add(vParDPI);
        }

        rs.close();
        stmt.close();
        return vDPIOuvert;
    }

    //Renvoie true si le dpi existe déjà, sinon renvoie false
    //VALIDE
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

    //Fusionne les DPI si le DPI des urgences existe déjà au CHU
    //
    public static void fusionDPI(Connection conn, DPITemporaire dpit) throws SQLException {
        //FICHES DE SOINS
        PreparedStatement stmtf = null;
        stmtf = conn.prepareStatement("SELECT * FROM FichesDeSoins_temporaire "
                + "WHERE IPP = ?");
        stmtf.setString(1, dpit.getIPP());
        ResultSet rsf = stmtf.executeQuery();
        while (rsf.next()) { //Parcours des fiches de soins temporaires
            //Ajout de la fiche de soin dans la table FichesDeSoins
            FicheDeSoins f = new FicheDeSoins(convertTimestampSQLenJava(rsf.getTimestamp("dateHeure_FichesDeSoins")));
            
            //Suppression de la fiche dans FichesDeSoins_temporaire
        }

        //PRESCRIPTIONS
        PreparedStatement stmtp = null;
        stmtp = conn.prepareStatement("SELECT * FROM Prescription "
                + "WHERE IPP = ?");
        stmtp.setString(1, dpit.getIPP());
        ResultSet rsp = stmtp.executeQuery();
        while (rsp.next()) { //parcours des prescriptions du DPI temporaire
            //Modifier l'IPP du patient sur la prescription pour le remplacer par son IPP réel
            PreparedStatement stmt3 = null;
            stmt3 = conn.prepareStatement("UPDATE Prescription SET IPP = ? WHERE IPP = ?");
            stmt3.setString(1, rs.getString("IPP"));
            stmt3.setString(2, dpit.getIPP());
            stmt3.executeUpdate();
            stmt3.close();
        }

        //EXAMENS
        PreparedStatement stmte = null;
        stmte = conn.prepareStatement("SELECT * FROM Examen "
                + "WHERE IPP = ?");
        stmte.setString(1, dpit.getIPP());
        ResultSet rse = stmte.executeQuery();
        while (rse.next()) { //parcours des prescriptions du DPI temporaire
            //Modifier l'IPP du patient sur la prescription pour le remplacer par son IPP réel
            PreparedStatement stmt4 = null;
            stmt4 = conn.prepareStatement("UPDATE Examen SET IPP = ? WHERE IPP = ?");
            stmt4.setString(1, rs.getString("IPP"));
            stmt4.setString(2, dpit.getIPP());
            stmt4.executeUpdate();
            stmt4.close();
        }

        //Suppression du DPI temporaire
        PreparedStatement stmt2 = null;
        stmt2 = conn.prepareStatement("DELETE FROM DPI_temporaire WHERE IPP = ?");
        stmt2.setString(1, dpit.getIPP());
        stmt2.executeUpdate();
        stmt2.close();
    }

    
    

    else { //Le DPIT n'existe pas -> création d'un DPI
            //creer nouveau DPI
        }
        
    rs.close ();

    stmt.close ();
}

//Creer une fiche de soins d'urgence (temporaire) et l'ajouter dans la base de données
//
public static void creerFicheDeSoinsTemp(Connection conn, FicheDeSoinsTemp fiche) throws SQLException {

        for (int i = 0; i < fiche.getActe().size(); i++) {
            PreparedStatement stmt2 = null;
            String ts = toStringTimestampJAVA(convertDateHeureJavaEnTimestampSQL(fiche.getDateHeure()));
            Timestamp t = Timestamp.valueOf(ts);
            int a;//idActe

            if (fiche.getInfirmier() != null) {
                //Insertion dans la table Acte
                a = creerActe(conn, fiche.getActe().get(i));
                //Insertion dans la table FichesDeSoins_temporaire
                stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins_temporaire values(?,?,?,?,?)");
                stmt2.setString(1, fiche.getDPI().getIPP());
                stmt2.setTimestamp(2, t);
                stmt2.setInt(3, a);//idActe
                stmt2.setString(4, null);
                stmt2.setString(5, fiche.getInfirmier().getIdInfirmiere());
            } else if (fiche.getpH() != null) {
                //Insertion dans la table Acte
                a = creerActe(conn, fiche.getActe().get(i));
                //Insertion dans la table FichesDeSoins_temporaire
                stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins_temporaire values(?,?,?,?,?)");
                stmt2.setString(1, fiche.getDPI().getIPP());
                stmt2.setTimestamp(2, t);
                stmt2.setInt(3, a);//idActe
                stmt2.setString(4, fiche.getpH().getIdPH());
                stmt2.setString(5, null);
            } else {
                //Insertion dans la table Acte
                a = creerActe(conn, fiche.getActe().get(i));
                //Insertion dans la table FichesDeSoins_temporaire
                stmt2 = conn.prepareStatement("INSERT INTO FichesDeSoins_temporaire values(?,?,?,?,?)");
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
        
    //Creer une prescription d'urgence (temporaire) et l'ajouter dans la base de données
    //
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
    
    //Creer un examen d'urgence (temporaire) et l'ajouter dans la base de données
    //
    public static void creerExamenTemp(Connection conn, ExamenTemp exam) throws SQLException{
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
}
