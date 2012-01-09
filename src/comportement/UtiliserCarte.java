package comportement;

import java.util.ArrayList;
import action.Action;
import joueur.Joueur;


/**
 *
 * @author simon.grabit
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
    public String UtiliserCarte(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours){
        String out = "";
        out += "--- Utiliser carte ---\n";
        out += "Le joueur utilise la compétence d'une carte\n";
        for(Action action : tabAction)
            out += action.action(joueurImpacte, nomPhase, joueurEnCours);
        out += "--- Fin Utiliser Carte ---\n";
        return out;
    }
}