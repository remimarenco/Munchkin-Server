package comportement;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Partie;

import action.Action;

/**
 * Classe mère des comportements des cartes
 * Permet de gérer un ensemble d'action à appliquer selon un comportement préçis
 * @author Remi
 *
 */
public abstract class Comportement {
	// Un comportement correspond toujours à un ensemble d'action
	protected ArrayList<Action> tabAction;
	
	/**
	 * Constructeur d'un comportement
	 * On récupère par valeur (tabAction.clone()) l'ensemble d'action passé en paramètre
	 * @param tabAction
	 */
	public Comportement(ArrayList<Action> tabAction)
	{
		try {
			this.tabAction = (ArrayList<Action>) tabAction.clone();
		} catch(Exception e){
			System.out.println("Aucune action dans le constructeur du comportement");
		}
	}
	
	/**
	 * Méthode que tous les comportements doivent implémenter.
	 * Permet de lancer l'action du comportement.
	 * @param joueurEmetteur
	 * @param joueurDestinataire
	 * @param partie
	 * @return retourne le message du déroulement du comportement
	 */
	public abstract String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie);
	
	/**
	 * Méthode que tous les comportements doivent implémenter.
	 * Permet de lancer l'action du comportement.
	 * Demande si le joueur doit choisir un joueur cible
	 * @param joueurEmetteur
	 * @param joueurDestinataire
	 * @param partie
	 * @return retourne le message du déroulement du comportement
	 */
	public abstract String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie, boolean choixJoueur);

}
