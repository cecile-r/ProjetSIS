/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.util.List;
import java.util.Vector;

/**
 *
 * @author garci
 */
public class ComparaisonEvaluables {
    
    
    public int comparer(Evaluable o1, Evaluable o2){
        DateHeure d1= o1.getDateHeure();
        DateHeure d2= o2.getDateHeure();
        return d1.compareTo(d2);
    }
    
        
    /* TRI D'EVALUABLES PAR DATE*/
     public List<Evaluable> trierEvaluablesParDate(List<Evaluable> liste) {
        Vector<Evaluable> newListe = new Vector<Evaluable>();

        while (!liste.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            Evaluable e1 = liste.get(imin);
            for (int i = 1; i < liste.size(); i++) {
                Evaluable e2 = liste.get(i);
                if (comparer(e1, e2) < 0) {
                    imin = i;
                    e1 = e2;
                }
            }
            // on affiche la fiche de soins trouvee :
            newListe.add(e1);
            //on la supprime de la liste :
            liste.remove(e1);
        }
        return newListe;
    }
     
    
        
}