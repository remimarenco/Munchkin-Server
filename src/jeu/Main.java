package jeu;

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
        while(serV.isVisible());
        System.exit(0);
    }
}