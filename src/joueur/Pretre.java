package joueur;

import partie.Constante;


/**
 * Classe Prêtre pour un personnage
 * @author Rémi Marenco
 */
public class Pretre extends Classe{

    // TODO: Completer la classe
    @Override
    public void utiliserCapacite() {
        if(choixJoueur(Constante.PRETRE_RESURRECTION, Constante.PRETRE_RENVOIE)==Constante.PRETRE_RENVOIE){
            renvoie();
        }
        else{
            resurrection();
        }
    }

    public int choixJoueur(int capacite1, int capacite2){
        
        return 0;
    }

    public void renvoie(){
    
    }
    
    public void resurrection(){
        
    }

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Prêtre";
	}
}
