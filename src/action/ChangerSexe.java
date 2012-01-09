package action;

import joueur.Joueur;
import partie.Constante;

/**
 * Classe permettant de définir l'action d'un changement de sexe
 * @author Rémi Marenco
 */
public class ChangerSexe extends Action {

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
    @Override
    public String action(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours) {
        String out = "";
        int sexe;
        
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
        return out;
    }
}
