package carte;
import comportement.Sortilege;

/**
 * Classe Malediction.
 * Hérite de la classe Donjon car piochée dans une pioche Donjon
 * @author Rémi Marenco
 */
public class Malediction extends Donjon {

    /**
     * Constructeur de la classe Malediction
     * @param nom
     * @param description
     * @param sortilege
     */
    public Malediction(int id,String nom, String description, Sortilege sortilege) {
        super(id,nom, description);
        this.sortilege = sortilege;
    }
}
