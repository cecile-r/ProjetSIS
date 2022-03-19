/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import static nf.DateHeure.convertirStringtoDateHeure;

/**
 *
 * @author garci
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
    Hopital h = new Hopital();
    
    Infirmier inf1 = new Infirmier("3587492736","Lo","Anna",Service.Biologie_clinique,"momodepasse");
    PH ph1 = new PH("1616161616","Pan","Peter",Service.Biologie_clinique,"peterpan","0456486756","Biologie");
         
    Acte a11 = new Acte("prise de sang",Type.diagnostic,Code.CS,2, "RAS");
    Acte a12 = new Acte("changement pansement",Type.therapeutique,Code.FP,1, "Propre");
    DateHeure d11 = new DateHeure(2022,02,10,10,00); //date Fiche de soins
    FicheDeSoins fs1 = new FicheDeSoins(d11);
    fs1.setpH(ph1);
    fs1.ajouterActe(a11);
    fs1.ajouterActe(a12);
    DateHeure d12 = new DateHeure(2022,02,10,11,00); //date presciption
    Prescription p1 = new Prescription(d12,"a prendre 2 fois par jour pendant 7 jours",null,"Doliprane");
    p1.setpH(ph1);
    DateHeure d13 = new DateHeure(2022,02,10,14,00); //date de sortie
    LettreDeSortie ls1= new LettreDeSortie("Madame ... sort de l'hopital",d13);
    ls1.setPh(ph1);
    DateHeure d14 = new DateHeure(2022,02,10,10,30); //date de soin quotidien
    DateHeure d15 = new DateHeure(2022,02,10,8,30); //date de soin quotidien
    SoinsQuotidien sq1 = new SoinsQuotidien(37.5,80,12.8,"RSA",d14);
    SoinsQuotidien sq2 = new SoinsQuotidien(37.0,80,12.9,"RSA",d15);
    sq1.setInfirmier(inf1);
    sq2.setInfirmier(inf1);
    DateHeure d16 = new DateHeure(2022,02,10,11,30); //date de l'examen
    Examen e1 = new Examen(TypeExamen.biopsie,"cancer trouvé",d16);
    e1.setPh(ph1);
    DM dm1 = new DM();
    dm1.ajouterFicheDeSoins(fs1);
    dm1.ajouterLettreDeSortie(ls1);
    dm1.ajouterPrescription(p1);
    dm1.ajouterSoinsQuotidien(sq1);
    dm1.ajouterSoinsQuotidien(sq2);
    dm1.ajouterExamen(e1);
    Localisation l1 = new Localisation(Service.Biologie_clinique,Lit.F,12,Service.Addictologie);
    DMA dma1 = new DMA(l1);
    dma1.ajouterFicheDeSoins(fs1);
    dma1.ajouterLettreDeSortie(ls1);
    dma1.ajouterExamen(e1);
    DateHeure d17 = new DateHeure(2022,02,15,10,00); //date de RDV
    RendezVous rdv1 = new RendezVous(d17, "reverifier les resultats");
    rdv1.setpH(ph1);
    dma1.ajouterRendezVous(rdv1);
    MedecinTraitant mt1 = new MedecinTraitant("id@gmail.com","PAT", "PATROUILLE","0467894567");
    Date dn1 = new Date(1997,07,13); //date de naissance
    DPI dpi1 = new DPI("1234567891","Jones","Jack",dn1,Sexe.homme,"3 rue Beranger, 45000 Tours","0657985613",mt1,dma1,dm1);
    
    inf1.ajouterSoinsQuotidien(sq1);
    inf1.ajouterSoinsQuotidien(sq2);
    ph1.ajouterFicheDeSoins(fs1);
    ph1.ajouterLettresDeSorties(ls1);
    ph1.ajouterPrescriptions(p1);
    ph1.ajouterExamen(e1);
    ph1.ajouterRdv(rdv1);
    sq1.setDPI(dpi1);
    sq2.setDPI(dpi1);
    fs1.setDPI(dpi1);
    ls1.setDPI(dpi1);
    p1.setDPI(dpi1);
    e1.setDPI(dpi1);
    rdv1.setDPI(dpi1);
    
    h.ajouterDPI(dpi1);
    
    //System.out.println(dma1.toString());
    System.out.println(rdv1.toStringDMA());
    */
    
    }
    
    
    
}
