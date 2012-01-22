package action;

import java.util.ArrayList;

import carte.Carte;
import joueur.CartesJoueur;
import joueur.Joueur;
import partie.Constante;
import partie.Partie;

/**
 * Classe permettant de defausser un ou plusieurs cartes d'un type spécifique dans un tas spécifique
 * @author Simon Grabit
 */
public class DefausserCarte extends Action {

    private ArrayList<Class> typeCarte;
    private int nbCarte;
    private int typeTas;
    protected Partie partie;

    /**
     * Constructeur par défaut
     */
    public DefausserCarte() {
    }

    /**
     * Constructeur
     * @param typeCarte : type de carte à défausser
     * @param nbCarte : nombre de carte à défausser
     * @param typeTas : type de tas (MAIN ou JEU) depuis lequel se défausser
     */
    public DefausserCarte(ArrayList<Class> typeCarte, int nbCarte, int typeTas) {
        if(typeCarte != null){
            this.typeCarte = (ArrayList<Class>) typeCarte.clone();
        }
        else{
            this.typeCarte = null;
        }
        this.nbCarte = nbCarte;
        this.typeTas = typeTas;
    }


    /**
     * Action de défausse des cartes
     * @param joueurImpacte : le joueur devant se défausser
     * @return out : texte résumant l'action
     */
    // TODO : Description méthode + PROTECTION NULL
    @Override
    public String action(Joueur joueurEmetteur,
            ArrayList<Joueur> joueurDestinataire, Partie partie) {

        String out = "";
        Carte c = null;
        CartesJoueur tas;
        int valeur;

        getJoueursTemporaire(joueurEmetteur, joueurDestinataire, partie);

        // TODO : Demander au joueur (joueur selon paramètre) la carte qu'il veut défausser 
        for (Joueur joueurImpacte : joueurDestinataireTemp) {
            out += "Une action de défausse de carte est en cours : \n";
            out += "On défausse " + this.nbCarte + " de type " + nomTypeCarte(this.typeCarte) + " dans le tas " + nomTypeTas(this.typeTas) + "\n";

            // Si on demande a choisir, c'est le dé qui le fait à notre place
            if (this.typeTas == Constante.TAS_CHOISIR) {  // Si c'est au joueur de choisir (main ou jeu)
                // TODO : donner la possibilité au joueur de choisir depuis quel tas se défausser
                partie.sendMessageToAll("On jette le dé pour savoir quel tas va se faire défausser !");
                valeur = Constante.nbAleatoire(1, 2 + 1); // On choisit aléatoirement pour lui
                partie.sendMessageToAll("Le dé a parlé : " + valeur);
                if (valeur == 1) {
                    partie.sendMessageToAll("On défausse " + this.nbCarte + " carte de la main");
                    this.typeTas = Constante.MAIN;
                } else if (valeur == 2) {
                    partie.sendMessageToAll("On défausse " + this.nbCarte + " carte du jeu");
                    this.typeTas = Constante.JEU;
                } else {
                    throw new UnsupportedOperationException("Erreur lors du choix du tas");
                }
            }

            // Si le nombre de cartes a défausser est choisi par le dé
            if (this.nbCarte == Constante.NB_PAR_DE) {            // Le nb de carte est défini par dé
                partie.sendMessageToAll("On jette le dé pour savoir le nombre de cartes dont on va etre defausser !");
                this.nbCarte = Constante.nbAleatoire(1, 6 + 1);
                partie.sendMessageToAll("Le dé a parlé : " + nbCarte);
            } else if (this.nbCarte == Constante.NB_TOUT) {        // Toutes les cartes du tas
                if (this.typeTas == Constante.MAIN) {
                    this.nbCarte = joueurImpacte.getMain().getCartes().size();
                } else if (this.typeTas == Constante.JEU) {
                    this.nbCarte = joueurImpacte.getJeu().getCartes().size();
                } else if (this.typeTas == Constante.TYPE_TAS_TOUT) {
                    this.nbCarte = joueurImpacte.getMain().getCartes().size();
                    this.nbCarte += joueurImpacte.getJeu().getCartes().size();
                }
            }

            if (this.nbCarte == Constante.NB_JOUEUR_MOINS_MOI) {
                partie.sendMessageToAllButCurrent(partie.getEnCours() + " va defausser le nb joueur - lui");
                partie.sendMessageToCurrent("Tu vas defausser le nb joueur - toi");
                this.nbCarte = partie.size() - 1;
            }

            // Récupére le tas de carte souhaité
            if (this.typeTas == Constante.MAIN) {
                tas = joueurImpacte.getMain();
            } else if (this.typeTas == Constante.JEU) {
                tas = joueurImpacte.getJeu();
            } else if (this.typeTas == Constante.TYPE_TAS_TOUT) {
                System.out.println("Aie, on supprime tout...");
                partie.defausserTout(joueurImpacte, joueurImpacte.getMain());
                partie.defausserTout(joueurImpacte, joueurImpacte.getJeu());
                return out;
            } else {
                throw new UnsupportedOperationException("Problème lors du choix du tas de défausse");
            }

            // Supprime le nombre de cartes souhaité du tas
            for (int i = 0; i < this.nbCarte; i++) {
                if ((c = tas.getRandomCarte(this.typeCarte)) != null) {
                    partie.defausserCarte(joueurImpacte, tas, c);
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

    private String nomTypeTas(int typeTas) {
        if (typeTas == Constante.MAIN) {
            return "main";
        } else if (typeTas == Constante.JEU) {
            return "jeu";
        } else {
            return "inconnu";
        }
    }

    private String nomTypeCarte(ArrayList<Class> typeCarte) {
        return "";
    }
    
    /**
     * On peut toujours poser une defausse de carte
     * @param partie
     * @return 
     */
    @Override
    public boolean isPosable(Partie partie, Joueur joueur) {
        return true;
    }

    @Override
    public boolean isIntervenable(int phaseTourEnCours) {
        return true;
    }
}
