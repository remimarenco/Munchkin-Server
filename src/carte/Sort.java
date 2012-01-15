package carte;

import java.util.ArrayList;

import joueur.Joueur;
import partie.Combat;
import comportement.Sortilege;

/**
 * Classe Sort piochée dans la pioche Tresor
 * @author Remi Marenco
 *
 */
public class Sort extends Tresor implements ISortilege{
	
	/**
     * Sortilege d'une carte => valable seulement si c'est un Sort
     * Résultat du design pattern Strategy
     */
    protected Sortilege sortilege;

	public Sort(int id, String nom, String description, Sortilege sortilege) {
		super(id, nom, description);
        this.sortilege = sortilege;
	}
	
	/**
     * Permet de lancer le comportement Sortilege d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * TODO : Faire un hashmap de cibles/destinations => Personnages + Monstres
     * ex   : Joueur A lance le sortilège qui s'applique à tous les personnages sauf lui
     * ex 2 : Joueur B lance le sortilège qui s'applique à tous les monstres en jeu
     * @param joueurImpacte
     */
    public String appliquerSortilege(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours){
        if(this.sortilege != null)
            return this.sortilege.actionSortilege(joueurEmetteur, joueurDestinataire, combatCible, phaseTour, joueurTourEnCours);
        else
            return "Cette carte n'a pas de malus\n";
    }

}
