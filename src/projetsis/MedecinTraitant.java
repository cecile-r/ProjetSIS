package projetsis;

public class MedecinTraitant {
    
    private String mail;
    private String nomMedecinTraitant;
    private String prenomMedecinTraitant;
    private int telephoneMedecinTraitant;
    
    public MedecinTraitant(String mail, String nomMedecinTraitant, String prenomMedecinTraitant){
        this.mail=mail;
        this.nomMedecinTraitant=nomMedecinTraitant;
        this.prenomMedecinTraitant=prenomMedecinTraitant;
        this.telephoneMedecinTraitant=telephoneMedecinTraitant;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the nomMedecinTraitant
     */
    public String getNomMedecinTraitant() {
        return nomMedecinTraitant;
    }

    /**
     * @param nomMedecinTraitant the nomMedecinTraitant to set
     */
    public void setNomMedecinTraitant(String nomMedecinTraitant) {
        this.nomMedecinTraitant = nomMedecinTraitant;
    }

    /**
     * @return the prenomMedecinTraitant
     */
    public String getPrenomMedecinTraitant() {
        return prenomMedecinTraitant;
    }

    /**
     * @param prenomMedecinTraitant the prenomMedecinTraitant to set
     */
    public void setPrenomMedecinTraitant(String prenomMedecinTraitant) {
        this.prenomMedecinTraitant = prenomMedecinTraitant;
    }

    /**
     * @return the telephoneMedecinTraitant
     */
    public int getTelephoneMedecinTraitant() {
        return telephoneMedecinTraitant;
    }

    /**
     * @param telephoneMedecinTraitant the telephoneMedecinTraitant to set
     */
    public void setTelephoneMedecinTraitant(int telephoneMedecinTraitant) {
        this.telephoneMedecinTraitant = telephoneMedecinTraitant;
    }
    

}
