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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static String convertirDatetoString(Date d){
        String d2 ="";
        d2 = d2 + d.getDay() +"/" + d.getMonth() +"/" +d.getYear();
        return d2;
    }

    public static Date convertirStringtoDate(String d1) throws ParseException{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d2 = simpleDateFormat.parse(d1);
        return d2;
    }
}
