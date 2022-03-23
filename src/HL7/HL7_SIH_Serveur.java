/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import nf.*;
import static database.RequetesBDDPI.creerExamen;
import static database.RequetesBDDPI.getDPI;
import static database.RequetesBDProfessionnels.getPH;
import static database.RequetesBDDPI.getIPPPatient;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class HL7_SIH_Serveur {

    private Patient patient; //HL7
    private Action action; // HL7
    private int nbr; // HL7
    private Message message; //Recep HL7
    private ServeurHL7 c; // Connexion HL7
    Connection conn; // Connexion BD

    /**
     * Creates new form HL7_SIS
     */
    public HL7_SIH_Serveur(Connection conn, int port) throws ClassNotFoundException, SQLException {
        this.patient = null;
        this.message = null;
        this.conn = conn;
        this.c = new ServeurHL7();
        this.c.connection(port);
    }

    /**
     * récupère les données dans HL7 venant du SIR creer un nouvel examen dans
     * la base données
     */
    public void recuperationDonnees() {

        //Timer t = new Timer();
        //TimerTask task = new TimerTask() {
        int i = 1;
        Patient patient;
        Message message;
        
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        run();

    }

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
        if (SexeOK.equals('F')) {
            sexe = Sexe.femme;
        } else if (SexeOK.equals("M")) {
            sexe = Sexe.homme;
        } else {
            sexe = Sexe.autre;
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
            String ipp;
            ipp = getIPPPatient(conn, nomOK, prenomOK, NaissanceOK);
            DPI dpi = getDPI(conn, ipp);
            PH ph = getPH(conn, MedRefOK);
            Examen e = new Examen(te, CROK, dh);
            e.setDPI(dpi);
            e.setPh(ph);
            creerExamen(conn, e);
        } catch (SQLException ex) {
            Logger.getLogger(HL7_SIH_Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Fin ajout BD 
        this.patient = null;
        this.message = null;
        
        /*if (i == 5) {
                    t.cancel();
                }
                i = i + 1;*/
        c.fermer();
    }
    //};

    //System.out.println("Current time: " + new Date());
    // temps de lecture = 1 min ou 2, il faut le changer pour que ce soit permanent
    //t.schedule(task, 5000, 1000);
}
