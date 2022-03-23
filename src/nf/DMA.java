package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class DMA {

    private int IPP;
    //!\ restriction du DMA : pas d'accès aux actes dans les fiches de soins
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins>();
    //!\ restriction du DMA : pas d'accès aux résultats, juste à l'examen
    private List<Examen> examens = new ArrayList<Examen>();
    private List<RendezVous> rendezVous = new ArrayList<RendezVous>();
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie>();
    private Localisation localisation;
    private List<Evaluable> documents = new ArrayList<Evaluable>();

    public DMA(Localisation localisation) {
        this.ficheDeSoins = new Vector<FicheDeSoins>();
        this.rendezVous = new Vector<RendezVous>();
        this.examens = new Vector<Examen>();
        this.lettreDeSortie = new Vector<LettreDeSortie>();
        this.localisation = localisation;
    }

    //ficheDeSoins --> INTITULE -- restrictions
    //precriptions --> NON
    //lettreDeSortie --> tout
    //soins quotidiens --> NON
    //examens --> INTITULE -- restrictions
    //RDV
    /**
     * ajoute une fiche de soins au DMA
     */
    public void ajouterFicheDeSoins(FicheDeSoins fs) {
        ficheDeSoins.add(fs);
    }

    /**
     * ajoute un rendez vous au DMA
     */
    public void ajouterRendezVous(RendezVous rdv) {
        rendezVous.add(rdv);
    }

    /**
     * ajoute une lettre de sortie au DMA
     */
    public void ajouterLettreDeSortie(LettreDeSortie l) {
        lettreDeSortie.add(l);
    }

    /**
     * ajoute un examen au DMA
     */
    public void ajouterExamen(Examen e) {
        examens.add(e);
    }

    /**
     * retourne une chaine de caractere pour afficher le DMA
     * @return String pour afficher le DMA
     */
    @Override
    public String toString() {
        List<Evaluable> documents = new ArrayList<Evaluable>();
        documents = new Vector<Evaluable>();
        documents.addAll(ficheDeSoins);
        documents.addAll(lettreDeSortie);
        documents.addAll(examens);
        ComparaisonEvaluables c = new ComparaisonEvaluables();
        documents = c.trierEvaluablesParDate(documents);

        String ch = "------------------------DMA------------------------\n";
        for (int i = 0; i < documents.size(); i++) {
            Evaluable e = documents.get(i);
            ch = ch + e.toStringDMA() + "\n";
        }
        return ch;
    }

    /**
     * @return the IPP
     */
    public int getIPP() {
        return IPP;
    }

    /**
     * @param IPP the IPP to set
     */
    public void setIPP(int IPP) {
        this.IPP = IPP;
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
     * @return the rendezVous
     */
    public List<RendezVous> getRendezVous() {
        return rendezVous;
    }

    /**
     * @param rendezVous the rendezVous to set
     */
    public void setRendezVous(List<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
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
     * @return the localisation
     */
    public Localisation getLocalisation() {
        return localisation;
    }

    /**
     * @param localisation the localisation to set
     */
    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    /**
     * @return the documents
     */
    public List<Evaluable> getDocuments() {
        return documents;
    }

    /**
     * @param documents the documents to set
     */
    public void setDocuments(List<Evaluable> documents) {
        this.documents = documents;
    }

}
