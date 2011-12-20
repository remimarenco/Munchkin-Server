package comportement.classes;

import java.util.ArrayList;
import java.util.Vector;

import comportement.Action;

import joueur.Joueur;
import joueur.Personnage;

public class Sortilege {
    private ArrayList<Action> tabAction;

    public Sortilege(ArrayList<Action> tabAction) {
        super();
        this.tabAction = tabAction;
    }

    public String actionSortilege(Joueur joueurImpacte)
    {
        String out = "";
        if(tabAction == null)
        {
            out += "Aucune sortilège à appliquer";
        }
        else
        {
            out += "----- Sortilège -----\n";
            out += "Un sortilège s'applique sur " + joueurImpacte.getName() + " :";
            for(Action action : tabAction)
                out += action.action(joueurImpacte);
            out += "\n";
            out += "--- Fin sortilège ---";
        }
        out += "\n";

        return out;
    }
}
