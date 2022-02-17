package nf;


public class SecretaireAdministrative {
    private String IdSecretaireAd;
    private String nomSecretaireAd;
    private String prenomSecretaireAd;
    private String mdp;
    
    public SecretaireAdministrative(String IdSecretaireAd,String nomSecretaireAd, String prenomSecretaireAd,String mdp){
        this.IdSecretaireAd=IdSecretaireAd;
        this.nomSecretaireAd=nomSecretaireAd;
        this.prenomSecretaireAd=prenomSecretaireAd;
        this.mdp=mdp;  
    }
    
    @Override
    public String toString(){
        String ch = "";
        ch= prenomSecretaireAd  + " " + nomSecretaireAd+"\n";
        return ch;
    }

    /**
     * @return the IdSecretaireAd
     */
    public String getIdSecretaireAd() {
        return IdSecretaireAd;
    }

    /**
     * @param IdSecretaireAd the IdSecretaireAd to set
     */
    public void setIdSecretaireAd(String IdSecretaireAd) {
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
