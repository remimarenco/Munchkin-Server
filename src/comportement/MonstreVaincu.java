package comportement;

import java.util.ArrayList;

import partie.Combat;
import action.Action;
import joueur.Joueur;


/**
 * Applique un ensemble d'action lorsqu'un monstre est vaincu
 * @author Julien Rouvier
 */
public class MonstreVaincu {
    
    public ArrayList<Action> tabAction;
    
    
    /**
     * Constructeur
     * @param tabAction 
     */
    public MonstreVaincu(ArrayList<Action> tabAction){
        try {
            this.tabAction = (ArrayList<Action>) tabAction.clone();
        } catch(Exception e) {
            System.out.println("Aucune action dans le comportement MonstreVaincu");
        }
    }
	
    
    /**
     * Action lorsqu'un monstre est vaincu
     * @param joueurImpacte : joueur ayant vaincu le monstre
     * @return 
     */
    public String actionMonstreVaincu(Joueur joueurDestinateur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours) {
        
        String out = "";

        if(tabAction == null) {
            out += "Aucune condition sur ce monstre\n";
        } else {
            out += "--- Monstre Vaincu ---\n";
            for(Joueur joueurImpacte : joueurDestinataire){
	            out += "Une action monstre vaincu vient de se déclencher sur " + joueurImpacte.getName() + " :\n";
	            for(Action action : tabAction)
	                out += action.action(joueurDestinateur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
            }
            out += "\n--- Fin de monstre vaincu ---";
        }
        out += "\n";    
        return out;
    }
}
