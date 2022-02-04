package projetsis;

import java.util.ArrayList;
import java.util.List;



public class Infirmiere {
    private String idInfirmiere;
    private String nomInfirmiere;
    private String prenomInfirmiere;
    private Service service;
    private String mdp;
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins> ();
  
    public Infirmiere(String idInfirmiere, String nomInfirmiere,String prenomInfirmiere, Service service, String mdp){
        this.idInfirmiere=idInfirmiere;
        this.nomInfirmiere=nomInfirmiere;
        this.prenomInfirmiere=prenomInfirmiere;
        this.service=service;
        this.mdp=mdp;
    }
    
     public String toString() {
       return getPrenomInfirmiere() + " " + getNomInfirmiere() + ", " + getService();
    }

    /**
     * @return the idInfirmiere
     */
    public String getIdInfirmiere() {
        return idInfirmiere;
    }

    /**
     * @param idInfirmiere the idInfirmiere to set
     */
    public void setIdInfirmiere(String idInfirmiere) {
        this.idInfirmiere = idInfirmiere;
    }

    /**
     * @return the nomInfirmiere
     */
    public String getNomInfirmiere() {
        return nomInfirmiere;
    }

    /**
     * @param nomInfirmiere the nomInfirmiere to set
     */
    public void setNomInfirmiere(String nomInfirmiere) {
        this.nomInfirmiere = nomInfirmiere;
    }

    /**
     * @return the prenomInfirmiere
     */
    public String getPrenomInfirmiere() {
        return prenomInfirmiere;
    }

    /**
     * @param prenomInfirmiere the prenomInfirmiere to set
     */
    public void setPrenomInfirmiere(String prenomInfirmiere) {
        this.prenomInfirmiere = prenomInfirmiere;
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

    /**
     * @return the ficheDeSoins
     */
    public List<FicheDeSoins> getFicheDeSoins() {
        return ficheDeSoins;
    }

    /**
     * @param ficheDeSoins the ficheDeSoins to set
     */
    public void setFicheDeSoins(List<FicheDeSoins> ficheDeSoins) {
        this.ficheDeSoins = ficheDeSoins;
    }
     
}
