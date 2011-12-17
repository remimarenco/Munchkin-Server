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
        appliquerCondition();
        appliquerIncidentFacheux(joueurImpacte);
        appliquerMalus();
        equiper();
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

    public void equiper(){
        if(this.equipement != null){
            Personnage pers = new Personnage("Joueur 3 ");
            equipement.equipe(pers);
        }else{
            System.out.println("Cette carte n'a pas d'�quipement");
        }
    }

    public void appliquerCondition(){
        if(this.condition != null){
            Personnage pers = new Personnage("Joueur 2 ");
            this.condition.mettreCondition(pers);
        }else{
            System.out.println("Cette carte n'a pas de condition");
        }
    }

    public void appliquerMalus(){
        if(this.sortilege != null){
            Personnage pers = new Personnage("Joueur 2 ");
            this.sortilege.mettreSortilege(pers);
        }else{
            System.out.println("Cette carte n'a pas de malus");
        }
    }
}
