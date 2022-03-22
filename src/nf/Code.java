/**
 *
 * @author garci
 */

package nf;

public enum Code {
    
    CS("consultation au cabinet", 23.0),
    CSC("consultation cardiologie", 45.73),
    FP("forfait pediatrique", 5.0),
    KC("actes de chirurgie et de specialite", 2.09),
    KE("actes d'echographie, de doppler", 1.89),
    K("autres actes de specialite", 1.92),
    KFA("forfait A", 30.49),
    KFB("forfait B", 60.98),
    ORT("orthodontie", 2.15),
    PRO("prothese dentaire", 2.15);
    
    
    
    private String libelle;
    private double cout;
    
     // constructeur :
    private Code(String libelle, double cout) {
        this.libelle = libelle;
        this.cout = cout;
        }
    
    /** 
     * 
     * @param coefficient --> coefficient pour le calcul de cout
     * @return le prix pour un coefficient donné
     */
    public double calculerCout(int coefficient) {
        return coefficient * getCout();
        }
    
    
    // m�thodes :
    /** 
     * 
     * @return une chaine de caractères sous la forme code : ... , cout = ... euros
     */
    @Override
    public String toString() {
        return super.toString() + " : " + getLibelle() + ", cout=" + getCout() + " euros";
        }
    
   

    /**
     * @return the libelle
     */
    public String getLibelle() {
        return libelle;
    }

    /**
     * @param libelle the libelle to set
     */
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    /**
     * @return the cout
     */
    public double getCout() {
        return cout;
    }

    /**
     * @param cout the cout to set
     */
    public void setCout(double cout) {
        this.cout = cout;
    }
    

    
}
