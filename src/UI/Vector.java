/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UI;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import nf.Acte;
import nf.DPI;
import static nf.Date2.convertirDatetoString;
import static nf.DateHeure.convertirDateHeuretoString;
import nf.Evaluable;
import nf.MedecinTraitant;
import nf.PH;
import nf.RendezVous;
import nf.SoinsQuotidien;

/**
 *
 * @author garci
 */
public class Vector {
    
    public static List<PH> trierPH(List<PH> phs) {
        int nbr = phs.size();
        PH tmp;

        for (int i = 0; i < nbr; i++) {
            for (int j = i + 1; j < nbr; j++) {
                if (phs.get(i).compareTo(phs.get(j)) > 0) {
                    tmp = phs.get(i);
                    phs.set(i, phs.get(j));
                    phs.set(j, tmp);
                }
            }
        }
        return phs;
    }

    public static java.util.Vector getVectorPH(List<PH> phs) {
        java.util.Vector v = new java.util.Vector();

        for (int i = 0; i < phs.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            PH ph = phs.get(i);
            ls.add(ph.getNomPH());
            ls.add(ph.getPrenomPH());
            ls.add(ph.getService().toString());
            v.add(ls);
        }
        return v;
    }

    public static List<DPI> trierDPI(List<DPI> dpis) {
        int nbr = dpis.size();
        DPI tmp;

        for (int i = 0; i < nbr; i++) {
            for (int j = i + 1; j < nbr; j++) {
                if (dpis.get(i).compareTo(dpis.get(j)) > 0) {
                    tmp = dpis.get(i);
                    dpis.set(i, dpis.get(j));
                    dpis.set(j, tmp);
                }
            }
        }
        return dpis;
    }

