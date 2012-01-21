package action;

import java.util.ArrayList;

import partie.Partie;
import joueur.Classe;
import joueur.Joueur;


/**
 * Classe permettant de gérer la modification de la classe
 * @author Rémi Marenco
 */
public class ChangerClasse extends Action{
    
    protected Classe classe;
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
        
        ArrayList<Joueur> joueurDestinataireTemp = new ArrayList<Joueur>();

        // Si on avait pas spécifié de joueurDestinataire, on demande le joueur destinataire
        if(joueurDestinataire == null || joueurDestinataire.isEmpty()){
            if(choixJoueur)
                // On renvoi les joueurs destinataires par une demande au joueur initiateur
                joueurDestinataireTemp.add(demandeChoixJoueur(partie, joueurEmetteur));
        }
        else{
        	joueurDestinataireTemp = (ArrayList<Joueur>) joueurDestinataire.clone();
        }
        
        if(joueurDestinataireTemp != null)
            for(Joueur joueurImpacte : joueurDestinataireTemp){             // Parcourt l'ensemble des joueurs cible
                    out = joueurImpacte.getName() + " passe de la classe " + joueurImpacte.getPersonnage().getClasse();
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