package carte;

/**
 * Classe Trésor.
 * Hérite de la classe Carte.
 * @author Rémi Marenco
 */
public abstract class Tresor extends Carte {

    /**
     * Constructeur de la classe Tresor.
     * Enregistre les paramètres de la classe carte ainsi que l'équipement
     * @param nom
     * @param description
     * @param equipement
     */
    public Tresor(int id,String nom, String description) {
        super(id, nom, description);
    }
}
