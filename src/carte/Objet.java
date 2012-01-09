package carte;
import comportement.ComportementDefausserCarte;
import comportement.Equipement;
import comportement.UtiliserCarte;


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
     * @param id
     * @param nom
     * @param description
     * @param equipement
     * @param utiliser
     * @param defausser
     */
    public Objet(int id,String nom, String description, Equipement equipement, UtiliserCarte utiliser, ComportementDefausserCarte defausser) {
        super(id,nom, description);
        this.equipement = equipement;
        this.utiliserCarte = utiliser;
        this.defausserCarte = defausser;
    }
}
