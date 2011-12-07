/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import carte.Carte;
import carte.Monstre;
import carte.Objet;
import carte.Sort;
import comportement.Action;
import comportement.ChangerNiveau;
import comportement.PiocherCarte;
import comportement.classes.IncidentFacheux;
import java.util.ArrayList;

/**
 *
 * @author washi
 */
public final class Deck {
    private static ArrayList<Carte> cartes;

    public Deck() {
        this.load();
    }

    public static ArrayList<Carte> getCartes() {
        return cartes;
    }

    public static void setCartes(ArrayList<Carte> cartes) {
        Deck.cartes = cartes;
    }


    /**
     * Permet de charger les cartes dans la piocheDonjon.
     * On définit :
     * - Un vecteur d'action, permettant de référencer les actions de la future carte
     * à instancier
     * - On ajout à ce vecteur
     */
    private void load(){
        ArrayList<Action> actionTab = new ArrayList<Action>();
        actionTab.add(new PiocherCarte(PiocherCarte.PIOCHE_DONJON, 1));
        actionTab.add(new ChangerNiveau(5));
        
        cartes.add(new Monstre("MonstreTest", "La description du monstre", null, new IncidentFacheux(actionTab)));
        cartes.add(new Monstre("Monstre1", "Le premier monstre", null, null));
        cartes.add(new Monstre("Monstre2", "Le deuxième monstre", null, null));
        cartes.add(new Objet("Objet1", "Le premier objet", null));
        cartes.add(new Objet("Objet1", "Le deuxième objet", null));
        cartes.add(new Sort("Sort1", "Le premier sort", null));
    }
}
