package comportement;

import java.util.ArrayList;

import partie.Combat;
import action.Action;
import joueur.Joueur;

/**
 * Contient une liste d'action qui ne sont applicables que sous certaines conditions // TODO : Vérifier si correct
 * @author Rémi Marenco
 */
public class Condition extends Comportement {    
    /**
     * Constructeur
     * @param tabAction : ensemble d'actions 
     */
    public Condition(ArrayList<Action> tabAction){
        super(tabAction);
    }

    /**
     * Met une condition sur un joueur
     * @param joueurImpacte : joueur sur lequel s'applique la condition
     * @return out : texte résumant l'action
     */
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Combat combatCible,
			int phaseTour, Joueur joueurTourEnCours) {
		String out = "";

        if(tabAction == null){
            out += "Aucune condition sur ce monstre\n";
        } else {
            out += "--- Condition ---\n";
            for(Joueur joueurImpacte : joueurDestinataire){
	            out += "Une condition vient de se déclencher sur " + joueurImpacte.getName() + " :";
	            for(Action action : tabAction)
	                out += action.action(joueurEmetteur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
	            out += "\n--- Fin de condition ---\n";
            }
        }
        return out;
	}
}