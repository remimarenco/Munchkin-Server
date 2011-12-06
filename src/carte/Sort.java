package carte;
import java.util.ArrayList;

import comportement.Action;
import comportement.classes.IncidentFacheux;
import comportement.classes.Sortilege;

/**
 * Classe Donjon.
 * Hérite de la classe Donjon
 * @author Rémi Marenco
 */
public class Sort extends Donjon {
        /**
         * Constructeur de la classe sort
         * Permet d'enregistrer l'action de lancer un ensemble de sortilege
         * @param nom
         * @param description
         * @param sortilege
         */
	public Sort(String nom, String description, Sortilege sortilege) {
		super(nom, description);
		this.sortilege = sortilege;
	}



}
