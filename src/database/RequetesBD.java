package database;


import java.sql.Connection;
//import java.sql.Date;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

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
    public static void nbSpectacles(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT count(*) FROM LesRepresentations");

        while (rs.next()) {
            System.out.println("Nombre de representations : " + rs.getInt(1));
            System.out.println("\n");
        }
        // Close the result set, statement and the connection 
        rs.close();
        stmt.close();
    }

    public static void spectacles(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesRepresentations");
        while (rs.next()) {
            System.out.print("numS : " + rs.getInt(1) + " ----> ");
            System.out.println("dateRep : " + rs.getString(2));
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

    // Question 2
    public static void afficherNomSpectacle(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        String numero;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "Veuillez saisir un numéro de spectacle : ");
        numero = sc.nextLine();

        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles where numS=" + numero);
        if (rs.next() && rs.getString(1).equals(numero)) {
            System.out.println("Le numéro de spectacle " + numero + " correspond à : " + rs.getString(2));
        } else {
            System.out.println("erreur, ce numéro ne correspond à aucun spectacle");
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

    // Question 3
    public static void afficherNumeroSpectacle(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        String nomSpec;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "Veuillez saisir un nom de spectacle : ");
        nomSpec = sc.nextLine();

        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles where nomS='" + nomSpec + "'");
        if (rs.next() && rs.getString(2).equals(nomSpec)) {
            System.out.println("Le nom du spectacle " + nomSpec + " correspond au numéro : " + rs.getString(1));
        } else {
            System.out.println("erreur, ce nom ne correspond à aucun spectacle");
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

    // Question 4
    public static void EnsRepSpect(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        String numero;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "Veuillez saisir un numéro de spectacle : ");
        numero = sc.nextLine();

        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesSpectacles LEFT OUTER JOIN LesRepresentations USING (numS) WHERE numS=" + numero);
        if (rs.next()) {
            if (rs.getString(3) != null) {
                System.out.print("Le numéro de spectacle " + rs.getString(1));
                System.out.print(", représente le spectacle " + rs.getString(2) + ". \n");
                System.out.print("Les dates de représentation de ce spectacle sont les suivantes : \n" + rs.getString(3) + "\n");
                while (rs.next()) {
                    System.out.print(rs.getString(3) + "\n");
                }
            } else {
                System.out.println("Aucune représentation n'est prévue pour ce spectacle.");
            }
        } else {
            System.out.println("Ce spectacle n'existe pas.");
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

    // Question 5
    public static void AjouterDateRep(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        String numero;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "Veuillez saisir un numéro de spectacle : ");
        numero = sc.nextLine();
        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesRepresentations LEFT OUTER JOIN LesSpectacles USING (numS) WHERE numS=" + numero);

        if (rs.next()) {
            String dateRep;
            Scanner sc2 = new Scanner(System.in);
            System.out.println("\n" + "Veuillez saisir une date de représentation : ");
            dateRep = sc2.nextLine();
            //Date date = new Date(dateRep);
            // Execute the query
            ResultSet rsAdd = stmt.executeQuery("SELECT * FROM LesRepresentations LEFT OUTER JOIN LesSpectacles USING (numS) where numS=" + numero + "and dateRep= " + dateRep);

            if (rsAdd.next()) {
                System.out.println("Cette date de représentation est déjà dans la BD");
            } else {
                stmt.executeQuery("insert into LESREPRESENTATIONS values (" + numero + " , '" + dateRep + "') ");
                System.out.println("La date de représentation a été ajoutée à la BD ! ");
            }
        } else {
            System.out.println("Ce spectacle n'existe pas.");
        }
        // Close the result set, statement and the connection
        rs.close();
        stmt.close();
    }

    //Question 6
    public static void EnleverDateRep(Connection conn) throws SQLException {

    }

    //Question 7
    public static void AjouterUnSpectacle(Connection conn) throws SQLException {
        // Get a statement from the connection
        Statement stmt = conn.createStatement();
        String nomSpect;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + "Nom du spectacle que vous souhaitez insérer : ");
        nomSpect = sc.nextLine();

        // Execute the query
        ResultSet rs = stmt.executeQuery("SELECT * FROM LesRepresentations LEFT OUTER JOIN LesSpectacles USING (nomS) WHERE nomS=" + nomSpect);

    }

    //Question 8
    public static void EnleverSpectacle(Connection conn) throws SQLException {

    }

////////////////////////////////////////////////////////////////////////////////////////////////////
    // Méthodes Cécile
    // Question 1 : A TESTER
    public static void affichageSpectacles(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM LESSPECTACLES");

        while (rs.next()) {
            System.out.print("Numéro spectacle : " + rs.getInt(1) + " ");
            System.out.println("Nom spectacle : " + rs.getString(2) + " ");
        }

        rs.close();
        stmt.close();
    }

    //Question 2 : A TESTER
    public static void afficherNomSpectacle(Connection conn, int numS) throws SQLException {
        // java.math.BigDecimal
        Statement stmt = conn.createStatement();
        String requete = "SELECT nomS FROM LESSPECTACLES WHERE numS = '" + numS + "' ";
        ResultSet rs = stmt.executeQuery(requete);

        if (rs.next()) {
            System.out.print("Nom du spectacle : " + rs.getString("nomS"));
        } else {
            System.out.print("Ce numéro ne correspond à aucun spectacle.");
        }

        rs.close();
        stmt.close();
    }

    //Question 3 : A TESTER
    public static void afficherNumeroSpectacle(Connection conn, String nomSpectacle) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT numS FROM LESSPECTACLES WHERE nomS = '" + nomSpectacle + "' ";
        ResultSet rs = stmt.executeQuery(requete);

        if (rs.next()) {
            System.out.print("Numéro du spectacle : " + rs.getString("numS"));
        } else {
            System.out.print("Ce nom ne correspond à aucun spectacle.");
        }

        rs.close();
        stmt.close();
    }

    //Question 4 : A TESTER
    public static void afficherRepresentations(Connection conn, int numeroSpectacle) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT * FROM LESSPECTACLES natural join LESREPRESENTATIONS WHERE numS = '" + numeroSpectacle + "' ";
        ResultSet rs = stmt.executeQuery(requete);

        if (rs.next()) {
            System.out.println("OK");
            System.out.print("Nom du spectacle : " + rs.getString("nomS"));
            if (rs.getString("dateRep") != null) {
                System.out.print("Représentations : ");
                System.out.println(rs.getString("dateRep") + "\n");
                while (rs.next()) {
                    System.out.println(rs.getString("dateRep") + "\n");
                }
            } else {
                System.out.print("Aucune représentation prévue pour ce sectacle.");
            }
        } else {
            System.out.print("Ce numéro ne correspond à aucun spectacle.");
        }

        rs.close();
        stmt.close();
    }

    //Question 5 : A TESTER
    public static void ajouterRepresentation(Connection conn, int numeroSpectacle, Date newRep) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT dateRep FROM LESREPRESENTATIONS WHERE numS = " + numeroSpectacle;
        ResultSet rs = stmt.executeQuery(requete);
        boolean dateRepDansRequete = false;

        while (rs.next()) {
            System.out.println("ok");
            System.out.println(rs.getDate("dateRep").toString());
            System.out.println(new java.sql.Date(newRep.getTime()).toString());

            if (rs.getDate("dateRep").toString().equals(new java.sql.Date(newRep.getTime()).toString())) {
                System.out.println(rs.getDate("dateRep").toString());
                System.out.println(new java.sql.Date(newRep.getTime()).toString());
                dateRepDansRequete = true;
                System.out.print("Cette représentation existe déjà pour ce spectacle.");
            }
        }
        if (!dateRepDansRequete) {
            rs = stmt.executeQuery("insert into LESREPRESENTATIONS values ('" + numeroSpectacle + "', TO_DATE('" + new java.sql.Date(newRep.getTime()).toString() + "','YYYY-MM-DD'))");
        }

        rs.close();

        stmt.close();
    }

