package joueur;


/**
 * Race nain pour un personnage
 * @author Julien Rouvier
 */
public class Nain extends Race {

    /**
     * Comportement de modification du personnage
     * @param personnage 
     */
    @Override
    public void modifPersonnage(Personnage personnage) {
        personnage.setCapaciteEquipement(personnage.getCapaciteEquipement()+1);
    }

    /**
     * Perd les attribut offerts par la race
     * @param personnage 
     */
    @Override
    public void defausserRace(Personnage personnage) {
        personnage.setCapaciteEquipement(personnage.getCapaciteEquipement()-1);
    }
}
