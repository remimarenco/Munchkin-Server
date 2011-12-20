package comportement;

import joueur.Joueur;
import partie.Constante;

/**
 * Classe permettant de faire une action de piocher une carte donjon ou trésor
 * @author Rémi Marenco
 */
public class PiocherCarte extends Action {

    /**
     * Nombre de carte à piocher
     */
    int nbCarte;

    /**
     * Type de pioche dans laquelle piocher les cartes
     */
    private Class type_pioche;


    /**
     * Constructeur de piocherCarte
     * @param type_pioche
     * => Type de pioche : PIOCHE_TRESOR ou PIOCHE_DONJON
     * @param nbCarte
     * => Nombre de cartes à piocher sur l'action
     */
    public PiocherCarte(Class type_pioche,int nbCarte){
        this.nbCarte = nbCarte;
        this.type_pioche=type_pioche;
    }

    /**
     * Action d'une carte qui va faire piocher un joueur dans un type de pioche un nombre de cartes
     * @param joueurImpacte
     * => Le joueur qui va piocher les cartes
     */
    @Override
    public String action(Joueur joueurImpacte) {
        String out = "";
        // On récupère le nom du joueur qui reçoit l'action
        out += "Le joueur " + joueurImpacte.getName();
        // Affichage selon le nombre de carte
        if(nbCarte > 1)
           out += " pioche "+nbCarte+" cartes dans la pioche ";
        else
            out += " pioche "+nbCarte+" carte dans la pioche ";

        if(type_pioche == Constante.DONJON)
        {
            out += "donjon";
            joueurImpacte.piocherCarte(Constante.DONJON);
        }
        else
        {
            out += "trésor";
            joueurImpacte.piocherCarte(Constante.TRESOR);
        }
        return out;
    }
}
