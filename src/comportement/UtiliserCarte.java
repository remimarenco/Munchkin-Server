package comportement;

import java.util.ArrayList;

import partie.Partie;
import action.Action;
import joueur.Joueur;


/**
 * Utiliser la compétence d'une carte
 * @author Simon Grabit
 */
public class UtiliserCarte extends Comportement{
    /**
     * Constructeur
     * @param tabAction 
     */
    public UtiliserCarte(ArrayList<Action> tabAction) {
        super(tabAction);
    	try {
                this.tabAction = (ArrayList<Action>) tabAction.clone();
        } catch(Exception e){
                System.out.println("Aucune action dans defausser");
        }
    }

    /**
     * Applique un incident lors d'un déguerpissage
     * @param joueurEmetteur
     * @param joueurDestinataire
     * @param partie
     * @return 
     */
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {
        String out = "";
        if(tabAction == null) {
                out += "Aucune action sur utiliserCarte\n";
        }
        else{
            out += "--- Utiliser carte ---\n";
            out += "Le joueur utilise la compétence d'une carte\n";
            for(Action action : tabAction)
                out += action.action(joueurEmetteur, joueurDestinataire, partie);
            out += "--- Fin Utiliser Carte ---\n";
        }
        return out;    
    }

    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie,
                    boolean choixJoueur) {
        String out = "";

        if(tabAction == null) {
            out += "Aucune action sur utiliserCarte\n";
        } else {
            out += "--- Utiliser carte ---\n";
            for(Action action : tabAction)
                    out += action.action(joueurEmetteur, joueurDestinataire, partie, choixJoueur);
            out += "--- Fin Utiliser Carte ---\n";
        }
        out += "\n";    
        return out;
    }
}