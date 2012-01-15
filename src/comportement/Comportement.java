package comportement;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Partie;

import action.Action;

public abstract class Comportement {
	// TODO : Phase jouable d'un comportement
	protected ArrayList<Action> tabAction;
	
	public Comportement(ArrayList<Action> tabAction)
	{
		try {
			this.tabAction = (ArrayList<Action>) tabAction.clone();
		} catch(Exception e){
			System.out.println("Aucune action dans le constructeur du comportement");
		}
	}
	
	public abstract String action(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie);

}
