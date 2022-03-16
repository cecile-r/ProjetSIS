/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author cecilerichard
 */
public class DPITemporaire {
    private String IPP;
    private String nom;
    private String prenom;
    private Date date_naissance;
    private PH ph;
    private List <FicheDeSoins> liste_f;
    private List <Prescription> liste_p;
    private List <Examen> liste_e;
    
    public DPITemporaire(String IPP, String nom, String prenom, Date date_naissance, PH ph){
        this.IPP = IPP;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        this.ph = ph;
        List <FicheDeSoins> liste_f = new ArrayList();
        List <Prescription> liste_p = new ArrayList();
        List <Examen> liste_e = new ArrayList();
    }

    @Override
    public String toString (){
        String ch = "";
        ch = ch + "Nom : "+nom+"\n";
        ch = ch + "Prénom : "+prenom+"\n";
        ch = ch + "Date de naissance : "+date_naissance+"\n";
        return ch;
    }
    
    /**
     * @return the IPP
     */
    public String getIPP() {
        return IPP;
    }

    /**
     * @param IPP the IPP to set
     */
    public void setIPP(String IPP) {
        this.IPP = IPP;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the date_naissance
     */
    public Date getDate_naissance() {
        return date_naissance;
    }

    /**
     * @param date_naissance the date_naissance to set
     */
    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    /**
     * @return the ph
     */
    public PH getPh() {
        return ph;
    }

    /**
     * @param ph the ph to set
     */
    public void setPh(PH ph) {
        this.ph = ph;
    }

    /**
     * @return the liste_f
     */
    public List <FicheDeSoins> getListe_f() {
        return liste_f;
    }

    /**
     * @param liste_f the liste_f to set
     */
    public void setListe_f(List <FicheDeSoins> liste_f) {
        this.liste_f = liste_f;
    }

    /**
     * @return the liste_p
     */
    public List <Prescription> getListe_p() {
        return liste_p;
    }

    /**
     * @param liste_p the liste_p to set
     */
    public void setListe_p(List <Prescription> liste_p) {
        this.liste_p = liste_p;
    }

    /**
     * @return the liste_e
     */
    public List <Examen> getListe_e() {
        return liste_e;
    }

    /**
     * @param liste_e the liste_e to set
     */
    public void setListe_e(List <Examen> liste_e) {
        this.liste_e = liste_e;
    }
    
}
