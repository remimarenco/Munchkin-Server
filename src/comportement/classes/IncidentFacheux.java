package comportement.classes;

import comportement.Action;
import java.util.ArrayList;
import joueur.Joueur;


/**
 * Classe permettant de déclencher les incidents fâcheux
 * @author Rémi Marenco
 */
public class IncidentFacheux {

    /**
     * Vecteur d'action. Permet de définir un ensemble d'action à effectuer sur un incident fâcheux
     */
    public ArrayList<Action> tabAction;

    /**
     * Constructeur de la classe.
     * On définit les actions que va faire l'incident fâcheux
     * @param tab => Ensemble d'actions de l'incident fâcheux
     */
    public IncidentFacheux(ArrayList<Action> tab)
    {
            tabAction = tab;
    }

    /**
     * Application de l'ensemble des actions de l'incident fâcheux sur le joueur
     * @param joueurImpacte
     */
    public void actionIncidentFacheux(Joueur joueurImpacte)
    {
            System.out.println("--- Incident facheux ---");
            System.out.println("Un incident facheux vient de se déclencher sur " + joueurImpacte.getNom() + " :");
            for(Action action : tabAction)
            {
                    action.action(joueurImpacte);
            }
            System.out.println("--- Fin d'incident facheux ---");
    }

}
