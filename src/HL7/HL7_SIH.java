/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import UI.Ajout_examen;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import nf.*;
import database.DatabaseAccessProperties;
import static database.RequetesBDDPI.creerExamen;
import static database.RequetesBDDPI.getDPI;
import static database.RequetesBDProfessionnels.getPH;
import static database.RequetesBDDPI.getIPPPatient;
import database.SQLWarningsExceptions;
import javax.swing.JLabel;
import java.sql.Connection;
import java.awt.*;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import library.interfaces.Action;
import library.interfaces.ClientHL7;
import library.interfaces.MessageInterface;
import library.interfaces.Patient;
import library.interfaces.PatientLocation;
import library.interfaces.ServeurHL7;
import library.structure.groupe.messages.Message;
import static nf.DateHeure.convertirDateHeuretoString;
import static nf.DateHeure.convertirStringtoDateHeure;

/**
 *
 * @author garci
 */
public class HL7_SIH {

    private Patient patient; //HL7
    private Action action; // HL7
    private int nbr; // HL7
    private Message message; //Recep HL7
    final ServeurHL7 c; // Connexion HL7
    Connection conn; // Connexion BD

    /**
     * Creates new form HL7_SIS
     */
    public HL7_SIH(Connection conn,int port) throws ClassNotFoundException, SQLException {
        this.patient = null;
        this.message = null;
        this.conn = conn;
        c = new ServeurHL7();
        c.connection(port);
    }

    /**
     * envoie les données nécessaires de la prescription dans HL7 pour le SIR
     * @param Prescription 
     */
    public void envoyerDonnees(Prescription p) {
        //INFOS PATIENT
        creerPatient(p.getDPI());
        setValPatient(p.getDPI());
        this.action = new Action("AD", "AD", "Madiou"); // normalement la 2ème et 3ème entrée variables

        //INFOS PRESCRIPTION
        setInfosPrescription(p);

        // insertion du code du bouton connexion
        ClientHL7 c = new ClientHL7();
        c.connexion("localhost", 4444);
        switch (this.nbr) {
            case 0: {
                c.admit(patient);
                break;
            }
            case 1: {
                c.transPat(patient);
                break;
            }
            case 2: {
                c.endPat(patient);
                break;
            }
        }
        MessageInterface messageAck = c.getMsg();
        c = new ClientHL7();
    }

    /**
     * met les informations de la prescription dans HL7 pour le SIR
     * @param Prescription 
     */
    private void setInfosPrescription(Prescription p) {
        PatientLocation assignedLocation = new PatientLocation(this.patient);
        this.patient.setAssignedPatLocation(assignedLocation);
        //Lit => Type d'examen
        if (p.getTypeExamen() == TypeExamen.radiologie) {
            assignedLocation.setBed("Radiologie");
        } else if (p.getTypeExamen() == TypeExamen.imagerie_par_resonance_magnetique) {
            assignedLocation.setBed("IRM");
        } else if (p.getTypeExamen() == TypeExamen.scanner) {
            assignedLocation.setBed("Scanner");
        } else {
            assignedLocation.setBed("");
        }

        //Room --> date_presciption
        assignedLocation.setRoom(convertirDateHeuretoString(p.getDateHeure()));

        //Status --> observation
        String observation = p.getObservation();
        observation = observation.replaceAll("\n","*");
        assignedLocation.setStatus(observation);
        
        //Floor --> adresse
        String adresse = p.getDPI().getAdresse();
        adresse = adresse.replaceAll("\n","*");
        assignedLocation.setFloor(adresse);
    }

    /**
     * créer le patient dans HL7 (id,nom,classe)
     * @param DPI 
     */
    private void creerPatient(DPI dpi) { // peut etre une erreur ici
        Integer id = Integer.parseInt(dpi.getIPP());
        this.patient = new Patient(id, dpi.getNom(), 'U');

    }

    /**
     * met les informations du patient dans HL7 pour le SIR Prenom, Sexe, Date
     * de naissance
     *
     * @param DPI
     */
    private void setValPatient(DPI dpi) {
        //prenom
        this.patient.setFirstName(dpi.getPrenom());
        //Sexe
        if (dpi.getSexe() == Sexe.femme) {
            this.patient.setSex('F');
        } else if (dpi.getSexe() == Sexe.homme) {
            this.patient.setSex('M');
        }
        //Date de naissance
        this.patient.setBirth(dpi.getDateNaissance());
        //locPat.setBed(""); //voir comment ajouter type examen
        //locPat.setStatus(""); //voir comment ajouter l'observation
    }

    /**
     * récupère les données dans HL7 venant du SIR
     * creer un nouvel examen dans la base données
     */
    public void recuperationDonnees() {

        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            int i = 1;
            private Patient patient;
            private Message message;

            public void run() {

                c.ecoute();
                String messageHL7 = c.protocole();

                System.out.println("Reçu :" + messageHL7);
                this.patient = c.getPatient();
                this.message = c.getMessage();

                //Attention, ici il y avait un if sur la condition  du sexe cf doc donné
                PatientLocation locPat = patient.getAssignedPatLocation();

                //Ajout BD
                String nomOK = patient.getFamillyName();
                String prenomOK = patient.getFirstName();
                String IPPOK = String.valueOf(patient.getID());
                String SexeOK = patient.getSex();
                String MedRefOK = locPat.getRoom(); //numero medecin
                String ExamOK = locPat.getBed(); //type examen
                String CROK = locPat.getStatus(); //CR
                String dateHeure = locPat.getFloor();
                DateHeure dh = convertirStringtoDateHeure(dateHeure);
                Sexe sexe;
                if (SexeOK == "F") {
                    sexe= Sexe.femme;
                }
                if (SexeOK == "M") {
                    sexe= Sexe.homme;
                } else {
                    sexe= Sexe.autre;
                }
                //Affichage BD SIH
                //java.sql.Date NaissanceOK = convertDateJavaEnSQL(patient.getBirth());
                Date NaissanceOK = patient.getBirth();

                ////////////////////////////////CREER EXAMEN //////////////////
                TypeExamen te;
                if (ExamOK.equals("Scanner")) {
                    te = TypeExamen.scanner;
                } else if (ExamOK.equals("IRM")) {
                    te = TypeExamen.imagerie_par_resonance_magnetique;
                } else {
                    te = TypeExamen.radiologie;
                }

                try {
                    String ipp = getIPPPatient(conn, nomOK,prenomOK,NaissanceOK);
                    DPI dpi = getDPI(conn,ipp); 
                    PH ph = getPH(conn, MedRefOK); 
                    Examen e =new Examen(te,CROK,dh); 
                    e.setDPI(dpi);
                    e.setPh(ph);
                    creerExamen(conn,e);

                } catch (SQLException ex) {
                    Logger.getLogger(HL7_SIH.class.getName()).log(Level.SEVERE, null, ex);
                }

                // Fin ajout BD 
                this.patient = null;
                this.message = null;
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                if (i == 5) {
                    t.cancel();
                }
                i = i + 1;
            }
        };

        //System.out.println("Current time: " + new Date());
        // temps de lecture = 1 min ou 2, il faut le changer pour que ce soit permanent
        t.schedule(task, 5000, 1000);
    }

}
