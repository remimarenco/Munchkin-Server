package joueur;

import partie.Constante;


/**
 * Classe Magicien pour un personnage
 * @author Rémi Marenco
 */
public class Magicien extends Classe {

    // TODO: Completer la classe
    /**
     * Choix de la capacité à utiliser
     */
    @Override
    public void utiliserCapacite() {
        if(choixJoueur(Constante.MAGICIEN_SORT_DE_CHARME, Constante.MAGICIEN_SORT_DE_VOL)==Constante.MAGICIEN_SORT_DE_CHARME){
            sortDeCharme();
        }
        else{
            sortDeVol();
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
     * Utiliser sort de Charme
     */
    public void sortDeCharme(){
    
    }
    
    /**
     * Utiliser sort de vol
     */
    public void sortDeVol(){
        
    }

	@Override
	public String toString() {		
		return "Magicien";
	}
}
