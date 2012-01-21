package comportement;

import java.util.ArrayList;

import partie.Partie;
import action.Action;
import joueur.Joueur;


/**
 * Comportement d'équipement a mettre sur une carte afin d'activer la possibilité de "s'équiper"
 * @author Rémi Marenco
 */
public  class Equipement extends Comportement {    
    /**
     * Constructeur
     * @param tabAction : ensemble d'action 
     */
    public Equipement(ArrayList<Action> tabAction) {
        super(tabAction);
    }

    /**
     * Equipe un joueur
     * @param joueurImpacte : le joueur a équiper
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {
            String out = "";

        if(tabAction != null){
            out += "--- Equipement ---\n";
            for(Action action : tabAction)
                out += action.action(joueurEmetteur, joueurDestinataire, partie);
            out += "--- Fin d'equipement ---\n";
        } else {
            out += "Aucun equipement !\n";
        }

        return out;
    }

    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie,
                    boolean choixJoueur) {
        String out = "";

        if(tabAction != null){
            out += "--- Equipement ---\n";
            for(Action action : tabAction)
                out += action.action(joueurEmetteur, joueurDestinataire, partie, choixJoueur);
            out += "--- Fin d'equipement ---\n";
        } else {
            out += "Aucun equipement !\n";
        }

        return out;
    }	
}