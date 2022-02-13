package nf;
import java.util.Date;

public class Prescription implements Evaluable{
    private DateHeure date_presciption;
    private String observation;
    private TypeExamen typeExamen;
    private String medicament;
    private PH pH;
    private DPI DPI;
    
    public Prescription(DateHeure date_presciption,String observation, TypeExamen typeExamen, String medicament){
        this.date_presciption = date_presciption;
        this.observation=observation;
        this.typeExamen=typeExamen;
        this.medicament = medicament;
        this.pH=null;
        this.DPI=null;
        //SET PH
        //SET DPI
    }

    @Override
    public String toString(){
        String ch="";
        ch+=date_presciption.StringDateHeure();
        ch+="\n";
        ch+=pH.toString();
        ch+="\n";
        if(typeExamen!=null){
            ch+=typeExamen;
            ch+="\n";
        }else{
            ch+=medicament;
            ch+="\n";
        }
        ch+=observation;
        ch+="\n";
        return ch;
    }
    
    public String toStringDM(){
        String ch="############# PRESCRIPTION ###########\n";
        ch+=date_presciption.StringDateHeure();
        ch+="\n";
        ch+=pH.toString();
        ch+="\n";
        if(typeExamen!=null){
            ch+=typeExamen;
            ch+="\n";
        }else{
            ch+=medicament;
            ch+="\n";
        }
        ch+=observation;
        ch+="\n";
        return ch;
    }
    
    public String toStringDMA(){return null;};
    
    /**
     * @return the date_presciption
     */
    @Override
    public DateHeure getDateHeure() {
        return date_presciption;
    }

    /**
     * @param date_presciption the date_presciption to set
     */
    public void setDate_presciption(DateHeure date_presciption) {
        this.date_presciption = date_presciption;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

    /**
     * @return the typeExamen
     */
    public TypeExamen getTypeExamen() {
        return typeExamen;
    }

    /**
     * @param typeExamen the typeExamen to set
     */
    public void setTypeExamen(TypeExamen typeExamen) {
        this.typeExamen = typeExamen;
    }

    /**
     * @return the medicament
     */
    public String getMedicament() {
        return medicament;
    }

    /**
     * @param medicament the medicament to set
     */
    public void setMedicament(String medicament) {
        this.medicament = medicament;
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