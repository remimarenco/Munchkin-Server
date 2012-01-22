package action;

import carte.Carte;
import java.util.ArrayList;

import partie.Partie;
import joueur.Joueur;
import joueur.Race;

/**
 * Classe permettant de modifier la race d'un joueur
 * @author Rémi Marenco
 */
public class ChangerRace extends Action {
    
    protected Race race;

    public Race getRace() {
        return race;
    }
    protected Partie partie;
    
    
    
    /**
     * Constructeur par défaut
     * @param race : nouvelle race
     */
    public ChangerRace(Race race) {
        this.race = race;
    }
    

    /**
     * Action permettant le changement de race
     * @param joueurImpacte : joueur qui subit le changement de race
     * @return out : texte résumant l'action 
     */
    // TODO : Description méthode + PROTECTION NULL
    @Override
    public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		
        String out = "";
		
        // Si on avait pas spécifié de joueurDestinataire, on demande le joueur destinataire
        if(joueurDestinataire == null || joueurDestinataire.isEmpty()){
            if(choixJoueur)
                // On renvoi les joueurs destinataires par une demande au joueur initiateur
            	joueurDestinataireTemp.add(demandeChoixJoueur(partie, joueurEmetteur));
        }
        else{
        	joueurDestinataireTemp = (ArrayList<Joueur>) joueurDestinataire.clone();
        }
		
        for(Joueur joueurImpacte : joueurDestinataireTemp){
            Carte carteTrouve = null;
            out = joueurImpacte.getName() + " passe de la race " + joueurImpacte.getPersonnage().getRace();
            // On parcourt les cartes du jeu du joueur
            for(Carte carte : joueurImpacte.getJeu().getCartes())
            {
                carte.Race carteRace;
                // Si la carte est une carte de race, on la défausse
                if(carte instanceof carte.Race)
                {
                    carteRace = (carte.Race) carte;
                    if(!carteRace.getRace().equals(this.race))
                    {
                        carteTrouve = carteRace;
                    }
                }
            }
            partie.defausserCarte(joueurImpacte, joueurImpacte.getJeu(), carteTrouve);
            joueurImpacte.getPersonnage().setRace(this.race);
            out += " à la race " + joueurImpacte.getPersonnage().getRace();
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
