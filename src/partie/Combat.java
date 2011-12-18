package partie;

import carte.Monstre;
import java.util.ArrayList;
import java.util.Iterator;
import joueur.Personnage;

public class Combat {
    private ArrayList<Personnage> CampGentil;
    private ArrayList<Object> CampMechant;
    private Partie partie;

    public Combat(Partie partie) {
        this.partie = partie;
        CampGentil= new ArrayList<Personnage>();
        CampMechant= new ArrayList<Object>();
    }

    public Combat() {
    }

    
    
    public ArrayList<Personnage> getCampGentil() {
        return CampGentil;
    }

    public ArrayList<Object> getCampMechant() {
        return CampMechant;
    }
    
    
    public boolean tenterDeguerpir() {
    	// Le camp gentil tente de déguerpir
    	for(Personnage gentil : CampGentil)
    	{
    		gentil.deguerpir();
    	}
        return true;
    }    
    
    
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
        
        if(puissanceGentil >= puissanceMechant)
            return true;
        else 
            return false;
    }
}
