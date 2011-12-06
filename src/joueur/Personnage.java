package joueur;

public class Personnage {
	
    private int     niveau;
    private char    sexe;
    private boolean aChangeSexe;
    private int     capaciteFuite;
    private int     nbEquipement;
	
    
    public Personnage(int niveau, char sexe, boolean aChangeSexe, int capaciteFuite, int nbEquipement) {
        this.niveau         = niveau;
        this.sexe           = sexe;
        this.aChangeSexe    = aChangeSexe;
        this.capaciteFuite  = capaciteFuite;
        this.nbEquipement   = nbEquipement;
    }
        
    public Personnage() {
        super();
        this.niveau = 1;
    }

    public Personnage(String name) {
        super();
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

    public void setSexe(char sexe) {
        this.sexe = sexe;
    }
    
    public char getSexe() {
        return sexe;
    }
    
    public boolean isaChangeSexe() {
        return aChangeSexe;
    }
    
    
    public void setaChangeSexe(boolean aChangeSexe) {
        this.aChangeSexe = aChangeSexe;
    }

    
    
    
    
}
