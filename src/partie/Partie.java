package partie;

import carte.Donjon;
import carte.Tresor;
import java.util.ArrayList;
import joueur.Joueur;

public class Partie {
	
    private Pioche              piocheTresor;
    private Pioche              piocheDonjon;
    private Defausse            defausseTresor;
    private Defausse            defausseDonjon;
    private ArrayList<Joueur>   listeJoueurs;


    public Partie(){
        
        piocheDonjon    = new Pioche<Donjon>();
        piocheTresor    = new Pioche<Tresor>();
        defausseTresor  = new DefausseTresor();
        defausseDonjon  = new DefausseDonjon();
        listeJoueurs    = new ArrayList<Joueur>();
        
        this.run();
    }

    
    public void run(){
        
    }
    
    
    public Partie(Pioche<Tresor> piocheTresor, Pioche<Donjon> piocheDonjon, Defausse defausseTresor, Defausse defausseDonjon, ArrayList<Joueur> listeJoueurs) {
        this.piocheTresor = piocheTresor;
        this.piocheDonjon = piocheDonjon;
        this.defausseTresor = defausseTresor;
        this.defausseDonjon = defausseDonjon;
        this.listeJoueurs = listeJoueurs;
    }


    public boolean commencerTour(){
        Donjon enJeu = (Donjon) piocheDonjon.tirerCarte();
        return true;
    }

    public Defausse getDefausseDonjon() {
        return defausseDonjon;
    }

    public void setDefausseDonjon(Defausse defausseDonjon) {
        this.defausseDonjon = defausseDonjon;
    }

    public Defausse getDefausseTresor() {
        return defausseTresor;
    }

    public void setDefausseTresor(Defausse defausseTresor) {
        this.defausseTresor = defausseTresor;
    }
    
    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    public Pioche<Donjon> getPiocheDonjon() {
        return piocheDonjon;
    }
    
    public void setPiocheDonjon(Pioche<Donjon> piocheDonjon) {
        this.piocheDonjon = piocheDonjon;
    }
    
    public void setPiocheTresor(Pioche<Tresor> piocheTresor) {
        this.piocheTresor = piocheTresor;
    }

    public Pioche<Tresor> getPiocheTresor() {
        return piocheTresor;
    }

    public boolean choixCombat(){
        return true;
    }
    
}
