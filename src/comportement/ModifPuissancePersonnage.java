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
	public String action(Joueur joueurImpacte) {
		int puissanceObjet;
		int niveauJoueur;
		boolean raceTrouve=false;
		boolean classeTrouve=false;
		boolean accept=true;

                String out = "";
                out += "On passe dans une action de modification de puissance de personnage :\n";
                out += "Le joueur impliqué est "+joueurImpacte.getName();
                out += ", le bonus puissance attribué est de " + this.bonusPuissance;
                out += ", les classes pour lesquelles ce bonus s'applique sont :";
                for(Race race : tabRace)
                {
                    out += " ";
                    out += race.toString();
                }
                out += ", les races pour lesquelles ce bonus s'applique sont :";
                for(Classe classe : tabClasse)
                {
                    out += " ";
                    out += classe.toString();
                }
                out += "\n";
		
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
			niveauJoueur=joueurImpacte.getPersonnage().getNiveau();
			this.bonusPuissance -= niveauJoueur;
		}
                if(!bObjet)
		{
			puissanceObjet=joueurImpacte.getPersonnage().getPuissance()-joueurImpacte.getPersonnage().getNiveau();
			this.bonusPuissance -= puissanceObjet;
		}
		
		if(accept)
			joueurImpacte.getPersonnage().setBonusPuissance(joueurImpacte.getPersonnage().getBonusPuissance()+bonusPuissance);
                return out;
        }

}
