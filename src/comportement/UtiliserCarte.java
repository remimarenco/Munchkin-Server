package comportement;

import java.util.ArrayList;

import partie.Combat;
import action.Action;
import joueur.Joueur;


/**
 *
 * @author Simon Grabit
 */
public class UtiliserCarte {

    public ArrayList<Action> tabAction;

    /**
     * Constructeur
     * @param tabAction 
     */
    public UtiliserCarte(ArrayList<Action> tabAction) {
        try {
                this.tabAction = (ArrayList<Action>) tabAction.clone();
        } catch(Exception e){
                System.out.println("Aucune action dans defausser");
        }
    }

    /**
     * Applique un incident lors d'un déguerpissage
     * @param joueurImpacte
     * @return 
     */
    public String UtiliserCarte(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours){
        String out = "";
        out += "--- Utiliser carte ---\n";
        out += "Le joueur utilise la compétence d'une carte\n";
        for(Action action : tabAction)
            out += action.action(joueurEmetteur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
        out += "--- Fin Utiliser Carte ---\n";
        return out;
    }
}