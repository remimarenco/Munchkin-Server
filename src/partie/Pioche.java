/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import carte.Carte;
import carte.Donjon;
import java.util.ArrayList;

/**
 *
 * @author user
 */
public abstract class Pioche {
    
    protected static ArrayList<Donjon> pioche = new ArrayList<Donjon>();

    public Pioche() {
    }
    
    public ArrayList<Donjon> getPioche() {
        return pioche;
    }
    
    public Carte tirerCarte(){
        Carte ret = pioche.get(0);
        pioche.remove(0);
        return ret;
    }
}
