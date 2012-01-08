package comportement;

import java.util.ArrayList;
import java.util.Vector;

import action.Action;


import joueur.Joueur;
import joueur.Personnage;

public class Condition {
    
    private ArrayList<Action> tabAction;
    
    public Condition(ArrayList<Action> tabAction){
        try{
            this.tabAction = (ArrayList<Action>) tabAction.clone();
        }
        catch(Exception e){
            System.out.println("Aucune action dans la condition");
        }
    }

    /**
     * Met une condition sur un joueur
     * @param joueurImpacte : joueur sur lequel s'applique la condition
     * @return out : texte résumant l'action
     */
    public String mettreCondition(Joueur joueurImpacte){
        String out = "";

        if(tabAction == null){
            out += "Aucune condition sur ce monstre\n";
        } else {
            out += "--- Condition ---\n";
            out += "Une condition vient de se déclencher sur " + joueurImpacte.getName() + " :";
            for(Action action : tabAction)
                out += action.action(joueurImpacte);
            out += "\n--- Fin de condition ---\n";
        }
        return out;
    }
}
