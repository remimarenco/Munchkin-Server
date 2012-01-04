package partie;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import carte.Objet;
import carte.Sort;
import carte.Tresor;
import communication.Message;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import joueur.Joueur;

/**
 * 
 * @author Julien
 */
public final class Partie extends ArrayList<Joueur> implements Runnable{
	
    private Deck                deck;
    private Pioche<Tresor>      piocheTresor;
    private Pioche<Donjon>      piocheDonjon;
    private Defausse<Tresor>    defausseTresor;
    private Defausse<Donjon>    defausseDonjon;
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
    public Partie(Pioche<Tresor> piocheTresor, Pioche<Donjon> piocheDonjon, Defausse<Tresor> defausseTresor, Defausse<Donjon> defausseDonjon, ArrayList<Joueur> listeJoueurs) {
        this.piocheTresor = piocheTresor;
        this.piocheDonjon = piocheDonjon;
        this.defausseTresor = defausseTresor;
        this.defausseDonjon = defausseDonjon;
        //this.listeJoueurs = listeJoueurs;
    }

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
    
    /**
     * Fonction permettant d'envoyer un message Ã  tous joueurs sauf le courant
     * @param txt
     */
    public void sendMessageToAllButCurrent(String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.GREEN);
        for(Joueur j : this)
        {
        	if(!j.equals(enCours))
        		j.sendMessage(msg);
        }
    }
     /**
     * Fonction permettant d'envoyer un message Ã  tous joueurs sauf le courant
     * @param txt
     */
    public void sendMessageToAllButSender(String source ,String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.GREEN);
        for(Joueur j : this)
        {
        	if(!j.equals(this.getJoueurByName(source)))
        		j.sendMessage(msg);
        }
    }
    
    /**
     * Fonction permettant d'envoyer un message au joueur en cours
     * @param txt
     */
    public void sendMessageToCurrent(String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
        enCours.sendMessage(msg);
    }
    
     public void sendMessageBackToSender(String source,String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
        this.getJoueurByName(source).sendMessage(msg);
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
     public void sendCartesJeuxJoueursToAll(){
         for(Joueur j :this){
             for(Joueur j2 :this)
             j.sendMessage(new Message
                     (Message.JEUX_JOUEUR, "Partie", j2.getName(), j2.getJeu().generateInfos()));
         }
     }
      public void sendCartesMainToOwner(){
         for(Joueur j :this){             
             j.sendMessage(new Message
                     (Message.MAIN_JOUEUR, "Partie", j.getName(), j.getMain().generateInfos()));
         }
     }
     public void sendCarteEnCoursToAll(Carte carte){
         for(Joueur j :this){             
             j.sendMessage(new Message
                     (Message.CARTE_EN_COURS, "Partie", j.getName(), carte.getId().toString()));
         }
     }
     public void sendSongToAll(int constante){
         for(Joueur j :this){
             j.sendMessage(new Message(Message.SOUND, "Partie", j.getName(), constante));
         }
     }
     
             
     
   
     public void intervenir(Message msg){
         switch(msg.getAction()){
             case Constante.ACTION_AIDER:
                 break;
             case Constante.ACTION_POSERCARTE:
                 this.sendMessageToAllButSender(msg.getNick_src(),"Le joueur :" +msg.getNick_src()+" souhaite poser une carte");
                 this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte Ã  poser");
                 break;
             case Constante.ACTION_POURRIR:
                 break;
         }
     }
     
     public boolean answer(Message msg){
      this.answer=msg.getMessage();      
      return true;     
    }    
 
   

    @Override
    public void run() {
    	piocheDonjon.init(this.deck);
        piocheTresor.init(this.deck);       
        this.distribuer();
        this.sendInfosJoueursToAll();
        this.sendCartesJeuxJoueursToAll();
        this.sendCartesMainToOwner();
        Iterator it = this.iterator();        
        Carte cartePiochee;
        
        while(true){
        	if(enCours != null)
        	{
        		if(enCours.getPersonnage().getNiveau() >= 10)
        		{
        			this.sendMessageToAll("La partie est terminÃ©e !!\n Le joueur "+enCours.getName()+" est passÃ© niveau 10 !!");
        			break;
        		}
        	}
        	else
        	{
        		System.out.println("Joueur en cours est null");
        	}
            while(it.hasNext()){
            	// On veut savoir si la partie est terminÃ©e ou non
            	
                enCours = (Joueur) it.next();
                
                if(piocheDonjon.isEmpty()){
                    System.out.println("\n\n\n*****\nPlus rien dans la pioche, on rÃ©cupÃ¨re la dÃ©fausse !\n*****\n\n\n");
                    piocheDonjon.setPioche(new ArrayList<Donjon> (defausseDonjon.getDefausse()));
                    defausseDonjon.vider();
                }
                
                if(piocheDonjon.isEmpty()){
                    System.out.println("Apparemment, il y avait rien dans la dÃ©fausse... Du coup, ciao !");
                    return;
                }
                System.out.println("Vous ouvrez la porte du donjon...");
                
                cartePiochee = (Carte) piocheDonjon.tirerCarte(); 
                if(cartePiochee == null){
                    System.out.println("ProblÃ¨me lors du tirage dans la pioche donjon");
                    return;
                }
                
                System.out.println("\n\n" + enCours.getName() + " (Niveau "+ enCours.getPersonnage().getNiveau() + ") : ");
                //envoi du message a tous les client connectÃ©
                this.sendMessageToAll("Le joueur : " +enCours.getName() + " pioche une carte ! : \n");
                this.sendCarteEnCoursToAll(cartePiochee);
                
                // === MONSTRE ===
                if(cartePiochee.getClass().equals(Monstre.class))
                {
                    this.sendSongToAll(Constante.SOUND_MONSTREFORT);
                    Combat combat = new Combat(this);
                    combat.getCampGentil().add(enCours.getPersonnage());
                    
                    Monstre monstrePioche = (Monstre) cartePiochee;

                    combat.getCampMechant().add(monstrePioche);
                    
                    System.out.println("Vous avez tirÃ© le monstre :");
                    System.out.println(monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")");
                    System.out.println(monstrePioche.getDescription());

                    /**
                     * On applique la condition du monstre
                     */
                    this.sendMessageToAll(monstrePioche.appliquerCondition(enCours));

                    System.out.println("Combattre ? (o/n)");
                    this.sendMessageToAll("Le joueur : " +enCours.getName() + " a tirÃ© le monstre : \n"
                            + monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")\n"
                            + monstrePioche.getDescription() +"\n"
                            +" Va-t il combattre ?\n");
                    this.sendQuestionToEnCours("Combattre ?");
                    this.answer=null;
                   
                    while( this.answer==null ){
                        try {
                           Thread.currentThread().sleep(200);//sleep for 1000 ms
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }                    
                    	
	                    if(this.answer.equals("Yes")){
	                    	// Si le joueur gagne le combat, on lance MonstreVaincu pour connaitre
	                    	// le nb de niveau gagnÃ© et les cartes trÃ©sors qu'il peut tirer
	                        if(combat.combattre()){
	                            System.out.println("Vous avez gagnÃ© !");
	                            this.sendMessageToAll(monstrePioche.appliquerMonstreVaincu(enCours));
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a gagnÃ© le combat ! \n");
                                    this.sendSongToAll(Constante.SOUND_COMBATGAGNE);
                                            
	                        }else{
	                            System.out.println("Vous avez perdu...");
                                    this.sendMessageToAll(monstrePioche.appliquerIncidentFacheux(enCours));
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a perdu le combat ! \n");
                                    this.sendSongToAll(Constante.SOUND_COMBATPERDU);
	                            
	                        }
	                    }else if(this.answer.equals("Non")){
	                        if(combat.tenterDeguerpir()){
	                            System.out.println("Vous avez rÃ©ussi Ã  dÃ©guÃ©rpir !");
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + " a rÃ©ussi a deguerpir ! \n");
	                        }
	                        else{
                                    this.sendMessageToAll(monstrePioche.appliquerIncidentFacheux(enCours));
	                            this.sendMessageToAll("Le joueur : " +enCours.getName() + " n'a pas rÃ©ussi a deguerpir ! \n");
                                    this.sendSongToAll(Constante.SOUND_INCIDENTFACHEUX);
	                        }
	                    }
	                    else
	                    {
	                    	System.out.println("Veuillez entrer une rÃ©ponse correcte");
	                    }
                    // On boucle tant qu'il n'a pas donnÃ© de rÃ©ponse
                    
	               monstrePioche.setBonusPuissance(0);
	               this.defausseDonjon.ajouterCarte(monstrePioche);
                   this.sendInfosJoueursToAll();
                   this.sendCartesJeuxJoueursToAll();
                   this.sendCartesMainToOwner();
                }
                // ===============
                
                // ==== SORT ====
                else if(cartePiochee.getClass().equals(Sort.class)){
                	Sort sort = (Sort) cartePiochee;
                    //System.out.println("C'est un sort !!");
                    //this.sendMessageToAll("C'est un sort !!\n");
                	this.sendMessageToAllButCurrent("Le joueur "+enCours.getName()+" vient de piocher une carte Sort !");
                	this.sendMessageToAllButCurrent("Que va-t-il faire ?\n");
                	
                	this.sendMessageToCurrent("Vous venez de piocher la carte Sort : ");
                	this.sendMessageToCurrent(sort.getDescription());
                	this.sendMessageToCurrent("Qu'allez vous faire ?");
                	this.sendMessageToCurrent("Pour l'utiliser maintenant, appuyez sur le bouton Utiliser, sinon (TODO)\n");
                	
                	//TODO : Gestion du clic sur le bouton intervention et du choix de ne pas utiliser le sort maintenant
                	// On fait l'utilisation
                	this.sendMessageToAllButCurrent("Il utilise la carte !! Tremblez, pauvres fous !\n");
                	this.sendMessageToCurrent("Vous avez choisi d'utiliser la carte sur vous (TODO : choix personne)!\n");
                	
                	this.sendMessageToAll(sort.appliquerSortilege(enCours));
                }
                
                // ==============
                
                // On annule les bonus temporaires
                this.enCours.getPersonnage().setBonusCapaciteFuite(0);
                this.enCours.getPersonnage().setBonusPuissance(0);
            }
            it = this.iterator();
        }
    }
 
     

    
}
