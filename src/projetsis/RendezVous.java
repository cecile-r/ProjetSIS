package projetsis;

import java.util.List;


public class RendezVous {
    private PH pH;
    private DateHeure dateHeure;
    private String remarque;
    private int idRDV;
    private DPI DPI;
    
    public RendezVous(PH ph, DateHeure dateHeure, String remarque, int idRDV, DPI DPI){
        this.pH=ph;
        this.dateHeure=dateHeure;
        this.remarque=remarque;
        this.idRDV=idRDV;
        this.DPI=DPI;
    }
    
    //!\  A FAIRE
    void afficherRvd(){
        
    }
    

    /**
     * @return the pH
     */
    public PH getpH() {
        return pH;
    }

    /**
     * @param pH the pH to set
     */
    public void setpH(PH pH) {
        this.pH = pH;
    }

    /**
     * @return the dateHeure
     */
    public DateHeure getDateHeure() {
        return dateHeure;
    }

    /**
     * @param dateHeure the dateHeure to set
     */
    public void setDateHeure(DateHeure dateHeure) {
        this.dateHeure = dateHeure;
    }

    /**
     * @return the remarque
     */
    public String getRemarque() {
        return remarque;
    }

    /**
     * @param remarque the remarque to set
     */
    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    /**
     * @return the idRDV
     */
    public int getIdRDV() {
        return idRDV;
    }

    /**
     * @param idRDV the idRDV to set
     */
    public void setIdRDV(int idRDV) {
        this.idRDV = idRDV;
    }

}
