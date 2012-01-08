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
    //protected int type;
    protected Class typePioche;
    
    public Pioche(Class typePioche) {
        pioche  = new ArrayList<T>();
        this.typePioche = typePioche;
    }
    
    public ArrayList<T> getPioche() {
        return pioche;
    }

    public void setPioche(ArrayList<T> pioche) {
        this.pioche = pioche;
    }
    
    
    public T tirerCarte(){
        if(pioche.isEmpty())
            return null;
        
        T ret = pioche.get(0);
        pioche.remove(0);
        return ret;
    }
    
    public boolean isEmpty(){
        return this.pioche.isEmpty();
    }
    
    public void init(Deck deck){
        ArrayList<Carte> cartes = Deck.getCartes();
        Iterator it = cartes.iterator();
        Carte c;

        /**
         * On ajoute la carte dans la pioche seulement si elle appartient à un type de pioche (Donjon ou Trésor)
         */
        while(it.hasNext()){
            c = (Carte) it.next();
            if(c.getClass().getSuperclass().equals(this.typePioche))
                this.pioche.add((T) c);
        }
    }
}