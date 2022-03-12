package nf;

import java.text.DecimalFormat;
import static nf.DateHeure.convertirDateHeuretoString;

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
    private Infirmier infirmier;
    private DateHeure dateHeure;
    private DPI DPI;
    
    public SoinsQuotidien(double temperature,int saturationO2,double tension, String remarque, DateHeure dateHeure){
        this.temperature=temperature;
        this.saturationO2=saturationO2;
        this.tension=tension;
        this.remarque=remarque;
        this.dateHeure=dateHeure;
        this.DPI=null;
        this.infirmier=null;
        //SET DPI
        //SET INFIRMIER
    }

    @Override
    public String toString(){
        String ch="";
        ch+=dateHeure.StringDateHeure();
        ch+="\n";
        ch+=infirmier.toString();
        ch+="\n";
        ch=ch+"Température : "+temperature;
        ch+="\n";
        ch=ch+"Saturation 0² : "+saturationO2;
        ch+="\n";
        ch=ch+"Tension : "+getTension();
        ch+="\n";
        ch=ch+"Remarque : "+remarque;
        ch+="\n";
        return ch;
    }
    
    public String toStringDM(){
        DecimalFormat df = new DecimalFormat("0.0");
        String ch="------------------------------SOINS QUOTIDIEN------------------------------\n\n";
        ch+=convertirDateHeuretoString(dateHeure);
        ch+="\n\n";
        ch+=infirmier.toString();
        ch+="\n\n";
        ch=ch+"Température : "+df.format(temperature);
        ch+="\n\n";
        ch=ch+"Saturation 0² : "+df.format(saturationO2);
        ch+="\n\n";
        ch=ch+"Tension : "+df.format(tension);
        ch+="\n\n";
        ch=ch+"Remarque : "+remarque;
        ch+="\n\n";
        return ch;
    }
    
    public String toStringDMA(){return null;}
    
      @Override
    public Object getProfessionnel(){
        return infirmier;
    }
    
    @Override
    public String getTypeEvaluable(){
        return "Soins quotidien";
    }
    
    @Override
    public String getContenu(){
        String ch ="";
        ch=ch+"Température : "+temperature;
        ch+="\n";
        ch=ch+"Saturation 0² : "+saturationO2;
        ch+="\n";
        ch=ch+"Tension : "+getTension();
        ch+="\n";
        return ch;
    }
    
    @Override
    public String getObservations(){
        return remarque;
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
    public Infirmier getInfirmier() {
        return infirmier;
    }

    /**
     * @param infirmiere the infirmiere to set
     */
    public void setInfirmier(Infirmier infirmier) {
        this.infirmier = infirmier;
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

    /**
     * @return the DPI
     */
    public DPI getDPI() {
        return DPI;
    }

    /**
     * @param DPI the DPI to set
     */
    public void setDPI(DPI DPI) {
        this.DPI = DPI;
    }
    
   
}
