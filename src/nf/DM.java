package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class DM {
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins> ();
    private List<Prescription> prescriptions = new ArrayList<Prescription> ();
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie> ();
    private List<SoinsQuotidien> soinsQuotidien = new ArrayList<SoinsQuotidien> ();
    private List<Examen> examens = new ArrayList<Examen>();
    private ArrayList documents = new ArrayList();
    
    public DM(){
       this.ficheDeSoins = new Vector<FicheDeSoins>();
       this.prescriptions = new Vector<Prescription>();
       this.lettreDeSortie=new Vector<LettreDeSortie>();
       this.soinsQuotidien= new Vector<SoinsQuotidien>();
       this.examens = new Vector<Examen>();
    }

    public DM(List<FicheDeSoins> ficheDeSoins,List<Prescription> prescriptions, List<LettreDeSortie> lettreDeSortie, List<SoinsQuotidien> soinsQuotidien, List<Examen> examens) {
       this.ficheDeSoins = ficheDeSoins;
       this.prescriptions = prescriptions;
       this.lettreDeSortie= lettreDeSortie;
       this.soinsQuotidien= soinsQuotidien;
       this.examens = examens;
    }
    
    
    //ficheDeSoins --> tout 
    //precriptions --> tout 
    //lettreDeSortie --> tout
    //soins quotidiens --> tout 
    //examens --> tout
    
    @Override
    public String toString(){
        List <Evaluable> documents = new ArrayList<Evaluable>();
        documents = new Vector<>();
        documents.addAll(getFicheDeSoins());
        documents.addAll(getPrescriptions());
        documents.addAll(getLettreDeSortie());
        documents.addAll(getSoinsQuotidien());
        documents.addAll(getExamens());
        ComparaisonEvaluables c = new ComparaisonEvaluables();
        documents = c.trierEvaluablesParDate(documents);
        
        String ch="------------------------DM------------------------\n";
        for (int i = 0; i < documents.size(); i++) {
            Evaluable e = documents.get(i);
            ch=ch+e.toStringDM()+"\n";
        }
        return ch;   
    }
    
   public void ajouterFicheDeSoins(FicheDeSoins fs){
        getFicheDeSoins().add(fs);
   }
   public void ajouterPrescription(Prescription p){
        getPrescriptions().add(p);
   }
   public void ajouterLettreDeSortie(LettreDeSortie l){
        getLettreDeSortie().add(l);
   }
   public void ajouterSoinsQuotidien(SoinsQuotidien s){
        getSoinsQuotidien().add(s);
   }
   public void ajouterExamen(Examen e){
        getExamens().add(e);
   }
    

    /**
     * @return the ficheDeSoins
     */
    public List<FicheDeSoins> getFicheDeSoins() {
        return ficheDeSoins;
    }

    /**
     * @param ficheDeSoins the ficheDeSoins to set
     */
    public void setFicheDeSoins(List<FicheDeSoins> ficheDeSoins) {
        this.ficheDeSoins = ficheDeSoins;
    }

    /**
     * @return the prescriptions
     */
    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    /**
     * @param prescriptions the prescriptions to set
     */
    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
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
     * @return the examens
     */
    public List<Examen> getExamens() {
        return examens;
    }

    /**
     * @param examens the examens to set
     */
    public void setExamens(List<Examen> examens) {
        this.examens = examens;
    }

    /**
     * @return the documents
     */
    public ArrayList getDocuments() {
        return documents;
    }

    /**
     * @param documents the documents to set
     */
    public void setDocuments(ArrayList documents) {
        this.documents = documents;
    }
    
    
}
