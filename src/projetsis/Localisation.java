package projetsis;

public class Localisation {
    
    private Service service_responsable;
    private Lit lit;
    private int nchambre;
    private Service service_geographique;

    public Localisation(Service service_responsable, Lit lit, int nchambre, Service service_geographique){
        this.service_geographique=service_geographique;
        this.service_responsable=service_responsable;
        this.lit=lit;
        this.nchambre=nchambre;
    }
    
    @Override
    public String toString(){
        String ch="";
        ch=ch+"Service responsable : "+service_responsable+"\n";
        ch=ch+"Service géographique : "+service_geographique+"\n";
        ch=ch+"Chambre : " + nchambre+ " " +lit.toString2(lit)+"\n";
        return ch;
    }

    /**
     * @return the service_responsable
     */
    public Service getService_responsable() {
        return service_responsable;
    }

    /**
     * @param service_responsable the service_responsable to set
     */
    public void setService_responsable(Service service_responsable) {
        this.service_responsable = service_responsable;
    }

    /**
     * @return the lit
     */
    public Lit getLit() {
        return lit;
    }

    /**
     * @param lit the lit to set
     */
    public void setLit(Lit lit) {
        this.lit = lit;
    }

    /**
     * @return the nchambre
     */
    public int getNchambre() {
        return nchambre;
    }

    /**
     * @param nchambre the nchambre to set
     */
    public void setNchambre(int nchambre) {
        this.nchambre = nchambre;
    }

    /**
     * @return the service_geographique
     */
    public Service getService_geographique() {
        return service_geographique;
    }

    /**
     * @param service_geographique the service_geographique to set
     */
    public void setService_geographique(Service service_geographique) {
        this.service_geographique = service_geographique;
    }
}
