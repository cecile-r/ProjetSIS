/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HL7;

import nf.*;
import java.sql.Connection;
import java.sql.SQLException;
import library.interfaces.Action;
import library.interfaces.ClientHL7;
import library.interfaces.MessageInterface;
import library.interfaces.Patient;
import library.interfaces.PatientLocation;
import library.structure.groupe.messages.Message;
import static nf.DateHeure.convertirDateHeuretoString;

/**
 *
 * @author garci
 */
public class HL7_SIH_Client {

    private Patient patient; //HL7
    private Action action; // HL7
    private int nbr; // HL7
    private Message message; //Recep HL7
    Connection conn; // Connexion BD

    /**
     * Creates new form HL7_SIS
     */
    public HL7_SIH_Client(Connection conn) throws ClassNotFoundException, SQLException {
        this.patient = null;
        this.message = null;
        this.conn = conn;
        //c = new ServeurHL7();
        //c.connection(port);
    }

    /**
     * envoie les données nécessaires de la prescription dans HL7 pour le SIR
     *
     * @param p
     */
    public void envoyerDonnees(Prescription p) {
        ClientHL7 c = new ClientHL7();
        c.connexion("localhost", 6504);//4444
        
        //INFOS PATIENT
        creerPatient(p.getDPI());
        setValPatient(p.getDPI());
        this.action = new Action("AD", "AD", "Madiou"); // normalement la 2ème et 3ème entrée variables

        //INFOS PRESCRIPTION
        setInfosPrescription(p);

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
     *
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
        observation = observation.replaceAll("\n", "*");
        assignedLocation.setStatus(observation);

        //Floor --> adresse
        String adresse = p.getDPI().getAdresse();
        adresse = adresse.replaceAll("\n", "*");
        assignedLocation.setFloor(adresse);
    }

    /**
     * créer le patient dans HL7 (id,nom,classe)
     *
     * @param DPI
     */
    private void creerPatient(DPI dpi) { // peut etre une erreur ici
        //Integer id = Integer.parseInt(dpi.getIPP());
        Integer id = 1;
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


}
