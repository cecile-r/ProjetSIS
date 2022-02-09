package projetsis;

public class LettreDeSortie implements Evaluable{
    private String texte;
    private PH ph;
    private DateHeure dateHeure;
    private DPI DPI;

    public LettreDeSortie(String texte, PH ph, DateHeure dateHeure, DPI DPI){
        this.texte=texte;
        this.ph=ph;
        this.dateHeure=dateHeure;
        this.DPI=DPI;
    }
    
    @Override
    public String toString(){
        String ch="Lettre de sortie :\n";
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
}
