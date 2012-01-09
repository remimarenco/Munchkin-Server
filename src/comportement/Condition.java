package comportement;

import java.util.ArrayList;
import action.Action;
import joueur.Joueur;

/**
 * // TODO : Commenter
 * @author Julien Rouvier
 */
public class Condition {
    
    private ArrayList<Action> tabAction;
    
    
    /**
     * // TODO : Commenter
     * @param tabAction 
     */
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
    public String mettreCondition(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        String out = "";

        if(tabAction == null){
            out += "Aucune condition sur ce monstre\n";
        } else {
            out += "--- Condition ---\n";
            out += "Une condition vient de se déclencher sur " + joueurImpacte.getName() + " :";
            for(Action action : tabAction)
                out += action.action(joueurImpacte, nomPhase, joueurEnCours);
            out += "\n--- Fin de condition ---\n";
        }
        return out;
    }
}
