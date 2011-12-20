package partie;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import carte.Sort;
import carte.Tresor;
import communication.Message;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.ByteArrayInputStream;
import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import joueur.Joueur;

/**
 * 
 * @author Julien
 */
public final class Partie extends ArrayList<Joueur>{
	
    private Deck                deck;
    private Pioche              piocheTresor;
    private Pioche              piocheDonjon;
    private Defausse            defausseTresor;
    private Defausse            defausseDonjon;
    //private ArrayList<Joueur>   listeJoueurs;
    private Joueur              enCours;
    private Color               Color;
    private String              answer;

    /**
     * 
     */
    public Partie(){
        piocheDonjon    = new Pioche<Donjon>(Constante.DONJON);
        piocheTresor    = new Pioche<Tresor>(Constante.TRESOR);
        defausseDonjon  = new Defausse<Donjon>();
        defausseTresor  = new Defausse<Tresor>();
        //listeJoueurs    = new ArrayList<Joueur>();
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
        //this.listeJoueurs = listeJoueurs;
    }

    /**
     * 
     */
  synchronized public void run(int nbJoueur){
        piocheDonjon.init(this.deck);
        piocheTresor.init(this.deck);       
        this.distribuer();
        
        Iterator it = this.iterator();        
        Carte cartePiochee;
        
        while(nbJoueur==this.size()){
            while(it.hasNext()){
                enCours = (Joueur) it.next();
                
                if(piocheDonjon.isEmpty()){
                    System.out.println("\n\n\n*****\nPlus rien dans la pioche, on récupère la défausse !\n*****\n\n\n");
                    piocheDonjon.setPioche(new ArrayList<Donjon> (defausseDonjon.getDefausse()));
                    defausseDonjon.vider();
                }
                
                if(piocheDonjon.isEmpty()){
                    System.out.println("Apparemment, il y avait rien dans la défausse... Du coup, ciao !");
                    return;
                }
                
                cartePiochee = (Carte) piocheDonjon.tirerCarte(); 
                if(cartePiochee == null){
                    System.out.println("Problème lors du tirage dans la pioche donjon");
                    return;
                }
                
                System.out.println("\n\n" + enCours.getName() + " (Niveau "+ enCours.getPersonnage().getNiveau() + ") : ");
                //envoi du message a tous les client connecté
                this.sendMessageToAll("Le joueur : " +enCours.getName() + "pioche une carte ! : \n");
                
                
                // === MONSTRE ===
                if(cartePiochee.getClass().equals(Monstre.class))
                {
                    Combat combat = new Combat(this);
                    combat.getCampGentil().add(enCours.getPersonnage());
                    
                    Monstre monstrePioche = (Monstre) cartePiochee;
                    combat.getCampMechant().add(monstrePioche);
                    
                    System.out.println("Vous avez tiré le monstre :");
                    System.out.println(monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")");
                    System.out.println(monstrePioche.getDescription());
                    
                    System.out.println("Combattre ? (o/n)");
                    this.sendMessageToAll("Le joueur : " +enCours.getName() + " a tiré le monstre : \n"
                            + monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")\n"
                            + monstrePioche.getDescription() +"\n"
                            +" Va-t il combattre ?\n");
                    this.sendQuestionToEnCours("Combattre ?");
                    this.answer=null;
                    while( this.answer==null && this.size()==nbJoueur){}
                    
                    	
	                    if(this.answer.equals("Yes")){
	                        if(combat.combattre()){
	                            System.out.println("Vous avez gagné !");
	                            monstrePioche.appliquerMonstreVaincu(enCours);
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a gagné le combat ! \n");
	                        }else{
	                            System.out.println("Vous avez perdu...");
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a perdu le combat ! \n");
	                            monstrePioche.appliquerIncidentFacheux(enCours);
	                        }
	                    }else if(this.answer.equals("Non")){
	                        if(combat.tenterDeguerpir()){
	                            System.out.println("Vous avez réussi à déguérpir !");
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + " a réussi a deguerpir ! \n");
	                        }
	                        else{
	                        	// Chelou le passage dans le else
		                        // Si on ne peut pas déguerpir, on dit au joueur d'entrer o ou n ?
	                            //System.out.println("Veuillez entrer 'o' ou 'n'");
	                             this.sendMessageToAll("Le joueur : " +enCours.getName() + " n'a pas réussi a deguerpir ! \n");
	                        }
	                    }
	                    else
	                    {
	                    	System.out.println("Veuillez entrer une réponse correcte");
	                    }
                    // On boucle tant qu'il n'a pas donné de réponse
                    
	               monstrePioche.setBonusPuissance(0);
                   this.defausseDonjon.ajouterCarte(cartePiochee);
                }
                // ===============
                
                // ==== SORT ====
                else if(cartePiochee.getClass().equals(Sort.class)){
                    System.out.println("C'est un sort !!");
                     this.sendMessageToAll("C'est un sort !!\n");
                }
                // ==============
                
                // On annule les bonus temporaires
                this.enCours.getPersonnage().setBonusCapaciteFuite(0);
                this.enCours.getPersonnage().setBonusPuissance(0);
            }
            it = this.iterator();
        }
    }
    
    
