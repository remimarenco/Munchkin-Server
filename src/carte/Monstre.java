package carte;

import comportement.classes.Condition;
import comportement.classes.IncidentFacheux;

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
    public Monstre(String nom, String description, Condition condition, IncidentFacheux incidentFacheux, int puissance, int tresor, int niveau_gagne) {
        super(nom, description);
        this.condition = condition;
        this.incidentFacheux = incidentFacheux;
        this.puissance = puissance;
        this.tresor = tresor;
        this.niveau_gagne = niveau_gagne;
    }
    
    public boolean appliquerIncidentsFacheux(){
        
        return true;
    }

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