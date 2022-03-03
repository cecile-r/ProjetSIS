/*
Fonctions relatives aux professionnels de santé
*/
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import nf.MedecinTraitant;
import nf.PH;
import nf.Service;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDProfessionnels {
    
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
    
}
