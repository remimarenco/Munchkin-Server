package action;

import joueur.Classe;
import joueur.Joueur;

/**
 * Classe permettant de gérer la modification de la classe
 * @author marenco-r
 */
public class ChangerClasse extends Action{
    
    /**
     * Constructeur par défaut
     * @param classe : Nouvelle classe
     */
    public ChangerClasse(Classe classe) {
        // TODO Auto-generated constructor stub
    }

    @Override
    /**
     * Change la classe d'un joueur
     * @param joueurImpacte : le joueur dont on veut changer la classe
     */
    public String action(Joueur joueurImpacte) {
        // TODO Auto-generated method stub
        return "action non implémentée";
    }
}