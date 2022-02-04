package projetsis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;


public class FicheDeSoins {
    private Date dateFicheSoins;
    private int idFiche;
    private List<SoinsQuotidien> soinsQuotidien = new ArrayList<SoinsQuotidien> ();
    private List<Examen> examen = new ArrayList<Examen> ();
    private List<Acte> acte = new ArrayList<Acte> ();
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie> ();
    private PH pH;
    private Infirmiere infirmiere;
    

   public FicheDeSoins(Date dateFicheSoins, int idFiche, PH pH, Infirmiere infirmiere){
       this.dateFicheSoins=dateFicheSoins;
       this.idFiche=idFiche;
       this.pH=pH;
       this.infirmiere = infirmiere;
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
        ch = ch + "Fiche de soins du " + dateFicheSoins;
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
     * @return chaine de caractere avecla date et le medecin de la fiche de soin
     */
    public String toStringFicheDeSoins() {
        String ch = "\n";
        ch = ch + dateFicheSoins;
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
    public Date getDateFicheSoins() {
        return dateFicheSoins;
    }

    /**
     * @param DateFicheSoins the DateFicheSoins to set
     */
    public void setDateFicheSoins(Date DateFicheSoins) {
        this.dateFicheSoins = DateFicheSoins;
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
     * @return the soinsQuotidien
     */
    public List<SoinsQuotidien> getSoinsQuotidien() {
        return soinsQuotidien;
    }

    /**
     * @param soinsQuotidien the soinsQuotidien to set
     */
    public void setSoinsQuotidien(List<SoinsQuotidien> soinsQuotidien) {
        this.soinsQuotidien = soinsQuotidien;
    }

    /**
     * @return the examen
     */
    public List<Examen> getExamen() {
        return examen;
    }

    /**
     * @param examen the examen to set
     */
    public void setExamen(List<Examen> examen) {
        this.examen = examen;
    }

    /**
     * @return the acte
     */
    public List<Acte> getActe() {
        return acte;
    }

    /**
     * @param acte the acte to set
     */
    public void setActe(List<Acte> acte) {
        this.acte = acte;
    }

    /**
     * @return the lettreDeSortie
     */
    public List<LettreDeSortie> getLettreDeSortie() {
        return lettreDeSortie;
    }

    /**
     * @param lettreDeSortie the lettreDeSortie to set
     */
    public void setLettreDeSortie(List<LettreDeSortie> lettreDeSortie) {
        this.lettreDeSortie = lettreDeSortie;
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
