package carte;
import java.util.ArrayList;

import joueur.Personnage;
import comportement.*;
import comportement.Interface.Condition;
import comportement.Interface.Equipement;
import comportement.Interface.IncidentFacheux;
import comportement.Interface.Malus;



public abstract class Carte {
	protected IncidentFacheux incidentFacheux;
	protected Equipement equipement;
	protected Malus malus;
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
			System.out.println("Cette carte n'a pas d'équipement");
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
		if(this.malus != null)
		{
			Personnage pers = new Personnage("Joueur 2 ");
			this.malus.mettreMalus(pers);
		}
		else
		{
			System.out.println("Cette carte n'a pas de malus");
		}
	}
	
	
	
	
	

}
