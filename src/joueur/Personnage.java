package joueur;

import partie.Constante;

/**
 * Personnage : entité participant aux combats, etc...
 * @author Rémi Marenco
 */
public class Personnage {
    
    private int     niveau;
    private int     sexe;
    private boolean aChangeSexe;
    private int     capaciteFuite;
    private int     bonusCapaciteFuite;
    private int     nbEquipement;
    private Race    race;
    private Classe  classe;
    private int     puissanceObjet;
    private int     bonusPuissance;
    private int     capaciteEquipement;
    private String  nom;
    private int     maxCartes;
    /**
     * Constructeur
     * @param niveau
     * @param sexe
     * @param aChangeSexe
     * @param capaciteFuite
     * @param bonusCapaciteFuite
     * @param nbEquipement
     * @param puissance
     * @param bonusPuissance 
     */
    public Personnage(int niveau, int sexe,boolean aChangeSexe, int capaciteFuite, int bonusCapaciteFuite, int nbEquipement, int puissanceObjet, int bonusPuissance) {
        this.niveau         	= niveau;
        this.sexe           	= sexe;
        this.aChangeSexe    	= aChangeSexe;
        this.capaciteFuite  	= capaciteFuite;
        this.bonusCapaciteFuite	= 0;
        this.nbEquipement   	= nbEquipement;
        this.bonusPuissance 	= 0;
        this.puissanceObjet     = puissanceObjet;
        this.race               = Constante.RACE_HUMAINE;
        this.classe             = null;
        this.capaciteEquipement = 6;
        this.maxCartes          = 5;
    }
   
    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public int getMaxCartes()
    {
        return maxCartes;
    }

    public int getNiveau() {
        return niveau;
    }

    public String getNom() {
        return nom;
    }
        
    public void setNom(String name) {
        this.nom=name;
    }
    
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    public int getCapaciteFuite() {
        return capaciteFuite;
    }

    public void setCapaciteFuite(int capaciteFuite) {
        this.capaciteFuite = capaciteFuite;
    }
    
    public int getNbEquipement() {
        return nbEquipement;
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

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        race.defausserRace(this);
        this.race = race;
        race.modifPersonnage(this); // On modifie le personnage selon la race
    }

    public Classe getClasse() {
        return classe;
    }

    public void setClasse(Classe classe) {
        this.classe = classe;
    }
    
    public int getBonusCapaciteFuite() {
        return bonusCapaciteFuite;
    }

    public void setBonusCapaciteFuite(int bonusCapaciteFuite) {
        this.bonusCapaciteFuite = bonusCapaciteFuite;
    }

    public int getBonusPuissance() {
        return bonusPuissance;
    }

    public void setBonusPuissance(int bonusPuissance) {
        this.bonusPuissance = bonusPuissance;
    }
    
    public int getPuissanceObjet() {
        return puissanceObjet;
    }

    public void setPuissanceObjet(int puissanceObjet) {
        this.puissanceObjet = puissanceObjet;
    }

    public void setMaxCartes(int maxCartes) {
		this.maxCartes = maxCartes;
	}

	public int getCapaciteEquipement() {
        return capaciteEquipement;
    }

    public void setCapaciteEquipement(int capaciteEquipement) {
        this.capaciteEquipement = capaciteEquipement;
    }
    // ================================== //
    
    
    
    /**
     * Changement du niveau du personnage
     * @param deltaNiveau : delta de modification du niveau (-2, +1, etc..)
     * @return 
     */
    public boolean changerNiveau(int deltaNiveau){
        if(this.niveau + deltaNiveau < 1)
            this.setNiveau(1);
        else if(this.niveau + deltaNiveau > 10)
            this.setNiveau(10);
        else
            this.setNiveau(this.niveau + deltaNiveau);
        return true;
    }

    
    /**
     * Méthode de déguerpissage du perso
     */
    // TODO : vérifier la pertinence & l'utilité de cette méthode
    public Boolean deguerpir() {
        int resultatDe = Constante.nbAleatoire(1, 7);
        if((capaciteFuite + bonusCapaciteFuite) >= resultatDe){
            return true;
        }
        return false;
    }    
    
    
    /**
     * Retourne la puissance du perso
     * @return 
     */
    public int getPuissance(){
        return this.getNiveau() + this.getPuissanceObjet();
    }
}
