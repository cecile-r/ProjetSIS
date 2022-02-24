package nf;

import static nf.DateHeure.convertirDatetoString;

public class Examen implements Evaluable {
    
    private String resultats;
    private TypeExamen type_examen;
    private PH ph;
    private DateHeure dateHeure;
    private DPI DPI;

    public Examen(TypeExamen type_examen, String resultats,DateHeure dateHeure){
        this.type_examen=type_examen;
        this.resultats = resultats;
        this.dateHeure=dateHeure;  
        this.ph=null;
        this.DPI=null;
        //SET PH
        //SET DPI
    }
    
    //Constructeur pour les examens mis dans les DMA -> pas de résultats
    public Examen(TypeExamen type_examen, DateHeure dateHeure){
        this.type_examen=type_examen;
        this.resultats = null;
        this.dateHeure=dateHeure;  
        this.ph=null;
        this.DPI=null;
        //SET PH
        //SET DPI
    }
    
    @Override
    public String toString(){
        String ch="";
        ch+=dateHeure.StringDateHeure();
        ch+="\n";
        ch+=ph.toString();
        ch+="\n";
        ch+=type_examen;
        ch+="\n";
        ch=ch + "Résultats : " + resultats;
        ch+="\n";
        return ch; 
    }
    
    public String toStringDM(){
        String ch="------------------------------EXAMEN------------------------------\n\n";
        ch+="Le ";
        ch+=convertirDatetoString(dateHeure);
        ch+="\n\nPar ";
        ch+=ph.toString();
        ch+="\n\nType d'examen : ";
        ch+=type_examen;
        ch+="\n\n";
        ch=ch + "Résultats : " + resultats;
        ch+="\n";
        return ch; 
    }
    
    public String toStringDMA(){
        String ch="------------------------------EXAMEN------------------------------\n\n";
        ch+="Le ";
        ch+=convertirDatetoString(dateHeure);
        ch+="\n\nPar ";
        ch+=ph.toString();
        ch+="\n\nType d'examen : ";
        ch+=type_examen;
        ch+="\n\n";
        return ch; 
    }
    
    @Override
    public Object getProfessionnel(){
        return ph;
    }
    
    @Override
    public String getTypeEvaluable(){
        return "Examen";
    }
    
    public String getContenu(){
        return type_examen.toString();
    }
    
    @Override
    public String getObservations(){
        return resultats;
    }
    
    /**
     * @return the resultats
     */
    public String getResultats() {
        return resultats;
    }

    /**
     * @param resultats the resultats to set
     */
    public void setResultats(String resultats) {
        this.resultats = resultats;
    }

    /**
     * @return the type_examen
     */
    public TypeExamen getType_examen() {
        return type_examen;
    }

    /**
     * @param type_examen the type_examen to set
     */
    public void setType_examen(TypeExamen type_examen) {
        this.type_examen = type_examen;
    }

    /**
     * @return the ph
     */
    public PH getPh() {
        return ph;
    }

    /**
     * @param ph the ph to set
     */
    public void setPh(PH ph) {
        this.ph = ph;
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
