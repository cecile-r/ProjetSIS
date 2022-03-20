/**
 * Cette classe est caractérisée par les informations suivantes :
 * <ul>
 * Permet de vérifier la bonne écriture du numéro de téléphone et le numero de
 * sécurité sociale
 * </ul>
 * Description des principales fonctionnalités de la classe
 * </p>
 *
 * @author garcinle
 * @version 1
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import static nf.DateHeure.convertirDateHeuretoString;

/**
 *
 * @author garci
 */
public class Checker {

    /*
    public static void main(String[] args) {
        String numero1 = "12 09 88 88 88";
        String numero2 = "1156784589";
        System.out.println(chekerTelephone(numero1));
        System.out.println(chekerTelephone(numero2));

        String secu = "2000938421087";
        String cle = "75";
        String secu2 = "2000938421087";
        String cle2 = "74";
        
        System.out.println(chekerNumero_secu(secu, cle));
        System.out.println(chekerNumero_secu(secu2, cle2));
    }        */
    /**
     *
     * @param telephone
     * @return vrai si la chaine a est dans le bon format de telephone
     */
    public static boolean checkerTelephone(String telephone) {
        String regex3 = "\\d{2} \\d{2} \\d{2} \\d{2} \\d{2}";
        return telephone.matches(regex3);
    }

    /**
     *
     * @param numero_secu
     * @param cle
     * @return vrai si le numero de secu est correct (format et cle)
     */
    public static boolean checkerNumero_secu(String numero_secu, String cle) {
        String regex = "\\d{13}";
        //on verifie d'abord qu'il y a 13 chiffres sinon la suite est inutile, le numero est deja incorrect
        if (!numero_secu.matches(regex)) {
            return false;
        } else { //on verifie avec la clé
            double cle2 = Integer.parseInt(cle);
            double numero_secu2 = Double.parseDouble(numero_secu);
            double cleCompare = 97 - (numero_secu2 % 97);
            return cleCompare == cle2;
        }
    }

    /**
     *
     * @param numero_secu
     * @return la cle a partir du numero secu
     */
    public double getCle(String numero_secu) {

        double numero_secu2 = Double.parseDouble(numero_secu);
        double cleCompare = 97 - (numero_secu2 % 97);
        return cleCompare;

    }

    public static boolean checkerDate(String date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(date);
            return true;
        } catch (ParseException ex) {
            Logger.getLogger(Checker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static String convertirDatetoString(Date d) {
        String d2 = "";
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        d2 = s.format(d);
        //d2 = d2 + d.getDate() + "/" + d.getMonth() + "/" + d.getYear();
        return d2;
    }

    public static Date convertirStringtoDate(String d1) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d2 = simpleDateFormat.parse(d1);
        return d2;
    }

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

    public static Vector getVectorPH(List<PH> phs) {
        Vector v = new Vector();

        for (int i = 0; i < phs.size(); i++) {
            Vector ls = new Vector();
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

    public static Vector getVectorDPI(List<DPI> dpis) {
        Vector v = new Vector();

        for (int i = 0; i < dpis.size(); i++) {
            Vector ls = new Vector();
            DPI dpi = dpis.get(i);
            ls.add(dpi.getNom());
            ls.add(dpi.getPrenom());
            ls.add(convertirDatetoString(dpi.getDateNaissance()));
            ls.add(dpi.getSexe().toString());
            v.add(ls);
        }
        return v;
    }

    public static Vector getVectorDPIFerme(List<DPI> dpis) {
        Vector v = new Vector();

        for (int i = 0; i < dpis.size(); i++) {
            Vector ls = new Vector();
            DPI dpi = dpis.get(i);
            ls.add(dpi.getNom());
            ls.add(dpi.getPrenom());
            ls.add(convertirDatetoString(dpi.getDateNaissance()));
            ls.add(dpi.getSexe().toString());
            v.add(ls);
        }
        return v;
    }

    public static Vector getVectorActes(List<Acte> actes) {
        Vector v = new Vector();

        for (int i = 0; i < actes.size(); i++) {
            Vector ls = new Vector();
            Acte a = actes.get(i);
            ls.add(a.getNomA());
            ls.add(a.getCode().toString());
            ls.add(a.getCoeff());
            ls.add(a.getObservation());
            v.add(ls);
        }
        return v;
    }

    public static Vector getVectorMT(List<MedecinTraitant> mts) {
        Vector v = new Vector();

        for (int i = 0; i < mts.size(); i++) {
            Vector ls = new Vector();
            MedecinTraitant mt = mts.get(i);
            ls.add(mt.getNomMedecinTraitant());
            ls.add(mt.getPrenomMedecinTraitant());
            ls.add(mt.getTelephoneMedecinTraitant());
            v.add(ls);
        }
        return v;
    }

    public static Vector getVectorPHRDV(List<PH> phs) {
        Vector v = new Vector();

        for (int i = 0; i < phs.size(); i++) {
            Vector ls = new Vector();
            PH ph = phs.get(i);
            ls.add(ph.toString());
            v.add(ls);
        }
        return v;
    }

    public static Vector getVectorSoinsQuotidien(List<Evaluable> sqs) {
        Vector v = new Vector();
        DecimalFormat df = new DecimalFormat("0.0"); //car sinon les doubles sont sur des grands nb de chiffres
        for (int i = 0; i < sqs.size(); i++) {
            Vector ls = new Vector();
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

    public static Vector getVectorSoinsQuotidienPH(List<Evaluable> sqs) {
        Vector v = new Vector();
        DecimalFormat df = new DecimalFormat("0.0"); //car sinon les doubles sont sur des grands nb de chiffres
        for (int i = 0; i < sqs.size(); i++) {
            Vector ls = new Vector();
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

    public static Vector getVectorPHplanning(List<Evaluable> evs) {
        Vector v = new Vector();
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
            Vector ls = new Vector();
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

    public static Vector getVectorSMplanningEntete(List<Evaluable> evs) {
        Vector v = new Vector();
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
    
    public static Vector getVectorHorairePlanning() {
        Vector v = new Vector();
        v.add("PH");
        for (int i = 8; i < 17; i++) {
            v.add(i+"h");
        }
        return v;
    }

    public static Vector getVectorSMplanning(List<Evaluable> evs) {
        List<PH> phs = new ArrayList<>();
        List<String> phString = new ArrayList<>();
        for (int i = 0; i < evs.size(); i++) {
            RendezVous rdv = (RendezVous) evs.get(i);
            if (!phString.contains(rdv.getpH().getIdPH())) {
                phs.add(rdv.getpH());
                phString.add(rdv.getpH().getIdPH());
            }
        }

        Vector v = new Vector();
        
        int index = 8;
        for (int i = 0; i < phs.size(); i++) { //pour chaque ph
            Vector ls= new Vector();
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
