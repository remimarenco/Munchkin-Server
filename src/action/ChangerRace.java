package action;

import joueur.Joueur;
import joueur.Race;

/**
 * Classe permettant de modifier la race d'un joueur
 * @author marenco-r
 *
 */
public class ChangerRace extends Action {
    
    /**
     * Constructeur par défaut
     * @param race : nouvelle race
     */
    public ChangerRace(Race race) {
        // TODO Auto-generated constructor stub
    }

    /**
     * Action permettant le changement de race
     * @param joueurImpacte : joueur qui subit le changement de race
     * @return out : texte résumant l'action 
     */
    @Override
    public String action(Joueur joueurImpacte) {
        // TODO Auto-generated method stub
        String out = "Non implémenté";
        return out;
    }
}
