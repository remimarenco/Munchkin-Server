/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package comportement.classes;

import java.util.ArrayList;
import comportement.Action;
import joueur.Joueur;


/**
 *
 * @author simon.grabit
 */
public class IncidentDeguerpir {

        public ArrayList<Action> tabAction;

	public IncidentDeguerpir(ArrayList<Action> tabAction)
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

        public String actionIncidentDeguerpir(Joueur joueurImpacte)
	{
            String out = "";
            out += "--- Incident de fuite ---\n";
            out += "Le joueur a fuit mais a subit l'incident suivant " + joueurImpacte.getName() + " :\n";
            for(Action action : tabAction)
            {
                    out += action.action(joueurImpacte);
            }
            out += "--- Fin de monstre vaincu ---\n";
            return out;
        }
}
