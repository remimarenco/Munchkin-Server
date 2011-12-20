package comportement.classes;

import java.util.ArrayList;
import java.util.Vector;

import comportement.Action;

import joueur.Joueur;
import joueur.Personnage;

public class Condition {
	private ArrayList<Action> tabAction;
	public Condition(ArrayList<Action> tabAction)
	{
		this.tabAction = tabAction;
	}
	
	public String mettreCondition(Joueur joueurImpacte)
	{
            String out = "";

            if(tabAction == null)
            {
                out += "Aucune condition sur ce monstre\n";
            }
            else
            {
                out += "--- Condition ---\n";
                out += "Une condition vient de se déclencher sur " + joueurImpacte.getName() + " :";
                for(Action action : tabAction)
                    out += action.action(joueurImpacte);
                out += "\n";
                out += "--- Fin de condition ---\n";
            }
            return out;
	}
}
