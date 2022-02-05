package projetsis;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class DM {
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins> ();
    private List<Prescriptions> prescriptions = new ArrayList<Prescriptions> ();
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie> ();
    private List<SoinsQuotidien> soinsQuotidien = new ArrayList<SoinsQuotidien> ();
    private List<Examen> examens = new ArrayList<Examen>();
    //List <Comparable> documents = new ArrayList<Comparable>();
    ArrayList documents = new ArrayList();

    public DM() {
        this.ficheDeSoins = new Vector<FicheDeSoins>();
        this.prescriptions = new Vector<Prescriptions>();
        this.lettreDeSortie=new Vector<LettreDeSortie>();
        this.soinsQuotidien= new Vector<SoinsQuotidien>();
        this.examens = new Vector<Examen>();
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
    public List<Prescriptions> getPrescriptions() {
        return prescriptions;
    }

    /**
     * @param prescriptions the prescriptions to set
     */
    public void setPrescriptions(List<Prescriptions> prescriptions) {
        this.prescriptions = prescriptions;
    }
    
    
}
