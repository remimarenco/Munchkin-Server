/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;

import partie.Combat;
import partie.Partie;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

/**
 * Action permettant d'équiper un personnage avec un objet
 * @author Simon Grabit
 */
public class EquiperObjet extends Action{
    
    private ArrayList<Race> tabRace;
    private ArrayList<Classe> tabClasse;
    boolean aChangeSexe;
    int bonusPuissance, bonusDeguerpir, poids;
   
    /**
     * Constructeur d'EquiperObjet
     * @param tabRace => race auxquelles s'appliquent l'objet 
     * @param tabClasse => classe auxquelles s'appliquent l'objet 
     * @param aChangeSexe => true si le personnage doit avoir changé de sexe
     * @param bonusPuissance => bonus de puissance conféré par l'objet
     * @param bonusDeguerpir => bonus de déguerpir conféré par l'objet
     * @param poids => poids de l'objet
     */
    public EquiperObjet(ArrayList<Race> tabRace, ArrayList<Classe> tabClasse, boolean aChangeSexe, int bonusPuissance, int bonusDeguerpir, int poids) {
        super();
        this.tabRace        = tabRace;
        this.tabClasse      = tabClasse;
        this.aChangeSexe    = aChangeSexe;
        this.bonusDeguerpir = bonusDeguerpir;
        this.bonusPuissance = bonusPuissance;
        this.poids          = poids;
    }

    
    /**
     * Equipe un joueur avec un objet
     * @param joueurImpacte : Joueur à équiper
     * @return out : texte résumant l'action
     */
    // TODO : Description méthode + PROTECTION NULL
	@Override
	public String action(Joueur joueurEmetteur,
			ArrayList<Joueur> joueurDestinataire, Partie partie) {
		
            String out           = "";
            boolean accept       = true;
            boolean raceTrouve   = false;
            boolean classeTrouve = false;

            for(Joueur joueurImpacte : joueurDestinataire)
            {
                    out += "Le joueur "+ joueurImpacte.getName() +"s'équipe d'un objet :\n";
                    out += "Le bonus déguerpir est de " + bonusDeguerpir + ", la puissance est de "+ bonusPuissance;


                    if(aChangeSexe)
                        if(!joueurImpacte.getPersonnage().isaChangeSexe())
                            accept=false;

                    if(tabRace != null){                                        // Si un tableau de race est défini
                        for(Race race: tabRace)                                 // On regarde si celle du personnage s'y trouve
                            if(joueurImpacte.getPersonnage().getRace().equals(race))
                                raceTrouve=true;
                        if(!raceTrouve)
                            accept=false;
                    }

                    if(tabClasse!=null){                                        // Si un tableau de classe est défini
                        for(Classe classe: tabClasse)                           // On regarde si celle du personnage s'y trouve
                            if(joueurImpacte.getPersonnage().getClasse()!=null && joueurImpacte.getPersonnage().getClasse().equals(classe))
                                            classeTrouve=true;
                            if(!classeTrouve)
                                    accept=false;
                    }
                    if(joueurImpacte.getPersonnage().getNbEquipement()+poids>joueurImpacte.getPersonnage().getCapaciteEquipement())
                        accept=false;

                    if(accept==true){    // Si toutes les conditions sont réunies, on applique la modif
                        joueurImpacte.getPersonnage().setCapaciteFuite(joueurImpacte.getPersonnage().getCapaciteFuite()+bonusDeguerpir);
                        joueurImpacte.getPersonnage().setPuissanceObjet(joueurImpacte.getPersonnage().getPuissanceObjet()+bonusPuissance);
                        joueurImpacte.getPersonnage().setNbEquipement(joueurImpacte.getPersonnage().getNbEquipement()+poids);
                    }
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
