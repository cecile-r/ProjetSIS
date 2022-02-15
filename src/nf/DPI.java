package nf;

import java.util.Date;
import java.util.List;
import java.util.Vector;


public class DPI {
    
    private String IPP;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    //Remettre date2 partout si on l'utilise
    //private Date2 dateNaissance; ///!\ créer date pour avoir 13eme mois
    private Sexe sexe;
    private String adresse;
    private String telephone;
    private MedecinTraitant medecin_traitant;
    private DMA dMA;
    private DM dM;

    public DPI(String IPP, String nom, String prenom, Date DateNaissance, Sexe sexe, String adresse, String telephone, MedecinTraitant medecin_traitant){
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
    
    public DPI(String IPP, String nom, String prenom, Date DateNaissance, Sexe sexe, String adresse, String telephone, MedecinTraitant medecin_traitant,DMA dMA, DM dM ){
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
    
    @Override
    public String toString (){
        String ch = "";
        ch = ch + "Nom : "+nom+"\n";
        ch = ch + "Prénom : "+prenom+"\n";
        ch = ch + "Date de naissance : "+dateNaissance+"\n";
        ch = ch + "Sexe : "+sexe+"\n";
        ch = ch + "Adresse : "+adresse+"\n";
        ch = ch + "Téléphone : "+telephone+"\n";
        return ch;
    }
    
    public String toStringSimple(){
        String ch = "";
        ch = ch +prenom+ " "+nom+"\n";
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
     * @return the dateNaissance
     */
    public Date getDateNaissance() {
        return dateNaissance;
    }

    /**
     * @param dateNaissance the dateNaissance to set
     */
    public void setDateNaissance(Date dateNaissance) {
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
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
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
