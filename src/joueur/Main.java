package joueur;

import carte.Carte;

/**
 * Main d'un joueur
 * @author Julien Rouvier
 */
public class Main extends CartesJoueur{
    
    /**
     * Ajout d'une carte dans la main
     * @param c
     * @return 
     */
    // TODO : Vérifier la pertinence de cette méthode
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
     * Vérifier que le nombre max d'équipement n'a pas été atteint
     * @return 
     */
    // TODO : Vérifier la pertinence de cette méthode
    public boolean atteintMaxEquipement(){
       for(int i = 0; i<this.cartes.size(); i++){

       }
       return false;
    }
}
