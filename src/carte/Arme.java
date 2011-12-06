package carte;
import comportement.classes.Equipement;

/**
 * Classe Arme.
 * Hérite de Tresor.
 * Représente les armes des personnages dans leur jeu
 * @author user
 */
public class Arme extends Tresor {
        /**
         * Constructeur de la classe Arme.
         * Permet de renseigner les paramètres de la classe Trésor ainsi que l'équipement engendré
         * @param nom
         * @param description
         * @param equipement
         */
	public Arme(String nom, String description, int id, Equipement equipement) {
		super(nom, description, id);
		this.equipement = equipement;
	}
}
