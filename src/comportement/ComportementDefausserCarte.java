package comportement;

import java.util.ArrayList;

import partie.Partie;
import action.Action;
import joueur.Joueur;


/**
 * Un joueur doit défausser une carte
 * @author simon.grabit
 */
public class ComportementDefausserCarte extends Comportement {
    /**
     * Constructeur
     * @param tabAction 
     */
    public ComportementDefausserCarte(ArrayList<Action> tabAction) {
    	super(tabAction);
    }
    
    /**
     * Applique un incident lors d'un déguerpissage
     * @param joueurEmetteur
     * @param joueurDestinataire
     * @param partie
     * @return out : texte résumant l'action
     */
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		String out = "";
        out += "--- Defausser carte ---\n";
        out += "Le joueur défausse une carte\n";
        for(Action action : tabAction)
            out += action.action(joueurEmetteur, joueurDestinataire, partie);
        out += "--- Fin Defausser Carte ---\n";
        return out;
	}
}
