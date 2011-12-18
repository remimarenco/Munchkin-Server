package joueur;

public class Personnage {

    
    private int     niveau;
    private int    sexe;
    private boolean aChangeSexe;
    private int     capaciteFuite;
    private int		bonusCapaciteFuite;
    private int     nbEquipement;
    private int     puissance;
    private int		bonusPuissance;
	
    
    public Personnage(int niveau, int sexe, boolean aChangeSexe, int capaciteFuite, int bonusCapaciteFuite, int nbEquipement, int puissance, int bonusPuissance) {
        this.niveau         	= niveau;
        this.sexe           	= sexe;
        this.aChangeSexe    	= aChangeSexe;
        this.capaciteFuite  	= capaciteFuite;
        this.bonusCapaciteFuite	= 0;
        this.nbEquipement   	= nbEquipement;
        this.puissance 			= 0;
        this.bonusPuissance 	= 0;
        		
    }
        
    public Personnage() {
        super();
        this.niveau = 1;
    }

    public Personnage(String name) {
        super();
    }

    public boolean changerNiveau(int deltaNiveau){
        if(this.niveau + deltaNiveau < 1)
            this.setNiveau(1);
        else
            this.setNiveau(this.niveau + deltaNiveau);
        
        return true;
    }
    
    public int getNiveau() {
        return niveau;
    }

    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getCapaciteFuite() {
        return capaciteFuite;
    }

    public int getNbEquipement() {
        return nbEquipement;
    }

    public void setCapaciteFuite(int capaciteFuite) {
        this.capaciteFuite = capaciteFuite;
    }

    public void setNbEquipement(int nbEquipement) {
        this.nbEquipement = nbEquipement;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }
    
    public int getSexe() {
        return sexe;
    }
    
    public boolean isaChangeSexe() {
        return aChangeSexe;
    }
    
    
    public void setaChangeSexe(boolean aChangeSexe) {
        this.aChangeSexe = aChangeSexe;
    }

	public int getBonusCapaciteFuite() {
		return bonusCapaciteFuite;
	}

	public void setBonusCapaciteFuite(int bonusCapaciteFuite) {
		this.bonusCapaciteFuite = bonusCapaciteFuite;
	}

	public int getPuissance() {
		return puissance;
	}

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public int getBonusPuissance() {
		return bonusPuissance;
	}

	public void setBonusPuissance(int bonusPuissance) {
		this.bonusPuissance = bonusPuissance;
	}    
}
