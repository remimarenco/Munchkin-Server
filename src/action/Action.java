package action;

import joueur.Joueur;

/**
 * Classe abstraite permettant de définir des actions
 * @author Rémi Marenco
 */
public abstract class Action {

    /**
     * Méthode abstraite permettant de lancer l'action
     * @param joueurImpacte => Joueur recevant l'action
     */
    public abstract String action(Joueur joueurImpacte);

}
