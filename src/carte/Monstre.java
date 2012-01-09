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
     * // TODO : Commenter
     * @return 
     */
    public int getPuissance() {
        return puissance;
    }

    
    /**
     * // TODO : Commenter
     * @return 
     */
    @Override
    public String getNom() {
        return nom;
    }

    
    /**
     * // TODO : Commenter
     * @return 
     */
    @Override
    public String getDescription() {
        return description;
    }

    
    /**
     * // TODO : Commenter
     * @param puissance 
     */
    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    
    /**
     * // TODO : Commenter
     * @return 
     */
    public int getBonusPuissance() {
        return bonusPuissance;
    }

    
    /**
     * // TODO : Commenter
     * @param bonusPuissance 
     */
    public void setBonusPuissance(int bonusPuissance) {
        this.bonusPuissance = bonusPuissance;
    }

    
    /**
     * // TODO : Commenter
     * @return 
     */
    public Condition getCondition() {
        return condition;
    }

    
    /**
     * // TODO : Commenter
     * @param condition 
     */
    public void setCondition(Condition condition) {
        this.condition = condition;
    }
}