package projetsis;


public class SecretaireAdministrative {
    private int IdSecretaireAd;
    private String nomSecretaireAd;
    private String prenomSecretaireAd;
    private String mdp;
    
    public SecretaireAdministrative(int IdSecretaireAd,String nomSecretaireAd, String prenomSecretaireAd,String mdp){
        this.IdSecretaireAd=IdSecretaireAd;
        this.nomSecretaireAd=nomSecretaireAd;
        this.prenomSecretaireAd=prenomSecretaireAd;
        this.mdp=mdp;  
    }

    /**
     * @return the IdSecretaireAd
     */
    public int getIdSecretaireAd() {
        return IdSecretaireAd;
    }

    /**
     * @param IdSecretaireAd the IdSecretaireAd to set
     */
    public void setIdSecretaireAd(int IdSecretaireAd) {
        this.IdSecretaireAd = IdSecretaireAd;
    }

    /**
     * @return the nomSecretaireAd
     */
    public String getNomSecretaireAd() {
        return nomSecretaireAd;
    }

    /**
     * @param nomSecretaireAd the nomSecretaireAd to set
     */
    public void setNomSecretaireAd(String nomSecretaireAd) {
        this.nomSecretaireAd = nomSecretaireAd;
    }

    /**
     * @return the prenomSecretaireAd
     */
    public String getPrenomSecretaireAd() {
        return prenomSecretaireAd;
    }

    /**
     * @param prenomSecretaireAd the prenomSecretaireAd to set
     */
    public void setPrenomSecretaireAd(String prenomSecretaireAd) {
        this.prenomSecretaireAd = prenomSecretaireAd;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    
    

}
