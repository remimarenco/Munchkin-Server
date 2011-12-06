package carte;
import java.util.ArrayList;

import joueur.Personnage;
import comportement.*;
import comportement.classes.Condition;
import comportement.classes.Equipement;
import comportement.classes.IncidentFacheux;
import comportement.classes.Sortilege;



public abstract class Carte {

        protected static int id = 0;
        protected String nom;
        protected String description;
        
	protected IncidentFacheux incidentFacheux;
	protected Equipement equipement;
	protected Sortilege sortilege;
	protected Condition condition;
	
	public void action(){
		appliquerCondition();
		appliquerIncidentFacheux();
		appliquerMalus();
		equiper();
	
	}
	

	public void setEquipement(Equipement equipement) {
		this.equipement = equipement;
	}

	public void setIncidentFacheux(IncidentFacheux incidentFacheux) {
		this.incidentFacheux = incidentFacheux;
	}
	
	
	
	public void appliquerIncidentFacheux(){
		if(this.incidentFacheux != null)
		{
			Personnage pers = new Personnage("Joueur 1 ");
		
			this.incidentFacheux.actionIncidentFacheux(pers);
		}
		else
		{
			System.out.println("Cette carte n'a pas d'incident facheux");
		}
	}
	
	public void equiper(){
		if(this.equipement != null)
		{
			Personnage pers = new Personnage("Joueur 3 ");
			equipement.equipe(pers);
		}
		else
		{
			System.out.println("Cette carte n'a pas d'�quipement");
		}
	}
	
	public void appliquerCondition(){
		if(this.condition != null)
		{
			Personnage pers = new Personnage("Joueur 2 ");
			this.condition.mettreCondition(pers);
		}
		else
		{
			System.out.println("Cette carte n'a pas de condition");
		}
	}
	
	public void appliquerMalus(){
		if(this.sortilege != null)
		{
			Personnage pers = new Personnage("Joueur 2 ");
			this.sortilege.mettreSortilege(pers);
		}
		else
		{
			System.out.println("Cette carte n'a pas de malus");
		}
	}
}
