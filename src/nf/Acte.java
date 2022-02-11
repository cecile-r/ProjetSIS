package nf;

public class Acte {
    private String nomA;
    private Type type;
    private Code code;
    private int coeff;
    private String observation;
    private int idActe;

    public Acte(String nomA, Type type, Code code, int coeff, String observation) {
        this.nomA = nomA;
        this.type = type;
        this.code = code;
        this.coeff = coeff;
        this.observation = observation;

    }
    
    public double cout() {
      return getCode().calculerCout(getCoeff());
    }
    
        /**
     * @return une chaine de caractère avec nom, prenom, code, coefficient , type, observation
     * */
    @Override
    public String toString() {
        return "         Nom : " + getNomA() + "\n         Code : " + getCode().toString() + "\n         Coefficient : " + getCoeff() + "\n         Type : " + getType() + "\n         Observation : " + getObservation();
    }

    /**
     * @return une chaine de caractère avec code, coefficient , type, observation
     */
    
    public String toString2() {
        return getCode().toString() + " Coefficient : " + getCoeff() + " Type : " + getType() + " Observation : " + getObservation();
    }
    
    public String toStringDMA() {
        return "         Nom : " + getNomA() + "\n";
    }


    /**
     * @return the nomA
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

    /**
     * @return the idActe
     */
    public int getIdActe() {
        return idActe;
    }

    /**
     * @param idActe the idActe to set
     */
    public void setIdActe(int idActe) {
        this.idActe = idActe;
    }
}
