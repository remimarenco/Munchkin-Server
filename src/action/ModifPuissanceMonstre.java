package action;

import java.util.ArrayList;

import partie.Combat;
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
            if(partie.getPhaseTour() != Constante.PHASE_CHERCHER_LA_BAGARRE)
            {
            	accept = false;
            }

            if(accept)
            {
                monstre.setBonusPuissance(monstre.getBonusPuissance()+bonusPuissance);
                out += "\nModification du monstre effectuée";
            }
            else
            {
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
	
	
}