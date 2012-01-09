package comportement;

import java.util.ArrayList;
import action.Action;
import joueur.Joueur;


/**
 * 
 * @author Julien Rouvier
 */
public  class Equipement {
    
    private ArrayList<Action> tabAction;

    /**
     * Constructeur
     * @param tabAction : ensemble d'action 
     */
    public Equipement(ArrayList<Action> tabAction) {
        try{
            this.tabAction = (ArrayList<Action>) tabAction.clone();
        } catch(Exception e) {
            System.out.println("Aucune action dans l'equipement");
        }
    }

    /**
     * Equipe un joueur
     * @param joueurImpacte : le joueur a équiper
     * @return out : texte résumant l'action
     */
    public String equipe(Joueur joueurImpacte){
        String out = "";
        
        if(tabAction != null){
            out += "--- Equipement ---\n";
            out += "Un équipement vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
            for(Action action : tabAction)
                out += action.action(joueurImpacte);
            out += "--- Fin d'equipement ---\n";
        } else {
           out += "Aucun equipement !\n";
        }
        
        return out;
    }	
}