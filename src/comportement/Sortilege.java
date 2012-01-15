package comportement;

import java.util.ArrayList;
import partie.Combat;
import action.Action;
import joueur.Joueur;


/**
 * Ensemble d'actions lancées par un sort
 * @author Rémi Marenco
 */
public class Sortilege extends Comportement{    
    /**
     * Constructeur
     * @param tabAction 
     */
    public Sortilege(ArrayList<Action> tabAction) {
    	super(tabAction);
    	try {
            // Enregistre une copie du tableau d'action dans le sortilège
            this.tabAction = (ArrayList<Action>) tabAction.clone();
        } catch(Exception e) {
            System.out.println("Aucune action dans le sort");
        }
    }

    
    /**
     * Action lors d'un sortilège
     * @param joueurImpacte : joueur recevant le sortilège
     * @return out : texte résumant l'action
     *         nomPhase : StackTraceElement permettant de situer la phase dans laquelle nous sommes
     */
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Combat combatCible,
			int phaseTour, Joueur joueurTourEnCours) {
		String out = "";
        if(tabAction == null){
            out += "Aucune sortilège à appliquer";
        } else {
            out += "----- Sortilège -----\n";
            if(joueurDestinataire == null)
            {
            	out += "Erreur dans la liste des joueurs destinataires";
            }
            for(Joueur joueurImpacte : joueurDestinataire){
                out += "Un sortilège s'applique sur " + joueurImpacte.getName() + " :";
                for(Action action : tabAction)
                    out += action.action(joueurEmetteur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
                out += "\n";
            }
            out += "--- Fin sortilège ---";
        }
        out += "\n";
        return out;
	}
}