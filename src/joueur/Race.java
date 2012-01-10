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
     * Modifie un personnage en fonction des spécificités de la race
     * @param personnage 
     */
    public abstract void modifPersonnage(Personnage personnage);

    /**
     * Modifie un personnage en fonction des spécificités de la race 
     * @param personnage 
     */
    public abstract void defausserRace(Personnage personnage);
    
    /**
     * Comportement lors d'un appel à println
     * @return String : le nom de la race
     */
    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
