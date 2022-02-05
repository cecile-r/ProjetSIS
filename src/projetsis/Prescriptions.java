package projetsis;

import java.util.Date;

public class Prescriptions {
    private DateHeure date_presciption;
    private String observation;
    private TypeExamen typeExamen;
    private Medicament medicament;
    private int idConsultation;
    private PH pH;
    
    public Prescriptions(DateHeure date_presciption,String observation, TypeExamen typeExamen, Medicament medicament, int idConsultation, PH pH){
        this.date_presciption = date_presciption;
        this.observation=observation;
        this.typeExamen=typeExamen;
        this.medicament = medicament;
        this.idConsultation=idConsultation;
        this.pH=pH;
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
    
    
    /**
     * @return the date_presciption
     */
    public DateHeure getDate_presciption() {
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
    public Medicament getMedicament() {
        return medicament;
    }

    /**
     * @param medicament the medicament to set
     */
    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    /**
     * @return the idConsultation
     */
    public int getIdConsultation() {
        return idConsultation;
    }

    /**
     * @param idConsultation the idConsultation to set
     */
    public void setIdConsultation(int idConsultation) {
        this.idConsultation = idConsultation;
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
    

}
