package joueur;


/**
 * Classe Elfe
 * @author Rémi Marenco
 */
public class Elfe extends Race {
    
    /**
     * Modifie un personnage en fonction des spécificités de la race
     * @param personnage 
     */
    @Override
    public void modifPersonnage(Personnage personnage) {
        // TODO : gain de niveau uniquement après avoir aidé en combat
        
        //personnage.changerNiveau(1);
        personnage.setCapaciteFuite(personnage.getCapaciteFuite()-1);
    }
    
    /**
     * Perd les attribut offerts par la race
     * @param personnage 
     */
    @Override
    public void defausserRace(Personnage personnage) {
        personnage.setCapaciteFuite(personnage.getCapaciteFuite()+1);
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return "Elfe";
    }
}
