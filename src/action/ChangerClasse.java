package action;

import joueur.Classe;
import joueur.Joueur;

/**
 * Classe permettant de gérer la modification de la classe
 * @author marenco-r
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

    @Override
    /**
     * Change la classe d'un joueur
     * @param joueurImpacte : le joueur dont on veut changer la classe
     * @return out : texte résumant l'action
     */
    public String action(Joueur joueurImpacte) {
        String out = joueurImpacte.getName() + " passe de la classe " + joueurImpacte.getPersonnage().getClasse();
        joueurImpacte.getPersonnage().setClasse(this.classe);
        out += " à  la classe " + joueurImpacte.getPersonnage().getClasse();
        return out;
    }
}