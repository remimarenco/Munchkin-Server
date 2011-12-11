/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;
import carte.Carte;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author user
 */
public class Pioche<T> {
    
    protected ArrayList<T> pioche;
    protected String type;
    
    public Pioche() {
        pioche  = new ArrayList<T>();
        type    = new String();
    }
    
    public Pioche(String n) {
        pioche  = new ArrayList<T>();
        type    = n;
    }
    
    public ArrayList<T> getPioche() {
        return pioche;
    }
    
    public T tirerCarte(){
        T ret = pioche.get(0);
        pioche.remove(0);
        return ret;
    }
    
    public void init(Deck deck){
        ArrayList<Carte> cartes = deck.getCartes();
        Iterator it = cartes.iterator();
        Carte c;
        
        while(it.hasNext()){
            c = (Carte) it.next(); 
//            System.out.println(c.getClass().getName());
            if(c.getClass().getSuperclass().getName().equals(this.type))
                this.pioche.add((T) c);
        }
    }
}