package projetsis;

public class SecretaireMedicale {
    private int idSecretaireMed;
    private String nomSecretaireMed;
    private String prenomSecretaireMed;
    private Service service;
    private String mdp;
    
    public SecretaireMedicale(int idSecretaireMed,String nomSecretaireMed,String prenomSecretaireMed, Service service,String mdp ){
        this.idSecretaireMed=idSecretaireMed;
        this.nomSecretaireMed=nomSecretaireMed;
        this.prenomSecretaireMed=prenomSecretaireMed;
        this.service=service;
        this.mdp=mdp;
    }
    

}
