package action;

import java.util.ArrayList;
import joueur.Classe;
import joueur.Joueur;
import partie.Constante;
import partie.Partie;

/**
 * Classe permettant de définir l'action d'un changement de niveau
 * @author Rémi Marenco
 */
public class ChangerNiveau extends Action {

    protected int niveau;                     // Delta de changement de niveau
    protected int niveauMin;                  // Niveau minimum
    protected ArrayList<Classe> tabClasse;    // Tableau de classe
    protected Partie partie;

    /**
     * Constructeur de l'action ChangerNiveau
     * Niveau positif : gain de niveaux
     * Niveau négatif : perte de niveaux
     * @param niveau : le delta de changement de niveau
     */
    public ChangerNiveau(int niveau){
        this.niveau    = niveau;
        this.niveauMin = 0;
        this.tabClasse = null;
    }
    
    /**
     * Constructeur de l'action ChangerNiveau
     * Un choix de joueur doit être fait
     * Niveau positif : gain de niveaux
     * Niveau négatif : perte de niveaux
     * @param niveau : le delta de changement de niveau
     * @param choixJoueur
     * @param partie => Permet de demander au joueur le joueur sur lequel le changement de niveau doit être fait
     */
    public ChangerNiveau(int niveau, boolean choixJoueur){
        this.niveau    = niveau;
        this.niveauMin = 0;
        this.tabClasse = null;
        this.choixJoueur = choixJoueur;
    }
    
    
    /**
     * Constructeur de l'action ChangerNiveau
     * Niveau positif : gain de niveaux
     * Niveau négatif : perte de niveaux
     * @param niveau : le delta de changement de niveau
     * @param niveauMin : niveau minimum
     */
    public ChangerNiveau(int niveau, int niveauMin){
        this.niveau    = niveau;
        this.niveauMin = niveauMin;
        this.tabClasse = null;
    }
    
    
    /**
     * Action de ChangerNiveau
     * Change le niveau selon la variable niveau sur le joueurImpacte
     * @param joueurImpacte : le joueur qui subit le changement de niveau
     */
    // TODO : Description méthode + PROTECTION NULL
    @Override
    public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		
        String out           = "";
        boolean classeTrouve = true;
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
        
        if(joueurDestinataireTemp != null){
            for(Joueur joueurImpacte : joueurDestinataireTemp){
                // Si le joueur est en dessous ou pile au niveau min & qu'on veut lui enlever des niveaux
                if(niveauMin >= joueurImpacte.getPersonnage().getNiveau() && this.niveau < 0)
                        return joueurImpacte.getName() + "ne peut pas perdre encore de niveau\n";

                if(tabClasse != null){
                    classeTrouve = false;
                    for(Classe classe: tabClasse)
                        if(joueurImpacte.getPersonnage().getClasse().equals(classe))
                            classeTrouve=true;
                }

                if(!classeTrouve) return out;

                // Si le nombre de niveau doit se choisir par dé...
                if(this.niveau == Constante.NB_PAR_DE) {
                    partie.sendMessageToAll("On lance le dé !");
                    this.niveau = Constante.nbAleatoire(1, 6+1);
                    partie.sendMessageToAll("Le dé a parlé : "+niveau);
                }

                out += joueurImpacte.getName();
                if(niveau < 0)
                    out += " perds ";
                else if(niveau > 0)
                    out += " gagne ";
                // Si le niveau est de 0
                else
                    return out + "ne gagne aucun niveau";

                joueurImpacte.getPersonnage().changerNiveau(niveau);
                if(niveau > 1 || niveau < -1)
                    out += Math.abs(niveau)+" niveaux !!\n";
                else
                    out += Math.abs(niveau)+" niveau !!\n";
                out += joueurImpacte.getName() + " est maintenant niveau " + joueurImpacte.getPersonnage().getNiveau() + "\n";
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
     * On peut toujours changer de niveau
     * @param partie
     * @return 
     */
    @Override
    public boolean isPosable(Partie partie, Joueur joueur) {
        return true;
    }
}
