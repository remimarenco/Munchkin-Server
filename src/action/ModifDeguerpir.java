package action;

import java.util.ArrayList;

import partie.Combat;
import partie.Partie;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

/**
 * Modifie la capacité qu'ont les personnage à déguerpir
 * @author Simon Grabit
 */
public class ModifDeguerpir extends Action{
    
    private Integer bonusDeguerpir;
    private Integer niveauMax;
    private Integer sexe;
    private ArrayList<Race> tabRace;
    private ArrayList<Classe> tabClasse;
	
    /**
     * Constructeur
     * @param bonusDeguerpir
     * @param niveauMax
     * @param sexe
     * @param tabRace
     * @param tabClasse 
     */
    public ModifDeguerpir(Integer bonusDeguerpir, Integer niveauMax, Integer sexe, ArrayList<Race> tabRace, ArrayList<Classe> tabClasse) {
        super();
        this.bonusDeguerpir = bonusDeguerpir;
        this.niveauMax      = niveauMax;
        this.sexe           = sexe;
        this.tabRace        = tabRace;
        this.tabClasse      = tabClasse;
    }

    /**
     * Action de modification du déguerpissage
     * @param joueurImpacte : joueur subissant la modif
     * @return out : texte résumant l'action
     */
    // TODO : Description méthode + PROTECTION NULL
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		
		String out              = "";
        boolean accept          = true;
        boolean raceTrouve      = false;
        boolean classeTrouve    = false;
	
        out += "On passe dans une action de modification déguerpir :\n";
        out += "Le bonus déguerpir est de " + bonusDeguerpir + ", le niveau max pour ce bonus est de "
                + niveauMax + ", le sexe sur lequel il s'applique est " + sexe;

        if(tabRace != null){
            out += ", les races impliquées sont : ";
            for(Race race : tabRace)
                out += race.toString();
        }else{
            out += ", aucune race impliquée";
        }

        if(tabClasse != null){
            out += ", les classes impliquées sont : ";
            for(Classe classe : tabClasse)
                out += classe.toString();    
        }
        else{
            out += ", aucune classe impliquée";
        }
        out += "\n";
        
        ArrayList<Joueur> joueurDestinataireTemp = new ArrayList<Joueur>();

        // Si on avait pas spécifié de joueurDestinataire, on demande le joueur destinataire
        if(joueurDestinataire == null || joueurDestinataire.isEmpty())
        {
        	if(choixJoueur)
        	{
        		// On renvoi les joueurs destinataires par une demande au joueur initiateur
            	joueurDestinataireTemp.add(demandeChoixJoueur(partie, joueurEmetteur));
        	}
        }
        else
        {
        	joueurDestinataireTemp = (ArrayList<Joueur>) joueurDestinataire.clone();
        }
        
        for(Joueur joueurImpacte : joueurDestinataireTemp)
        {
	        if(niveauMax != null)                                       // Si un niveau max est défini
	            if(joueurImpacte.getPersonnage().getNiveau()>niveauMax) // On regarde que le personnage ne le dépasse pas
	                accept=false;
			
	        if(sexe != null)                                            // Si un sexe est défini
	            if(joueurImpacte.getPersonnage().getSexe()!=sexe)       // On regarde si c'est celui du personnage
	                accept=false;
	
	        if(tabRace != null){                                        // Si un tableau de race est défini                                        
	            for(Race race: tabRace)                                 // On regarde si celle du personnage s'y trouve
	                if(joueurImpacte.getPersonnage().getRace().equals(race))
	                    raceTrouve=true;
	            if(!raceTrouve)
	                accept=false;
	        }
			
	        if(tabClasse != null){                                        // Si un tableau de classe est défini
	            for(Classe classe: tabClasse)                           // On regarde si celle du personnage s'y trouve
	                if(joueurImpacte.getPersonnage().getClasse() != null && joueurImpacte.getPersonnage().getClasse().equals(classe))
	                                raceTrouve=true;
	                if(!classeTrouve)
	                        accept=false;
	        }
			
	        if(accept == true)    // Si toutes les conditions sont réunies, on applique la modif
	            joueurImpacte.getPersonnage().setBonusCapaciteFuite(joueurImpacte.getPersonnage().getBonusCapaciteFuite()+bonusDeguerpir);
        }
        return out;
	}

	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie,
			boolean choixJoueur) {
		boolean ancienChoixJoueur = this.choixJoueur;
		this.choixJoueur = choixJoueur;
		String out = action(joueurEmetteur, joueurDestinataire, partie);
		this.choixJoueur = ancienChoixJoueur;
		return out;
	}
}
