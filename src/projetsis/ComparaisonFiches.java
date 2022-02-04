/**
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * Permet de comparer des fiches
 * </ul>
 * Description des principales fonctionnalités de la classe
 * </p>
 * @author garcinle
 * @version 1
 */


package projetsis;



/**
 * cette interface permet de representer une comparaison entre deux FicheDeSoins
 * @author garci
 */
interface ComparaisonFiches {
    // la fonction 'comparer' doit retourner :
    //    <0  si fiche1 < fiche2
    //     0  si fiche1 == fiche2
    //    >0  si fiche1 > fiche2
    public int comparer(FicheDeSoins fiche1, FicheDeSoins fiche2);
}
