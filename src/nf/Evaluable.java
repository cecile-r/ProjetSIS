package nf;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author garci
 */
public interface Evaluable {
    public DateHeure getDateHeure();
    public Object getProfessionnel();
    public String getTypeEvaluable();
    public String getContenu();
    public String getObservations();
    public String toStringDM();
    public String toStringDMA();
    
}
