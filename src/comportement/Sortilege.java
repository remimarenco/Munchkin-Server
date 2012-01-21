package comportement;

import java.util.ArrayList;

import partie.Partie;
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
     * @param joueurEmetteur
     * @param joueurDestinataire
     * @param partie
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {
        String out = "";
        if(tabAction == null){
                out += "Aucune sortilège à appliquer";
        } else {
                out += "----- Sortilège -----\n";
                for(Action action : tabAction)
                        out += action.action(joueurEmetteur, joueurDestinataire, partie);
                out += "\n";
        }
        out += "--- Fin sortilège ---";
        out += "\n";
        return out;
    }


	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie,
			boolean choixJoueur) {
		String out = "";

		if(tabAction == null) {
			out += "Aucune sortilège à appliquer\n";
		} else {
			out += "----- Sortilège -----\n";
			for(Action action : tabAction)
				out += action.action(joueurEmetteur, joueurDestinataire, partie, choixJoueur);
			out += "\n--- Fin sortilège ---";
		}
        out += "\n";    
        return out;
	}
}