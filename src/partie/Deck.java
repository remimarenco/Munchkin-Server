/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import carte.Carte;
import carte.Monstre;
import carte.Objet;
import carte.Sort;
import java.util.ArrayList;

/**
 *
 * @author washi
 */
public class Deck {
    private static ArrayList<Carte> cartes;

    public Deck() {
        
        Carte carte1 = new Monstre("Monstre1", "Le premier monstre", null, null);
        Carte carte2 = new Monstre("Monstre2", "Le deuxième monstre", null, null);
        Carte carte3 = new Objet("Objet1", "Le premier objet", null);
        Carte carte4 = new Objet("Objet1", "Le deuxième objet", null);
        Carte carte5 = new Sort("Sort1", "Le premier sort", null);
    }

    
    
    public static ArrayList<Carte> getCartes() {
        return cartes;
    }

    public static void setCartes(ArrayList<Carte> cartes) {
        Deck.cartes = cartes;
    }
}
