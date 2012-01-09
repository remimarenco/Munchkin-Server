package action;

import joueur.Joueur;
import joueur.Race;

/**
 * Classe permettant de modifier la race d'un joueur
 * @author Rémi Marenco
 */
public class ChangerRace extends Action {
    
    protected Race race;
    
    /**
     * Constructeur par défaut
     * @param race : nouvelle race
     */
    public ChangerRace(Race race) {
        this.race = race;
    }

    /**
     * Action permettant le changement de race
     * @param joueurImpacte : joueur qui subit le changement de race
     * @return out : texte résumant l'action 
     */
    @Override
    public String action(Joueur joueurImpacte) {
        String out = joueurImpacte.getName() + " passe de la race " + joueurImpacte.getPersonnage().getRace();
        joueurImpacte.getPersonnage().setRace(this.race);
        out += " à la race " + joueurImpacte.getPersonnage().getRace();
        return out;
    }
}