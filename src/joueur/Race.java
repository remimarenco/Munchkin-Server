package joueur;

/**
 * Classe abstraite étendue par toutes les races
 * @author washi
 */
public abstract class Race {
	
    /**
     * Constructeur
     */
    public Race(){

    }

    public abstract boolean deguerpir();

    public abstract void modifPersonnage(Personnage personnage);

    @Override
    public String toString(){
        return this.getClass().toString();
    }
}
