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
        
     // Création d'une nouvelle ArrayList pour éviter de modifier les paramètres...possible de les passer en final
        ArrayList<Joueur> joueurDestinataireTemp = (ArrayList<Joueur>) joueurDestinataire.clone();

        // On demande ici la liste des joueurs destinataires au joueur émetteur si choix est a true
        if(choixJoueur)
        {
        	// Si on avait spécifié null, on doit créer l'arraylist
        	if(joueurDestinataireTemp == null)
        	{
        		joueurDestinataireTemp = new ArrayList<Joueur>();
        	}

        	// On renvoi les joueurs destinataires par une demande au joueur initiateur
        	joueurDestinataireTemp.add(demandeChoixJoueur(partie, joueurEmetteur));
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
}
