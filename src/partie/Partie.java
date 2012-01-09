package partie;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import carte.Sort;
import carte.Tresor;
import communication.Message;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import jeu.Main;
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
        this.piocheTresor   = piocheTresor;
        this.piocheDonjon   = piocheDonjon;
        this.defausseTresor = defausseTresor;
        this.defausseDonjon = defausseDonjon;        
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
     * Début d'un tour
     * @return boolean
     */
    public boolean commencerTour(){
        Donjon enJeu = (Donjon) piocheDonjon.tirerCarte();
        return true;
    }

    
    /**
     * Retourne la défausse des cartes donjon
     * @return 
     */
    public Defausse getDefausseDonjon() {
        return defausseDonjon;
    }

    
    /**
     * Défini la défausse des cartes donjon
     * @param defausseDonjon 
     */
    public void setDefausseDonjon(Defausse defausseDonjon) {
        this.defausseDonjon = defausseDonjon;
    }

    
    /**
     * Retourne la défausse des cartes trésor
     * @return 
     */
    public Defausse getDefausseTresor() {
        return defausseTresor;
    }

    
    /**
     * Défini la défausse des cartes trésor
     * @param defausseTresor 
     */
    public void setDefausseTresor(Defausse defausseTresor) {
        this.defausseTresor = defausseTresor;
    }

    
    /**
     * Retourne la pioche de carte donjon
     * @return 
     */
    public Pioche<Donjon> getPiocheDonjon() {
        return piocheDonjon;
    }
    
    
    /**
     * Défini la pioche des cartes donjon
     * @param piocheDonjon 
     */
    public void setPiocheDonjon(Pioche<Donjon> piocheDonjon) {
        this.piocheDonjon = piocheDonjon;
    }
    
    
    /**
     * Défini la pioche des cartes trésor
     * @param piocheTresor 
     */
    public void setPiocheTresor(Pioche<Tresor> piocheTresor) {
        this.piocheTresor = piocheTresor;
    }

    
    /**
     * Retourne la pioche des cartes trésor
     * @return 
     */
    public Pioche<Tresor> getPiocheTresor() {
        return piocheTresor;
    }

    
    /**
     * Supprime un joueur grâce à son nom
     * @param name 
     */
    public void removeJoueurByName(String name){
        for(Joueur j : this)
            if(j.getName().equals(name))
                this.remove(j);
    }
    
    
    /**
     * Retourne un joueur grâce à son nom
     */
    public Joueur getJoueurByName(String name){
        Joueur ret = null;
        for(Joueur j : this)
            if(j.getName().equals(name))
                ret=j;
        return ret;
    }
    
    
    /**
     * Verifie qu'un login est disponible dans la partie
     * @param log
     * @return 
     */
    public boolean LoginDispo(String log){
        String l = getListe();
        boolean k =true;
        StringTokenizer l2=new StringTokenizer(l,";");
        while(l2.hasMoreTokens())
            try{
                if(l2.nextToken().equals(log))
                    k = false;
            } catch(Exception e){
                System.out.println("Exception :" + e.toString());
            }
        return k;
    }
    
    
    /**
     * Renvoi la liste de nom des joueurs
     * @return 
     */
    public String getListe(){
        String liste="";
        for(int i=0;i<size();i++)
            liste+=get(i).getName() + ";";
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
            }else{
                i++;
                j--;
            }
        }
        return j;
    }
    
    
    /**
     * Envoi un message à l'ensemble des joueurs de la partie
     * @param txt 
     */
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
            if(!j.equals(enCours))
                    j.sendMessage(msg);
    }
    
    
     /**
     * Fonction permettant d'envoyer un message Ã  tous joueurs sauf le courant
     * @param txt
     */
    public void sendMessageToAllButSender(String source ,String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.GREEN);
        for(Joueur j : this)
            if(!j.equals(this.getJoueurByName(source)))
                j.sendMessage(msg);
    }
    
    
    /**
     * Fonction permettant d'envoyer un message au joueur en cours
     * @param txt
     */
    public void sendMessageToCurrent(String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
        enCours.sendMessage(msg);
    }
    
    
    /**
     * 
     * @param source
     * @param txt 
     */
    public void sendMessageBackToSender(String source,String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
        this.getJoueurByName(source).sendMessage(msg);        
    }
    
    
    /**
     * 
     * @param txt 
     */
    public void sendQuestionToEnCours(String txt){
        Message msg=new Message(Message.QUESTION,"Partie",this.enCours.getName(),txt);
        this.enCours.sendMessage(msg);
    }
    
    
    /**
     * 
     */
    public void sendInfosJoueursToAll(){
         for(Joueur j :this)
             for(Joueur j2 :this)
                j.sendMessage(new Message(Message.INFO_JOUEUR, "Partie", j2.getName(), j2.generateInfos()));
     }
    
    
    /**
     * 
     */
    public void sendCartesJeuxJoueursToAll(){
        for(Joueur j :this)
            for(Joueur j2 :this)
                j.sendMessage(new Message
                       (Message.JEUX_JOUEUR, "Partie", j2.getName(), j2.getJeu().generateInfos()));
     }
    
    
    /**
     * 
     */
    public void sendCartesMainToOwner(){
         for(Joueur j :this)             
             j.sendMessage(new Message
                     (Message.MAIN_JOUEUR, "Partie", j.getName(), j.getMain().generateInfos()));
     }
    
    
    /**
     * 
     * @param carte 
     */
    public void sendCarteEnCoursToAll(Carte carte){
         for(Joueur j :this)             
             j.sendMessage(new Message
                     (Message.CARTE_EN_COURS, "Partie", j.getName(), carte.getId().toString()));
     }
    
    
    /**
     * 
     * @param constante 
     */
    public void sendSongToAll(int constante){
         for(Joueur j :this)
             j.sendMessage(new Message(Message.SOUND, "Partie", j.getName(), constante));
     }
    
    
    /**
     * 
     * @param msg 
     */
    public void intervenir(Message msg){
         switch(msg.getAction()){
            case Constante.ACTION_AIDER:
                 if(!msg.getIdCard().equals("")){
                     
                 } else {
                     this.sendMessageToAllButSender(msg.getNick_src(), msg.getNick_src()+" souhaite aider "+this.enCours.getName());
                     this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à poser");
                 }
                 break;
            case Constante.ACTION_POSERCARTE:
                if(!msg.getIdCard().equals("")){ //Le joueur a envoyé la carte
                    Integer id= new Integer(msg.getIdCard());
                    this.getJoueurByName(msg.getNick_src()).getJeu().ajouterCarte(Deck.getCardById(id));
                    this.getJoueurByName(msg.getNick_src()).getMain().supprimerCarte(Deck.getCardById(id));
                    this.sendCartesJeuxJoueursToAll();
                    this.sendCartesMainToOwner(); 
                } else {   //Le joueur informe qu'il veut poser une carte
                    this.sendMessageToAllButSender(msg.getNick_src(), msg.getNick_src()+" souhaite poser une carte");
                    this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à  poser");
                    /**
                    * TODO
                    * Ici on va executer un algo qui va regarder les cartes du joueur et lui renvoyer les cartes qu'il peut jouer.
                    * Pour le moment on envoi juste une liste de carte modifier on envoi une carte sur 
                    * deux de la main du joueur grace a la methode generateFalseInfo
                    */                     

                    this.getJoueurByName(msg.getNick_src()).sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
                    this.getJoueurByName(msg.getNick_src()).getMain().generateFalseInfos()));

                }
                break;
            case Constante.ACTION_POURRIR:
                if(!msg.getIdCard().equals("")){
                     
                }
                else{
                    this.sendMessageToAllButSender(msg.getNick_src(),"Le joueur :" +msg.getNick_src()+" souhaite pourrir le joueur "+this.enCours.getName());
                    this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à poser");
                }
                break;
            }
     }
    
    /**
     * 
     * @param msg 
     */
    public void aider(Message msg){

    }
    
    
    /**
     * 
     * @param msg 
     */
    public void pourrir(Message msg){

    }
     
    
    /**
     * 
     * @param msg 
     */
    public void poserCarte(Message msg){

    }
    
    
    /**
     * 
     * @param msg
     * @return 
     */
    public boolean answer(Message msg){
        this.answer=msg.getMessage();      
        return true;     
    }    
 
   
    /**
     * 
     */
    @Override
    public void run() {
    	// TODO : Phases du tour de jeu
	    	// Ouvrir une porte : Tirer carte Donjon 
		    	// => Si Monstre => Combat ou Déguerpir
		    	// => Si Sort => Agit sur le joueur si possible => Défaussée
		    	// => Autre type => Jouer ou mettre dans main
    		// Chercher la bagarre (pas dans specs)
    			// Jouer un monstre de la main
    			// Combattre
    		// Piller la pièce
    			// Tué monstre ou pas rencontré monstre => Tirer deuxième carte paquet donjon
    			// Sinon pas le droit
    		// Charité
    			// Trop de cartes en main => Défausse
    	// TODO : Deguerpir
    	
        // Initialisation de la partie       
        init();
        
        while(true){
            // Si la partie est terminée, on stop le jeu
            if(PartieTerminee()) {
                // TODO Faire ce qu'il se passe en fin de partie
                finPartie();
                break;
            }
            tour();
        }
    }

    
    /**
     * 
     */
    private void init() {
        piocheDonjon.init(this.deck);
        piocheTresor.init(this.deck);       
        this.distribuer();
        this.sendInfosJoueursToAll();
        this.sendCartesJeuxJoueursToAll();
        this.sendCartesMainToOwner();
    }

    
    /**
     * 
     */
    private void tour() {
        Iterator it = this.iterator(); 
        Carte cartePiochee;
        // Pour chaque joueur
        while(it.hasNext()){            	
            enCours = (Joueur) it.next();

            testPioche(piocheDonjon);
            testPioche(piocheTresor);

            System.out.println("Vous ouvrez la porte du donjon...");

            cartePiochee = (Carte) piocheDonjon.tirerCarte(); 

            if(cartePiochee == null){
                System.out.println("Problème lors du tirage dans la pioche donjon");
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

                System.out.println("Vous avez tiré le monstre :");
                System.out.println(monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")");
                System.out.println(monstrePioche.getDescription());

                /**
                 * On applique la condition du monstre
                 */
                this.sendMessageToAll(monstrePioche.appliquerCondition(enCours));

                System.out.println("Combattre ? (o/n)");
                this.sendMessageToAll("Le joueur : " +enCours.getName() + " a tiré le monstre : \n"
                        + monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")\n"
                        + monstrePioche.getDescription() +"\n"
                        +" Va-t il combattre ?\n");
                this.sendQuestionToEnCours("Combattre ?");
                this.answer=null;

                while( this.answer==null )
                    try {
                       Thread.currentThread().sleep(200);//sleep for 200 ms
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                    }                 

                if(this.answer.equals("Yes")){
                    // Si le joueur gagne le combat, on lance MonstreVaincu pour connaitre
                    // le nb de niveau gagnÃ© et les cartes trÃ©sors qu'il peut tirer
                    if(combat.combattre()){
                        System.out.println("Vous avez gagné !");
                        this.sendMessageToAll(monstrePioche.appliquerMonstreVaincu(enCours));
                        this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a gagné le combat ! \n");
                        this.sendSongToAll(Constante.SOUND_COMBATGAGNE);
                    } else {
                        System.out.println("Vous avez perdu...");
                        this.sendMessageToAll(monstrePioche.appliquerIncidentFacheux(enCours));
                        this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a perdu le combat ! \n");
                        this.sendSongToAll(Constante.SOUND_COMBATPERDU);

                    }
                } else if(this.answer.equals("Non")){
                    if(combat.tenterDeguerpir()){
                        System.out.println("Vous avez réussi Ã  déguèrpir !");
                        this.sendMessageToAll("Le joueur : " +enCours.getName() + " a réussi a deguerpir ! \n");
                    }
                    else{
                        this.sendMessageToAll(monstrePioche.appliquerIncidentFacheux(enCours));
                        this.sendMessageToAll("Le joueur : " +enCours.getName() + " n'a pas réussi a deguerpir ! \n");
                        this.sendSongToAll(Constante.SOUND_INCIDENTFACHEUX);
                    }
                }
                else {
                    System.out.println("Veuillez entrer une réponse correcte");
                }
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

    
    /**
     * Méthode permettant de tester si la pioche est vide ou pleine
     */
    private void testPioche(Pioche pioche) {
        Defausse defausse;
        // Si la pioche passée en paramètre est vide, on récupère la défausse
        if(pioche.isEmpty())
        {
            System.out.println("\n\n\n*****\nPlus rien dans la pioche, on récupère la défausse !\n*****\n\n\n");
            if(pioche.equals(piocheDonjon))
                    defausse = defausseDonjon;
            else
                    defausse = defausseTresor;
            
            if(defausse.isEmpty()){
            	// TODO : Vérification fonctionnement de cette ligne
                pioche.setPioche(new ArrayList<Pioche>(defausse.getDefausse()));
                defausse.vider();
            } else {
            	System.out.println("Apparemment, il y avait rien dans la défausse... Du coup, ciao !");
            	// TODO : Vérifier si la partie est terminée
            	finPartie();
            }
        }
    }

    
    /**
     * Méthode appelée lorsque la partie est terminée
     */
    private void finPartie() {
        // TODO Fin de partie
        this.SendDebugMessage("La partie est terminée !!");
    }

    
    /**
     * Méthode permettant d'envoyer un message de debug (sur out) + à tous les joueurs
     * @param string : Message à envoyer
     */
    private void SendDebugMessage(String string) {
            System.out.println(string);
            this.sendMessageToAll(string);
    }

    
    /**
     * Méthode appelée pour tester si la partie est terminée
     * @return Vrai : La partie est terminée, 
     * Faux : La partie n'est pas terminée
     */
    private boolean PartieTerminee() {
        if(enCours != null){
            if(enCours.getPersonnage().getNiveau() >= 10){
                this.sendMessageToAll("La partie est terminée !!\n Le joueur "+enCours.getName()+" est passé niveau 10 !!");
                return true;
            }
    	} else {
            System.out.println("Joueur en cours est null");
    	}
        return false;
    }
}
