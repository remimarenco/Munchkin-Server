package carte;
import comportement.classes.Sortilege;

/**
 * Classe Donjon.
 * Hérite de la classe Donjon
 * @author Rémi Marenco
 */
public class Sort extends Donjon {
    
        /**
         * Constructeur de la classe sort (un sortilège)
         * @param nom
         * @param description
         * @param sortilege
         */
	public Sort(int id,String nom, String description, Sortilege sortilege) {
		super(id,nom, description);
		this.sortilege = sortilege;
	}

        

}
