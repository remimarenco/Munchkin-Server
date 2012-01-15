package action;

import java.util.ArrayList;

import partie.Combat;
import partie.Partie;
import joueur.Classe;
import joueur.Joueur;


/**
 * Classe permettant de gérer la modification de la classe
 * @author Rémi Marenco
 */
public class ChangerClasse extends Action{
    
    protected Classe classe;
    protected boolean choixJoueur;
    protected Partie partie;
    
    
    /**
     * Constructeur par défaut
     * @param classe : Nouvelle classe
     */
    public ChangerClasse(Classe classe) {
        this.classe = classe;
    }
    
    /**
     * Constructeur de la classe ChangerClasse, permet de modifier la classe d'un personnage choisit
     * @param classe
     * @param choixJoueur
     * @param partie => Pour contacter le joueur afin qu'il puisse donner le joueur choisit
     */
    public ChangerClasse(Classe classe, boolean choixJoueur, Partie partie) {
        this.classe = classe;
        this.choixJoueur = choixJoueur;
        this.partie = partie;
    }

    
    /**
     * Change la classe d'un joueur
     * @param joueurImpacte : le joueur dont on veut changer la classe
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Combat combatCible,
                    int phaseTour, Joueur joueurTourEnCours) {
        String out = "";
        
        if(choixJoueur)
        {
        	// TODO : Faire suite du traitement
        	joueurDestinataire.add(demandeChoixJoueur(partie, joueurEmetteur));
        }
        
        if(joueurDestinataire != null){
            for(Joueur joueurImpacte : joueurDestinataire){             // Parcourt l'ensemble des joueurs cible
                out = joueurImpacte.getName() + " passe de la classe " + joueurImpacte.getPersonnage().getClasse();
                joueurImpacte.getPersonnage().setClasse(this.classe);   // Change la classe du personnage
                out += " à  la classe " + joueurImpacte.getPersonnage().getClasse();
            }
        }
        return out;
    }
}