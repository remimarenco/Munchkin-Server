package comportement;

import java.util.ArrayList;

import partie.Partie;
import action.Action;
import joueur.Joueur;


/**
 * Incident qui intervient lorqu'un personnage déguerpit d'un combat
 * @author Simon Grabit
 */
public class IncidentDeguerpir extends Comportement{

    public ArrayList<Action> tabAction;

    /**
     * Constructeur
     * @param tabAction 
     */
    public IncidentDeguerpir(ArrayList<Action> tabAction) {
    	super(tabAction);
    }

    /**
     * Applique un incident lors d'un déguerpissage
     * @param joueurEmetteur
     * @param joueurDestinataire
     * @param partie
     * @return 
     */
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		String out = "";
        out += "--- Incident de fuite ---\n";
        out += "Le joueur a fuit mais subit des incidents\n";
        for(Action action : tabAction)
            out += action.action(joueurEmetteur, joueurDestinataire, partie);
        out += "--- Fin d'incident fuite ---\n";
        return out;
	}
}
