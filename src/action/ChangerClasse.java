package action;

import java.util.ArrayList;

import partie.Combat;
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
    // TODO : Description méthode + PROTECTION NULL
	@Override
	public String action(Joueur joueurDestinateur,
			ArrayList<Joueur> joueurDestinataire, Combat combatCible,
			int phaseTour, Joueur joueurTourEnCours) {
		String out = "";
		for(Joueur joueurImpacte : joueurDestinataire){
			out = joueurImpacte.getName() + " passe de la classe " + joueurImpacte.getPersonnage().getClasse();
	        joueurImpacte.getPersonnage().setClasse(this.classe);
	        out += " à  la classe " + joueurImpacte.getPersonnage().getClasse();
		}
        return out;
	}
}