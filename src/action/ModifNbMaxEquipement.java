package action;

import java.util.ArrayList;

import partie.Combat;
import joueur.Joueur;

/**
 * TODO :
 * @author Simon Grabit
 *
 */
public class ModifNbMaxEquipement extends Action{

    private int bonusNbMax;
	
    
    /**
     * Constructeur
     * @param bonusNbMax
     */
    public ModifNbMaxEquipement(int bonusNbMax) {
        super();
        this.bonusNbMax = bonusNbMax;
    }

    /**
     * Méthode modifiant le nombre maximum d'objet porté par le joueur
     * @param joueurImpacte
     * @return 
     */    
    // TODO : Description méthode + PROTECTION NULL
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Combat combatCible,
			int phaseTour, Joueur joueurTourEnCours) {
		
		String out = "";
        out += "On modifie le nombre maximum d'objet portés par un joueur :\n";
        
        for(Joueur joueur : joueurDestinataire)
        {
        	out += "Le joueur impliqué est "+joueur.getName();
        	joueur.getPersonnage().setCapaciteEquipement(joueur.getPersonnage().getCapaciteEquipement()+bonusNbMax);
        }
        
        return out;
	}
}