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
	// TODO : Avoir une implémentation de l'action du type => String action(Joueur joueurDestinateur, ArrayList<Joueur> joueursDestinataires), afin de gérer la multi-application
    public abstract String action(Joueur joueurImpacte);
}
