/*
Fonctions relatives aux conversions
 */
package database;

import java.util.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import nf.DateHeure;

/**
 *
 * @author cecilerichard
 */
public class RequetesBDConversion {
    
    //Convertir une Date java en DateHeure avec heure et min 00:00
    //VALIDE
    public static Timestamp convertDateJavaEnTimestampJavaMin(Date date){
        Timestamp t = new Timestamp(date.getYear()-1900, date.getMonth()-1, date.getDate(), 0, 0 ,0 ,0);
        return t;
    }
    
    //Convertir une Date java en DateHeure avec heure et min 23:59
    //VALIDE
    public static Timestamp convertDateJavaEnTimestampJavaMax(Date date){
        Timestamp t = new Timestamp(date.getYear()-1900, date.getMonth()-1, date.getDate(), 23, 59, 0, 0);
        return t;
    }
    
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
    
    //Convertir une date heure Java en Timestamp SQL correctement
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
        int year = ts.getYear();
        int month = ts.getMonth();
        int day = ts.getDate();
        int hour = ts.getHours();
        int min = ts.getMinutes();
        String s;
        
        //Condition du jour
        if(day < 10){
            s = "0" + day + "-";
        }
        else{
            s = day + "-";
        }
        
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

        //Conditions des heures
        if(hour >= 13){//si c'est pm
            if((hour - 12) >= 10){
                if(min == 0){
                    s = s + (hour - 12) + ":00:00 PM";
                }
                else{
                    s = s + (hour - 12) + ":" + min + ":00 PM";
                }
            }
            else{
                if(min == 0){
                    s = s + "0" + (hour - 12) + ":00:00 PM";
                }
                else{
                    s = s + "0" + (hour - 12) + ":00:00 PM";
                }
            }
            
        }
        else if(0 <= hour && hour <13){ //si c'est am
            if(hour >= 10){
                if(min == 0){
                    s = s + hour + ":00:00 AM";
                }
                else{
                    s = s + hour + ":" + min + ":00 AM";
                }
            }
            else{
                if(min == 0){
                    s = s + "0" + hour + ":00:00 AM";
                }
                else{
                    s = s + "0" + hour + ":" + min + ":00 AM";
                }
            }
        }
        
        return s;
    }
    
    //Renvoie en string le timestamp sous la forme DD-MON-YYYY HH:MI:SS AM ou PM
    //VALIDE
    public static String toStringTimestampJAVA(java.sql.Timestamp ts){
        //String year = String.valueOf(ts.getYear()+1900).substring(2, 4);
        int year = ts.getYear()+1900;
        int month = ts.getMonth()+1;
        int day = ts.getDate();
        int hour = ts.getHours();
        int min = ts.getMinutes();
        int sec = ts.getSeconds();
        String s = year + "-" + month + "-" + day + " " + hour + ":" + min + ":" + sec;
        
        return s;
    }

    //Renvoie une Date java à partir d'une LocalDate
    //VALIDE
    public static Date convertLocalDateEnDate(LocalDate ld){
        Date d = new Date(ld.getYear(), ld.getMonthValue(), ld.getDayOfMonth());
        return d;
    }
}
