package partie;

import java.lang.reflect.Array;
import java.util.ArrayList;


import carte.Arme;
import carte.Tresor;


public class PiocheTresor {
	
	private static ArrayList<Tresor> piocheTresor= new ArrayList<Tresor>();
	
	
	
	public PiocheTresor() {
		super();
		load();
	}



	public ArrayList<Tresor> getPiocheTresor() {
		return piocheTresor;
	}



	public void load(){
	
	}
	

}
