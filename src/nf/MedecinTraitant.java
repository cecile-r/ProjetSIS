package nf;

public class MedecinTraitant {

    private String mail;
    private String nomMedecinTraitant;
    private String prenomMedecinTraitant;
    private String telephoneMedecinTraitant;

    public MedecinTraitant(String mail, String nomMedecinTraitant, String prenomMedecinTraitant, String telephoneMedecinTraitant) {
        this.mail = mail;
        this.nomMedecinTraitant = nomMedecinTraitant;
        this.prenomMedecinTraitant = prenomMedecinTraitant;
        this.telephoneMedecinTraitant = telephoneMedecinTraitant;
    }

    /*
     * prenom nom 
     */
    @Override
    public String toString() {
        return prenomMedecinTraitant + " " + nomMedecinTraitant;
    }

    /*
     * prenom nom telephone mail
     */
    public String toString2() {
        return prenomMedecinTraitant + " " + nomMedecinTraitant + " | " + telephoneMedecinTraitant + " | " + mail;
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
    public String getTelephoneMedecinTraitant() {
        return telephoneMedecinTraitant;
    }

    /**
     * @param telephoneMedecinTraitant the telephoneMedecinTraitant to set
     */
    public void setTelephoneMedecinTraitant(String telephoneMedecinTraitant) {
        this.telephoneMedecinTraitant = telephoneMedecinTraitant;
    }

}
