package comportement;

import action.Action;
import java.util.ArrayList;

import partie.Partie;
import joueur.Joueur;


/**
 * Classe permettant de déclencher les incidents fâcheux
 * @author Rémi Marenco
 */
public class IncidentFacheux extends Comportement{

    public ArrayList<Action> tabAction; // Esemble d'action à effectuer sur un incident fâcheux

    /**
     * Constructeur
     * On définit les actions que va faire l'incident fâcheux
     * @param tab : Ensemble d'actions de l'incident fâcheux
     */
    public IncidentFacheux(ArrayList<Action> tabAction)
    {
    	super(tabAction);
    }

    
    /**
     * Application de l'ensemble des actions de l'incident fâcheux sur le joueur
     * @param joueurImpacte
     */
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		String out = "";
        if(tabAction != null && !(tabAction.isEmpty()))
        {
            out += "--- Incident fâcheux ---\n";
            
            for(Joueur joueurImpacte : joueurDestinataire)
            {
	            out += "Un incident fâcheux vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
	            out += "Il y a " + tabAction.size();
	            for(Action action : tabAction)
	                out += "\nAction : !" + action.action(joueurEmetteur, joueurDestinataire, partie);
            }
	        out += "--- Fin d'incident fâcheux ---\n";
        } else {
            out += "Aucune incident fâcheux !!\n";
        }
        return out;
	}
}
