package action;

import java.util.ArrayList;

import partie.Constante;
import partie.Partie;
import carte.Monstre;
import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

/**
 * Modifie la puissance d'un monstre
 * @author Simon Grabit
 */
public class ModifPuissanceMonstre extends Action{

    private ArrayList<Race> tabRace;
    private ArrayList<Classe> tabClasse;
    private int bonusPuissance;
    private Monstre monstre;
    protected ArrayList<Integer> phasesIntervenir;
	
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
        this.phasesIntervenir = new ArrayList<Integer>();
        this.phasesIntervenir.add(Constante.PHASE_CHERCHER_LA_BAGARRE);
    }

    /**
     * Action modifiant la puissance d'un monstre
     * @param joueurImpacte : joueur contre lequel se bat le monstre
     * @return out : texte résumant l'action
     */    
    // TODO : Description méthode + PROTECTION NULL
    // Méthode permettant de modifier la puissance d'un monstre
    @Override
    public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
        String out           = "";
        boolean accept       = true;
        boolean raceTrouve   = false;
        boolean classeTrouve = false;
		
        out += "On passe dans une action de modification de puissance de monstre :\n";
        out += "Le monstre impliqué est "+monstre.getNom();
        out += ", le bonus puissance attribué est de " + this.bonusPuissance;
    
        for(Joueur joueurImpacte : joueurDestinataire){
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
            
            // Si on ne se trouve pas dans la phase de recherche de la bagarre => En combat, cette carte n'aura aucun effet
            if(partie.getPhaseTour() != Constante.PHASE_CHERCHER_LA_BAGARRE){
            	accept = false;
            }
            //TODO : Ce n'est pas le personnage qui recoit un bonus de puissance mais le camp méchant du combat
            if(accept){
                monstre.setBonusPuissance(monstre.getBonusPuissance()+bonusPuissance);
                out += "\nModification du monstre effectuée";
            }
            else{
            	out += "\nModification du monstre NON effectuée";
            }
            System.out.println(out);
        }
        return out;	
    }

    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie,
                    boolean choixJoueur) {
        ArrayList<Joueur> array = new ArrayList<Joueur>();
        array.add(partie.getEnCours());
        return action(joueurEmetteur, array, partie);
    }
    
    @Override
    public boolean isPosable(Partie partie, Joueur joueur) {
        return true;
    }
    
    /**
     * Méthode permettant de savoir si une action est intervenable ou non selon la phase du tour passé en paramètre
     * @param phaseTourEnCours
     * @return 
     */
    @Override
    public boolean isIntervenable(int phaseTourEnCours) {
        // Si la phase n'a pas été spécifiée ou contient PHASE_ALL, on retourne immédiatement vrai
        if(phasesIntervenir == null || phasesIntervenir.contains(Constante.PHASE_ALL) || phasesIntervenir.isEmpty())
        {
            return true;
        }
        // Sinon on parcourt les phasesIntervenir renseigné à la construction de l'objet, si on trouve un match avec celle passée en paramètre, on retourne vrai sinon on retourn faux
        else
        {
            for(Integer phaseIntervenir : phasesIntervenir)
            {
                if(phaseIntervenir.equals(phaseTourEnCours))
                {
                    return true;
                }
            }
        }
        return false;
    }
}