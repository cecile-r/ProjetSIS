/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

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
    //
    public static void creerDPITemporaire(Connection conn, DPITemporaire dpit) throws SQLException {
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT * FROM DPI_temporaire WHERE nom_DPI_temp=? AND prenom_DPI_temp=? AND date_de_naissance_temp=?");
        stmt.setString(1, dpit.getNom());
        stmt.setString(2, dpit.getPrenom());
        stmt.setDate(3, convertDateJavaEnSQL(dpit.getDate_naissance()));
        ResultSet rs = stmt.executeQuery();

        if (!rs.next()) {
            PreparedStatement stmt2 = null;
            stmt2 = conn.prepareStatement("INSERT INTO DPI_temporaire values(?,?,?,?,?)");
            stmt2.setString(1, dpit.getIPP());
            stmt2.setString(2, dpit.getNom());
            stmt2.setString(3, dpit.getPrenom());
            stmt2.setDate(4, convertDateJavaEnSQL(dpit.getDate_naissance()));
            stmt2.setString(5, dpit.getPh().getIdPH());
            stmt2.executeUpdate();
            stmt2.close();
            
            Localisation locUrgences = new Localisation(Service.Urgences, Lit.P, 0, Service.Urgences);
            RequetesBDDPI.creerLocalisation(conn, dpit.getIPP(), locUrgences);
        } else {
            System.out.println("Ce patient est déjà dans le service des urgences.");
        }

    }

    //Renvoie la liste des DPI temporaires -> patients aux urgences
    //
    public static List<DPITemporaire> getListeDPITemporaires(Connection conn) throws SQLException {
        List<DPITemporaire> listeDPI = new ArrayList();
        Statement stmt = conn.createStatement();
        //Sélection des DPI temporaires
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "JOIN PH USING (idPH)");

        while (rs.next()) {
            System.out.println("ok");
            Date d = new Date(rs.getDate("date_de_naissance_temp").getTime());
            PH ph = new PH(rs.getString("idPH"), rs.getString("nom_PH"), rs.getString("prenom_PH"), Service.valueOf(rs.getString("service_PH")), rs.getString("mdp_PH"), rs.getString("telephone_PH"), rs.getString("specialite_PH"));
            DPITemporaire dpi = new DPITemporaire(rs.getString("IPP"), rs.getString("nom_DPI_temp"), rs.getString("prenom_DPI_temp"), d, ph);
            listeDPI.add(dpi);
        }

        rs.close();
        stmt.close();
        return listeDPI;
    }

    //Renvoie le vecteur des DPI ouverts
    //
    public static Vector getVectorDPITemporaires(Connection conn) throws SQLException {
        Vector vDPIOuvert = new Vector();
        Statement stmt = conn.createStatement();
        //Sélection des DPI temporaires
        ResultSet rs = stmt.executeQuery("SELECT * FROM DPI_temporaire "
                + "JOIN PH USING (idPH)");

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
}
