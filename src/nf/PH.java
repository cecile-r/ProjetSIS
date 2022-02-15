package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;



public class PH {
    
    private String IdPH;
    private String nomPH;
    private String prenomPH;
    private Service service;
    private String mdp;
    private String telephone;
    private String specialite;
    private List<FicheDeSoins> ficheDeSoins = new ArrayList<FicheDeSoins> ();
    private List<Prescription> precriptions = new ArrayList<Prescription>();
    private List<LettreDeSortie> lettresDeSorties = new ArrayList<LettreDeSortie>();
    private List<Examen> examens = new ArrayList<Examen>();
    private List<RendezVous> rendezVous = new ArrayList<RendezVous>();
    
    public PH(String idPH, String nomPh, String prenomPH,Service service, String mdp, String telephone, String specialite){
        this.IdPH=idPH;
        this.nomPH=nomPh;
        this.prenomPH=prenomPH;
        this.service=service;
        this.mdp=mdp;
        this.telephone=telephone;
        this.specialite=specialite;
        this.ficheDeSoins= new Vector<FicheDeSoins>();
        this.precriptions= new Vector<Prescription>();
        this.lettresDeSorties = new Vector<LettreDeSortie>();
        this.examens = new Vector<Examen>();
        this.rendezVous = new Vector<RendezVous>();
    }
    
    @Override
    public String toString() {
       return prenomPH + " " + nomPH + ", " + specialite;
    }
    
    public String toStringDetail() {
        String ch= "\n\n\n";
        ch = ch+"Prénom : "+prenomPH + "\n\n";
        ch = ch+"Nom : " + nomPH + "\n\n";
        ch = ch+"Service : " + service + "\n\n";
        ch = ch+"Téléphone : " + telephone+ "\n\n";
        ch = ch+"Spécialité : " + specialite+"\n\n";
        return ch;
    }
    
    
    /**
     * @return the IdPH
     */
    public String getIdPH() {
        return IdPH;
    }

    /**
     * @param IdPH the IdPH to set
     */
    public void setIdPH(String IdPH) {
        this.IdPH = IdPH;
    }

    /**
     * @return the nomPH
     */
    public String getNomPH() {
        return nomPH;
    }

    /**
     * @param nomPH the nomPH to set
     */
    public void setNomPH(String nomPH) {
        this.nomPH = nomPH;
    }

    /**
     * @return the prenomPH
     */
    public String getPrenomPH() {
        return prenomPH;
    }

    /**
     * @param prenomPH the prenomPH to set
     */
    public void setPrenomPH(String prenomPH) {
        this.prenomPH = prenomPH;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }

    /**
     * @return the mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * @param mdp the mdp to set
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    /**
     * @return the telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * @param telephone the telephone to set
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * @return the specialite
     */
    public String getSpecialite() {
        return specialite;
    }

    /**
     * @param specialite the specialite to set
     */
    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    /**
     * @return the ficheDeSoins
     */
    public List<FicheDeSoins> getFicheDeSoins() {
        return ficheDeSoins;
    }

    /**
     * @param ficheDeSoins the ficheDeSoins to set
     */
    public void setFicheDeSoins(List<FicheDeSoins> ficheDeSoins) {
        this.ficheDeSoins = ficheDeSoins;
    }
    
    
    public void ajouterFicheDeSoins(FicheDeSoins fs){
        ficheDeSoins.add(fs);
    }
    
    public void ajouterPrescriptions(Prescription p){
        precriptions.add(p);
    }
    
    public void ajouterLettresDeSorties(LettreDeSortie ls){
        lettresDeSorties.add(ls);
    }
    
    public void ajouterExamen(Examen e){
        examens.add(e);
    }
    
    public void ajouterRdv(RendezVous rdv){
        rendezVous.add(rdv);
    }
    
    
}
