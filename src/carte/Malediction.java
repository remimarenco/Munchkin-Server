package carte;
import java.util.ArrayList;

import joueur.Joueur;
import partie.Partie;
import comportement.Sortilege;

/**
 * Classe Malediction.
 * Hérite de la classe Donjon car piochée dans une pioche Donjon
 * @author Rémi Marenco
 */
public class Malediction extends Donjon implements ISortilege{
	/**
     * Sortilege d'une carte => valable seulement si c'est un Sort
     * Résultat du design pattern Strategy
     */
    protected Sortilege sortilege;
    
    /**
     * Constructeur de la classe Malediction
     * @param nom
     * @param description
     * @param sortilege
     */
    public Malediction(int id,String nom, String description, Sortilege sortilege) {
        super(id,nom, description);
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
    @Override
    public String appliquerSortilege(Joueur joueurEmetteur, ArrayList<Joueur> joueurDestinataire, Partie partie){
        if(this.sortilege != null)
            return this.sortilege.action(joueurEmetteur, joueurDestinataire, partie);
        else
            return "Cette carte n'a pas de malus\n";
    }

    @Override
    public String appliquerSortilege(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie,
                    boolean choixJoueur) {
        if(this.sortilege != null)
            return this.sortilege.action(joueurEmetteur, joueurDestinataire, partie, choixJoueur);
        else
            return "Cette carte n'a pas de malus\n";
    }
}
