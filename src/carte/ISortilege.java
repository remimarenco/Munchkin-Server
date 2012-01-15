package carte;

import java.util.ArrayList;

import comportement.Sortilege;

import joueur.Joueur;
import partie.Combat;

public interface ISortilege {	
	/**
     * Permet de lancer le comportement Sortilege d'une carte monstre
     * TODO : Vérifier si c'est toujours applicable => Voir au dessus
     * TODO : Faire un hashmap de cibles/destinations => Personnages + Monstres
     * ex   : Joueur A lance le sortilège qui s'applique à tous les personnages sauf lui
     * ex 2 : Joueur B lance le sortilège qui s'applique à tous les monstres en jeu
     * @param joueurImpacte
     */
    public String appliquerSortilege(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Combat combatCible, int phaseTour, Joueur joueurTourEnCours);
}
