/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

import joueur.Classe;
import joueur.Elfe;
import joueur.Guerrier;
import joueur.Halfelin;
import joueur.Humain;
import joueur.Magicien;
import joueur.Nain;
import joueur.Pretre;
import joueur.Race;
import joueur.Voleur;

import java.util.Random;

/**
 *
 * @author Julien
 */
public class Constante {
    
    /**
     * Type de pioche
     */
    public static final int NB_PAR_DE           = -1;
    public static final int NB_TOUT             = -2;
    public static final int NB_TOUT_CONCERNE    = -3;
    
    public static final int TRESOR = 1;
    public static final int DONJON = 2;
    
    /**
     * Type de sexe
     */
    public static final int SEXE_M = 1;
    public static final int SEXE_F = 2;
    
    /**
     * Races
     */
    public static final Race Race_Humaine = new Humain();
    /*public static final Race Race_Elfe = new Elfe();
    public static final Race Race_Nain = new Nain();
    public static final Race Race_Halfelin = new Halfelin();
    */
    
    /**
     * Classes
     */
    /*public static final Classe Classe_Pretre = new Pretre();
    public static final Classe Classe_Voleur = new Voleur();
    public static final Classe Classe_Guerrier = new Guerrier();
    public static final Classe Classe_Magicien = new Magicien();
    */
    public static final int CARTE_OBJET     = 1;
    public static final int CARTE_SORT      = 2;
    public static final int CARTE_RACE      = 3;
    public static final int CARTE_CLASSE    = 4;
    
    public static final int MAIN        = 1;
    public static final int JEU         = 2;
    public static final int TAS_CHOISIR = 3;
    
    /**
     * Génère un nombre aléatoire entre min inclus et max exlus
     */
    public static int nbAleatoire(int min, int max){
        Random r = new Random();
        int valeur = min + r.nextInt(max - min);
        return valeur;
    }
    
}
