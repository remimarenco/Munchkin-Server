package partie;

import carte.Carte;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Pioche de cartes (pioche tresor ou donjon)
 * @author Julien Rouvier
 */
public class Pioche<T> {
    
    protected ArrayList<T> pioche;
    protected Class typePioche;
    
    /**
     * Constructeur
     * @param typePioche : la classe des cartes qui vont se trouver dans la pioche (trésor ou donjon) 
     */
    public Pioche(Class typePioche) {
        pioche          = new ArrayList<T>();
        this.typePioche = typePioche;
    }
    
    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public ArrayList<T> getPioche() {
        return pioche;
    }

    public void setPioche(ArrayList<T> pioche) {
        this.pioche = pioche;
    }
    // ================================== //
    
    
    
    /**
     * Tire la première carte de la pioche
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
     * Permet de poser la carte en dessous de la pioche
     * @param carte
     */
    public void poserEnDessousPioche(T carte)
    {
    	pioche.add(pioche.size(), carte);
    }
    
    
    /**
     * Vérifie si la pioche est vide
     * @return boolean : true si la pioche est vide, false sinon
     */
    public boolean isEmpty(){
        return this.pioche.isEmpty();
    }
    
    
    /**
     * Initialisation de la pioche
     * Prend dans le deck toutes les cartes dont la classe correspond à la 
     * classe de la pioche (trésor ou donjon)
     * @param deck 
     */
    public void init(Deck deck){
        ArrayList<Carte> cartes = Deck.getCartes();
        Iterator it             = cartes.iterator();
        Carte c;

        // On ajoute la carte dans la pioche seulement si elle appartient à un type de pioche (Donjon ou Trésor)
        while(it.hasNext()){
            c = (Carte) it.next();
            if(c.getClass().getSuperclass().equals(this.typePioche))
                this.pioche.add((T) c);
        }
    }
}