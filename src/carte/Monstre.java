package carte;

import comportement.Condition;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;

/**
 * Classe Monstre.
 * Permet de créer des monstres.
 * Hérite de la classe Donjon
 * @author Rémi Marenco
 */
public class Monstre extends Donjon {
    protected int puissance;
    protected int bonusPuissance;

    /**
     * Constructeur de la classe Monstre. Hérite de la classe Donjon
     * @param nom
     * @param description
     * @param condition
     * @param incidentFacheux
     */
    public Monstre(int id,String nom, String description, Condition condition, IncidentFacheux incidentFacheux, MonstreVaincu monstreVaincu, IncidentDeguerpir incidentDeguerpir, int puissance) {
        super(id,nom, description);
        this.condition          = condition;
        this.incidentFacheux    = incidentFacheux;
        this.monstreVaincu      = monstreVaincu;
        this.incidentDeguerpir  = incidentDeguerpir;
        this.puissance          = puissance;
    }
    
    /**
     * 
     * @return 
     */
    public int getPuissance() {
        return puissance;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public String getNom() {
        return nom;
    }

    
    /**
     * 
     * @return 
     */
    @Override
    public String getDescription() {
        return description;
    }

    
    /**
     * 
     * @param puissance 
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    
    /**
     * 
     * @return 
     */
    public int getBonusPuissance() {
        return bonusPuissance;
    }

    
    /**
     * 
     * @param bonusPuissance 
     */
    public void setBonusPuissance(int bonusPuissance) {
        this.bonusPuissance = bonusPuissance;
    }

    
    /**
     * 
     * @return 
     */
    public Condition getCondition() {
        return condition;
    }

    
    /**
     * 
     * @param condition 
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}