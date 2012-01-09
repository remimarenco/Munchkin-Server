/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.ArrayList;

import joueur.Classe;
import joueur.Joueur;
import joueur.Race;

/**
 *
 * @author Simon Grabit
 */
public class EquiperObjet extends Action{
    
    private ArrayList<Race> tabRace;
    private ArrayList<Classe> tabClasse;
    boolean aChangeSexe;
    int bonusPuissance, bonusDeguerpir, poids;
    /**
     * Constructeur
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

    @Override
    public String action(Joueur joueurImpacte) {
        String out              = "";
        
        boolean accept          = true;
        boolean raceTrouve      = false;
        boolean classeTrouve    = false;
	
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
                                raceTrouve=true;
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
        return out;
    }
    
}
