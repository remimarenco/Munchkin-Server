/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;

import carte.Carte;
import java.util.ArrayList;

/**
 *
 * @author washi
 */
public class CartesJoueur {
    
    protected ArrayList<Carte> cartes;
    protected Joueur joueur;

    public CartesJoueur(ArrayList<Carte> cartes, Joueur joueur) {
        this.cartes = cartes;
        this.joueur = joueur;

    }

    public CartesJoueur() {
        cartes = new ArrayList<Carte>();
    }
    
    public boolean ajouterCarte(Carte c){
        cartes.add(c);
        return true;
    }
    
    public boolean supprimerCarte(Carte c){
        cartes.remove(c);
        return true;
    }
    
}
