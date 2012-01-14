package carte;

import comportement.Sortilege;

/**
 * Classe Sort piochée dans la pioche Tresor
 * @author Remi Marenco
 *
 */
public class Sort extends Tresor {

	public Sort(int id, String nom, String description, Sortilege sortilege) {
		super(id, nom, description);
        this.sortilege = sortilege;
	}

}
