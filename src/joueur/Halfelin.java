package joueur;


/**
 * Race Halfelin pour un personnage
 * @author Julien Rouvier
 */
public class Halfelin extends Race {

    /**
     * Comportement pour la modif du personnage
     * @param personnage 
     */
    @Override
    public void modifPersonnage(Personnage personnage) {
        
    }

    /**
     * Perd les attribut offerts par la race
     * @param personnage 
     */
    @Override
    public void defausserRace(Personnage personnage) {
        // TODO : gain de niveau uniquement après avoir aidé en combat
        
        personnage.setCapaciteFuite(personnage.getCapaciteFuite()-1);
    }
}
