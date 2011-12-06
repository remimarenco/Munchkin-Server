package partie;

import java.util.ArrayList;
import java.util.Vector;

import comportement.Action;
import comportement.ChangerNiveau;
import comportement.PiocherCarte;
import comportement.classes.*;

import carte.*;



public class PiocheDonjon extends Pioche{

    public PiocheDonjon() {
        super();
        load();
    }
    /**
     * Permet de charger les cartes dans la piocheDonjon.
     * On définit :
     * - Un vecteur d'action, permettant de référencer les actions de la future carte
     * à instancier
     * - On ajout à ce vecteur
     */
    public void load(){
        Vector<Action> actionTab = new Vector<Action>();
        actionTab.add(new PiocherCarte(1));
        actionTab.add(new ChangerNiveau(5));
        pioche.add(new Monstre("MonstreTest", "La description du monstre", null, new IncidentFacheux(actionTab)));
    }
}
