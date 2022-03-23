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
    private List<FicheDeSoinsTemp> liste_f;
    private List<PrescriptionTemp> liste_p;
    private List<ExamenTemp> liste_e;

    public DPITemporaire(String IPP, String nom, String prenom, Date date_naissance) {
        this.IPP = IPP;
        this.nom = nom;
        this.prenom = prenom;
        this.date_naissance = date_naissance;
        List<FicheDeSoinsTemp> liste_f = new ArrayList();
        List<PrescriptionTemp> liste_p = new ArrayList();
        List<ExamenTemp> liste_e = new ArrayList();
    }

    /**
     * retourne une chaine de caractere avec les informations du patient
     *
     * @return nom, prenom, date de naissance
     */
    @Override
    public String toString() {
        String ch = "";
        ch = ch + "Nom : " + nom + "\n";
        ch = ch + "Prénom : " + prenom + "\n";
        ch = ch + "Date de naissance : " + date_naissance + "\n";
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
     * @return the liste_f
     */
    public List<FicheDeSoinsTemp> getListe_f() {
        return liste_f;
    }

    /**
     * @param liste_f the liste_f to set
     */
    public void setListe_f(List<FicheDeSoinsTemp> liste_f) {
        this.liste_f = liste_f;
    }

    /**
     * @return the liste_p
     */
    public List<PrescriptionTemp> getListe_p() {
        return liste_p;
    }

    /**
     * @param liste_p the liste_p to set
     */
    public void setListe_p(List<PrescriptionTemp> liste_p) {
        this.liste_p = liste_p;
    }

    /**
     * @return the liste_e
     */
    public List<ExamenTemp> getListe_e() {
        return liste_e;
    }

    /**
     * @param liste_e the liste_e to set
     */
    public void setListe_e(List<ExamenTemp> liste_e) {
        this.liste_e = liste_e;
    }

    /**
     * ajoute une fiche de soins au DPI temporaire
     */
    public void ajouterFicheDeSoins(FicheDeSoinsTemp fs) {
        getListe_f().add(fs);
    }

    /**
     * ajoute une prescription au DPI temporaire
     */
    public void ajouterPrescription(PrescriptionTemp p) {
        getListe_p().add(p);
    }

    /**
     * ajoute un examen au DPI temporaire
     */
    public void ajouterExamen(ExamenTemp e) {
        getListe_e().add(e);
    }

    /**
     * compare 2 dpi temporaire en fonction de l'ordre alphabetique du nom
     *
     * @return un nombre représentant la comparaison alphabetique
     */
    public int compareTo(DPITemporaire dpi) {
        return getNom().compareTo(dpi.getNom());
    }
}
