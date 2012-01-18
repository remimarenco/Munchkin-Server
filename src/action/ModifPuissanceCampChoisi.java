package action;

import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.Iterator;

import carte.Monstre;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;
import partie.Combat;
import partie.Partie;

public class ModifPuissanceCampChoisi extends Action {
	//private ArrayList<Race> tabRace;
    //private ArrayList<Classe> tabClasse;
	protected int bonus;
	protected Object campCible;
	
	public ModifPuissanceCampChoisi(int bonus)
	{
		this.bonus = bonus;
	}
	
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		
		String out = "";
		out += "Camp Cible !";
		Combat combat = partie.getCombat();
		if(combat != null)
		{
			Object obj = demandeCampCible(partie, joueurEmetteur);
			if(obj != null)
			{
				if(obj.getClass().equals(ArrayList.class))
				{
					ArrayList aL = (ArrayList) obj;
					java.util.Iterator it = aL.iterator();
					if(it.hasNext())
					{
						Object o = it.next();
						if(o.getClass().equals(Joueur.class))
						{
							out += "On applique le bonus de "+ bonus + " au camp gentil";
							partie.getCombat().setBonusTemporaireGentil(bonus);
						}
						else if(o.getClass().equals(Monstre.class))
						{
							out += "On applique le bonus de "+ bonus + " au camp mechant";
							partie.getCombat().setBonusTemporaireMechant(bonus);
						}
						else
						{

							out += "Probleme dans ModifPuissanceChoisi sur le campCible !";
						}
					}
				}
			}
			else
			{
				partie.getCombat().setBonusTemporaireGentil(bonus);
				out += "obj est null, on applique sur le camp Gentil";
			}
		}
		else
		{
			out += "combat est null, on est apparemment pas dans la bonne phase";
		}
		
		return out;
	}

	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie,
			boolean choixJoueur) {
		// TODO Auto-generated method stub
		return action(joueurEmetteur, joueurDestinataire, partie);
	}

}
