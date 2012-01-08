package joueur;

import carte.Carte;
import java.util.ArrayList;

/**
 * Jeu d'un joueur 
 * @author Julien Rouvier
 */
public class Jeu extends CartesJoueur{
    
    /**
     * Ajout d'une carte dans le jeu
     * @param c
     * @return 
     */
    @Override
    public boolean ajouterCarte(Carte c) {
        if(super.ajouterCarte(c)){
            // TODO : Appliquer la classe, la race, etc...
            return true;
        }else{
            return false;
        }
    }
    
    
    /**
     * Vérifie si le nombre maximum d'équipement est atteint
     * @return boolean 
     */
    // TODO : A implémenter
    public boolean atteintMaxEquipement(){
       for(int i = 0; i<this.cartes.size(); i++){
          
       } 
       return false;
    }

    /**
     * Retourne l'ensemble des cartes du jeu
     * @return 
     */
    public ArrayList<Carte> getJeu() {
        return this.cartes;
    }
}
