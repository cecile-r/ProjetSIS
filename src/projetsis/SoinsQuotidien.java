package projetsis;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author garci
 */
public class SoinsQuotidien implements Evaluable {
    private double temperature;
    private int saturationO2;
    private double tension;
    private String remarque;
    private Infirmiere infirmiere;
    private DateHeure dateHeure;
    
    public SoinsQuotidien(double temperature,int saturationO2,double tension, String remarque, Infirmiere infirmiere, DateHeure dateHeure){
        this.temperature=temperature;
        this.saturationO2=saturationO2;
        this.tension=tension;
        this.remarque=remarque;
        this.infirmiere=infirmiere;
        this.dateHeure=dateHeure;
    }

    /**
     * @return the temperature
     */
    public double getTemperature() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the saturationO2
     */
    public int getSaturationO2() {
        return saturationO2;
    }

    /**
     * @param saturationO2 the saturationO2 to set
     */
    public void setSaturationO2(int saturationO2) {
        this.saturationO2 = saturationO2;
    }

    /**
     * @return the tension
     */
    public double getTension() {
        return tension;
    }

    /**
     * @param tension the tension to set
     */
    public void setTension(double tension) {
        this.tension = tension;
    }

    /**
     * @return the remarque
     */
    public String getRemarque() {
        return remarque;
    }

    /**
     * @param remarque the remarque to set
     */
    public void setRemarque(String remarque) {
        this.remarque = remarque;
    }

    /**
     * @return the infirmiere
     */
    public Infirmiere getInfirmiere() {
        return infirmiere;
    }

    /**
     * @param infirmiere the infirmiere to set
     */
    public void setInfirmiere(Infirmiere infirmiere) {
        this.infirmiere = infirmiere;
    }

    /**
     * @return the dateHeure
     */
    public DateHeure getDateHeure() {
        return dateHeure;
    }

    /**
     * @param dateHeure the dateHeure to set
     */
    public void setDateHeure(DateHeure dateHeure) {
        this.dateHeure = dateHeure;
    }
   
}
