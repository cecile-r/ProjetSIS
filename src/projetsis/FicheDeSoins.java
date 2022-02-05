package projetsis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


public class FicheDeSoins implements Evaluable{
    private DateHeure dateHeure;
    private int idFiche;
    private List<Acte> actes = new ArrayList<Acte> ();
    private PH pH;
    private Infirmiere infirmiere;
    

   public FicheDeSoins(DateHeure dateHeure, int idFiche, PH pH, Infirmiere infirmiere){
       this.dateHeure=dateHeure;
       this.idFiche=idFiche;
       this.pH=pH;
       this.infirmiere = infirmiere;
       this.actes= new Vector<Acte>();
   }
   
    /**
     *
     * @return le cout total de la fiche de soins
     */
    public double coutTotal() {
        double total = 0;
        for (int i = 0; i < getActe().size(); i++) {
            Acte a = getActe().get(i);
            total += a.cout();
        }
        return total;
    }
    
    /**
     *
     * @return une chaine de caractere avec toute les informations de la fiche
     * de soins
     */
    public String toString() {
        String ch = "";
        ch = ch + "Fiche de soins du " + dateHeure;
        if(getpH()!=null){
            ch = ch + "\n\nPH : " + getpH().toString();
        }else{
            ch = ch + "\n\nIDE : " + getpH().toString();
        }
        ch = ch + "\n\n\n Actes medicaux :";
        for (int i = 0; i < getActe().size(); i++) {
            Acte a = getActe().get(i);
            ch = ch + "\n\n         ----------------------------------\n" + a.toString();
        }
        return ch;
    }

    /**
     * permet d'ajouter un acte a la liste a partir d'un acte
     *
     * @param acte
     */
    public void ajouterActe(Acte acte) {
        getActe().add(acte);
    }
    
    
    /**
     *
     * @return chaine de caractere avec la date et le medecin de la fiche de soin/infirmiere
     */
    public String toString2() {
        String ch = "\n";
        ch = ch + dateHeure;
        if(getpH()!=null){
            ch = ch + " _PH : " + getpH().toString() + "\n";
        }else{
            ch = ch + " _IDE: " + getInfirmiere().toString() + "\n";
        }
        return ch;
    }
    
    

    /**
     * @return the DateFicheSoins
     */
    @Override
    public DateHeure getDateHeure() {
        return dateHeure;
    }

    /**
     * @param DateHeure the DateHeure to set
     */
    public void setDateHeure(DateHeure dateHeure) {
        this.dateHeure = dateHeure;
    }

    /**
     * @return the idFiche
     */
    public int getIdFiche() {
        return idFiche;
    }

    /**
     * @param idFiche the idFiche to set
     */
    public void setIdFiche(int idFiche) {
        this.idFiche = idFiche;
    }

    /**
     * @return the acte
     */
    public List<Acte> getActe() {
        return actes;
    }

    /**
     * @param acte the acte to set
     */
    public void setActe(List<Acte> acte) {
        this.actes = acte;
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
     * @return the infirmiere
     */
    public Infirmiere getInfirmiere() {
        return infirmiere;
    }

    /**
     * @param infirmiere the infirmiere to set
     */
    public void setInfirmiere(Infirmiere infirmiere) {
        this.infirmiere = infirmiere;
    }
    
    
}