//Question 6 : A TESTER
    public static void enleverRepresentation(Connection conn, int numeroSpectacle, String rep) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT dateRep FROM LESREPRESENTATIONS WHERE numS = '" + numeroSpectacle + "' ";
        ResultSet rs = stmt.executeQuery(requete);
        boolean dateRepDansRequete = false;

        if (rs.next()) {
            while (rs.next() && !dateRepDansRequete) {
                if (rs.getString(1) == rep) {
                    dateRepDansRequete = true;
                    rs = stmt.executeQuery("delete from LESREPRESENTATIONS where (numS = numeroSpectacle) and (dateRep = rep)");
                }
            }
        } else {
            System.out.print("Ce numéro ne correspond à aucun spectacle.");
        }

        rs.close();
        stmt.close();
    }

    //Question 7 : A TESTER
    public static void ajouterSpectacle(Connection conn, String numeroSpectacle, String nomSpectacle) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT numS FROM LESSPECTACLES WHERE numS = '" + numeroSpectacle + "' ";
        ResultSet rs = stmt.executeQuery(requete);
        boolean numSDansRequete = false;

        while (rs.next() && !numSDansRequete) {
            if (rs.getString(1) == numeroSpectacle) {
                numSDansRequete = true;
                System.out.print("Ce spectacle existe déjà.");
            }
        }
        if (!numSDansRequete) {
            rs = stmt.executeQuery("insert into LESSPECTACLES values ('" + numeroSpectacle + "', '" + nomSpectacle + "', '" + null + "') ");
        }

        rs.close();
        stmt.close();
    }

    //Question 8 : A TESTER
    public static void enleverSpectacle(Connection conn, String numeroSpectacle) throws SQLException {
        Statement stmt = conn.createStatement();
        String requete = "SELECT numS FROM LESSPECTACLES WHERE numS = '" + numeroSpectacle + "' ";
        String requete2 = "SELECT numS FROM LESREPRESENTATIONS WHERE numS = '" + numeroSpectacle + "' ";
        ResultSet rs = stmt.executeQuery(requete);
        ResultSet rs2 = stmt.executeQuery(requete2);

        while (rs.next()) {
            rs = stmt.executeQuery("delete from LESSPECTACLES where (numS = '" + numeroSpectacle + "') ");
        }
        while (rs2.next()) {
            rs2 = stmt.executeQuery("delete from LESREPRESENTATIONS where (numS = '" + numeroSpectacle + "') ");
        }

        rs.close();
        stmt.close();
    }
    
    ///////////////////////////////////////////////////////
    
    
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
}
