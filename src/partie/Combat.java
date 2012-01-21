package partie;

import carte.Monstre;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import joueur.Personnage;


/**
 * Combat entre un perso & un monstre
 * @author Julien Rouvier
 */
public class Combat {
    
    private ArrayList<Personnage> CampGentil;
    private ArrayList<Monstre> CampMechant;
    private int bonusTemporaireGentil = 0;
    private int bonusTemporaireMechant = 0;
    private Partie partie;
    private Object campCible = null;

    
    public int getBonusTemporaireGentil() {
		return bonusTemporaireGentil;
	}


	public void setBonusTemporaireGentil(int bonusTemporaireGentil) {
		this.bonusTemporaireGentil = bonusTemporaireGentil;
	}


	public int getBonusTemporaireMechant() {
		return bonusTemporaireMechant;
	}


	public void setBonusTemporaireMechant(int bonusTemporaireMechant) {
		this.bonusTemporaireMechant = bonusTemporaireMechant;
	}


	/**
     * Constructeur
     * @param partie : partie dans laquelle à lieu le combat 
     */
    public Combat(Partie partie) {
        this.partie = partie;
        CampGentil  = new ArrayList<Personnage>();
        CampMechant = new ArrayList<Monstre>();
    }

    
    /**
     * Constructeur
     */
    public Combat() {
    }
    
    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public ArrayList<Personnage> getCampGentil() {
        return CampGentil;
    }

    public ArrayList<Monstre> getCampMechant() {
        return CampMechant;
    }
    
    public Object getCampCible(){
        if(partie.getCampCible() != null)
        {
            if(partie.getCampCible().equals("Gentil"))
                    campCible=this.CampGentil;
            else
                    campCible=this.CampMechant;
            return campCible;
        }
        System.out.println("Le camp cible est null dans getCampCible");
        return null; 
    }
    
    // ================================== //
    
    
    
    /**
     * Retourne le total de puissance du camp gentil
     * @return total: valeur (chaine) du camp gentil
     */
    public String getPuissanceCampGentil(){    
        Integer total = 0;
        for(Personnage p : CampGentil)
            total += p.getPuissance();        
        return total.toString();
    }
    
    
    /**
     * Retourne le total de puissance du camp mechant
     * @return total: valeur (chaine) du camp gentil
     */
    public String getPuissanceCampMechant(){    
        Integer total = 0;
        for(Monstre p : CampMechant)
            total += p.getPuissance();        
        return total.toString();
    }
    
    
    
    /**
     * Méthode permettant de savoir si les personnages d'un combat ont réussi à déguerpir
     * @return HashMap contenant Vrai et Faux. Vrai et Faux sont les clés de tableau de personnage
     */
    public HashMap<Boolean, ArrayList<Personnage>> tenterDeguerpir() {
        // Tableaux de personnage qui ont réussi ou non
        ArrayList<Personnage> reussi = new ArrayList<Personnage>();
        ArrayList<Personnage> echec  = new ArrayList<Personnage>();

        // HashMap permettant de répartir les personnages qui ont réussi ou non
        HashMap<Boolean, ArrayList<Personnage>> dico = new HashMap<Boolean, ArrayList<Personnage>>();
        
        // Assignation des tableaux au HashMap
        dico.put(Boolean.TRUE, reussi);
        dico.put(Boolean.FALSE, echec);

        // Parcourt des personnages du campGentil, assignation dans les tableaux
    	for(Personnage gentil : CampGentil)
        {
            partie.sendMessageToAll("On lance le dé !");
            int valeurDe = Constante.nbAleatoire(1, 7);
            partie.sendMessageToAll("Le dé a parlé : "+valeurDe);
            if(gentil.deguerpir(valeurDe))
            {
                reussi.add(gentil);
            }
            else
            {
                echec.add(gentil);
            }
        }
        return dico;
    }    
    
    
    /**
     * Combat à proprement parlé
     * Vérification des puissances des 2 camps pour voir lequel l'emporte
     * @return 
     */
    public boolean combattre(){
        
        int puissanceGentil  = 0;
        int puissanceMechant = 0;
        Iterator itGentil    = CampGentil.iterator();
        Iterator itMechant   = CampMechant.iterator();
        boolean guerrier     = false;
        
        // IL N'Y A JAMAIS DE MONSTRE COTE GENTIL !!!!!!!!!!!!!!!!!!!!!!
        while(itGentil.hasNext()){
            Object obj = itGentil.next();
            if(obj instanceof Personnage){
                Personnage p = (Personnage) obj;
                // On regarde la puissance et non le niveau
                puissanceGentil += p.getPuissance();
            }
            else{
                System.out.print("Petit problème dans le camps des gentils !");
                return false;
            }
        }
        
        // IL N'Y A JAMAIS DE PERSONNAGE COTE MECHANT !!!!!!!!!!!!!!!!!!!!!!!!!
        while(itMechant.hasNext()){
            Object obj = itMechant.next();
            if(obj instanceof Monstre){
                Monstre m = (Monstre) obj;
                puissanceMechant += m.getPuissance();
            }else{
                System.out.print("Petit problème dans le camps des méchants !");
                return false;
            }
        }
        for(Personnage persoImpacte : CampGentil){
            if(persoImpacte.getClasse()==Constante.CLASSE_GUERRIER)
                guerrier=true;
        }
        if(guerrier)
        	bonusTemporaireGentil++;
        
        // On applique les bonusTemporaires en plus
        puissanceGentil += bonusTemporaireGentil;
        puissanceMechant += bonusTemporaireMechant;
        boolean resultat = puissanceGentil > puissanceMechant;
        bonusTemporaireGentil = 0;
        bonusTemporaireMechant = 0;
        return (puissanceGentil > puissanceMechant); // True si camps gentil gagne
    }
    
    /**
     * Génère les infos du combat : qui se trouve dans quel camps, avec les 
     * puissances associées
     * @return HashMap : ensembles des infos du combat
     */
    public LinkedHashMap<String,String> generateInfos(){
        LinkedHashMap<String,String> map = new LinkedHashMap<String, String>();        
        map.put("Camp Gentil", getPuissanceCampGentil());
        for(Personnage p : CampGentil)
            map.put(p.getNom(),String.valueOf(p.getPuissance()));
        
        map.put("BonusG", String.valueOf(bonusTemporaireGentil));
        
        map.put("Camp Mechant", getPuissanceCampMechant());
        for(Monstre m : CampMechant)
            map.put(m.getNom(),String.valueOf(m.getPuissance()));
        
        map.put("BonusM", String.valueOf(bonusTemporaireMechant));
        
        return map;
    }
}
