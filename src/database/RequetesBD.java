package database;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Vector;
import nf.*;

/*
* To change this license header, choose License Headers in 
Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
/**
 *
 * @author catherineberrut
 */
public class RequetesBD {

    /**
     * Afficher toutes les informations sur tous les spectacles.
     *
     * @param conn connexion à la base de données
     * @throws SQLException en cas d'erreur d'accès à la base de donn�es
     */
    //Test pour la BD
    //VALIDE
    public static void afficherNomPrenom(Connection conn, String IPP) throws SQLException {
        // java.math.BigDecimal
        Statement stmt = conn.createStatement();
        String requete = "SELECT * FROM DPI WHERE IPP = '" + IPP + "' ";
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
        String requete = "SELECT * FROM DPI WHERE IPP = '" + IPP + "' ";
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
    
    

    ////////////////////////////////////////////////////////////////////////////
    
    //Renvoie la liste des PH
    //VALIDE
    public static List<PH> getListePH(Connection conn) throws SQLException {
        List<PH> listePH = new ArrayList();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp"), rs.getString("telephone"), rs.getString("specialite"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }
    
    
    //Renvoie la liste des PH simplifiés
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH WHERE nom_PH = '" + nom + "'");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }
    
    
    //Renvoie la liste des PH simplifiés en fonction du nom
    //VALIDE
    public static Vector getVectPHNom(Connection conn, String nom) throws SQLException {
        Vector vPHTotal = new Vector();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH WHERE nom_PH = '" + nom + "'");

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
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH WHERE service_PH = '" + service + "'");

        while (rs.next()) {
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            listePH.add(ph);
        }

        rs.close();
        stmt.close();
        return listePH;
    }
    
    
    //Renvoie la liste des PH simplifiés en fonction du service
    //VALIDE
    public static Vector getVectPHService(Connection conn, String service) throws SQLException {
        Vector vPHTotal = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection de tous les PH
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH WHERE service_PH = '" + service + "'");

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
    
    
   

    
}
