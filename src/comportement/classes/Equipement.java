package comportement.classes;

import java.util.ArrayList;
import java.util.Vector;

import comportement.Action;

import joueur.Joueur;
import joueur.Personnage;



public  class Equipement {
	private ArrayList<Action> tabAction;
	
	
	
	public Equipement(ArrayList<Action> tabAction) {
		super();
		this.tabAction = tabAction;
	}



	public String equipe(Joueur joueurImpacte)
        {
            String out;
            out = "--- Equipement ---\n";
            out += "Un equipement vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
            for(Action action : tabAction)
                out += action.action(joueurImpacte);
            out += "--- Fin d'equipement ---\n";
            return out;
	}
	
}