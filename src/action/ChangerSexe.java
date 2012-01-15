package action;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Combat;
import partie.Constante;
import partie.Partie;

/**
 * Classe permettant de définir l'action d'un changement de sexe
 * @author Rémi Marenco
 */
public class ChangerSexe extends Action {
	protected boolean choixJoueur;
	protected Partie partie;
	
    /**
     * Constructeur par défaut
     */
    public ChangerSexe() {

    }
    
    /**
     * Méthode permettant de définir si le changement de sexe demande un choix utilisateur
     * @param choixJoueur => True si on veut faire choisir un joueur
     * @param partie
     */
    public ChangerSexe(boolean choixJoueur, Partie partie)
    {
    	this.choixJoueur = choixJoueur;
    	this.partie = partie;
    }
    
    /**
     * Action permettant le changement de sexe
     * @param joueurImpacte : le joueur qui subit le changement de sexe
     * @return out : texte résumant l'action
     */    
    // TODO : Description méthode + PROTECTION NULL
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Combat combatCible,
			int phaseTour, Joueur joueurTourEnCours) {
		
		String out = "";
        int sexe;
        
        // On demande ici la liste des joueurs destinataires au joueur émetteur si choix est a true
        if(choixJoueur)
        {
        	// Si on avait spécifié null, on doit créer l'arraylist
        	if(joueurDestinataire == null)
        	{
        		joueurDestinataire = new ArrayList<Joueur>();
        	}
        	
        	// On renvoi les joueurs destinataires par une demande au joueur initiateur
        	joueurDestinataire.add(demandeChoixJoueur(partie, joueurEmetteur));
        }
        
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
        
        return out;
	}
}
