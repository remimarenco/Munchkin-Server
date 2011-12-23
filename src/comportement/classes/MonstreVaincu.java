package comportement.classes;

import java.util.ArrayList;

import joueur.Joueur;

import comportement.Action;

public class MonstreVaincu {
	public ArrayList<Action> tabAction;
	
	public MonstreVaincu(ArrayList<Action> tabAction)
        {
			try
			{
				this.tabAction = (ArrayList<Action>) tabAction.clone();
			}
			catch(Exception e)
			{
				System.out.println("Aucune action dans la condition");
			}
        }
	
	public String actionMonstreVaincu(Joueur joueurImpacte)
	{
            String out = "";

            if(tabAction == null)
            {
                out += "Aucune condition sur ce monstre\n";
            }
            else
            {
                out += "--- Monstre Vaincu ---\n";
                out += "Une action monstre vaincu vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
                for(Action action : tabAction)
                {
                        out += action.action(joueurImpacte);
                }
                out += "\n";
                out += "--- Fin de monstre vaincu ---";
            }
            out += "\n";
            
            return out;
        }
}
