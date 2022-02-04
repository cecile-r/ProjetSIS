/**
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * Permet de comparer deux fiches de soins suivant les coûts
 * </ul>
 * Description des principales fonctionnalités de la classe
 * </p>
 * @author garcinle
 * @version 1
 */


package projetsis;



class ComparaisonFichesCouts implements ComparaisonFiches {
    
    /**
     * 
     * @param fiche1
     * @param fiche2
     * @return entier pour savoir si les 2 fiches sont égales en cout
     */
    public int comparer(FicheDeSoins fiche1, FicheDeSoins fiche2) {
        Double c1 = new Double(fiche1.coutTotal());
        Double c2 = new Double(fiche2.coutTotal());
        return c1.compareTo(c2);
        }
    }
