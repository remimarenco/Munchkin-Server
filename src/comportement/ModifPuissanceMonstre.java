package comportement;

import java.util.ArrayList;

import carte.Monstre;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

public class ModifPuissanceMonstre extends Action{

	private ArrayList<Race> tabRace;
	private ArrayList<Classe> tabClasse;
	private int bonusPuissance;
	private Monstre monstre;
	
	public ModifPuissanceMonstre(ArrayList<Race> tabRace,
			ArrayList<Classe> tabClasse, int bonusPuissance, Monstre monstre) {
		super();
		this.tabRace = tabRace;
		this.tabClasse = tabClasse;
		this.bonusPuissance = bonusPuissance;
		this.monstre = monstre;
	}

	@Override
	public void action(Joueur joueurImpacte) {
		
		boolean accept=true;
		boolean raceTrouve=false;
		boolean classeTrouve=false;
		if(tabRace!=null)
		{
			for(Race race: tabRace)
			{
				if(joueurImpacte.getPersonnage().getRace().equals(race))
					raceTrouve=true;
			}
			if(!raceTrouve)
				accept=false;
		}
		
		if(tabClasse!=null)
		{
			for(Classe classe: tabClasse)
			{
				if(joueurImpacte.getPersonnage().getClasse().equals(classe))
					raceTrouve=true;
			}
			if(!classeTrouve)
				accept=false;
		}
		
		if (accept)
			monstre.setPuissance(monstre.getPuissance()+bonusPuissance);
		
	}

}
