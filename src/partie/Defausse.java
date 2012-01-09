package partie;

import java.util.ArrayList;


/**
 * Défausse
 * @author Julien Rouvier
 * @param <T> : classe du type de carte (DONJON ou TRESOR) de la défausse 
 */
public class Defausse<T> {
    
    protected ArrayList<T> defausse= new ArrayList<T>();

    /**
     * Constructeur
     */
    public Defausse() {
        super();
    }

    
    /**
     * Retourne les carte de la défausse
     * @return 
     */
    public ArrayList<T> getDefausse() {
        return defausse;
    }

    
    /**
     * Défini les cartes de la défausse
     * @param defausse 
     */
    public void setDefausse(ArrayList<T> defausse) {
        this.defausse = defausse;
    }
    
    
    /**
     * Ajoute une carte dans la défausse
     * @param c
     * @return 
     */
    public boolean ajouterCarte(T c){
    	return this.defausse.add(c);
        
    }
    
    
    /**
     * Supprime une carte de la défausse
     */
    public boolean supprimerCarte(T c){
    	return this.defausse.remove(c);
        
    }
    
    
    /**
     * Vide la défausse
     */
    public void vider(){
        defausse.clear();
    }

    
    /**
     * Regarde si la défausse est vide
     * @return boolean : true si vide, false sinon
     */
    public boolean isEmpty() {
        return defausse.isEmpty();
    }
}
