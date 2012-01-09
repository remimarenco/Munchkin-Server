package action;

import joueur.Classe;
import joueur.Joueur;

/**
 * Classe permettant de gérer la modification de la classe
 * @author Rémi Marenco
 */
public class ChangerClasse extends Action{
    
    protected Classe classe;
    
    
    /**
     * Constructeur par défaut
     * @param classe : Nouvelle classe
     */
    public ChangerClasse(Classe classe) {
        this.classe = classe;
    }

    
    /**
     * Change la classe d'un joueur
     * @param joueurImpacte : le joueur dont on veut changer la classe
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours) {
        String out = joueurImpacte.getName() + " passe de la classe " + joueurImpacte.getPersonnage().getClasse();
        joueurImpacte.getPersonnage().setClasse(this.classe);
        out += " à  la classe " + joueurImpacte.getPersonnage().getClasse();
        return out;
    }
}