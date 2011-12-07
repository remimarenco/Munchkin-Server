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
public class Pioche<T> {
    
    protected ArrayList<T> pioche = new ArrayList<T>();

    public Pioche() {
    }
    
    public ArrayList<T> getPioche() {
        return pioche;
    }
    
    public T tirerCarte(){
        T ret = pioche.get(0);
        pioche.remove(0);
        return ret;
    }
}
