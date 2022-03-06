/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nf;

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

    
}
