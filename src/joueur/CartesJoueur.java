/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package joueur;

import carte.Carte;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import partie.Constante;

/**
 *
 * @author washi
 */
public class CartesJoueur {
    
    protected ArrayList<Carte> cartes;
    protected Joueur joueur;

    
    /**
     * Constructeur
     * @param cartes
     * @param joueur 
     */
    public CartesJoueur(ArrayList<Carte> cartes, Joueur joueur) {
        this.cartes = cartes;
        this.joueur = joueur;
    }

    
    /**
     * Constructeur
     */
    public CartesJoueur() {
        cartes = new ArrayList<Carte>();
    }
    
    
    /**
     * Ajout d'une carte dans le tas
     * @param c : carte à ajouter
     * @return boolean : indique si l'ajout s'est bien passé
     */
    public boolean ajouterCarte(Carte c){
        cartes.add(c);
        return true;
    }
    
    
    /**
     * Supprime une carte du tas
     * @param c : carte a supprime
     * @return boolean : indique si la suppression s'est bien passé
     */
    public boolean supprimerCarte(Carte c){
        cartes.remove(c);
        return true;
    }
    
    
    /**
     * Retourne une carte aléatoire du tas
     * @return Carte : une carte du tas
     */
    public Carte getRandomCarte(){
        int valeur = Constante.nbAleatoire(0, cartes.size());
        // Si le joueur n'a plus de carte en main
        if(cartes.isEmpty())
            return null;
        return cartes.get(valeur);
    }    

    
    /**
     * Renvoi l'ensemble des cartes du tas
     * @return ArrayList<Carte> : les cartes du tas
     */
    public ArrayList<Carte> getCartes() {
        return cartes;
    }
    
    
    /**
     * Prépare les données pour envoi au client
     * @return HashMap<String,String> infos sur les cartes 
     */
    public HashMap<String,String> generateInfos(){
        HashMap<String,String> map = new HashMap<String, String>();
        for(Carte c : this.cartes){
            map.put(c.getId().toString(), c.getId().toString());           
        }
        return map;
    }
    
    
    /**
     * 
     * @return 
     */
    // TODO : A supprimer
    public HashMap<String,String> generateFalseInfos(){
        int i=1;
        HashMap<String,String> map=new HashMap<String, String>();
        for(Carte c : this.cartes){
            if(i%2 ==1)
                map.put(c.getId().toString(), c.getId().toString());  
            i++;
        }
        return map;
    }
}
