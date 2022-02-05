/**
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * Permet de comparer deux fiches de soins suivant les couts
 * </ul>
 * Description des principales fonctionnalités de la classe
 * </p>
 * @author garcinle
 * @version 1
 */

package projetsis;

class ComparaisonFichesDates implements ComparaisonFiches {
    
    /**
     * 
     * @param fiche1
     * @param fiche2
     * @return entier pour savoir si les 2 fiches sont égales en date
     */
    public int comparer(FicheDeSoins fiche1, FicheDeSoins fiche2) {
        return fiche1.getDateHeure().compareTo(fiche2.getDateHeure());
    }
    
    
}
