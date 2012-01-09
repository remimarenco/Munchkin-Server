package partie;

import carte.Monstre;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import joueur.Personnage;

/**
 * Combat entre un perso & un monstre
 * @author Julien Rouvier
 */
public class Combat {
    
    private ArrayList<Personnage> CampGentil;
    private ArrayList<Object> CampMechant;
    private Partie partie;

    /**
     * Constructeur
     * @param partie : partie dans laquelle à lieu le combat 
     */
    public Combat(Partie partie) {
        this.partie = partie;
        CampGentil  = new ArrayList<Personnage>();
        CampMechant = new ArrayList<Object>();
    }

    /**
     * Constructeur
     */
    public Combat() {
    }
    
    
    /**
     * Retourne le camps gentil (camps du perso)
     * @return 
     */
    public ArrayList<Personnage> getCampGentil() {
        return CampGentil;
    }

    
    /**
     * Retourne le camps méchant (camps du monstre)
     * @return 
     */
    public ArrayList<Object> getCampMechant() {
        return CampMechant;
    }
    
    
    /**
     * Méthode permettant de savoir si les personnages d'un combat ont réussi à déguerpir
     * @return HashMap contenant Vrai et Faux. Vrai et Faux sont les clés de tableau de personnage
     */
    public HashMap<Boolean, ArrayList<Personnage>> tenterDeguerpir() {
        // Tableaux de personnage qui ont réussi ou non
        ArrayList<Personnage> reussi = new ArrayList<Personnage>();
        ArrayList<Personnage> echec = new ArrayList<Personnage>();

        // HashMap permettant de répartir les personnages qui ont réussi ou non
        HashMap<Boolean, ArrayList<Personnage>> dico = new HashMap<Boolean, ArrayList<Personnage>>();
        // Assignation des tableaux au HashMap
        dico.put(Boolean.TRUE, reussi);
        dico.put(Boolean.FALSE, echec);

        // Parcourt des personnages du campGentil, assignation dans les tableaux
    	for(Personnage gentil : CampGentil){
            if(gentil.deguerpir())
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
        
        int puissanceGentil = 0;
        int puissanceMechant = 0;
        
        Iterator itGentil = CampGentil.iterator();
        Iterator itMechant = CampMechant.iterator();
        
        while(itGentil.hasNext()){
            Object obj = itGentil.next();
            if(obj instanceof Personnage){
                Personnage p = (Personnage) obj;
                puissanceGentil += p.getNiveau();
            }else if(obj instanceof Monstre){
                Monstre m = (Monstre) obj;
                puissanceGentil += m.getPuissance();
            }else{
                System.out.print("Petit problème dans le camps des gentils !");
                return false;
            }
        }
        
        while(itMechant.hasNext()){
            Object obj = itMechant.next();
            if(obj instanceof Personnage){
                Personnage p = (Personnage) obj;
                puissanceMechant += p.getNiveau();
            }else if(obj instanceof Monstre){
                Monstre m = (Monstre) obj;
                puissanceMechant += m.getPuissance();
            }else{
                System.out.print("Petit problème dans le camps des méchants !");
                return false;
            }
        }
        
        if(puissanceGentil >= puissanceMechant) // Le camps gentil (du perso) a gagné
            return true;
        else                                    // Le camps méchant (du monstre) a gagné
            return false;
    }
}
