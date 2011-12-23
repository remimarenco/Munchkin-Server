package carte;

import comportement.Condition;
import comportement.IncidentDeguerpir;
import comportement.IncidentFacheux;
import comportement.MonstreVaincu;
import joueur.Joueur;

/**
 * Classe Monstre.
 * Permet de créer des monstres.
 * Hérite de la classe Donjon
 * @author Rémi Marenco
 */
public class Monstre extends Donjon {
    protected int puissance;
    protected int bonusPuissance;
    // TODO : Comportement monstre vaincu

    /**
     * Constructeur de la classe Monstre. Hérite de la classe Donjon
     * @param nom
     * @param description
     * @param condition
     * @param incidentFacheux
     */
    public Monstre(int id,String nom, String description, Condition condition, IncidentFacheux incidentFacheux, MonstreVaincu monstreVaincu, IncidentDeguerpir incidentDeguerpir, int puissance) {
        super(id,nom, description);
        this.condition = condition;
        this.incidentFacheux = incidentFacheux;
        this.monstreVaincu = monstreVaincu;
        this.incidentDeguerpir = incidentDeguerpir;
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

	public void setPuissance(int puissance) {
		this.puissance = puissance;
	}

	public int getBonusPuissance() {
		return bonusPuissance;
	}

	public void setBonusPuissance(int bonusPuissance) {
		this.bonusPuissance = bonusPuissance;
	}

        public Condition getCondition() {
            return condition;
        }

        public void setCondition(Condition condition) {
            this.condition = condition;
        }

}