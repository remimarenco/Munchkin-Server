package action;

import java.util.ArrayList;

import carte.Monstre;
import joueur.Joueur;
import joueur.Personnage;
import partie.Combat;
import partie.Constante;
import partie.Partie;

public class ModifPuissanceCampChoisi extends Action {
        
    protected int bonus;
    protected Object campCible;
    protected ArrayList<Integer> phasesIntervenir;
	
    public ModifPuissanceCampChoisi(int bonus){
        this.bonus = bonus;
        this.phasesIntervenir = new ArrayList<Integer>();
        this.phasesIntervenir.add(Constante.PHASE_CHERCHER_LA_BAGARRE);
    }
    
    public ModifPuissanceCampChoisi(int bonus, ArrayList<Integer> phasesIntervenir)
    {
        this.bonus = bonus;
        this.phasesIntervenir = (ArrayList<Integer>) phasesIntervenir.clone();
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
				if(obj instanceof ArrayList)
				{
					ArrayList aL = (ArrayList) obj;
					java.util.Iterator it = aL.iterator();
					if(it.hasNext())
					{
						Object o = it.next();
						if(o instanceof Personnage)
						{
							out += "On applique le bonus de "+ bonus + " au camp gentil";
							partie.getCombat().setBonusTemporaireGentil(partie.getCombat().getBonusTemporaireGentil()+bonus);
						}
						else if(o instanceof Monstre)
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
    
    /**
     * Méthode permettant de savoir si une action est intervenable ou non selon la phase du tour passé en paramètre
     * @param phaseTourEnCours
     * @return 
     */
    @Override
    public boolean isIntervenable(int phaseTourEnCours) {
        // Si la phase n'a pas été spécifiée ou contient PHASE_ALL, on retourne immédiatement vrai
        if(phasesIntervenir == null || phasesIntervenir.contains(Constante.PHASE_ALL) || phasesIntervenir.isEmpty())
        {
            return true;
        }
        // Sinon on parcourt les phasesIntervenir renseigné à la construction de l'objet, si on trouve un match avec celle passée en paramètre, on retourne vrai sinon on retourn faux
        else
        {
            for(Integer phaseIntervenir : phasesIntervenir)
            {
                if(phaseIntervenir.equals(phaseTourEnCours))
                {
                    return true;
                }
            }
        }
        return false;
    }
}
