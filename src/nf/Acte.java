/**
 *
 * @author garci
 */

package nf;

public class Acte {

    private String nomA;
    private Type type;
    private Code code;
    private int coeff;
    private String observation;

    /**
     * Constructeur -> creer un acte
     *
     * @param nomA  : nom de l'acte
     * @param type : type d'acte
     * @param code : code de l'acte
     * @param coeff : coefficient de l'acte
     * @param observation : observation quelconque 
     */
    public Acte(String nomA, Type type, Code code, int coeff, String observation) {
        this.nomA = nomA;
        this.type = type;
        this.code = code;
        this.coeff = coeff;
        this.observation = observation;

    }

    /**
     * Calcule le cout d'un acte
     *
     * @return double
     */
    public double cout() {
        return getCode().calculerCout(getCoeff());
    }

    /**
     * Réécriture toString Retourne une chaine de caractere de la forme nom,
     * code, coefficient, type, observation
     *
     * @return String
     */
    @Override
    public String toString() {
        return "         Nom : " + getNomA() + "\n         Code : " + getCode().toString() + "\n         Coefficient : " + getCoeff() + "\n         Type : " + getType() + "\n         Observation : " + getObservation();
    }

    /**
     * Retour la chaine de caractere qui affiche l'acte pour le DMA
     * Avec les restrictions d'affichage appliquées au DMA
     *
     * @return String
     */
    public String toStringDMA() {
        return "         Nom : " + getNomA() + "\n";
    }

    /**
     * 
     * @return String
     */
    public String getNomA() {
        return nomA;
    }

    /**
     * @param nomA the nomA to set
     */
    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    /**
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * @return the code
     */
    public Code getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(Code code) {
        this.code = code;
    }

    /**
     * @return the coeff
     */
    public int getCoeff() {
        return coeff;
    }

    /**
     * @param coeff the coeff to set
     */
    public void setCoeff(int coeff) {
        this.coeff = coeff;
    }

    /**
     * @return the observation
     */
    public String getObservation() {
        return observation;
    }

    /**
     * @param observation the observation to set
     */
    public void setObservation(String observation) {
        this.observation = observation;
    }

}
