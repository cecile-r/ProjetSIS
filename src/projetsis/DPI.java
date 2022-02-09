package projetsis;

import java.util.Date;
import java.util.List;
import java.util.Vector;



public class DPI {
    
    private int IPP;
    private String nom;
    private String prenom;
    private Date2 dateNaissance; ///!\ créer date pour avoir 13eme mois
    private Sexe sexe;
    private String adresse;
    private int telephone;
    private MedecinTraitant medecin_traitant;
    private DMA dMA;
    private DM dM;

    public DPI(int IPP, String nom, String prenom, Date2 DateNaissance, Sexe sexe, String adresse, int telephone, MedecinTraitant medecin_traitant){
        this.IPP=IPP;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaissance=DateNaissance;
        this.sexe=sexe;
        this.adresse=adresse;
        this.telephone=telephone;
        this.medecin_traitant=medecin_traitant; 
        this.dM=null;
        this.dMA=null;
    }
    
    public DPI(int IPP, String nom, String prenom, Date2 DateNaissance, Sexe sexe, String adresse, int telephone, MedecinTraitant medecin_traitant,DMA dMA, DM dM ){
        this.IPP=IPP;
        this.nom=nom;
        this.prenom=prenom;
        this.dateNaissance=DateNaissance;
        this.sexe=sexe;
        this.adresse=adresse;
        this.telephone=telephone;
        this.medecin_traitant=medecin_traitant; 
        this.dM=dM;
        this.dMA=dMA;
    }
    


    /**
     * @return the IPP
     */
    public int getIPP() {
        return IPP;
    }

    /**
     * @param IPP the IPP to set
     */
    public void setIPP(int IPP) {
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
     * @return the dateNaissance
     */
    public Date2 getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date2 dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    /**
     * @return the sexe
     */
    public Sexe getSexe() {
        return sexe;
    }

    /**
     * @param sexe the sexe to set
     */
    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    /**
     * @return the adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     * @return the telephone
     */
    public int getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the medecin_traitant
     */
    public MedecinTraitant getMedecin_traitant() {
        return medecin_traitant;
    }

    /**
     * @param medecin_traitant the medecin_traitant to set
     */
    public void setMedecin_traitant(MedecinTraitant medecin_traitant) {
        this.medecin_traitant = medecin_traitant;
    }

    /**
     * @return the dMA
     */
    public DMA getdMA() {
        return dMA;
    }

    /**
     * @param dMA the dMA to set
     */
    public void setdMA(DMA dMA) {
        this.dMA = dMA;
    }

    /**
     * @return the dM
     */
    public DM getdM() {
        return dM;
    }

    /**
     * @param dM the dM to set
     */
    public void setdM(DM dM) {
        this.dM = dM;
    }
    
    
    
}
