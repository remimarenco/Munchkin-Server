package carte;

import joueur.Personnage;
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

    public int getType() {
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
        if(this.incidentFacheux != null){
            System.out.println(joueurImpacte.getNom());
            this.incidentFacheux.actionIncidentFacheux(joueurImpacte);
        }else{
            System.out.println("Cette carte n'a pas d'incident facheux");
        }
    }

    public void equiper(Joueur joueurImpacte){
        if(this.equipement != null){
            // Personnage pers = new Personnage("Joueur 3 ");
            equipement.equipe(joueurImpacte);
        }else{
            System.out.println("Cette carte n'a pas d'�quipement");
        }
    }

    public void appliquerCondition(Joueur joueurImpacte){
        if(this.condition != null){
            //Personnage pers = new Personnage("Joueur 2 ");
            this.condition.mettreCondition(joueurImpacte);
        }else{
            System.out.println("Cette carte n'a pas de condition");
        }
    }

    public void appliquerSortilege(Joueur joueurImpacte){
        if(this.sortilege != null){
            //Personnage pers = new Personnage("Joueur 2 ");
            this.sortilege.mettreSortilege(joueurImpacte);
        }else{
            System.out.println("Cette carte n'a pas de malus");
        }
    }
    
    public void appliquerMonstreVaincu(Joueur joueurImpacte)
    {
    	if(this.monstreVaincu != null){
            //Personnage pers = new Personnage("Joueur 2 ");
            this.monstreVaincu.actionMonstreVaincu(joueurImpacte);
        }else{
            System.out.println("Cette carte n'a pas de résultat d'un monstre vaincu");
        }
    }
}
