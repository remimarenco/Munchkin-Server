package comportement;

import java.util.ArrayList;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

public class ModifPuissancePersonnage extends Action{

	private ArrayList<Race> tabRace;
	private ArrayList<Classe> tabClasse;
	private int bonusPuissance;
	private boolean bNiveau=true;
	private boolean bObjet=true;
	
	public ModifPuissancePersonnage(ArrayList<Race> tabRace, ArrayList<Classe> tabClasse,
			int bonusPuissance) {
		super();
		this.tabRace = tabRace;
		this.tabClasse = tabClasse;
		this.bonusPuissance = bonusPuissance;
	}
	
	public ModifPuissancePersonnage(ArrayList<Race> tabRace, ArrayList<Classe> tabClasse,
			int bonusPuissance, boolean bNiveau, boolean bObjet) {
		super();
		this.tabRace = tabRace;
		this.tabClasse = tabClasse;
		this.bonusPuissance = bonusPuissance;
		this.bNiveau = bNiveau;
		this.bObjet = bObjet;
	}

	@Override
	public void action(Joueur joueurImpacte) {
		int puissanceObjet;
		int niveauJoueur;
		boolean raceTrouve=false;
		boolean classeTrouve=false;
		boolean accept=true;
		
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
		
		if(!bNiveau)
		{
			puissanceObjet=joueurImpacte.getPersonnage().getPuissance()-joueurImpacte.getPersonnage().getNiveau();
			this.bonusPuissance -= puissanceObjet;
		}
		if(!bObjet)
		{
			niveauJoueur=joueurImpacte.getPersonnage().getNiveau();
			this.bonusPuissance -= niveauJoueur;
		}
		
		if(accept)
			joueurImpacte.getPersonnage().setBonusPuissance(joueurImpacte.getPersonnage().getBonusPuissance()+bonusPuissance);
	}

}
