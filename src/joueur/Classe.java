package joueur;


/**
 * La classe d'un personnage
 * @author Julien Rouvier
 */
public class Classe {
    
    /**
     * Constructeur
     */
    public Classe() {

    }

    /**
     * Comportement lors d'un appel à println
     * @return String : le nom de la classe
     */
    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
