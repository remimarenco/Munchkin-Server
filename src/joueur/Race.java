package joueur;


/**
 * Classe abstraite étendue par toutes les races
 * @author Julien Rouvier
 */
public abstract class Race {
	
    /**
     * Constructeur
     */
    public Race(){

    }

    
    /**
     * // TODO : Commenter
     * @param personnage 
     */
    public abstract void modifPersonnage(Personnage personnage);

    
    /**
     * // TODO : Commenter
     * @return 
     */
    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
