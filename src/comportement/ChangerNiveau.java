package comportement;

import joueur.Joueur;

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
    public void action(Joueur joueurImpacte) {
            /**
             * On affiche des informations différentes selon le gain ou la perte de niveau
             */
            System.out.print(joueurImpacte.getName());
            if(niveau < 0)
                    System.out.println(" perds ");
            else if(niveau > 0)
                    System.out.print(" gagne ");
            joueurImpacte.getPersonnage().changerNiveau(niveau);
            if(niveau > 1 || niveau < -1)
            {
            	System.out.println(niveau+" niveaux !!");
            }
            else
            {
            	System.out.println(niveau+" niveau !!");
            }
            System.out.println(joueurImpacte.getName() + " est maintenant niveau " + joueurImpacte.getPersonnage().getNiveau());
    }

}
