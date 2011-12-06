package comportement;

import joueur.Personnage;

public class PiocherCarte extends Action {
    
    int nbCarte;

    public PiocherCarte(int nbCarte){
        this.nbCarte = nbCarte;
    }

    @Override
    public void action() {
            // TODO Auto-generated method stub
            System.out.println("pioche "+nbCarte+" carte");
    }
}
