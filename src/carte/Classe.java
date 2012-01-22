/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carte;

import action.Action;
import action.ChangerClasse;
import comportement.ComportementDefausserCarte;
import comportement.Equipement;
import comportement.UtiliserCarte;

/**
 *
 * @author Remi
 */
public class Classe extends Objet {
    joueur.Classe classe;

    public joueur.Classe getClasse() {
        return classe;
    }
    public Classe(int id, String nom, String description, Equipement equipement, UtiliserCarte utiliser, ComportementDefausserCarte defausser) {
        super(id, nom, description, equipement, utiliser, defausser);
        // On récupère la classe passée dans l'equipement
        for(Action action : equipement.getTabAction())
        {
            if(action instanceof ChangerClasse)
            {
                classe = ((ChangerClasse) action).getClasse();
            }
        }
    }
    
}
