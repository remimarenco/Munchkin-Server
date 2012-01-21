package joueur;

import carte.Carte;
import java.util.ArrayList;

/**
 * Jeu d'un joueur 
 * @author Rémi Marenco
 */
public class Jeu extends CartesJoueur{
    
    /**
     * Ajout d'une carte dans le jeu
     * @param c
     * @return 
     */
    @Override
    public boolean ajouterCarte(Carte c) {
        if(super.ajouterCarte(c))
            // TODO : Appliquer la classe, la race, etc...
            return true;
        else
            return false;
    }
    
}
