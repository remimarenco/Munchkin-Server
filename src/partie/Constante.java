/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package partie;

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
    
    public static final int SEXE_M = 1;
    public static final int SEXE_F = 2;
    
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
