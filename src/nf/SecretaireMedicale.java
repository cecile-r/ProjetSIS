package nf;

public class SecretaireMedicale {
    private String idSecretaireMed;
    private String nomSecretaireMed;
    private String prenomSecretaireMed;
    private Service service;
    private String mdp;
    
    public SecretaireMedicale(String idSecretaireMed,String nomSecretaireMed,String prenomSecretaireMed, Service service,String mdp ){
        this.idSecretaireMed=idSecretaireMed;
        this.nomSecretaireMed=nomSecretaireMed;
        this.prenomSecretaireMed=prenomSecretaireMed;
        this.service=service;
        this.mdp=mdp;
    }
    
    @Override
    public String toString(){
        String ch = "";
        ch= getNomSecretaireMed()  + " " + getPrenomSecretaireMed()+"\n";
        return ch;
    }

    /**
     * @return the idSecretaireMed
     */
    public String getIdSecretaireMed() {
        return idSecretaireMed;
    }

    /**
     * @param idSecretaireMed the idSecretaireMed to set
     */
    public void setIdSecretaireMed(String idSecretaireMed) {
        this.idSecretaireMed = idSecretaireMed;
    }

    /**
     * @return the nomSecretaireMed
     */
    public String getNomSecretaireMed() {
        return nomSecretaireMed;
    }

    /**
     * @param nomSecretaireMed the nomSecretaireMed to set
     */
    public void setNomSecretaireMed(String nomSecretaireMed) {
        this.nomSecretaireMed = nomSecretaireMed;
    }

    /**
     * @return the prenomSecretaireMed
     */
    public String getPrenomSecretaireMed() {
        return prenomSecretaireMed;
    }

    /**
     * @param prenomSecretaireMed the prenomSecretaireMed to set
     */
    public void setPrenomSecretaireMed(String prenomSecretaireMed) {
        this.prenomSecretaireMed = prenomSecretaireMed;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
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
