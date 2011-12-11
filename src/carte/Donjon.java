package carte;

/**
 * Classe Donjon permettant de référencer les cartes donjon
 * @author Rémi Marenco
 */
public class Donjon extends Carte {

    /**
     * Constructeur de la classe donjon. Hérite de la classe Carte
     * @param nom
     * @param description
     */
    public Donjon(String nom, String description) {
        this.nom = nom;
        this.description = description;
        this.id = this.id + 1;
    }
    
    
}
