/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import carte.Carte;
import joueur.Joueur;
import partie.Constante;

/**
 * Classe permettant de defausser un ou plusieurs cartes d'un type spécifique dans un tas spécifique
 * @author washi
 */
public class DefausserCarte extends Action{

    private int typeCarte;
    private int nbCarte;
    private int typeTas;
    
    public DefausserCarte() {
    }

    public DefausserCarte(int typeCarte, int nbCarte, int typeTas) {
        this.typeCarte = typeCarte;
        this.nbCarte = nbCarte;
        this.typeTas = typeTas;
    }

    @Override
    public String action(Joueur joueurImpacte) {
        String out = "";
        out += "Une action de défausse de carte est en cours : \n";
        out += "On défausse " + this.nbCarte + " de type " + this.typeCarte + " dans le tas " + this.typeTas + "\n";
        Carte c = null;
        
        if(this.typeTas == Constante.TAS_CHOISIR){
            int valeur = Constante.nbAleatoire(1, 2+1);
            if(valeur == 1)
                this.typeTas = Constante.MAIN;
            else if(valeur == 2)
                this.typeTas = Constante.JEU;
            else
                throw new UnsupportedOperationException("Erreur lors du choix du tas");
        }
        
        if(this.nbCarte == Constante.NB_PAR_DE){
            this.nbCarte = Constante.nbAleatoire(1, 6+1);
        }else if(this.nbCarte == Constante.NB_TOUT){
            if(this.typeTas == Constante.MAIN)
                this.nbCarte = joueurImpacte.getMain().getCartes().size();
            else if(this.typeTas == Constante.JEU)
                this.nbCarte = joueurImpacte.getJeu().getCartes().size();
        }
        
        if(this.typeTas == Constante.MAIN){
            for(int i=0; i<this.nbCarte; i++){
                c = joueurImpacte.getMain().getRandomCarte();
                if(c != null) joueurImpacte.getMain().supprimerCarte(c);
            }
        }else if(this.typeTas == Constante.JEU){
            for(int i=0; i<this.nbCarte; i++){
                c = joueurImpacte.getJeu().getRandomCarte();
                if(c != null) joueurImpacte.getJeu().supprimerCarte(c);
            }
        }else{
            throw new UnsupportedOperationException("Problème lors du choix du tas de défausse");
        }

        return out;
    }
    
    
}
