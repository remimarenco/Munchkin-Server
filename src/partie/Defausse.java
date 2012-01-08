package partie;

import java.util.ArrayList;

import carte.Carte;
import java.util.Iterator;


public class Defausse<T> {
    
    protected ArrayList<T> defausse= new ArrayList<T>();

    public Defausse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<T> getDefausse() {
        return defausse;
    }

    public void setDefausse(ArrayList<T> defausse) {
        this.defausse = defausse;
    }
    
    

    public boolean ajouterCarte(T c){
    	return this.defausse.add(c);
        
    }
    
    public boolean supprimerCarte(T c){
    	return this.defausse.remove(c);
        
    }
    
    public void vider(){
        defausse.clear();
    }

	public boolean isEmpty() {
		return defausse.isEmpty();
	}
}
