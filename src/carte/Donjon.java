package carte;

import partie.Constante;

/**
 * Classe Donjon permettant de référencer les cartes donjon
 * @author Rémi Marenco
 */
public class Donjon extends Carte {

    /**
     * Constructeur de la classe donjon. Hérite de la classe Carte
     * @param nom
     * @param description
     */
    public Donjon(String nom, String description) {
        this.nom = nom;
        this.description = description;
        Donjon.id = Donjon.id + 1;
        this.type = Constante.DONJON;
    }
    
    
}
