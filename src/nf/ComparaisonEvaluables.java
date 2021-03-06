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
    
    /** 
     * 
     * @param o1 : evaluable 1
     * @param o2 : evaluable 2
     * @return un entier représentant la différence entre les 2 dates 
     */
    public static int comparer(Evaluable o1, Evaluable o2){
        DateHeure d1= o1.getDateHeure();
        DateHeure d2= o2.getDateHeure();
        return d1.compareTo(d2);
    }
    
        
    /** 
     * 
     * @param liste : liste d'evaluables (examen, lettre de sortie, fiche de soins, prescription, constantes, rdv)
     * @return le prix pour un coefficient donné
     */
    public static List<Evaluable> trierEvaluablesParDate(List<Evaluable> liste) {
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
     
    /** 
     * Tri les evaluables par date
     * @param es : liste d'evaluables (examen, lettre de sortie, fiche de soins, prescription, constantes, rdv)
     * @return la liste d'evaluables triee
     */
     public static List<Evaluable> trierEvaluables(List<Evaluable> es) {
        int nbr = es.size();
        Evaluable tmp;

        for (int i = 0; i < nbr; i++) {
            for (int j = i + 1; j < nbr; j++) {
                if (comparer(es.get(i),es.get(j))<0) {
                    tmp = es.get(i);
                    es.set(i, es.get(j));
                    es.set(j, tmp);
                }
            }
        }
        return es;
    }
    
        
}
