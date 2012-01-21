package comportement;

import java.util.ArrayList;

import partie.Partie;
import action.Action;
import joueur.Joueur;

/**
 * Contient une liste d'action qui ne sont applicables que sous certaines conditions // TODO : Vérifier si correct
 * @author Rémi Marenco
 */
public class Condition extends Comportement {    
    /**
     * Constructeur
     * @param tabAction : ensemble d'actions 
     */
    public Condition(ArrayList<Action> tabAction){
        super(tabAction);
    }

    /**
     * Met une condition sur un joueur
     * @param joueurEmetteur
     * @param joueurDestinataire
     * @param partie
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {
        String out = "";

        if(tabAction == null){
                out += "Aucune condition sur ce monstre\n";
        } else {
                out += "--- Condition ---\n";
                for(Action action : tabAction)
                        out += action.action(joueurEmetteur, joueurDestinataire, partie);
                out += "\n--- Fin de condition ---\n";
        }
        return out;
    }

    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie,
                    boolean choixJoueur) {
        String out = "";

        if(tabAction == null){
                out += "Aucune condition sur ce monstre\n";
        } else {
                out += "--- Condition ---\n";
                for(Action action : tabAction)
                        out += action.action(joueurEmetteur, joueurDestinataire, partie, choixJoueur);
                out += "\n--- Fin de condition ---\n";
        }
        return out;
    }
}