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
public enum Lit {
    P,
    F;
    
    /**
     * 
     * @param lit --> un lit de type Lit (P/F)
     * @return la chainde caractere correcpondant au lit
     */
    public String toString2(Lit lit){
    	if(null != lit)switch (lit) {
            case P:
                return "P";
            case F:
                return "F";
            default:
                break;
        }
    	return null;
}
    
}

