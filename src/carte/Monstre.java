package carte;

import comportement.classes.Condition;
import comportement.classes.IncidentFacheux;
import comportement.classes.MonstreVaincu;
import joueur.Joueur;

/**
 * Classe Monstre.
 * Permet de créer des monstres.
 * Hérite de la classe Donjon
 * @author Rémi Marenco
 */
public class Monstre extends Donjon {
    protected int puissance;
    protected int tresor;
    protected int niveau_gagne;
    // TODO : Comportement monstre vaincu

    /**
     * Constructeur de la classe Monstre. Hérite de la classe Donjon
     * @param nom
     * @param description
     * @param condition
     * @param incidentFacheux
     */
    public Monstre(String nom, String description, Condition condition, IncidentFacheux incidentFacheux, MonstreVaincu monstreVaincu, int puissance) {
        super(nom, description);
        this.condition = condition;
        this.incidentFacheux = incidentFacheux;
        this.monstreVaincu = monstreVaincu;
        this.puissance = puissance;
    }
    
    /*
     * Déjà implémentée dans Carte
    public boolean appliquerCondition(Joueur joueurImpacte)
    {
    	this.condition.mettreCondition(joueurImpacte);
    	return true;
    }
    */
    /* Déjà implémentée dans Carte
     * public boolean appliquerIncidentsFacheux(Joueur joueurImpacte){
        this.incidentFacheux.actionIncidentFacheux(joueurImpacte);
        return true;
    } */
    
    /*
     * Déjà implémentée dans Carte
    public boolean appliquerMonstreVaincu(Joueur joueurImpacte)
    {
    	this.monstreVaincu.actionMonstreVaincu(joueurImpacte);
    	return true;
    }
    */

    public int getPuissance() {
        return puissance;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }    
}