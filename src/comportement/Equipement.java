package comportement;

import java.util.ArrayList;

import partie.Combat;
import action.Action;
import joueur.Joueur;


/**
 * Comportement d'équipement a mettre sur une carte afin d'activer la possibilité de "s'équiper"
 * @author Rémi Marenco
 */
public  class Equipement extends Comportement {    
    /**
     * Constructeur
     * @param tabAction : ensemble d'action 
     */
    public Equipement(ArrayList<Action> tabAction) {
        super(tabAction);
    }

    /**
     * Equipe un joueur
     * @param joueurImpacte : le joueur a équiper
     * @return out : texte résumant l'action
     */
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Combat combatCible,
			int phaseTour, Joueur joueurTourEnCours) {
		String out = "";

		if(tabAction != null){
			out += "--- Equipement ---\n";

			for(Joueur joueurImpacte : joueurDestinataire){
				out += "Un équipement vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
				for(Action action : tabAction)
					out += action.action(joueurEmetteur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
				out += "--- Fin d'equipement ---\n";
			}
		} else {
			out += "Aucun equipement !\n";
		}

		return out;
	}	
}