/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

/**
 *
 * @author garci
 */
public class Date2 {
    private int jour;
    private int mois;
    private int annee;
    
    public Date2(int jour, int mois, int annee) {
        this.jour = jour;
        this.mois = mois;
        this.annee = annee;
    }
    
    /**
     * 
     * @return une chaine de caractère sous la forme ..-..-..
     */
    @Override
    public String toString() {
    	return annee + "-" + mois + "-" + jour;
    }
     

    
}