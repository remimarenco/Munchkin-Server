package joueur;

import partie.Constante;


/**
 * Classe Magicien pour un personnage
 * @author Rémi Marenco
 */
public class Magicien extends Classe {

    // TODO: Completer la classe
    @Override
    public void utiliserCapacite() {
        if(choixJoueur(Constante.MAGICIEN_SORT_DE_CHARME, Constante.MAGICIEN_SORT_DE_VOL)==Constante.MAGICIEN_SORT_DE_CHARME){
            sortDeCharme();
        }
        else{
            sortDeVol();
        }
    }
    
    public int choixJoueur(int capacite1, int capacite2){
        
        return 0;
    }

    public void sortDeCharme(){
    
    }
    
    public void sortDeVol(){
        
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Magicien";
	}
}
