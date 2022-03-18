/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import nf.*;
import database.DatabaseAccessProperties;
import static database.RequetesBDDPI.creerExamen;
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
    public HL7_SIH(Connection conn) throws ClassNotFoundException, SQLException {
        this.patient = null;
        this.message = null;
        this.conn = conn;
        c = new ServeurHL7();
        c.connection(4445);
    }

    public void envoyerDonnees(DPI dpi) {
        creerPatient(dpi);
        setValPatient(dpi);
        this.action = new Action("AD", "AD", "Madiou"); // normalement la 2ème et 3ème entrée variables

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

    private void creerPatient(DPI dpi) { // peut etre une erreur ici
        Integer id = Integer.parseInt(dpi.getIPP());
        this.patient = new Patient(id, dpi.getNom(), 'U');

    }

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
    }

    public static java.sql.Date convertDateJavaEnSQL(Date d) {
        Date dateReelle = new Date(d.getYear() - 1900, d.getMonth() - 1, d.getDate());
        java.sql.Date dateSQL = new java.sql.Date(dateReelle.getTime());
        return dateSQL;
    }

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
                if (SexeOK == "Female") {
                    SexeOK = "femme";
                }
                if (SexeOK == "Male") {
                    SexeOK = "homme";
                } else {
                    SexeOK = "autre";
                }
                //Affichage BD SIH
                java.sql.Date NaissanceOK = convertDateJavaEnSQL(patient.getBirth());

                ////////////////////////////////CREER EXAMEN //////////////////
                TypeExamen te;
                if(ExamOK.equals("Scanner")){
                    te = TypeExamen.scanner;
                }else if(ExamOK.equals("IRM")){
                    te = TypeExamen.imagerie_par_resonance_magnetique;
                }else{
                    te= TypeExamen.radiologie;
                }
                String resultats = CROK;
                /*
                Examen e =new Examen(te,resultats,dh);
                e.setDPI(dpi);
                e.setPh(ph);
                creerExamen(conn,e);
                */
                
                
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

