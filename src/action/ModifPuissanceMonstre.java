package action;

import java.util.ArrayList;

import carte.Monstre;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

/**
 * // TODO : Commenter
 * @author Julien Rouvier
 */
public class ModifPuissanceMonstre extends Action{

    private ArrayList<Race> tabRace;
    private ArrayList<Classe> tabClasse;
    private int bonusPuissance;
    private Monstre monstre;
	
    /**
     * Constructeur
     * @param tabRace
     * @param tabClasse
     * @param bonusPuissance
     * @param monstre 
     */
    public ModifPuissanceMonstre(ArrayList<Race> tabRace,
			ArrayList<Classe> tabClasse, int bonusPuissance, Monstre monstre) {
        super();
        if(tabRace != null)
            this.tabRace = (ArrayList<Race>) tabRace.clone();
        else
            this.tabRace = null;
        	
        if(tabClasse != null)
            this.tabClasse = (ArrayList<Classe>) tabClasse.clone();
        else
            this.tabClasse = null;
		
        this.bonusPuissance = bonusPuissance;
        this.monstre = monstre;
    }

    /**
     * Action modifiant la puissance d'un monstre
     * @param joueurImpacte : joueur contre lequel se bat le monstre
     * @return out : texte résumant l'action
     */
    @Override
    public String action(Joueur joueurImpacte, java.lang.StackTraceElement[] nomPhase, Joueur joueurEnCours) {
        
        String out           = "";
        boolean accept       = true;
        boolean raceTrouve   = false;
        boolean classeTrouve = false;
		
        out += "On passe dans une action de modification de puissance de monstre :\n";
        out += "Le monstre impliqué est "+monstre.getNom();
        out += ", le bonus puissance attribué est de " + this.bonusPuissance;
    
        if(tabRace != null && !(tabRace.isEmpty())){
            out += ", les races contre lesquelles ce bonus s'applique sont :";
            for(Race race : tabRace)
                out += " " + race.toString();
        }else{
            out += " , aucune race n'est concernée par ce bonus";
        }
        
        if(tabClasse != null && !(tabClasse.isEmpty())){
            out += ", les classes contre lesquelles ce bonus s'applique sont :";
            for(Classe classe : tabClasse){
                out += " ";
                out += classe.toString();
            }
            out += "\n";
        }else{
            out += " , aucune classe n'est concernée par ce bonus";
        }
        System.out.println(out);

        if(tabRace != null && !tabRace.isEmpty()){      // Si des races sont spécifiées  
            for(Race race: tabRace)                     // On regarde si celle du joueur est concernée
                if(joueurImpacte.getPersonnage().getRace().toString().equals(race.toString()))
                    raceTrouve=true;
            if(!raceTrouve)
                accept=false;
        }
		
        if(tabClasse != null && !tabClasse.isEmpty()){  // Si des classes sont spécifiées  
            for(Classe classe: tabClasse)               // On regarde si celle du joueur est concernée
                if(joueurImpacte.getPersonnage().getClasse()!=null && joueurImpacte.getPersonnage().getClasse().equals(classe))
                    raceTrouve=true;
            if(!classeTrouve)
                    accept=false;
        }
		
        if(accept)
            monstre.setPuissance(monstre.getPuissance()+bonusPuissance);
        return out;	
    }
}