//  synchronized public void run2(){
//        piocheDonjon.init(this.deck);
//        piocheTresor.init(this.deck);
//        
//        listeJoueurs.add(new Joueur("Joueur 1", this));
//        listeJoueurs.add(new Joueur("Joueur 2", this));
//        listeJoueurs.add(new Joueur("Joueur 3", this));
//        listeJoueurs.add(new Joueur("Joueur 4", this));
////        this.enCours= this.get(0);
//        this.distribuer();
//        
//        Iterator it = listeJoueurs.iterator();
//        Joueur enCours;
//        Carte cartePiochee;
//        
//        while(true){
//            while(it.hasNext()){
//                enCours = (Joueur) it.next();
//                
//                if(piocheDonjon.isEmpty()){
//                    System.out.println("\n\n\n*****\nPlus rien dans la pioche, on récupère la défausse !\n*****\n\n\n");
//                    piocheDonjon.setPioche(new ArrayList<Donjon> (defausseDonjon.getDefausse()));
//                    defausseDonjon.vider();
//                }
//                
//                if(piocheDonjon.isEmpty()){
//                    System.out.println("Apparemment, il y avait rien dans la défausse... Du coup, ciao !");
//                    return;
//                }
//                
//                cartePiochee = (Carte) piocheDonjon.tirerCarte(); 
//                if(cartePiochee == null){
//                    System.out.println("Problème lors du tirage dans la pioche donjon");
//                    return;
//                }
//                
//                System.out.println("\n\n" + enCours.getNom() + " (Niveau "+ enCours.getPersonnage().getNiveau() + ") : ");
//                //envoi du message a tous les client connecté
//                this.sendMessageToAll("Le joueur : " +enCours.getNom() + "pioche une carte ! : \n");
//                
//                
//                // === MONSTRE ===
//                if(cartePiochee.getClass().equals(Monstre.class))
//                {
//                    Combat combat = new Combat(this);
//                    combat.getCampGentil().add(enCours.getPersonnage());
//                    
//                    Monstre monstrePioche = (Monstre) cartePiochee;
//                    combat.getCampMechant().add(monstrePioche);
//                    
//                    System.out.println("Vous avez tiré le monstre :");
//                    System.out.println(monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")");
//                    System.out.println(monstrePioche.getDescription());
//                    
//                    System.out.println("Combattre ? (o/n)");
////                    this.sendMessageToAll("Le joueur : " +enCours.getNom() + " a tiré le monstre : \n"
////                            + monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")\n"
////                            + monstrePioche.getDescription() +"\n"
////                            +" Va-t il combattre ?\n");
////                    this.sendQuestionToEnCours("Combattre ?");
////                    this.answer=null;
////                    while( this.answer==null){}
//                    
//                    Scanner sc = new Scanner(System.in);
//                    this.answer = sc.nextLine();
//                    
//	                    if(this.answer.equals("O") || this.answer.equals("o")){
//	                        if(combat.combattre()){
//	                            System.out.println("Vous avez gagné !");
//	                            monstrePioche.appliquerMonstreVaincu(enCours);
//	                            this.sendMessageToAll("Le joueur : " +enCours.getNom() + "  a gagné le combat ! \n");
//	                        }else{
//	                            System.out.println("Vous avez perdu...");
//	                            this.sendMessageToAll("Le joueur : " +enCours.getNom() + "  a perdu le combat ! \n");
//	                            monstrePioche.appliquerIncidentFacheux(enCours);
//	                        }
//	                    }else if(this.answer.equals("N") || this.answer.equals("n")){
//	                        if(combat.tenterDeguerpir()){
//	                            System.out.println("Vous avez réussi à déguérpir !");
//	                            this.sendMessageToAll("Le joueur : " +enCours.getNom() + " a réussi a deguerpir ! \n");
//	                        }
//	                        else{
//	                        	// Chelou le passage dans le else
//		                        // Si on ne peut pas déguerpir, on dit au joueur d'entrer o ou n ?
//	                            //System.out.println("Veuillez entrer 'o' ou 'n'");
//	                             this.sendMessageToAll("Le joueur : " +enCours.getNom() + " n'a pas réussi a deguerpir ! \n");
//	                        }
//	                    }
//	                    else
//	                    {
//	                    	System.out.println("Veuillez entrer une réponse correcte");
//	                    }
//                    // On boucle tant qu'il n'a pas donné de réponse
//                    
//                    this.defausseDonjon.ajouterCarte(cartePiochee);
//                }
//                // ===============
//                
//                // ==== SORT ====
//                else if(cartePiochee.getClass().equals(Sort.class)){
//                    System.out.println("C'est un sort !!");
////                     this.sendMessageToAll("C'est un sort !!\n");
//                    Sort s = (Sort) cartePiochee;
//                    s.appliquerSortilege(enCours);
//                }
//                // ==============
//            }
//            it = listeJoueurs.iterator();
//        }
//    }
    
    

    /**
     * 
     */
    public void distribuer(){
        Iterator it = this.iterator();
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
        return this;
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
    
    /**
     * 
     * @param name 
     */
    public void removeJoueurByName(String name){
        for(Joueur j : this){
            if(j.getName().equals(name))
                this.remove(j);
        }            
    }
    
    public Joueur getJoueurByName(String name){
        Joueur ret=null;
        for(Joueur j : this){
            if(j.getName().equals(name))
                ret=j;
        }
        return ret;
    }
    
    /**
     * 
     * @param log
     * @return 
     */
    public boolean LoginDispo(String log){
        String l = getListe();
        boolean k =true;
        StringTokenizer l2=new StringTokenizer(l,";");
        while(l2.hasMoreTokens()){
            try{
            if(l2.nextToken().equals(log)){
              k = false;
             }
        }
        catch(Exception e){System.out.println("Exception :" + e.toString()); }
        }
        return k;
    }
    
    /**
     * 
     * @return 
     */
    public String getListe(){
        String liste="";
        for(int i=0;i<size();i++){
            liste+=get(i).getName() + ";";           
        }
        return liste;
    }
    
    /**
     * 
     * @param nick_dest
     * @return 
     */
    public Integer getCommunication(String nick_dest){
         int i=0;
         int j=0;
        while(i<this.size()){
            if(this.get(i).getName().equals(nick_dest)){
                j=i;
                i=this.size() +1;
            }
            else{
                i++;
                j--;
            }
        }
       return j;
    }
    
    public void sendMessageToAll(String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.RED);
        for(Joueur j : this)
            j.sendMessage(msg);
    }
     public void sendQuestionToEnCours(String txt){
        Message msg=new Message(Message.QUESTION,"Partie",this.enCours.getName(),txt);
        this.enCours.sendMessage(msg);
    }
     
     public void sendInfosJoueursToAll(){
         for(Joueur j :this){
             for(Joueur j2 :this)
             j.sendMessage(new Message(Message.INFO_JOUEUR, "Partie", j2.getName(), j2.generateInfos()));
         }
     }
             
     
     public boolean answer(Message msg){          
      this.answer=msg.getMessage();
      return true;     
    }

    public void run2() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
 
     

    
}
