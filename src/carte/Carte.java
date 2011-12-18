package carte;

import comportement.classes.Condition;
import comportement.classes.Equipement;
import comportement.classes.IncidentFacheux;
import comportement.classes.MonstreVaincu;
import comportement.classes.Sortilege;
import joueur.Joueur;



public abstract class Carte {

    protected static int id = 0;
    protected String nom;
    protected String description;
    protected int type;

    protected IncidentFacheux incidentFacheux;
    protected Equipement equipement;
    protected Sortilege sortilege;
    protected Condition condition;
    protected MonstreVaincu monstreVaincu;

    
    public int getType(){
        return type;
    }
    
    /**
     * Méthode de test de toutes les méthodes d'actions
     */
    public void action(Joueur joueurImpacte){
        appliquerCondition(joueurImpacte);
        appliquerIncidentFacheux(joueurImpacte);
        appliquerSortilege(joueurImpacte);
        equiper(joueurImpacte);
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public void setIncidentFacheux(IncidentFacheux incidentFacheux) {
        this.incidentFacheux = incidentFacheux;
    }

    public void appliquerIncidentFacheux(Joueur joueurImpacte){
        if(this.incidentFacheux != null)
            this.incidentFacheux.actionIncidentFacheux(joueurImpacte);
        else
            System.out.println("Cette carte n'a pas d'incident facheux");
    }

    public void equiper(Joueur joueurImpacte){
        if(this.equipement != null)
            equipement.equipe(joueurImpacte);
        else
            System.out.println("Cette carte n'a pas d'équipement");
    }

    public void appliquerCondition(Joueur joueurImpacte){
        if(this.condition != null)
            this.condition.mettreCondition(joueurImpacte);
        else
            System.out.println("Cette carte n'a pas de condition");
    }

    public void appliquerSortilege(Joueur joueurImpacte){
        if(this.sortilege != null)
            this.sortilege.actionSortilege(joueurImpacte);
        else
            System.out.println("Cette carte n'a pas de malus");
    }
    
    public void appliquerMonstreVaincu(Joueur joueurImpacte){
    	if(this.monstreVaincu != null)
            this.monstreVaincu.actionMonstreVaincu(joueurImpacte);
        else
            System.out.println("Cette carte n'a pas de résultat d'un monstre vaincu");
    }
}