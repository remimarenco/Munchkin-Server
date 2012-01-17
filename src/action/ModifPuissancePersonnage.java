package action;

import java.util.ArrayList;

import partie.Combat;
import partie.Partie;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

/**
 * Modifie la puissance d'un personnage
 * @author Simon Grabit
 */
public class ModifPuissancePersonnage extends Action{

    private ArrayList<Race> tabRace;
    private ArrayList<Classe> tabClasse;
    private int bonusPuissance;
    private boolean bNiveau = true;
    private boolean bObjet  = true;
	
    
    /**
     * Constructeur
     * @param tabRace
     * @param tabClasse
     * @param bonusPuissance 
     */
    public ModifPuissancePersonnage(ArrayList<Race> tabRace, ArrayList<Classe> tabClasse,
                int bonusPuissance) {
        super();
        this.tabRace        = tabRace;
        this.tabClasse      = tabClasse;
        this.bonusPuissance = bonusPuissance;
    }
	
    
    /**
     * Constructeur
     * @param tabRace
     * @param tabClasse
     * @param bonusPuissance
     * @param bNiveau
     * @param bObjet 
     */
    public ModifPuissancePersonnage(ArrayList<Race> tabRace, ArrayList<Classe> tabClasse,
                int bonusPuissance, boolean bNiveau, boolean bObjet) {
        super();
        this.tabRace        = tabRace;
        this.tabClasse      = tabClasse;
        this.bonusPuissance = bonusPuissance;
        this.bNiveau        = bNiveau;
        this.bObjet         = bObjet;
    }

    /**
     * Méthode modifiant la puissance d'un personnage
     * @param joueurImpacte
     * @return 
     */
    // TODO : Description méthode + PROTECTION NULL
    @Override
    public String action(Joueur joueurEmetteur,
                    ArrayList<Joueur> joueurDestinataire, Partie partie) {

        int puissanceObjet   = 0;
        int niveauJoueur     = 0;
        boolean raceTrouve   = false;
        boolean classeTrouve = false;
        boolean accept       = true;

        String out = "";

        for(Joueur joueurImpacte : joueurDestinataire) {
            out += "On passe dans une action de modification de puissance de personnage :\n";
            out += "Le joueur impliqué est "+joueurImpacte.getName();
            out += ", le bonus puissance attribué est de " + this.bonusPuissance;

            if(tabRace != null){
                out += ", les races pour lesquelles ce bonus s'applique sont :";
                for(Race race : tabRace)
                    out += (" " + race.toString());
            }
            else
                out += "Aucun classe pour ce bonus";

            if(tabClasse != null){
                out += ", les classes pour lesquelles ce bonus s'applique sont :";
                for(Classe classe : tabClasse)
                    out += " " + classe.toString();

            }
            else
                out += "Aucune classe pour ce bonus";

            out += "\n";

            if(tabRace!=null){                  // Si des races ont été spécifiées
                for(Race race: tabRace)         // On regarde si celle du perso en fait parti
                    if(joueurImpacte.getPersonnage().getRace().equals(race))
                        raceTrouve=true;
                if(!raceTrouve)
                    accept=false;
            }

            if(tabClasse!=null){                // Si des classes ont été spécifiées
                for(Classe classe: tabClasse)   // On regarde si celle du perso en fait parti
                    if(joueurImpacte.getPersonnage().getClasse().equals(classe))
                        classeTrouve=true;
                if(!classeTrouve)
                    accept=false;
            }

            // TODO : Commenter, expliciter ce qu'est bNiveau & bObjet

            if(!bNiveau){
                niveauJoueur=joueurImpacte.getPersonnage().getNiveau();
                this.bonusPuissance -= niveauJoueur;
            }

            if(!bObjet){
                puissanceObjet=joueurImpacte.getPersonnage().getPuissance()-joueurImpacte.getPersonnage().getNiveau();
                this.bonusPuissance -= puissanceObjet;
            }

            if(accept)
                joueurImpacte.getPersonnage().setBonusPuissance(joueurImpacte.getPersonnage().getBonusPuissance()+bonusPuissance);
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
