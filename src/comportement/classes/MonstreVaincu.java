package comportement.classes;

import java.util.ArrayList;

import joueur.Joueur;

import comportement.Action;

public class MonstreVaincu {
	public ArrayList<Action> tabAction;
	
	public MonstreVaincu(ArrayList<Action> tab)
    {
            tabAction = tab;
    }
	
	public void actionMonstreVaincu(Joueur joueurImpacte)
	{
		System.out.println("--- Monstre Vaincu ---");
        System.out.println("Une action monstre vaincu vient de se déclencher sur " + joueurImpacte.getNom() + " :");
        for(Action action : tabAction)
        {
                action.action(joueurImpacte);
        }
        System.out.println("--- Fin d'incident facheux ---");
	}
}
