package action;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Constante;
import partie.Partie;

/**
 * Classe permettant de définir l'action d'un changement de sexe
 * @author Simon Grabit
 */
public class ChangerSexe extends Action {

    protected Partie partie;
	
    /**
     * Constructeur par défaut
     */
    public ChangerSexe() {

    }
    
    
    /**
     * Action permettant le changement de sexe
     * @param joueurImpacte : le joueur qui subit le changement de sexe
     * @return out : texte résumant l'action
     */    
    // TODO : Description méthode + PROTECTION NULL
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {

        String out = "";
        int sexe;
        
        getJoueursTemporaire(joueurEmetteur, joueurDestinataire, partie);

        if(joueurDestinataireTemp != null){
            for(Joueur joueurImpacte : joueurDestinataire){
                out += joueurImpacte.getName() + " se transforme en";
                sexe=joueurImpacte.getPersonnage().getSexe();
                if(sexe==Constante.SEXE_M){
                        joueurImpacte.getPersonnage().setSexe(Constante.SEXE_F);
                        out += " femme!";
                }
                else{
                        joueurImpacte.getPersonnage().setSexe(Constante.SEXE_M);
                        out += " homme!";
                }

                joueurImpacte.getPersonnage().setaChangeSexe(true);
            }
        }
        else{
            out += "Aucun joueurDestinataire spécifié";
        }
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
     * On peut toujours changer de sexe
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