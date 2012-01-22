package action;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Constante;
import partie.Partie;


/**
 * Classe permettant de faire une action de piocher une carte donjon ou trésor
 * @author Rémi Marenco
 */
public class PiocherCarte extends Action {

    int nbCarte;                // Nombre de carte à piocher
    private Class type_pioche;  // Type de pioche dans laquelle piocher les cartes

    /**
     * Constructeur de piocherCarte
     * @param type_pioche : PIOCHE_TRESOR ou PIOCHE_DONJON
     * @param nbCarte : Nombre de cartes à piocher sur l'action
     */
    public PiocherCarte(Class type_pioche,int nbCarte){
        this.nbCarte     = nbCarte;
        this.type_pioche = type_pioche;
    }

    
    /**
     * Action d'une carte qui va faire piocher un joueur dans un type de pioche un nombre de cartes
     * @param joueurImpacte : Le joueur qui va piocher les cartes
     * @return out : texte résumant l'action
     */
    // TODO : Description méthode + PROTECTION NULL
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {

        String out = "";
        int i;
        
        getJoueursTemporaire(joueurEmetteur, joueurDestinataire, partie);

        for(Joueur joueurImpacte : joueurDestinataireTemp) {
            if(joueurImpacte.getPersonnage().getRace()==Constante.RACE_HALFELIN)
                    nbCarte++;

            out += "Le joueur " + joueurImpacte.getName();

            if(nbCarte > 1)
                    out += " pioche "+nbCarte+" cartes dans la pioche ";
            else
                    out += " pioche "+nbCarte+" carte dans la pioche ";

            for(i=0; i<nbCarte; i++){
                if(type_pioche == Constante.DONJON){
                    out += "donjon";
                    joueurImpacte.piocherCarte(Constante.DONJON);
                } else {
                    out += "trésor";
                    joueurImpacte.piocherCarte(Constante.TRESOR);
                }
            }
        }
        return out;
    }


    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie,
                    boolean choixJoueur) {
        boolean ancienChoixJoueur = this.choixJoueur;
        this.choixJoueur = choixJoueur;
        String out = action(joueurEmetteur, joueurDestinataire, partie);
        this.choixJoueur = ancienChoixJoueur;
        return out;
    }
    
    @Override
    public boolean isPosable(Partie partie, Joueur joueur) {
        return true;
    }

    @Override
    public boolean isIntervenable(int phaseTourEnCours) {
        return true;
    }
}
