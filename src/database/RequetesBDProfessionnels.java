/*
Fonctions relatives aux professionnels de santé
*/
package database;

import static database.RequetesBDConversion.convertDateJavaEnTimestampJavaMax;
import static database.RequetesBDConversion.convertDateJavaEnTimestampJavaMin;
import static database.RequetesBDConversion.convertDateSQLenJava;
import static database.RequetesBDConversion.convertTimestampSQLenJava;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import nf.DPI;
import nf.MedecinTraitant;
import nf.PH;
import nf.RendezVous;
import nf.Service;
import nf.Sexe;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDProfessionnels {
    
    ////////////////////////////////////////////////////////////////////////////
    //Fonctions PH
    
    /**
     * @param conn la connection établie pour la base de données
     * @return la liste de tous les PH
     * @throws java.sql.SQLException
     */
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

    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom du PH
     * @return la liste des PH en fonction du nom
     * @throws java.sql.SQLException
     */
    public static List<PH> getListePH(Connection conn, String nom) throws SQLException {
        List<PH> listePH = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE UPPER(nom_PH) LIKE UPPER('" + nom + "%')");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param service le service du PH
     * @return la liste des PH en fonction du service
     * @throws java.sql.SQLException
     */
    public static List<PH> getListePHService(Connection conn, String service) throws SQLException {
        List<PH> listePH = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection de tous les PH
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE UPPER(service_PH) LIKE UPPER('" + service + "%')");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }

    /**
     * @param conn la connection établie pour la base de données
     * @param ph le PH
     * @param date la date du rendez-vous
     * @return la liste des rendez-vous d'un PH pour un jour donné
     * @throws java.sql.SQLException
     */
    public static List<RendezVous> getListeRDVparJour(Connection conn, PH ph, Date date) throws SQLException{
        List<RendezVous> listeRDV = new ArrayList();
        //Sélection de tous les rdv d'un PH selon la date donnée en paramètre
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM RendezVous WHERE dateHeure_RDV BETWEEN ? AND ? AND idPH = ?");
        stmt.setTimestamp(1, convertDateJavaEnTimestampJavaMin(date));
        stmt.setTimestamp(2, convertDateJavaEnTimestampJavaMax(date));
        stmt.setString(3, ph.getIdPH());
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            RendezVous rdv = new RendezVous(convertTimestampSQLenJava(rs.getTimestamp("dateHeure_RDV")), rs.getString("remarque"));
            rdv.setpH(ph);
            //Sélection du patient concerné par le rendez vous
            Statement stmt2 = conn.createStatement();
            ResultSet rs2 = stmt2.executeQuery("SELECT * FROM DPI "
                + "LEFT OUTER JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) "
                + "WHERE IPP = '" + rs.getString("IPP") + "'");
            if (rs2.next()) {
                MedecinTraitant m = new MedecinTraitant(rs2.getString("mail"), rs2.getString("nom_medecin_traitant"), rs2.getString("prenom_medecin_traitant"), rs2.getString("telephone_medecin_traitant"));
                DPI dpi = new DPI(rs2.getString("IPP"), rs2.getString("nom_DPI"), rs2.getString("prenom_DPI"), convertDateSQLenJava(rs2.getDate("date_de_naissance")), Sexe.valueOf(rs2.getString("sexe_DPI")), rs2.getString("adresse_DPI"), rs2.getString("telephone_DPI"), m);
                rdv.setDPI(dpi);
            }
            listeRDV.add(rdv);
        }

        rs.close();
        stmt.close();
        return listeRDV;
    }
    
    /**
     * @param conn la connection établie pour la base de données
     * @param id l'identifiant
     * @return le PH à partir de l'identifiant rentré
     * @throws java.sql.SQLException
     */
    public static PH getPH(Connection conn, String id) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE UPPER(idPH) = UPPER('" + id + "')");

        if (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            rs.close();
            stmt.close();
            return ph;
        }
        else{
            System.out.println("Cet identifiant ne correspond à aucun PH.");
            rs.close();
            stmt.close();
            return null;
        }
    }
    
    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom du PH
     * @param prenom le prénom du PH
     * @return le PH à partir du nom et prénom entrés
     * @throws java.sql.SQLException
     */
    public static PH getPHNomPrenom(Connection conn, String nom, String prenom) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH "
                + "WHERE UPPER(nom_PH) = UPPER('" + nom + "') AND  UPPER(prenom_PH) = UPPER('" + prenom + "')");

        if (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            rs.close();
            stmt.close();
            return ph;
        }
        else{
            System.out.println("Cet identifiant ne correspond à aucun PH.");
            rs.close();
            stmt.close();
            return null;
        }
    }
    
    ////////////////////////////////////////////////////////////////////////////
    //Fonctions médecin traitant
    
    /**
     * @param conn la connection établie pour la base de données
     * @return la liste de tous les médecins traitants
     * @throws java.sql.SQLException
     */
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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param nom le nom de famille du médecin traitant
     * @return la liste des médecins traitants selon le nom de famille
     * @throws java.sql.SQLException
     */
    public static List<MedecinTraitant> getListeMTNom(Connection conn, String nom) throws SQLException {
        List<MedecinTraitant> listeMT = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT nom_medecin_traitant, prenom_medecin_traitant, mail, telephone_medecin_traitant "
                + "FROM Medecin_traitant "
                + "WHERE UPPER(nom_medecin_traitant) LIKE UPPER('" + nom + "%')");

        while (rs.next()) {
            MedecinTraitant m = new MedecinTraitant(rs.getString("mail"), rs.getString("nom_medecin_traitant"), rs.getString("prenom_medecin_traitant"), rs.getString("telephone_medecin_traitant"));
            listeMT.add(m);
        }

        rs.close();
        stmt.close();
        return listeMT;
    }
    
}
