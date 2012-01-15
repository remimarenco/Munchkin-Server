package partie;

import carte.Donjon;
import carte.Tresor;
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
 * Ensemble de constante utilisées dans l'ensemble du code.
 * Contient également des méthodes pouvant être utilisées dans différentes classes
 * @author Julien Rouvier
 */
public class Constante {	
    /**
     * Type de pioche
     */
    public static final int NB_PAR_DE        = -50;
    public static final int NB_TOUT          = -51;
    public static final int NB_TOUT_CONCERNE = -52;
    
    public static final Class TRESOR = Tresor.class;
    public static final Class DONJON = Donjon.class;
    
    /**
     * Type de sexe
     */
    public static final int SEXE_M = 1;
    public static final int SEXE_F = 2;
    
    /**
     * Races
     */
    public static final Race RACE_HUMAINE  = new Humain();
    public static final Race RACE_ELFE     = new Elfe();
    public static final Race RACE_NAIN     = new Nain();
    public static final Race RACE_HALFELIN = new Halfelin();
    
    /**
     * Classes
     */
    public static final Classe CLASSE_PRETRE   = new Pretre();
    public static final Classe CLASSE_VOLEUR   = new Voleur();
    public static final Classe CLASSE_GUERRIER = new Guerrier();
    public static final Classe CLASSE_MAGICIEN = new Magicien();
    public static final Classe CLASSE_AUCUNE   = null;
    
    public static final int CARTE_OBJET  = 1;
    public static final int CARTE_SORT   = 2;
    public static final int CARTE_RACE   = 3;
    public static final int CARTE_CLASSE = 4;
    
    public static final int MAIN        = 1;
    public static final int JEU         = 2;
    public static final int TAS_CHOISIR = 3;
    
    /**
     * TYPE D'ACTION
     */
    
    public static final int ACTION_POSERCARTE 				  = 1;    
    public static final int ACTION_RACE       				  = 3;
    public static final int ACTION_INTERVENIR 				  = 4;
    public static final int ACTION_DEFAUSSER  				  = 5;
    public static final int ACTION_DESEQUIPER 				  = 6;
    public static final int ACTION_CARTE_INTERVENTION_CHOISIE             = 7;
    public static final int ACTION_PRET                                   = 8;
    
     /**
     * Constante Son
     */
    
    public static final ConstanteSon SOUND_DEGUERPIR = new ConstanteSon(100,105);
    public static final int SOUND_DEGUERPIR100          = 100;
    public static final int SOUND_DEGUERPIR101          = 101;
    public static final int SOUND_DEGUERPIR102          = 102;
    public static final int SOUND_DEGUERPIR103          = 103;
    public static final int SOUND_DEGUERPIR104          = 104;
    public static final int SOUND_DEGUERPIR105          = 105;
    
    public static final ConstanteSon SOUND_MONSTRE = new ConstanteSon(200,203);
    public static final int SOUND_MONSTRE200            = 200;
    public static final int SOUND_MONSTRE201            = 201;
    public static final int SOUND_MONSTRE202            = 202;
    public static final int SOUND_MONSTRE203            = 203;

    public static final ConstanteSon SOUND_INCIDENTFACHEUX = new ConstanteSon(300,305);
    public static final int SOUND_INCIDENTFACHEUX000    = 300;
    public static final int SOUND_INCIDENTFACHEUX001    = 301;
    public static final int SOUND_INCIDENTFACHEUX002    = 302;
    public static final int SOUND_INCIDENTFACHEUX003    = 303;
    public static final int SOUND_INCIDENTFACHEUX004    = 304;
    public static final int SOUND_INCIDENTFACHEUX005    = 305;

    public static final ConstanteSon SOUND_SORT = new ConstanteSon(400,401);
    public static final int SOUND_SORT400               = 400;
    public static final int SOUND_SORT401               = 401;
    
    public static final ConstanteSon SOUND_VICTOIRE = new ConstanteSon(500,505);
    public static final int SOUND_VICTOIRE500           = 500;
    public static final int SOUND_VICTOIRE501           = 501;
    public static final int SOUND_VICTOIRE502           = 502;
    public static final int SOUND_VICTOIRE503           = 503;
    public static final int SOUND_VICTOIRE504           = 504;
    public static final int SOUND_VICTOIRE505           = 505;
    
    public static final int SOUND_RIRES           = 999;
    
    /**
     * Constante Phase d'un tour
     */
    public static final int PHASE_OUVRIR_PORTE = 0;
    public static final int PHASE_CHERCHER_LA_BAGARRE = 1;
    public static final int PHASE_PILLER_LA_PIECE = 2;
    public static final int PHASE_CHARITE_SIOUPLAIT = 3;

    /**
     * Constantes de gestion des joueurs impliqués
     */
    public static final int TOUS_JOUEURS = 0;
    public static final int AUCUN_JOUEUR = 0;
    
    /**
     * Constantes de sorts de classe
     */
    public static final int MAGICIEN_SORT_DE_VOL = 0;
    public static final int MAGICIEN_SORT_DE_CHARME = 1;
    public static final int PRETRE_RESURRECTION = 0;
    public static final int PRETRE_RENVOIE = 1;
    public static final int VOLEUR_POIGNARDER = 0;
    public static final int VOLEUR_VOL_A_LA_TIRE = 1;
    
    /**
     * Génère un nombre aléatoire entre min inclus et max exlus
     */
    public static int nbAleatoire(int min, int max){
    	int valeur;
    	Random r = new Random();
        if(max != min)
            valeur = min + r.nextInt(Math.abs(max - min));
        else
            valeur = 0;
        return valeur;
    }
    
    /**
     * Génère un nombre aléatoire entre min inclus et max exlus
     */
    public static int jouerSon(ConstanteSon son){
    	return nbAleatoire(son.debutSon, son.finSon + 1);
    }

    private static class ConstanteSon {
        int debutSon;
        int finSon;
        
        public ConstanteSon(int debutSon, int finSon) {
            this.debutSon = debutSon;
            this.finSon = finSon;
        }
    }
}
