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
		super();
		this.tabAction = tabAction;
	}
	
	public void mettreCondition(Joueur joueurImpacte)
	{
            System.out.println("--- Condition ---");
            System.out.println("Une condition vient de se déclencher sur " + joueurImpacte.getName() + " :");
            for(Action action : tabAction)
            action.action(joueurImpacte);
            System.out.println("--- Fin de condition ---");
	}
}
