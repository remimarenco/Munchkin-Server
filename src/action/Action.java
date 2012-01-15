package action;

import communication.Message;
import java.util.ArrayList;

import java.util.logging.Level;
import java.util.logging.Logger;
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
    public abstract String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie);
    
    /**
     * Méthode permettant de retourner le joueur choisit
     * @param partie
     * @return
     */
    protected Joueur demandeChoixJoueur(Partie partie, Joueur joueurEmetteur)
    {
        joueurEmetteur.sendMessage(new Message(Message.CHOIXJOUEUR, "Partie", joueurEmetteur.getName(),"Veuiller choisir le joueur destination "));
        partie.setJoueurCible(null);
        while(partie.getJoueurCible()==null){
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Action.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    	return partie.getJoueurCible();
    }
}
