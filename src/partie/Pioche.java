package partie;

import carte.Carte;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * // TODO : Commenter
 * @author Julien Rouvier
 */
public class Pioche<T> {
    
    protected ArrayList<T> pioche;
    protected Class typePioche;
    
    /**
     * // TODO : Commenter
     * @param typePioche 
     */
    public Pioche(Class typePioche) {
        pioche  = new ArrayList<T>();
        this.typePioche = typePioche;
    }
    
    
    /**
     * // TODO : Commenter
     * @return 
     */
    public ArrayList<T> getPioche() {
        return pioche;
    }

    /**
     * // TODO : Commenter
     * @param pioche 
     */
    public void setPioche(ArrayList<T> pioche) {
        this.pioche = pioche;
    }
    
    
    /**
     * // TODO : Commenter
     * @return 
     */
    public T tirerCarte(){
        if(pioche.isEmpty())
            return null;
        
        T ret = pioche.get(0);
        pioche.remove(0);
        return ret;
    }
    
    
    /**
     * // TODO : Commenter
     * @return 
     */
    public boolean isEmpty(){
        return this.pioche.isEmpty();
    }
    
    
    /**
     * // TODO : Commenter
     * @param deck 
     */
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