package action;

import joueur.Joueur;

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
    @Override
    public String action(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours) {

        String out = "";
        out += "On modifie le nombre maximum d'objet portés par un joueur :\n";
        out += "Le joueur impliqué est "+joueurImpacte.getName();
        
        joueurImpacte.getPersonnage().setCapaciteEquipement(joueurImpacte.getPersonnage().getCapaciteEquipement()+bonusNbMax);
        
        return out;
    }
}