package partie;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import carte.Tresor;
import com.sun.xml.internal.ws.api.DistributedPropertySet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Vector;
import joueur.Joueur;

/**
 * 
 * @author Julien
 */
public final class Partie extends Vector<Joueur> {
	
    private Deck                deck;
    private Pioche              piocheTresor;
    private Pioche              piocheDonjon;
    private Defausse            defausseTresor;
    private Defausse            defausseDonjon;
    private ArrayList<Joueur>   listeJoueurs;

    /**
     * 
     */
    public Partie(){
        piocheDonjon    = new Pioche<Donjon>("carte.Donjon");
        piocheTresor    = new Pioche<Tresor>("carte.Tresor");
        defausseTresor  = new DefausseTresor();
        defausseDonjon  = new DefausseDonjon();
        listeJoueurs    = new ArrayList<Joueur>();
        deck            = new Deck();
    }
    
    /**
     * 
     * @param piocheTresor
     * @param piocheDonjon
     * @param defausseTresor
     * @param defausseDonjon
     * @param listeJoueurs 
     */
    public Partie(Pioche<Tresor> piocheTresor, Pioche<Donjon> piocheDonjon, Defausse defausseTresor, Defausse defausseDonjon, ArrayList<Joueur> listeJoueurs) {
        this.piocheTresor = piocheTresor;
        this.piocheDonjon = piocheDonjon;
        this.defausseTresor = defausseTresor;
        this.defausseDonjon = defausseDonjon;
        this.listeJoueurs = listeJoueurs;
    }

    /**
     * 
     */
    public void run(){
        piocheDonjon.init(this.deck);
        piocheTresor.init(this.deck);
        
        listeJoueurs.add(new Joueur("Joueur 1"));
        listeJoueurs.add(new Joueur("Joueur 2"));
        listeJoueurs.add(new Joueur("Joueur 3"));
        listeJoueurs.add(new Joueur("Joueur 4"));
        
        this.distribuer();
        
        Iterator it = listeJoueurs.iterator();
        Joueur enCours;
        Carte c;
        
        while(true){
            while(it.hasNext()){
                enCours = (Joueur) it.next();
                c = (Carte) piocheDonjon.tirerCarte(); 
                if(c == null){
                    System.out.println("Plus rien dans la pioche, au revoir !");
                    return;
                }
                System.out.println("Pour le joueur " + enCours.getNom() + " : ");
                
                
                if(c.getClass().getName().equals("carte.Monstre")){
                    Combat combat = new Combat(this);
                    combat.getCampGentil().add(enCours.getPersonnage());
                    
                    Monstre m = (Monstre) c;
                    combat.getCampMechant().add(m);
                    
                    System.out.println("Vous avez tiré le monstre :");
                    System.out.println(m.getNom() + "(Puissance : " + m.getPuissance() + ")");
                    System.out.println(m.getDescription());
                    
                    System.out.println("Combattre ? (o/n)");
                    Scanner sc = new Scanner(System.in);
                    String str = sc.nextLine();
                    if(str.equals("o") || str.equals("O")){
                        if(combat.combattre()){
                            System.out.println("Vous avez gagné !");
                        }else{
                            System.out.println("Vous avez perdu...");
                        }
                    }else if(str.equals("n") || str.equals("n")){
                        if(combat.tenterDeguerpir()){
                            System.out.println("Vous avez réussi à déguérpir !");
                        }else{
                            System.out.println("Vous n'avez pas réussi à déguerpir...");
                        }
                    }else{
                        System.out.println("Pô compris");
                    }
                    
                }else{
                    System.out.println("Ce n'est pas un monstre...");
                }
            }
            it = listeJoueurs.iterator();
        }
    }
    
    

    /**
     * 
     */
    public void distribuer(){
        Iterator it = listeJoueurs.iterator();
        Joueur j;
        
        while(it.hasNext()){
            j = (Joueur) it.next();
            for(int i=0; i<4; i++){
                j.getMain().ajouterCarte((Carte) piocheDonjon.tirerCarte());
                j.getMain().ajouterCarte((Carte) piocheTresor.tirerCarte());
            }
        }
        
//      AFFICHAGE DES CARTES DES JOUEURS (DEBUG)  
//        it = listeJoueurs.iterator();
//        while(it.hasNext()){
//            j = (Joueur) it.next();
//            System.out.println("Cartes du joueur " + j.getNom());
//            ArrayList<Carte> c = j.getMain().getCartes();
//            Iterator itCarte = c.iterator();
//            while(itCarte.hasNext()){
//                Carte carte = (Carte) itCarte.next();
//                System.out.println(carte);
//            }
//        }
    }

    
    /**
     * 
     * @return 
     */
    public boolean commencerTour(){
        Donjon enJeu = (Donjon) piocheDonjon.tirerCarte();
        return true;
    }

    /**
     * 
     * @return 
     */
    public Defausse getDefausseDonjon() {
        return defausseDonjon;
    }

    /**
     * 
     * @param defausseDonjon 
     */
    public void setDefausseDonjon(Defausse defausseDonjon) {
        this.defausseDonjon = defausseDonjon;
    }

    /**
     * 
     * @return 
     */
    public Defausse getDefausseTresor() {
        return defausseTresor;
    }

    /**
     * 
     * @param defausseTresor 
     */
    public void setDefausseTresor(Defausse defausseTresor) {
        this.defausseTresor = defausseTresor;
    }
    
    /**
     * 
     * @return 
     */
    public ArrayList<Joueur> getListeJoueurs() {
        return listeJoueurs;
    }

    /**
     * 
     * @param listeJoueurs 
     */
    public void setListeJoueurs(ArrayList<Joueur> listeJoueurs) {
        this.listeJoueurs = listeJoueurs;
    }

    /**
     * 
     * @return 
     */
    public Pioche<Donjon> getPiocheDonjon() {
        return piocheDonjon;
    }
    
    /**
     * 
     * @param piocheDonjon 
     */
    public void setPiocheDonjon(Pioche<Donjon> piocheDonjon) {
        this.piocheDonjon = piocheDonjon;
    }
    
    /**
     * 
     * @param piocheTresor 
     */
    public void setPiocheTresor(Pioche<Tresor> piocheTresor) {
        this.piocheTresor = piocheTresor;
    }

    /**
     * 
     * @return 
     */
    public Pioche<Tresor> getPiocheTresor() {
        return piocheTresor;
    }

    /**
     * 
     * @return 
     */
    public boolean choixCombat(){
        return true;
    }
    
}
