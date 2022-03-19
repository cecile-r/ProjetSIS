package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import static nf.DateHeure.convertirDateHeuretoString;


public class FicheDeSoins implements Evaluable{
    private DateHeure dateHeure;
    private List<Acte> actes = new ArrayList<Acte> ();
    private PH pH;
    private Infirmier infirmier;
    private DPI DPI;
    

   public FicheDeSoins(DateHeure dateHeure){
       this.dateHeure=dateHeure;
       this.actes= new Vector<>();
       this.DPI = null;
       this.pH=null;
       this.infirmier = null;
       //SET DPI
       //SET PH
       //SET INFIRMIER
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
    @Override
    public String toString() {
        String ch = "";
        ch = ch + "Fiche de soins du " + convertirDateHeuretoString(dateHeure);
        if(getpH()!=null){
            ch = ch + "\n\nPar -PH- : " + getpH().toString();
        }else{
            ch = ch + "\n\nPar -IDE- : " + getInfirmier().toString();
        }
        ch = ch + "\n\n\n Actes medicaux :";
        for (int i = 0; i < getActe().size(); i++) {
            Acte a = getActe().get(i);
            ch = ch + "\n\n         ----------------------------------\n" + a.toString();
        }
        return ch;
    }
    
    @Override
    public String toStringDM(){
        String ch="------------------------------FICHE DE SOINS------------------------------\n\n";
        ch+="Le ";
        ch = ch + convertirDateHeuretoString(dateHeure)+"\n\n";
        if(getpH()!=null){
            ch = ch + "Par -PH- : " + getpH().toString()+"\n\n";
        }else{
            ch = ch + "Par -IDE- : " + getInfirmier().toString()+"\n\n";
        }
        ch = ch + "Actes medicaux :\n";
        for (int i = 0; i < getActe().size(); i++) {
            Acte a = getActe().get(i);
            ch = ch + "    ------ " + a.toString()+"\n";
        }
        return ch;
    }
    
    @Override
    public String toStringDMA(){
        String ch="------------------------------FICHE DE SOINS------------------------------\n\n";
        ch = ch + convertirDateHeuretoString(dateHeure)+"\n\n";
        if(getpH()!=null){
            ch = ch + "Par -PH- : " + getpH().toString()+"\n\n";
        }else{
            ch = ch + "Par -IDE- : " + getInfirmier().toString()+"\n\n";
        }
        ch = ch + "Actes medicaux :\n";
        for (int i = 0; i < getActe().size(); i++) {
            Acte a = getActe().get(i);
            ch = ch + "    ------ " + a.toStringDMA()+"\n";
        }
        return ch;
    }
    
    @Override
    public Object getProfessionnel(){
        if(pH!=null){
            return "PH- "+pH;
        }else if(infirmier!=null){
            return "IDE- "+infirmier;
        }
        return null;
    }
    
    @Override
    public String getTypeEvaluable(){
        return "Fiche de soins";
    }
    
    @Override
    public String getContenu(){
        String ch="";
        ch += "Actes medicaux :\n";
        for (int i = 0; i < getActe().size(); i++) {
            Acte a = getActe().get(i);
            ch = ch + "    ------ " + a.toString()+"\n";
        }
        return ch;
    }
    
    @Override
    public String getObservations(){
        return null;
    }

    /**
     * permet d'ajouter un acte a la liste a partir d'un acte
     *
     * @param acte
     */
    public void ajouterActe(Acte acte) {
        actes.add(acte);
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
            ch = ch + " _IDE: " + getInfirmier().toString() + "\n";
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
    public Infirmier getInfirmier() {
        return infirmier;
    }

    /**
     * @param infirmiere the infirmiere to set
     */
    public void setInfirmier(Infirmier infirmier) {
        this.infirmier = infirmier;
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
