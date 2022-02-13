package nf;

import java.util.List;


public class RendezVous implements Evaluable{
    private PH pH;
    private DateHeure dateHeure;
    private String remarque;
    private DPI DPI;
    
    public RendezVous(DateHeure dateHeure, String remarque){
        this.dateHeure=dateHeure;
        this.remarque=remarque;
        this.DPI=null;
        this.pH=null;
        //SET DPI
        //SET PH
    }
    
    @Override
    public String toString(){
        String ch = "";
        ch = ch+ "Patient : "+getDPI().toString()+"\n";
        ch = ch+ "PH : "+pH.toString()+"\n";
        ch = ch+ dateHeure.StringDateHeure()+"\n";
        if(remarque!=null){
            ch = ch+ "Remarque : "+ remarque+"\n";
        }
        return ch;
    }
    
    @Override
    public String toStringDM(){return null;}
    
    @Override
    public String toStringDMA(){
        String ch = "############# RENDEZ VOUS ###########\n";
        ch = ch + "Patient : "+DPI.toStringSimple();
        ch = ch+ "PH : "+pH.toString()+"\n";
        ch = ch+ dateHeure.StringDateHeure()+"\n";
        if(remarque!=null){
            ch = ch+ "Remarque : "+ remarque +"\n";
        }
        return ch;
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
     * @return the DPI
     */
    public DPI getDPI() {
        return DPI;
    }

    /**
     * @param DPI the DPI to set
     */
    public void setDPI(DPI DPI) {
        this.DPI = DPI;
    }

}