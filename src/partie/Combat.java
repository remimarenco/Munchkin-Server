package partie;

import carte.Monstre;
import java.util.ArrayList;
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
     * Tenter de déguerpir du combat
     * @return 
     */
    public boolean tenterDeguerpir() {
    	// Le camp gentil tente de déguerpir
    	for(Personnage gentil : CampGentil){
    		gentil.deguerpir();
    	}
        return true;
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
