/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import carte.Monstre;
import java.util.ArrayList;
import joueur.Joueur;
import partie.Constante;
import partie.Partie;

/**
 *
 * @author Remi
 */
public class ModifPuissanceMonstreObjet extends Action {
    int bonus=0;
    public ModifPuissanceMonstreObjet(int bonus) {
        this.bonus = bonus;
    }  
    
    @Override
    public String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie) {
        String out = "";
        // On vérifie que rien n'est null
        if(partie != null)
        {
            if(partie.getCombat() != null)
            {
                if(partie.getCombat().getCampMechant() != null)
                {
                    // On ajoute le bonus spécifié au bonus en cours
                    partie.getCombat().setBonusTemporaireMechant(partie.getCombat().getBonusTemporaireMechant() + bonus);
                    out += "On a ajouté un bonus de "+bonus+" au camp méchant. Il a maintenant une puissance de "+partie.getCombat().getPuissanceCampMechant();
                    return out;
                }
            }
        }
        return "Erreur dans ModifPuissanceMonstre !";
    }

    @Override
    public String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie, boolean choixJoueur) {
        return action(joueurEmetteur, joueurDestinataire, partie);
    }

    @Override
    public boolean isPosable(Partie partie, Joueur joueur) {
        // Une action de ce type ne peut aujourd'hui jamais etre posé
        return false;
    }

    @Override
    public boolean isIntervenable(int phaseTourEnCours) {
        // Si la phase n'est pas null
        if(Integer.valueOf(phaseTourEnCours) != null)
        {
            // Si la phase est une phase de recherche de bagarre
            if(phaseTourEnCours == Constante.PHASE_CHERCHER_LA_BAGARRE)
            {
                return true;
            }
        }
        return false;
    }
    
}
