package jeu;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe principale
 * @author Guillaume Renoult
 */
public class Main {
    
    /**
     * Méthode lançant l'interface graphique du serveur
     * @param args : paramètre passés à l'application lors de son appel
     */
    public static void main(String[] args) {
        ServeurVue serV = new ServeurVue();
        serV.setVisible(true);
        while(serV.isVisible()){try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
}
        System.exit(0);
    }
}