/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author garci
 */
public class Hopital {
    private List<DPI> DPI = new ArrayList<DPI> ();
    
    public Hopital(){
        this.DPI= new Vector<DPI>();
    }
    
    public void ajouterDPI(DPI dpi){
        DPI.add(dpi);
    }
   
    /**
     * retourne la liste des PH
     *
     * @return
     */
    /*
    public List<PH> getListePH();
    */
    
    /**
     * retourne la liste des patients dossier ouvert
     * creer un nouveau patient
     * retourne la liste des patients dossier ouvert d'un service
     * @return
     */
    /*
    public List<DPI> getListeDPI();
    public void creerDPI(DPI dpi);
    public List<DPI> filtrerDPIparService(Service service);
    */
    
    
    /**
     * retourne l'IPP du patient recherché + association du DMA et DM associé
     *
     * @return
     */
    /*
    public List<DPI> filtrerDMAparService(String, nom, Sring prenom, Date2 date);
    }
    */
    
    /**
     * Liste des rdv résultant du filtrage par médecin, par date, par patient 
     *
     * @return
     */
    /*
    public List<RendezVous> ListeRdv();
    public List<RendezVous> filtrerRdvparPH(String nom, Sring prenom, Specialite);
    public List<RendezVous> filtrerRdvparDate(DateHeure dateHeure);
    public List<RendezVous> filtrerRdvparPatient(String nom, Sring prenom, Date dateNaissance);
    public void creerRdv(RendezVous rdv); 
    }
    */
    
    /**
     * Archivage
     *
     * @return
     */
    /*
    public void ajoutArchive(DPI dpi);
    public List<DPI> ListeArchive();
    */
    
    /**
    * Fiche de soins - Actes / Examens / lettre de sortie / soins quotidiens
    *
    * @return
    */
    /*
    public List<FicheDeSoins> ListeFichesDeSoins();
    public List<FicheDeSoins> ListeFichesDeSoinsEntreDates(DateHeure d1, DateHeure d2);
    public void ajoutFicheDeSoins(FicheDeSoins fs);
    public void ajoutActe(FicheDeSoins fs, Acte a);
    
    public List<Examen> ListeExamens();
    public List<Examen> ListeExamensEntreDates(DateHeure d1, DateHeure d2);
    public void ajoutExamen(Examen examen);
    
    public List<LettreDeSortie> ListeLettreDeSortie();
    public List<LettreDeSortie> ListeLettreDeSortieEntreDates(DateHeure d1, DateHeure d2);
    public void ajoutLettreDeSortie(LettreDeSortie lettre);
    
    public List<SoinsQuoditiens> ListeSoinsQuoditiens();
    public List<SoinsQuoditiens> ListeSoinsQuoditiensEntreDates(DateHeure d1, DateHeure d2);
    public void ajoutSoinsQuoditiens(SoinsQuoditiens soinsQ);
    
    */
    
    /**
    * Prescription
    *
    * @return
    */
    /*
    public List<Prescriptions> ListePrescriptions();
    public List<Prescriptions> ListePresciptionsEntreDates(DateHeure d1, DateHeure d2);
    public void ajoutPrescription(Prescriptions p);
    
    */
    

    

    /* TRI DE RDV PAR DATE*/
     public List<RendezVous> trierRdvParDate(List<RendezVous> liste) {
        Vector<RendezVous> newListe = new Vector<RendezVous>();

        while (!liste.isEmpty()) {
            // on cherche la fiche de soins de date minimale :
            int imin = 0;
            RendezVous rdv1 = liste.get(imin);
            for (int i = 1; i < liste.size(); i++) {
                RendezVous rdv2 = liste.get(i);
                if (rdv1.getDateHeure().compareTo(rdv2.getDateHeure()) < 0) {
                    imin = i;
                    rdv1 = rdv2;
                }
            }
            // on affiche la fiche de soins trouvee :
            newListe.add(rdv1);
            //on la supprime de la liste :
            liste.remove(rdv1);
        }
        return newListe;
    }
     
}
