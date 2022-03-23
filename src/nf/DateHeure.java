package nf;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class DateHeure {
    private int annee;
    private int mois;
    private int jour;
    private int heure;
    private int minutes;

    public DateHeure(int annee, int mois, int jour, int heure, int minute){
     this.annee=annee;
     this.mois=mois;
     this.jour = jour;
     this.heure = heure;
     this.minutes=minute;
    }
       
    
    /**
     * 
     * @param o
     * @return vrai si les dates sont les memes
     */
    public boolean equals(Object o) {
        if (o instanceof DateHeure) {
            DateHeure dh = (DateHeure)o;
            return (getAnnee() == dh.getAnnee()) && (getMois() == dh.getMois()) && (getJour() == dh.getJour())&& (getHeure() == dh.getHeure())&& (getMinutes() == dh.getMinutes());
            }
        else
            return false;
        }
    
    // precondition : 'o' est une instance de 'Date' :
    /**
     * 
     * @param o
     * @return un entier representant l'ecart entre les dates
     */
    public int compareTo(Object o) {
        DateHeure d = (DateHeure)o;
        if (getAnnee() != d.getAnnee())
            return getAnnee() - d.getAnnee();
        // ici on a forcement annee == d.annee :
        if (getMois() != d.getMois())
            return getMois()  - d.getMois();
        // ici on a forcement annee == d.annee et mois == d.mois :
        if (getJour() != d.getJour())
            return getJour()  - d.getJour();
        if (getHeure() != d.getHeure())
            return getHeure() -d.getHeure();
        return getMinutes() - d.getMinutes() ;
        }
    
    
    /**
     * 
     * @return une chaine de caractère sous la forme ..-..-..
     */
     public String StringDate() {
    	return annee + "-" + mois + "-" + jour;
 	}
     
     
    /**
     * 
     * @return une chaine de caractère sous la forme ..-..-.. ..:..
     */
     public String StringDateHeure() {
    	return annee + "-" + mois + "-" + jour + " " + heure +":"+minutes;
 	}
     
     

     /**
      * remplit la date à partir d'une chaine de caractères de format ..-..-..
      * 
      * @param donneesCourantes 
      */
    public void creerDate(String donneesCourantes) {
        setAnnee(Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-'))));
        setMois(Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-'))));
        setJour(Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length())));
    }

    public void creerHeure(String donneesCourantes) {
        setHeure(Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf(':'))));
        setMinutes(Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf(':') + 1, donneesCourantes.length())));
    }
    
	 
    /**
     * remplit la date à partir d'une chaine de caractères de format ../../..
     * @param donneesCourantes 
     */
    public void creerDate2(String donneesCourantes) {
        setJour(Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('/'))));
        setMois(Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('/') + 1, donneesCourantes.lastIndexOf('/'))));
        setAnnee(Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('/') + 1, donneesCourantes.length())));
    }
    
    public static String convertirDateHeuretoString(DateHeure d){
        String d2 ="";
        d2 = d2 + d.jour +"/" + d.mois +"/" +d.annee +" " + d.heure+ ":"+d.minutes;
        return d2;
    }
    
    public static DateHeure convertirStringtoDateHeure(String ch){
        String[] split = ch.split(" ");
        String[] date = split[0].split("/");
        String[] h = split[1].split(":");
        int jour = Integer.parseInt(date[0]);
        int mois = Integer.parseInt(date[1]);
        int annee = Integer.parseInt(date[2]);
        int heure = Integer.parseInt(h[0]);
        int minutes = Integer.parseInt(h[1]);
        DateHeure dh = new DateHeure(annee,mois,jour,heure,minutes);
        return dh;
    }
    
    public static DateHeure convertirStringtoDateHeureSpecialJournee(String ch){
     
        String[] date = ch.split("/");
        int jour = Integer.parseInt(date[0]);
        int mois = Integer.parseInt(date[1]);
        int annee = Integer.parseInt(date[2]);
        DateHeure dh = new DateHeure(annee,mois,jour,0,0);
        return dh;
    }
    
    public static boolean estApresDateCourante(int annee, int mois, int jour, int heure, int minutes) {
            LocalDate current_date = LocalDate.now();
            LocalTime current_time = LocalTime.now();
            if (annee < current_date.getYear()) {
                return false;
            } else if (annee > current_date.getYear()) {
                return true;
            } else if (mois < current_date.getMonthValue()) {
                return false;
            } else if (mois > current_date.getMonthValue()) {
                return true;
            } else if (jour < current_date.getDayOfMonth()) {
                return false;
            } else if (jour > current_date.getDayOfMonth()) {
                return true;
            } else if (heure < current_time.getHour()) {
                return false;
            } else if (heure > current_time.getHour()) {
                return true;
            } else if (minutes < current_time.getMinute()) {
                return false;
            } else if (minutes > current_time.getMinute()) {
                return true;
            }
            return true;

        }
        
     

    /**
     * @return the annee
     */
    public int getAnnee() {
        return annee;
    }

    /**
     * @return the mois
     */
    public int getMois() {
        return mois;
    }

    /**
     * @return the jour
     */
    public int getJour() {
        return jour;
    }

    /**
     * @return the heure
     */
    public int getHeure() {
        return heure;
    }

    /**
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * @param annee the annee to set
     */
    public void setAnnee(int annee) {
        this.annee = annee;
    }

    /**
     * @param mois the mois to set
     */
    public void setMois(int mois) {
        this.mois = mois;
    }

    /**
     * @param jour the jour to set
     */
    public void setJour(int jour) {
        this.jour = jour;
    }

    /**
     * @param heure the heure to set
     */
    public void setHeure(int heure) {
        this.heure = heure;
    }

    /**
     * @param minutes the minutes to set
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

       
}

