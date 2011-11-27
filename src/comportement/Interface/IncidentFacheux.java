package comportement.Interface;

import java.util.Vector;

import comportement.Action;

import joueur.Personnage;



public class IncidentFacheux {
	public Vector<Action> tabAction;
	
	public IncidentFacheux(Vector<Action> tab)
	{
		tabAction = tab;
	}
	
	public void actionIncidentFacheux(Personnage pers)
	{
		System.out.println("Un incident facheux vient de se déclencher : ");
		for(Action action : tabAction)
		{
			action.action();
		}
		System.out.println("--- Fin d'incident facheux ---");
	}

}
