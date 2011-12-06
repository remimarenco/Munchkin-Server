package partie;

import joueur.Personnage;
import carte.Carte;
import java.util.ArrayList;
import joueur.Joueur;

public class Partie {
	
    private PiocheTresor        piocheTresor;
    private PiocheDonjon        piocheDonjon;
    private Defausse            defausseTresor;
    private Defausse            defausseDonjon;
    private ArrayList<Joueur>   listeJoueurs;


    public Partie(){
        
        piocheDonjon    = new PiocheDonjon();
        piocheTresor    = new PiocheTresor();
        defausseTresor  = new DefausseTresor();
        defausseDonjon  = new DefausseDonjon();
        listeJoueurs    = new ArrayList<Joueur>();
        
        this.run();
    }

    
    public void run(){
        
    }
    
    
    public Partie(PiocheTresor piocheTresor, PiocheDonjon piocheDonjon, Defausse defausseTresor, Defausse defausseDonjon, ArrayList<Joueur> listeJoueurs) {
        this.piocheTresor = piocheTresor;
        this.piocheDonjon = piocheDonjon;
        this.defausseTresor = defausseTresor;
        this.defausseDonjon = defausseDonjon;
        this.listeJoueurs = listeJoueurs;
    }


    public boolean commencerTour(){
        Carte enJeu = piocheDonjon.tirerCarte();
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

    public PiocheDonjon getPiocheDonjon() {
        return piocheDonjon;
    }
    
    public void setPiocheDonjon(PiocheDonjon piocheDonjon) {
        this.piocheDonjon = piocheDonjon;
    }
    
    public void setPiocheTresor(PiocheTresor piocheTresor) {
        this.piocheTresor = piocheTresor;
    }

    public PiocheTresor getPiocheTresor() {
        return piocheTresor;
    }

    public boolean choixCombat(){
        return true;
    }
    
}
