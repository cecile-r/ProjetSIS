package projetsis;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class DMA {
    
    private int IPP;
    //!\ restriction du DMA : pas d'accès aux actes dans les fiches de soins
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins> ();
    //!\ restriction du DMA : pas d'accès aux résultats, juste à l'examen
    private List<Examen> examens = new ArrayList<Examen> ();
    private List<RendezVous> rendezVous = new ArrayList<RendezVous> ();
    private List<LettreDeSortie> lettreDeSortie = new ArrayList<LettreDeSortie> ();
    private Localisation localisation;
    
    public DMA(Localisation localisation) {
        this.ficheDeSoins = new Vector<FicheDeSoins>();
        this.rendezVous = new Vector<RendezVous>();
        this.examens=  new Vector<Examen>();
        this.lettreDeSortie= new Vector<LettreDeSortie>();
        this.localisation=localisation;
    }

}
