/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author garci
 */
public class Date2 {
    
    
    /**
     * 
     * @return une chaine de caractère sous la forme ..-..-..
     */
    
    public String toString(Date date) {
        
    	return date.getYear() + "-" + date.getMonth()+ "-" + date.getDay();
    }

    /**
     * convertit une date en String sous la forme dd/MM/yyyy
     * @return la chaine de caractere sous la forme dd/MM/yyyy
     */
    public static String convertirDatetoString(Date d) {
        String d2 = "";
        SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
        d2 = s.format(d);
        return d2;
    }

    /**
     * convertit un String de la forme dd/MM/yyyy en date
     * @param d1 : une date en String sous la forme dd/MM/yyyy 
     * @return la date correspondante
     */
    public static Date convertirStringtoDate(String d1) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date d2 = simpleDateFormat.parse(d1);
        return d2;
    }
    
}
