/*
Fonctions relatives aux conversions
 */
package database;

import java.util.Date;
import nf.DateHeure;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDConversion {
    
    //Convertir une date sql en date java correctement
    //VALIDE
    public static Date convertDateSQLenJava(java.sql.Date date){
        Date dateReelle = new Date(date.getYear(), date.getMonth() - 1, date.getDate());
        return dateReelle;
    }
    
    //Convertir une date heure sql en dateheure java correctement
    //VALIDE
    public static DateHeure convertTimestampSQLenJava(java.sql.Timestamp timestamp){
        DateHeure dh = new DateHeure(timestamp.getYear()+1900, timestamp.getMonth()+1, timestamp.getDate(), timestamp.getHours(), timestamp.getMinutes());
        return dh;
    }
    
    //Convertir une date Java en Date SQL correctement
    //VALIDE
    public static java.sql.Date convertDateJavaEnSQL(Date d){
        Date dateReelle = new Date(d.getYear()-1900, d.getMonth()-1, d.getDate());
        java.sql.Date dateSQL = new java.sql.Date(dateReelle.getTime());
        return dateSQL;
    }
    
    //Convertir une date Java en Date SQL correctement
    //VALIDE
    public static java.sql.Timestamp convertDateHeureJavaEnTimestampSQL(DateHeure dh){
        DateHeure dateHeureReelle = new DateHeure(dh.getAnnee()-1900, dh.getMois()-1, dh.getJour(), dh.getHeure(), dh.getMinutes());
        java.sql.Timestamp timestampSQL = new java.sql.Timestamp(dateHeureReelle.getAnnee(),dateHeureReelle.getMois(),dateHeureReelle.getJour(), dateHeureReelle.getHeure(), dateHeureReelle.getMinutes(), 0, 0);
        return timestampSQL;
    }
    
    //Renvoie en string le timestamp sous la forme DD-MON-YYYY HH:MI:SS AM ou PM
    //VALIDE
    public static String toStringTimestamp(java.sql.Timestamp ts){
        //String year = String.valueOf(ts.getYear()+1900).substring(2, 4);
        int year = ts.getYear()+1900;
        int month = ts.getMonth()+1;
        int day = ts.getDate();
        int hour = ts.getHours();
        int min = ts.getMinutes();
        String s = day + "-";
        
        //Conditions des mois
        if(month==1){
            s = s + "JAN-" + year + " ";
        }
        else if(month==2){
            s = s + "FEB-" + year + " ";
        }
        else if(month==3){
            s = s + "MAR-" + year + " ";
        }
        else if(month==4){
            s = s + "APR-" + year + " ";
        }
        else if(month==5){
            s = s + "MAY-" + year + " ";
        }
        else if(month==6){
            s = s + "JUN-" + year + " ";
        }
        else if(month==7){
            s = s + "JUL-" + year + " ";
        }
        else if(month==8){
            s = s + "AUG-" + year + " ";
        }
        else if(month==9){
            s = s + "SEP-" + year + " ";
        }
        else if(month==10){
            s = s + "OCT-" + year + " ";
        }
        else if(month==11){
            s = s + "NOV-" + year + " " ;
        }
        else if(month==12){
            s = s + "DEC-" + year + " ";
        }
        
        //s = s + hour + ":" + min + ":00.000000";

        //Conditions des heures
        if(hour > 12 && hour <= 24){//si c'est pm
            if((hour - 12) >= 10){
                s = s + (hour - 12) + ":" + min + ":00.000000";
            }
            else{
                s = s + "0" + (hour - 12) + ":" + min + ":00.000000";
            }
            
        }
        else if(0 <= hour && hour <=12){ //si c'est am
            s = s + "0" + hour + ":" + min + ":00.000000";
        }
        
        return s;
    }

    
}
