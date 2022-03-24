/*
Fonctions relatives à la connexions
 */
package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static nf.Cryptage.hashPassword;
import nf.Infirmier;
import nf.PH;
import nf.SecretaireAdministrative;
import nf.SecretaireMedicale;
import nf.Service;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDConnexion {
    /**
     * @param conn la connection établie pour la base de données
     * @param id pour l'identifiant de l'utilisateur entré
     * @param mdp pour le mot de passe entré
     * @param statut pour définir le statut de l'utilisateur au sein du CHU
     * @return boolean pour vérifier si la connexion est correcte ou non
     */
    public static boolean verifyConnexion(Connection conn, String id, String mdp, String statut) throws SQLException {
        boolean correct = false;
        mdp = hashPassword(mdp).get();
        Statement stmt = conn.createStatement();
        //Requete ci dessous juste pour initialiser le resultset et pas faire buger la suite du programme, pas utilisée
        ResultSet rs = stmt.executeQuery("SELECT idPH from PH");

        if (statut == "PH") {
            rs = stmt.executeQuery("SELECT idPH, mdp_PH FROM PH "
                    + "WHERE (idPH = '" + id + "') AND (mdp_PH = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        } else if (statut == "SA") {
            rs = stmt.executeQuery("SELECT idSecretaireAd, mdp_SA FROM Secretaire_administrative "
                    + "WHERE (idSecretaireAd = '" + id + "') AND (mdp_SA = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        } else if (statut == "SM") {
            rs = stmt.executeQuery("SELECT idSecretaireMed, mdp_SM FROM Secretaire_medicale "
                    + "WHERE (idSecretaireMed = '" + id + "') AND (mdp_SM = '" + mdp + "')");
            if (rs.next()) {
                correct = true;
            }
        } else if (statut == "Inf") {
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

    /**
     * @param conn la connection établie pour la base de données
     * @param id pour l'identifiant du PH
     * @return l'instance PH dont on donne l'identifiant
     */
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

    /**
     * @param conn la connection établie pour la base de données
     * @param id pour l'identifiant de l'infirmier
     * @return l'instance Infirmier dont on donne l'identifiant
     */
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

    /**
     * @param conn la connection établie pour la base de données
     * @param id pour l'identifiant de la secrétaire médicale
     * @return l'instance SecretaireMedicale dont on donne l'identifiant
     */
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

    /**
     * @param conn la connection établie pour la base de données
     * @param id pour l'identifiant de la secrétaire administrative
     * @return l'instance SecretaireAdministrative dont on donne l'identifiant
     */
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
    
    /**
     * @param conn la connection établie pour la base de données
     * @param id pour l'identifiant de l'utilisateur
     * @return une chaine de caractère qui défini le statut d el'utilisateur au sein du CHU
     */
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
