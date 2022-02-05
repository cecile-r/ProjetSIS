package projetsis;

public class LettreDeSortie implements Evaluable{
    private String texte;
    private PH ph;
    private DateHeure dateHeure;

    public LettreDeSortie(String texte, PH ph, DateHeure dateHeure){
        this.texte=texte;
        this.ph=ph;
        this.dateHeure=dateHeure;
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
