package action;

import java.util.ArrayList;
import joueur.Classe;
import joueur.Joueur;
import partie.Constante;

/**
 * Classe permettant de définir l'action d'un changement de niveau
 * @author Rémi Marenco
 */
public class ChangerNiveau extends Action {

    int niveau;                     // Delta de changement de niveau
    int niveauMin;                  // Niveau minimum
    ArrayList<Classe> tabClasse;    // Tableau de classe

    /**
     * Constructeur de l'action ChangerNiveau
     * Niveau positif : gain de niveaux
     * Niveau négatif : perte de niveaux
     * @param niveau : le delta de changement de niveau
     */
    public ChangerNiveau(int niveau){
        this.niveau    = niveau;
        this.niveauMin = 0;
        this.tabClasse = null;
    }
    
    /**
     * Constructeur de l'action ChangerNiveau
     * Niveau positif : gain de niveaux
     * Niveau négatif : perte de niveaux
     * @param niveau : le delta de changement de niveau
     * @param tabClasse : tableau des classe impactée par le changement
     */
    public ChangerNiveau(int niveau, ArrayList<Classe> tabClasse){
        this.niveau    = niveau;
        this.tabClasse = tabClasse;
        this.niveauMin = 0;
    }
    
    /**
     * Constructeur de l'action ChangerNiveau
     * Niveau positif : gain de niveaux
     * Niveau négatif : perte de niveaux
     * @param niveau : le delta de changement de niveau
     * @param niveauMin : niveau minimum
     */
    public ChangerNiveau(int niveau, int niveauMin){
        this.niveau    = niveau;
        this.niveauMin = niveauMin;
        this.tabClasse = null;
    }
    
    /**
     * Action de ChangerNiveau
     * Change le niveau selon la variable niveau sur le joueurImpacte
     * @param joueurImpacte : le joueur qui subit le changement de niveau
     */
    @Override
    public String action(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours) {
        
        String out           = "";
        boolean classeTrouve = true;
        
        // Si le joueur est en dessous ou pile au niveau min & qu'on veut lui enlever des niveaux
        if(niveauMin >= joueurImpacte.getPersonnage().getNiveau() && this.niveau < 0){
            return joueurImpacte.getName() + "ne peut pas perdre encore de niveau\n";
        }
        
        if(tabClasse != null){
            classeTrouve = false;
            for(Classe classe: tabClasse)
                if(joueurImpacte.getPersonnage().getClasse().equals(classe))
                    classeTrouve=true;
        }
        
        if(!classeTrouve)
            return out;
        
        // Si le nombre de niveau doit se choisir par dé...
        if(this.niveau == Constante.NB_PAR_DE)  
            this.niveau = Constante.nbAleatoire(1, 6+1);

        out += joueurImpacte.getName();
        if(niveau < 0)
            out += " perds ";
        else if(niveau > 0)
            out += " gagne ";
        // Si le niveau est de 0
        else
            return out + "ne gagne aucun niveau";

        joueurImpacte.getPersonnage().changerNiveau(niveau);
        if(niveau > 1 || niveau < -1)
            out += Math.abs(niveau)+" niveaux !!\n";
        else
            out += Math.abs(niveau)+" niveau !!\n";
        out += joueurImpacte.getName() + " est maintenant niveau " + joueurImpacte.getPersonnage().getNiveau() + "\n";
        
        return out;
    }
}