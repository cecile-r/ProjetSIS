package nf;

import java.text.SimpleDateFormat;
import java.time.DateTimeException;
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
            return getHeure()  - d.getHeure();
        return getMinutes() - d.getMinutes();
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
    annee = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('-')));
    mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('-') + 1, donneesCourantes.lastIndexOf('-')));
    jour = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('-') + 1, donneesCourantes.length()));
    }

    public void creerHeure(String donneesCourantes) {
    heure = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf(':')));
    minutes = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf(':') + 1, donneesCourantes.length()));
    }
    
	 
    /**
     * remplit la date à partir d'une chaine de caractères de format ../../..
     * @param donneesCourantes 
     */
    public void creerDate2(String donneesCourantes) {
    jour = Integer.parseInt(donneesCourantes.substring(0, donneesCourantes.indexOf('/')));
    mois = Integer.parseInt(donneesCourantes.substring(donneesCourantes.indexOf('/') + 1, donneesCourantes.lastIndexOf('/')));
    annee = Integer.parseInt(donneesCourantes.substring(donneesCourantes.lastIndexOf('/') + 1, donneesCourantes.length()));
    }
    
    public static String convertirDateHeuretoString(DateHeure d){
        String d2 ="";
        d2 = d2 + d.jour +"/" + d.mois +"/" +d.annee +" " + d.heure+ ":"+d.minutes;
        return d2;
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

       
}