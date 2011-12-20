package comportement;

import joueur.Joueur;
import partie.Constante;

/**
 * Classe permettant de définir l'action d'un changement de niveau
 * @author Rémi Marenco
 */
public class ChangerNiveau extends Action {

    /**
     * Entier permettant de connaître le changement de niveau
     */
    int niveau;

    /**
     * Constructeur de l'action ChangerNiveau
     * Un niveau positif permet de gagner des niveaux
     * Un niveau négatif permet de perdre des niveaux
     * @param niveau
     */
    public ChangerNiveau(int niveau)
    {
            this.niveau = niveau;
    }

    /**
     * Action de ChangerNiveau
     * Change le niveau selon la variable niveau sur le joueurImpacte
     * @param joueurImpacte
     */
    @Override
    public String action(Joueur joueurImpacte) {
            /**
             * On affiche des informations différentes selon le gain ou la perte de niveau
             */
             String out = "";

             if(this.niveau == Constante.NB_PAR_DE){
                 this.niveau = Constante.nbAleatoire(1, 6+1);
             }
        
            out += joueurImpacte.getName();
            if(niveau < 0)
                    out += " perds ";
            else if(niveau > 0)
                    out += " gagne ";
            joueurImpacte.getPersonnage().changerNiveau(niveau);
            if(niveau > 1 || niveau < -1)
            {
            	out += niveau+" niveaux !!\n";
            }
            else
            {
            	out += niveau+" niveau !!\n";
            }
            out += joueurImpacte.getName() + " est maintenant niveau " + joueurImpacte.getPersonnage().getNiveau() + "\n";
            return out;
    }

}
