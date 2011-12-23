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
	public String action(Joueur joueurImpacte) {
                String out = "";
                out += "On passe dans une action de modification de puissance de monstre :\n";
                out += "Le monstre impliqué est "+monstre.getNom();
                out += ", le bonus puissance attribué est de " + this.bonusPuissance;
                

                if(tabRace != null && !(tabRace.isEmpty()))
                {
                    out += ", les races contre lesquelles ce bonus s'applique sont :";
                    for(Race race : tabRace)
                    {
                        out += " ";
                        out += race.toString();
                    }
                }
                else
                {
                    out += " , aucune race n'est concernée par ce bonus";
                }

                
                
                if(tabClasse != null && !(tabClasse.isEmpty()))
                {
                    out += ", les classes contre lesquelles ce bonus s'applique sont :";
                    for(Classe classe : tabClasse)
                    {
                        out += " ";
                        out += classe.toString();
                    }
                    out += "\n";
                }
                else
                {
                    out += " , aucune classe n'est concernée par ce bonus";
                }
                System.out.println(out);


		boolean accept=true;
		boolean raceTrouve=false;
		boolean classeTrouve=false;
		if(tabRace != null && tabRace.isEmpty())
		{
			for(Race race: tabRace)
			{
				if(joueurImpacte.getPersonnage().getRace().equals(race))
					raceTrouve=true;
			}
			if(!raceTrouve)
				accept=false;
		}
		
		if(tabClasse != null && tabClasse.isEmpty())
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
                return out;
		
	}

}
