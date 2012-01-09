package joueur;

import partie.Constante;

/**
 * Personnage : entité participant aux combats, etc...
 * @author Julien Rouvier
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
    private int     bonusPuissance;  
    
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
    public Personnage(int niveau, int sexe, boolean aChangeSexe, int capaciteFuite, int bonusCapaciteFuite, int nbEquipement, int puissance, int bonusPuissance) {
        this.niveau         	= niveau;
        this.sexe           	= sexe;
        this.aChangeSexe    	= aChangeSexe;
        this.capaciteFuite  	= capaciteFuite;
        this.bonusCapaciteFuite	= 0;
        this.nbEquipement   	= nbEquipement;
        this.bonusPuissance 	= 0;
        		
        this.race = Constante.RACE_HUMAINE;
        this.classe = null;
    }
   

    
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
     * Renvoi le niveau du personnage
     * @return niveau : le niveau du personnage
     */
    public int getNiveau() {
        return niveau;
    }

    
    /**
     * Défini le niveau du personnage
     * @param niveau 
     */
    public void setNiveau(int niveau) {
        this.niveau = niveau;
    }

    
    /**
     * Retourne la capacité de fuite
     * @return capaciteFuite
     */
    public int getCapaciteFuite() {
        return capaciteFuite;
    }

    
    /**
     * Défini la capacité de fuite du perso
     * @param capaciteFuite 
     */
    public void setCapaciteFuite(int capaciteFuite) {
        this.capaciteFuite = capaciteFuite;
    }
    
    
    /**
     * Retourne le nombre d'équipement (??)
     * @return nbEquipement
     */
    public int getNbEquipement() {
        return nbEquipement;
    }

    
    /**
     * Défini le nombre d'équipement du perso
     * @param nbEquipement 
     */
    public void setNbEquipement(int nbEquipement) {
        this.nbEquipement = nbEquipement;
    }

    
    /**
     * Défini le sexe du perso
     * @param sexe 
     */
    public void setSexe(int sexe) {
        this.sexe = sexe;
    }
    
    
    /**
     * Renvoi le sexe du perso
     * @return sexe (Constante.SEXE_M ou Constante.Sexe_F)
     */
    public int getSexe() {
        return sexe;
    }
    
    
    /**
     * Vérifie si le personnage a déjà changé de sexe
     * @return 
     */
    public boolean isaChangeSexe() {
        return aChangeSexe;
    }
    
    
    /**
     * Indique que le personnage a changé de sexe
     * @param aChangeSexe 
     */
    public void setaChangeSexe(boolean aChangeSexe) {
        this.aChangeSexe = aChangeSexe;
    }

    
    /**
     * Retourne la race du perso
     * @return 
     */
    public Race getRace() {
        return race;
    }

    
    /**
     * Défini la race du perso
     * @param race 
     */
    public void setRace(Race race) {
        this.race = race;
        // On modifie le personnage selon la race
        race.modifPersonnage(this);
    }

    
    /**
     * Retourne la classe du perso
     * @return 
     */
    public Classe getClasse() {
        return classe;
    }

    
    /**
     * Défini la classe du perso
     * @param classe 
     */
    public void setClasse(Classe classe) {
        this.classe = classe;
    }

    
    /**
     * Méthode de déguerpissage du perso
     */
    // TODO : vérifier la pertinence & l'utilité de cette méthode
    public void deguerpir() {
        // TODO : A implémenter
    }    

    
    /**
     * Renvoi le bonus en capacité de fuite du perso
     * @return 
     */
    public int getBonusCapaciteFuite() {
        return bonusCapaciteFuite;
    }

    
    /**
     * Défini le bonus en capacité de fuite du perso
     * @param bonusCapaciteFuite 
     */
    public void setBonusCapaciteFuite(int bonusCapaciteFuite) {
        this.bonusCapaciteFuite = bonusCapaciteFuite;
    }

    
    /**
     * Retourne le bonus de puissance du perso
     * @return 
     */
    public int getBonusPuissance() {
        return bonusPuissance;
    }

    
    /**
     * Défini le bonus de puissance du perso
     * @param bonusPuissance 
     */
    public void setBonusPuissance(int bonusPuissance) {
        this.bonusPuissance = bonusPuissance;
    }
    
    
    /**
     * Retourne la puissance du perso
     * @return 
     */
    public int getPuissance(){
        return this.getNiveau() + this.getBonusPuissance();
    }
}
