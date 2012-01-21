package action;

import java.util.ArrayList;

import carte.Monstre;
import joueur.Joueur;
import joueur.Personnage;
import partie.Combat;
import partie.Partie;

public class ModifPuissanceCampChoisi extends Action {
        
    protected int bonus;
    protected Object campCible;
	
    public ModifPuissanceCampChoisi(int bonus){
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
						if(o.getClass().equals(Personnage.class))
						{
							out += "On applique le bonus de "+ bonus + " au camp gentil";
							partie.getCombat().setBonusTemporaireGentil(partie.getCombat().getBonusTemporaireGentil()+bonus);
						}
						else if(o.getClass().equals(Monstre.class))
						{
							out += "On applique le bonus de "+ bonus + " au camp mechant";
							partie.getCombat().setBonusTemporaireMechant(partie.getCombat().getBonusTemporaireMechant()+bonus);
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
				partie.getCombat().setBonusTemporaireGentil(partie.getCombat().getBonusTemporaireGentil()+bonus);
				out += "obj est null, on applique sur le camp Gentil";
			}
		}
		else
		{
			out += "combat est null, on est apparemment pas dans la bonne phase";
		}
		partie.sendInfos();
		return out;
	}

	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie,
			boolean choixJoueur) {
		// TODO Auto-generated method stub
        return action(joueurEmetteur, joueurDestinataire, partie);
    }

    @Override
    public boolean isPosable(Partie partie, Joueur joueur) {
        return true;
    }
}
