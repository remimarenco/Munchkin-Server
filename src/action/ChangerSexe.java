package action;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Combat;
import partie.Constante;
import partie.Partie;

/**
 * Classe permettant de définir l'action d'un changement de sexe
 * @author Simon Grabit
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
    public ChangerSexe(boolean choixJoueur)
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
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		
		String out = "";
        int sexe;
        
        ArrayList<Joueur> joueurDestinataireTemp = new ArrayList<Joueur>();

        // Si on avait pas spécifié de joueurDestinataire, on demande le joueur destinataire
        if(joueurDestinataire == null || joueurDestinataire.isEmpty())
        {
        	if(choixJoueur)
        	{
        		// On renvoi les joueurs destinataires par une demande au joueur initiateur
            	joueurDestinataireTemp.add(demandeChoixJoueur(partie, joueurEmetteur));
        	}
        }
        else
        {
        	joueurDestinataireTemp = (ArrayList<Joueur>) joueurDestinataire.clone();
        }

        if(joueurDestinataireTemp != null)
        {
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
        else
        {
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
}
