package carte;
import java.util.ArrayList;

import comportement.Action;
import comportement.Interface.Condition;
import comportement.Interface.IncidentFacheux;
import comportement.Interface.Malus;


public class Donjon extends Carte {

	public Donjon(IncidentFacheux tab) {
		super();
		incidentFacheux = tab;
		// TODO Auto-generated constructor stub
	}

	public Donjon(Malus malus) {
		super();
		this.malus = malus;
		// TODO Auto-generated constructor stub
	}




}
