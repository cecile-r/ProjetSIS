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
        ResultSet rs = stmt.executeQuery("SELECT * FROM PH WHERE nom_PH = '" + nom + "'");

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
    
    
    //Renvoie le vecteur des PH en fonction du service
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
    
    
    //Renvoie la liste des DPI ouverts -> patients dans le CHU
    //VALIDE
    public static List<DPI> getListeDPI(Connection conn) throws SQLException {
        List<DPI> listeDPIOuvert = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI ouverts -> Un patient est au CHU ssi il a une localisation
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI NATURAL JOIN Localisation JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) WHERE (service_responsable IS NOT NULL)");
        
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI NATURAL JOIN Localisation WHERE (service_responsable IS NOT NULL)");
        
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI NATURAL JOIN Localisation JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) WHERE (service_responsable IS NOT NULL) AND (nom_DPI = '" + nom + "')");
        
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI NATURAL JOIN Localisation WHERE (service_responsable IS NOT NULL) AND (nom_DPI = '" + nom + "')");
        
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI NATURAL JOIN Localisation JOIN Medecin_traitant USING(telephone_medecin_traitant, IPP) WHERE (service_responsable = '" + service + "') AND (nom_DPI = '" + nom + "')");
        
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
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI NATURAL JOIN Localisation WHERE (service_responsable = '" + service + "') AND (nom_DPI = '" + nom + "')");
        
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
}