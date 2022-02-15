/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

/**
 *
 * @author cecilerichard
 */
public class PHSimplifie {
    
    private String nomPH;
    private String prenomPH;
    private String service;
    
    public PHSimplifie(String nomPH, String prenomPH, String service){
        this.nomPH = nomPH;
        this.prenomPH = prenomPH;
        this.service = service;
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
    public String getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(String service) {
        this.service = service;
    }
    
    @Override
    public String toString() {
       return nomPH + " " + prenomPH + ", " + service;
    }
}
