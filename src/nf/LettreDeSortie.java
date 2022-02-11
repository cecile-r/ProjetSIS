package nf;

public class LettreDeSortie implements Evaluable{
    private String texte;
    private PH ph;
    private DateHeure dateHeure;
    private DPI DPI;

    public LettreDeSortie(String texte,DateHeure dateHeure){
        this.texte=texte;
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
        ch+=texte;
        ch+="\n";
        return ch;
    }
    
     
    public String toStringDM(){
        String ch="############# LETTRE DE SORTIE ###########\n";
        ch+=dateHeure.StringDateHeure();
        ch+="\n";
        ch+=ph.toString();
        ch+="\n";
        ch+=texte;
        ch+="\n";
        return ch;
    }
    
    public String toStringDMA(){
        String ch="############# LETTRE DE SORTIE ###########\n";
        ch+=dateHeure.StringDateHeure();
        ch+="\n";
        ch+=ph.toString();
        ch+="\n";
        ch+=texte;
        ch+="\n";
        return ch;
    }

    /**
     * @return the texte
     */
    public String getTexte() {
        return texte;
    }

    /**
     * @param texte the texte to set
     */
    public void setTexte(String texte) {
        this.texte = texte;
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
    @Override
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
