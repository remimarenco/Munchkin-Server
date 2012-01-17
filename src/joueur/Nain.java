package joueur;


/**
 * Race nain pour un personnage
 * @author Rémi Marenco
 */
public class Nain extends Race {

    /**
     * Comportement de modification du personnage
     * @param personnage 
     */
    @Override
    public void modifPersonnage(Personnage personnage) {
    	personnage.setMaxCartes(personnage.getMaxCartes()+1);
        personnage.setCapaciteEquipement(personnage.getCapaciteEquipement()+1);
    }

    /**
     * Perd les attribut offerts par la race
     * @param personnage 
     */
    @Override
    public void defausserRace(Personnage personnage) {
    	personnage.setMaxCartes(personnage.getMaxCartes()-1);
        personnage.setCapaciteEquipement(personnage.getCapaciteEquipement()-1);
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Nain";
	}
}
