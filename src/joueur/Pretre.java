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
        if(choixJoueur(Constante.PRETRE_RESURRECTION, Constante.PRETRE_RENVOIE)==Constante.PRETRE_RENVOIE)
            renvoie();
        else
            resurrection();
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
     * Utiliser capacité renvoie
     */
    public void renvoie(){
    
    }
    
    /**
     * Utiliser capacité resurrection
     */
    public void resurrection(){
        
    }

    @Override
    public String toString() {		
            return "Prêtre";
    }
}
