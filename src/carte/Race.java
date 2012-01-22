/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

import action.Action;
import action.ChangerRace;
import comportement.ComportementDefausserCarte;
import comportement.Equipement;
import comportement.UtiliserCarte;

/**
 *
 * @author Remi
 */
public class Race extends Objet{
    joueur.Race race;

    public joueur.Race getRace() {
        return race;
    }
    public Race(int id, String nom, String description, Equipement equipement, UtiliserCarte utiliser, ComportementDefausserCarte defausser) {
        super(id, nom, description, equipement, utiliser, defausser);
        // On récupère la race passée dans l'équipement
        for(Action action : equipement.getTabAction())
        {
            if(action instanceof ChangerRace)
            {
                race = ((ChangerRace) action).getRace();
            }
        }
    }
    
    
    
}
