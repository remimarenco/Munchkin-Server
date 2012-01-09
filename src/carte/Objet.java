package carte;
import comportement.Equipement;


/**
 * Classe Objet.
 * Hérite de la classe trésor.
 * Permet de renseigner les objets que les personnages pourront équiper
 * @author Rémi Marenco
 */
public class Objet extends Tresor {
    
    
    /**
     * Constructeur de la classe objet.
     * Permet de renseigner les paramètres de la classe Trésor ainsi que l'équipement
     * apporté par la carte
     * @param nom
     * @param description
     * @param equipement
     */
    public Objet(int id,String nom, String description, Equipement equipement) {
        super(id,nom, description);
        this.equipement = equipement;
    }
}
