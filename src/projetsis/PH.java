package projetsis;

import java.util.ArrayList;
import java.util.List;



public class PH {
    
    private int IdPH;
    private String nomPH;
    private String prenomPH;
    private Service service;
    private String mdp;
    private int telephone;
    private String specialite;
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins> ();

    
    public PH(int idPH, String nomPh, String prenomPH,Service service, String mdp, int telephone, String specialite){
        this.IdPH=IdPH;
        this.nomPH=nomPH;
        this.prenomPH=prenomPH;
        this.service=service;
        this.mdp=mdp;
        this.telephone=telephone;
        this.specialite=specialite;
        ficheDeSoins =null;
    }
    
    public String toString() {
       return prenomPH + " " + nomPH + ", " + specialite;
    }
    

    /**
     * @return the IdPH
     */
    public int getIdPH() {
        return IdPH;
    }

    /**
     * @param IdPH the IdPH to set
     */
    public void setIdPH(int IdPH) {
        this.IdPH = IdPH;
    }

    /**
     * @return the nomPH
     */
    public String getNomPH() {
        return nomPH;
    }

    /**
     * @param nomPH the nomPH to set
     */
    public void setNomPH(String nomPH) {
        this.nomPH = nomPH;
    }

    /**
     * @return the prenomPH
     */
    public String getPrenomPH() {
        return prenomPH;
    }

    /**
     * @param prenomPH the prenomPH to set
     */
    public void setPrenomPH(String prenomPH) {
        this.prenomPH = prenomPH;
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
     * @return the specialite
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * @param specialite the specialite to set
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
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
