package projetsis;

public class Examen  {
    
    private String resultats;
    private TypeExamen type_examen;

    public Examen(TypeExamen type_examen, String resultats){
        this.resultats=resultats;
        this.resultats = resultats;
    }

    /**
     * @return the resultats
     */
    public String getResultats() {
        return resultats;
    }

    /**
     * @param resultats the resultats to set
     */
    public void setResultats(String resultats) {
        this.resultats = resultats;
    }

    /**
     * @return the type_examen
     */
    public TypeExamen getType_examen() {
        return type_examen;
    }

    /**
     * @param type_examen the type_examen to set
     */
    public void setType_examen(TypeExamen type_examen) {
        this.type_examen = type_examen;
    }
    
}
