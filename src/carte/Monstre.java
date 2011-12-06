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
    /**
     * Constructeur de la classe Monstre. Hérite de la classe Donjon
     * @param nom
     * @param description
     * @param condition
     * @param incidentFacheux
     */
    public Monstre(String nom, String description, int id, Condition condition, IncidentFacheux incidentFacheux) {
        super(nom, description, id);
        this.condition = condition;
        this.incidentFacheux = incidentFacheux;
    }

}
