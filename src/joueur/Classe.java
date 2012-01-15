package joueur;


/**
 * La classe d'un personnage
 * @author Rémi Marenco
 */
public abstract class Classe {
    
    /**
     * Constructeur
     */
    public Classe() {

    }
    
    public abstract void utiliserCapacite();
    
    /**
     * Comportement lors d'un appel à println
     * @return String : le nom de la classe
     */
    @Override
    public abstract String toString();
}
