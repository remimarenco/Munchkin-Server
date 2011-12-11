package joueur;

import carte.Carte;
import partie.Partie;

public class Joueur extends Thread {
	
    private Main main;
    private Jeu jeu;
    private String nom;
    private Personnage personnage;
    private Partie partie;

    public Joueur() {
        this.main = new Main();
        this.jeu = new Jeu();
        this.nom = new String();
        this.personnage = new Personnage();
    }
    
    public Joueur(String nom) {
        this.main = new Main();
        this.jeu = new Jeu();
        this.nom = nom;
        this.personnage = new Personnage();
    }
    
    public Joueur(Main main, Jeu jeu, String nom, Personnage personnage) {
        this.main = main;
        this.jeu = jeu;
        this.nom = nom;
        this.personnage = personnage;
    }

    public Joueur(Main main, Jeu jeu, String nom, Personnage personnage, Partie partie) {
        this.main = main;
        this.jeu = jeu;
        this.nom = nom;
        this.personnage = personnage;
        this.partie = partie;
    }
    
    
	
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
    }
    
    public boolean defausserCarte(Carte c){
        this.jeu.supprimerCarte(c);
        
        if(c.getClass().getName().equals("Donjon")){
            
        }else if(c.getClass().getName().equals("Tresor")){
            
        }else{
            return false;
        }
        
        return true;
    }
    
    public boolean equiperCarte(Carte c){
        this.jeu.ajouterCarte(c);
        return true;
    }
}
