package action;

import java.util.ArrayList;

import partie.Combat;
import partie.Partie;
import joueur.Joueur;

/**
 * Classe abstraite permettant de définir des actions
 * @author Rémi Marenco
 */
public abstract class Action {    
    /**
     * Méthode abstraite permettant de lancer l'action
     * On s'arrête sur : une action a été lancée par un joueur (ou pas), cible un ensemble de joueurs (ou pas), dépend d'une phase d'un tour (ou pas), dépend d'un tour (ou pas)
     * @param joueurImpacte : Joueur recevant l'action
     */
    public abstract String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours);
    
    /**
     * Méthode permettant de retourner le joueur choisit
     * @param partie
     * @return
     */
    // TODO : Faire les échanges avec le serveur pour une demande de choix du joueur
    protected Joueur demandeChoixJoueur(Partie partie, Joueur joueurEmetteur)
    {
    	return null;
    }
}
