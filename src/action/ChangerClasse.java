package action;

import carte.Carte;
import java.util.ArrayList;

import partie.Partie;
import joueur.Classe;
import joueur.Joueur;
import partie.Constante;


/**
 * Classe permettant de gérer la modification de la classe
 * @author Rémi Marenco
 */
public class ChangerClasse extends Action{
    
    protected Classe classe;

    public Classe getClasse() {
        return classe;
    }
    protected Partie partie;
    
    
    /**
     * Constructeur par défaut
     * @param classe : Nouvelle classe
     */
    public ChangerClasse(Classe classe) {
        this.classe = classe;
    }
    
    /**
     * Change la classe d'un joueur
     * @param joueurImpacte : le joueur dont on veut changer la classe
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {
        String out = "";
        
        getJoueursTemporaire(joueurEmetteur, joueurDestinataire, partie);
        
        if(joueurDestinataireTemp != null)
            for(Joueur joueurImpacte : joueurDestinataireTemp){             // Parcourt l'ensemble des joueurs cible
                    Carte carteTrouve = null;
                    out = joueurImpacte.getName() + " passe de la classe " + joueurImpacte.getPersonnage().getClasse();
                    // Si on passe null, il n'y a pas besoin de defauser une autre carte de classe car cela signifie que l'action provient d'une defausse
                    if(!(this.classe == Constante.CLASSE_AUCUNE))
                    {
                        // On parcourt les cartes du jeu du joueur
                        for(Carte carte : joueurImpacte.getJeu().getCartes())
                        {
                            // Si c'est la dernière carte, on a pas besoin de l'évaluer c'est la carte en cours
                            if(joueurImpacte.getJeu().getCartes().indexOf(carte) != joueurImpacte.getJeu().getCartes().size()-1)
                            {
                                carte.Classe carteClasse;
                                // Si la carte est une carte de race, on la défausse
                                if(carte instanceof carte.Classe)
                                {
                                    carteClasse = (carte.Classe) carte;
                                    if(!carteClasse.getClasse().equals(this.classe))
                                    {
                                        carteTrouve = carteClasse;
                                    }
                                }
                            }
                        }
                        if(carteTrouve != null)
                        {
                            System.out.println("On défausse la carte de classe");
                            partie.defausserCarte(joueurImpacte, joueurImpacte.getJeu(), carteTrouve);
                        }
                    }
                    joueurImpacte.getPersonnage().setClasse(this.classe);   // Change la classe du personnage
                    out += " à  la classe " + joueurImpacte.getPersonnage().getClasse();
            }
        else
            out += "Aucun joueurDestinataire spécifié";
        
        System.out.println(out);
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

    /**
     * On peut toujours changer de classe
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