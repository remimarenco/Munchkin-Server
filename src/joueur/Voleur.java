package joueur;

import partie.Constante;


/**
 * Classe Voleur pour un personnage
 * @author Rémi Marenco
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
    
    /**
     * Demande au joueur de la capacité à utiliser
     * @param capacite1
     * @param capacite2
     * @return 
     */
    public int choixJoueur(int capacite1, int capacite2){
        
        return 0;
    }

    /**
     * Utiliser vol à la tire
     */
    public void voleaLaTire(){
    
    }
    /**
     * tiliser poignarder
     */
    public void poignarder(){
        
    }

	@Override
	public String toString() {		
		return "Voleur";
	}
}
