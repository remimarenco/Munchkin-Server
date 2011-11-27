package joueur;

public class Personnage {
	
	private String name= new String();
	private int niveau =1;

	
	
	public Personnage() {
		super();
	}

	public Personnage(String name) {
		super();
		this.name = name;
	}

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public String getName() {
		return name;
	}
	
	

}
