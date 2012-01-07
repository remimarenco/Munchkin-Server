package action;

import java.util.ArrayList;
import joueur.Joueur;
import partie.Constante;

/**
 * Classe permettant de définir l'action d'un changement de niveau
 * @author Rémi Marenco
 */
public class ChangerSexe extends Action {

    public ChangerSexe()
    {

    }
    
    public String action(Joueur joueurImpacte) {
        /**
         * On affiche des informations différentes selon le gain ou la perte de niveau
         */
         String out = "";
         int sexe;
         out += joueurImpacte.getName();
         sexe=joueurImpacte.getPersonnage().getSexe();
         if(sexe==Constante.SEXE_M)
         {
             joueurImpacte.getPersonnage().setSexe(Constante.SEXE_F);
             out += " se transforme en femme!";
         }
         else
         {
             joueurImpacte.getPersonnage().setSexe(Constante.SEXE_M);
             out += " se transforme en homme!";
         }
         joueurImpacte.getPersonnage().setaChangeSexe(true);

         return out;
    }

}
