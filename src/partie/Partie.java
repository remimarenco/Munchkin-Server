package partie;

import joueur.Personnage;
import carte.Carte;

public class Partie {
	
	private PiocheTresor piocheTresor= new PiocheTresor();
	private PiocheDonjon piocheDonjon= new PiocheDonjon();
	private Defausse defausse= new Defausse();
	
	
	public Partie() {
		super();	
		for (Carte carte : this.piocheTresor.getPiocheTresor()) {
			carte.action();
		} 
		
		for (Carte carte : this.piocheDonjon.getPiocheDonjon()) {
			// Cas d'un besoin de faire une condition
			carte.action();
			// Cas d'un besoin de faire un incident fâcheux
			
			// Cas d'un besoin de faire un malus
			// Cas d'un besoin d'équiper un objet
		} 
		
		
	}
	
	

	
	
	
	
}