    public static java.util.Vector getVectorDPI(List<DPI> dpis) {
        java.util.Vector v = new java.util.Vector();

        for (int i = 0; i < dpis.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            DPI dpi = dpis.get(i);
            ls.add(dpi.getNom());
            ls.add(dpi.getPrenom());
            ls.add(convertirDatetoString(dpi.getDateNaissance()));
            ls.add(dpi.getSexe().toString());
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorDPIFerme(List<DPI> dpis) {
        java.util.Vector v = new java.util.Vector();

        for (int i = 0; i < dpis.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            DPI dpi = dpis.get(i);
            ls.add(dpi.getNom());
            ls.add(dpi.getPrenom());
            ls.add(convertirDatetoString(dpi.getDateNaissance()));
            ls.add(dpi.getSexe().toString());
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorActes(List<Acte> actes) {
        java.util.Vector v = new java.util.Vector();

        for (int i = 0; i < actes.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            Acte a = actes.get(i);
            ls.add(a.getNomA());
            ls.add(a.getCode().toString());
            ls.add(a.getCoeff());
            ls.add(a.getObservation());
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorMT(List<MedecinTraitant> mts) {
        java.util.Vector v = new java.util.Vector();

        for (int i = 0; i < mts.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            MedecinTraitant mt = mts.get(i);
            ls.add(mt.getNomMedecinTraitant());
            ls.add(mt.getPrenomMedecinTraitant());
            ls.add(mt.getTelephoneMedecinTraitant());
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorPHRDV(List<PH> phs) {
        java.util.Vector v = new java.util.Vector();

        for (int i = 0; i < phs.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            PH ph = phs.get(i);
            ls.add(ph.toString());
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorSoinsQuotidien(List<Evaluable> sqs) {
        java.util.Vector v = new java.util.Vector();
        DecimalFormat df = new DecimalFormat("0.0"); //car sinon les doubles sont sur des grands nb de chiffres
        for (int i = 0; i < sqs.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            SoinsQuotidien sq = (SoinsQuotidien) sqs.get(i);
            ls.add(convertirDateHeuretoString(sq.getDateHeure()));
            ls.add(sq.getInfirmier().toString());
            String temp = df.format(sq.getTemperature());
            ls.add(temp);
            temp = df.format(sq.getSaturationO2());
            ls.add(temp);
            temp = df.format(sq.getTension());
            ls.add(temp);
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorSoinsQuotidienPH(List<Evaluable> sqs) {
        java.util.Vector v = new java.util.Vector();
        DecimalFormat df = new DecimalFormat("0.0"); //car sinon les doubles sont sur des grands nb de chiffres
        for (int i = 0; i < sqs.size(); i++) {
            java.util.Vector ls = new java.util.Vector();
            SoinsQuotidien sq = (SoinsQuotidien) sqs.get(i);
            ls.add(convertirDateHeuretoString(sq.getDateHeure()));
            ls.add(sq.getInfirmier().toString());
            String temp = df.format(sq.getTemperature());
            ls.add(temp);
            temp = df.format(sq.getSaturationO2());
            ls.add(temp);
            temp = df.format(sq.getTension());
            ls.add(temp);
            ls.add(sq.getObservations());
            v.add(ls);
        }
        return v;
    }

    public static java.util.Vector getVectorPHplanning(List<Evaluable> evs) {
        java.util.Vector v = new java.util.Vector();
        /*if(evs.isEmpty()){
            for(int i=8;i<17;i++){
                Vector ls = new Vector();
                ls.add(i+"h");
                ls.add("");
                ls.add("");
                v.add(ls);
            }
            return v;
        }*/
        int j = 0;
        for (int i = 8; i < 17; i++) {
            java.util.Vector ls = new java.util.Vector();
            ls.add(i + "h");
            if (evs.size() > j) {
                RendezVous rdv = (RendezVous) evs.get(j);
                if (rdv.getDateHeure().getHeure() == i) {
                    ls.add(rdv.getDPI().toStringSimple());
                    ls.add(rdv.getRemarque());
                    j++;
                } else {
                    ls.add("");
                    ls.add("");
                }
                v.add(ls);
            } else {
                ls.add("");
                ls.add("");
                v.add(ls);
            }
        }
        return v;
    }

    public static java.util.Vector getVectorSMplanningEntete(List<Evaluable> evs) {
        java.util.Vector v = new java.util.Vector();
        List<PH> phs = new ArrayList<>();
        for (int i = 0; i < evs.size(); i++) {
            RendezVous rdv = (RendezVous) evs.get(i);
            if (!phs.contains(rdv.getpH())) {
                phs.add(rdv.getpH());
                v.add(rdv.getpH().toStringDetail());
            }
        }
        return v;
    }
    
    public static java.util.Vector getVectorHorairePlanning() {
        java.util.Vector v = new java.util.Vector();
        v.add("PH");
        for (int i = 8; i < 17; i++) {
            v.add(i+"h");
        }
        return v;
    }

    public static java.util.Vector getVectorSMplanning(List<Evaluable> evs) {
        List<PH> phs = new ArrayList<>();
        List<String> phString = new ArrayList<>();
        for (int i = 0; i < evs.size(); i++) {
            RendezVous rdv = (RendezVous) evs.get(i);
            if (!phString.contains(rdv.getpH().getIdPH())) {
                phs.add(rdv.getpH());
                phString.add(rdv.getpH().getIdPH());
            }
        }

        java.util.Vector v = new java.util.Vector();
        
        int index = 8;
        for (int i = 0; i < phs.size(); i++) { //pour chaque ph
            java.util.Vector ls= new java.util.Vector();
            ls.add(phs.get(i).toString());
            for (int j = 0; j < evs.size(); j++) {//on parcours la liste des rdvs
                RendezVous rdv = (RendezVous) evs.get(j);
                PH phActu = rdv.getpH();
                if (phActu.getIdPH().equals(phs.get(i).getIdPH())) {//si on trouve notre ph
                    while(rdv.getDateHeure().getHeure() != index) {
                        ls.add("");
                        index++;
                    }
                    ls.add(rdv.getDPI().toStringSimple());
                    
                }
            }
            index=8;
            v.add(ls);
        }
        return v;

    }
}

