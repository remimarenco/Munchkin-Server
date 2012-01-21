package carte;

/**
 * Classe Donjon permettant de référencer les cartes donjon
 * @author Rémi Marenco
 */
public abstract class Donjon extends Carte {

    /**
     * Constructeur de la classe donjon. Hérite de la classe Carte
     * @param nom
     * @param description
     */
    public Donjon(int id ,String nom, String description) {
        super(id, nom, description);
    }
}
