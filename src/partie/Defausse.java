package partie;

import java.util.ArrayList;

import carte.Carte;


public abstract class Defausse {
    
    protected ArrayList<Carte> defausse= new ArrayList<Carte>();

    public Defausse() {
        super();
        // TODO Auto-generated constructor stub
    }

    public boolean ajouterCarte(Carte c){
        this.defausse.add(c);
        return true;
    }
    
    public boolean supprimerCarte(Carte c){
        this.defausse.remove(c);
        return true;
    }
}
