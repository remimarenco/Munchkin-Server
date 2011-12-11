package partie;

import java.util.ArrayList;
import java.util.Iterator;

public class Combat {
    private ArrayList<Object> CampGentil;
    private ArrayList<Object> CampMechant;
    private Partie partie;

    public Combat(Partie partie) {
        this.partie = partie;
        CampGentil= new ArrayList<Object>();
        CampMechant= new ArrayList<Object>();
    }

    public Combat() {
    }

    
    
    public ArrayList<Object> getCampGentil() {
        return CampGentil;
    }

    public ArrayList<Object> getCampMechant() {
        return CampMechant;
    }
    
    
    public boolean tenterDeguerpir() {
        return true;
    }    
    
    
    public boolean combattre(){
        int puissanceGentil = 0;
        int puissanceMechant = 0;
        
        Iterator itGentil = CampGentil.iterator();
        Iterator itMechant = CampMechant.iterator();
        
        while(itGentil.hasNext()){
            
        }
        
        while(itMechant.hasNext()){
            
        }
        
        return true;
    }
}
