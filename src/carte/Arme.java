package carte;
import comportement.classes.Equipement;

/**
 * Classe Arme.
 * Hérite de Tresor.
 * Représente les armes des personnages dans leur jeu
 * @author Rémi Marenco
 */
public class Arme extends Tresor {

        /**
         * Constructeur de la classe Arme.
         * Permet de renseigner les paramètres de la classe Trésor ainsi que l'équipement engendré
         * @param nom
         * @param description
         * @param equipement
         */
	public Arme(int id,String nom, String description, Equipement equipement) {
		super(id,nom, description);
		this.equipement = equipement;
	}
}