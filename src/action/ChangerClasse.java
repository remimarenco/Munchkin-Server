package action;

import java.util.ArrayList;

import partie.Combat;
import joueur.Classe;
import joueur.Joueur;


/**
 * Classe permettant de gérer la modification de la classe
 * @author Rémi Marenco
 */
public class ChangerClasse extends Action{
    
    protected Classe classe;
    
    
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
                    ArrayList<Joueur> joueurDestinataire, Combat combatCible,
                    int phaseTour, Joueur joueurTourEnCours) {
        String out = "";
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