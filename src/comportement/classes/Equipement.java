package comportement.classes;

import java.util.ArrayList;
import java.util.Vector;

import comportement.Action;

import joueur.Joueur;
import joueur.Personnage;



public  class Equipement {
	private ArrayList<Action> tabAction;
	
	
	
	public Equipement(ArrayList<Action> tabAction) {
		try
		{
			this.tabAction = (ArrayList<Action>) tabAction.clone();
		}
		catch(Exception e)
		{
			System.out.println("Aucune action dans la condition");
		}
	}



	public String equipe(Joueur joueurImpacte)
        {
            String out = "";

            if(tabAction != null)
            {
                out += "--- Equipement ---\n";
                out += "Un equipement vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
                for(Action action : tabAction)
                    out += action.action(joueurImpacte);
                out += "--- Fin d'equipement ---\n";
            }
            else
            {
               out += "Aucun equipement !\n";
            }

            return out;
	}
	
}