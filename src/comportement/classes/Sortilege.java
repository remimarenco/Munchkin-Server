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

    public void actionSortilege(Joueur joueurImpacte){
        System.out.println("----- Sortilège -----");
        System.out.println("Un sortilège s'applique sur " + joueurImpacte.getNom() + " :");
        for(Action action : tabAction)
            action.action(joueurImpacte);
        System.out.println("--- Fin sortilège ---");
    }
}
