package carte;

import joueur.Jeu;
import joueur.Joueur;
import partie.Partie;


/**
 * Classe carte permettant de gérer toutes les cartes du jeu
 * @author Guillaume Renoult
 */
public abstract class Carte {
    
    protected Integer id;           // id de la carte, reference sur serveur et client
    protected String nom;
    protected String description;   // Description de la carte, correspondante à la réalité
    
    /**
     * Constructeur de la carte
     * @param id de la carte
     * @param nom de la carte
     * @param description de la carte
     */
    public Carte(int id, String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.id = id;
    }
    
    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public  Integer getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }    
    // ================================== //    

    /**
     * Retourne le nom de la carte
     * @return this.nom
     */
    @Override
    public String toString(){
        return this.nom;
    }
    
    public abstract boolean isPosable(Partie partie, Joueur joueurEmetteur);
}
