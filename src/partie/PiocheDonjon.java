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

    public void load(){
            Vector<Action> actionTab = new Vector<Action>();
            actionTab.add(new PiocherCarte(1));
            actionTab.add(new ChangerNiveau(5));
            pioche.add(new Monstre(null,new IncidentFacheux(actionTab)));
            //piocheDonjon.add(new Malediction());
            /*piocheDonjon.add(new Monstre(new ChangerNiveau(1)));			
            piocheDonjon.add(new Malediction(new ChangerEquipement(true)));
            piocheDonjon.add(new Objet(new ))*/
    }
}
