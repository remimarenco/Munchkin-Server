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
        /**
         * Permet de référencer les actions de la carte
         */
        ArrayList<Action> actionTab = new ArrayList<Action>();
        /**
         * Premiere action
         */
        actionTab.add(new PiocherCarte(PiocherCarte.PIOCHE_DONJON, 1));
        /**
         * Deuxième action
         */
        actionTab.add(new ChangerNiveau(5));
        /**
         * Ajout de la carte avec les actions définies au dessus
         */
        cartes.add(new Monstre("MonstreTest", "La description du monstre", null, new IncidentFacheux(actionTab), 10));

        cartes.add(new Monstre("Monstre1", "Le premier monstre", null, null, 5));
        cartes.add(new Monstre("Monstre2", "Le deuxième monstre", null, null, 3));
        cartes.add(new Objet("Objet1", "Le premier objet", null));
        cartes.add(new Objet("Objet1", "Le deuxième objet", null));
        cartes.add(new Sort("Sort1", "Le premier sort", null));
    }
}
