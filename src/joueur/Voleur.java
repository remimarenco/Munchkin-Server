package joueur;

import partie.Constante;


/**
 * Classe Voleur pour un personnage
 * @author Julien Rouvier
 */
public class Voleur extends Classe {

    // TODO: Completer la classe
    @Override
    public void utiliserCapacite() {
        if(choixJoueur(Constante.VOLEUR_POIGNARDER, Constante.VOLEUR_VOL_A_LA_TIRE)==Constante.VOLEUR_VOL_A_LA_TIRE){
            voleaLaTire();
        }
        else{
            poignarder();
        }
    }
    
    public int choixJoueur(int capacite1, int capacite2){
        
        return 0;
    }

    public void voleaLaTire(){
    
    }
    
    public void poignarder(){
        
    }
}
