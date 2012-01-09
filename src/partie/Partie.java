package partie;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import carte.Sort;
import carte.Tresor;
import communication.Message;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import joueur.Joueur;
import joueur.Personnage;

/**
 * Classe principale du jeu.
 * Gére l'ensemble du déroulement de la partie.
 * Cette classe est à la fois une liste de joueur & un thread   // TODO : A vérifier
 * @author Julien Rouvier
 */
public final class Partie extends ArrayList<Joueur> implements Runnable{
	
    private Deck             deck;
    private Pioche<Tresor>   piocheTresor;
    private Pioche<Donjon>   piocheDonjon;
    private Defausse<Tresor> defausseTresor;
    private Defausse<Donjon> defausseDonjon;
    private Joueur           enCours;
    private Color            Color;
    private String           answer;

    /**
     * Constructeur
     */
    public Partie(){
        piocheDonjon   = new Pioche<Donjon>(Constante.DONJON);
        piocheTresor   = new Pioche<Tresor>(Constante.TRESOR);
        defausseDonjon = new Defausse<Donjon>();
        defausseTresor = new Defausse<Tresor>();       
        deck           = new Deck();
    }
    
    /**
     * Constructeur
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

    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public Defausse getDefausseDonjon() {
        return defausseDonjon;
    }

    public void setDefausseDonjon(Defausse defausseDonjon) {
        this.defausseDonjon = defausseDonjon;
    }

    public Defausse getDefausseTresor() {
        return defausseTresor;
    }

    public void setDefausseTresor(Defausse defausseTresor) {
        this.defausseTresor = defausseTresor;
    }

    public Pioche<Donjon> getPiocheDonjon() {
        return piocheDonjon;
    }
    
    public void setPiocheDonjon(Pioche<Donjon> piocheDonjon) {
        this.piocheDonjon = piocheDonjon;
    }
    
    public void setPiocheTresor(Pioche<Tresor> piocheTresor) {
        this.piocheTresor = piocheTresor;
    }

    public Pioche<Tresor> getPiocheTresor() {
        return piocheTresor;
    }
    // ================================== //
    
    
    
    /**
     * Distribue les cartes aux joueurs au début de la partie
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
     * // TODO : Commenter
     * @param nick_dest
     * @return 
     */
    public Integer getCommunication(String nick_dest){
        int i = 0;
        int j = 0;
        while(i<this.size()){
            if(this.get(i).getName().equals(nick_dest)){
                j = i;
                i = this.size() +1;
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
     * Renvoi un message à l'utilisateur qui l'a lui même envoyé // TODO : A vérifier
     * @param source
     * @param txt 
     */
    public void sendMessageBackToSender(String source,String txt){
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
        this.getJoueurByName(source).sendMessage(msg);        
    }
    
    
    /**
     * Envoi une question au joueur en cours (celui dont c'est le tour de jouer)
     * @param txt 
     */
    public void sendQuestionToEnCours(String txt){
        Message msg=new Message(Message.QUESTION,"Partie",this.enCours.getName(),txt);
        this.enCours.sendMessage(msg);
    }
    
    
    /**
     * Envoi les infos de chaque joueurs à tous les joueurs
     */
    public void sendInfosJoueursToAll(){
         for(Joueur j :this)
             for(Joueur j2 :this)
                j.sendMessage(new Message(Message.INFO_JOUEUR, "Partie", j2.getName(), j2.generateInfos()));
     }
    
    /**
     * 
     */
    public void sendInfosCampsToAll(Combat c){
         for(Joueur j :this)            
            j.sendMessage(new Message(Message.INFO_CAMPS, "Partie", j.getName(), c.generateInfos()));
     }
    
    
    /**
     * Envoi le jeu de chaque joueur à tous les joueurs
     */
    public void sendCartesJeuxJoueursToAll(){
        for(Joueur j :this)
            for(Joueur j2 :this)
                j.sendMessage(new Message
                            (Message.JEUX_JOUEUR, "Partie", j2.getName(), j2.getJeu().generateInfos()));
     }
    
    
    /**
     * Envoi les cartes de la main à son propriétaire
     */
    public void sendCartesMainToOwner(){
         for(Joueur j :this)             
             j.sendMessage(new Message
                            (Message.MAIN_JOUEUR, "Partie", j.getName(), j.getMain().generateInfos()));
     }
    
    
    /**
     * // TODO : Commenter
     * @param carte 
     */
    public void sendCarteEnCoursToAll(Carte carte){
         for(Joueur j :this)             
             j.sendMessage(new Message
                            (Message.CARTE_EN_COURS, "Partie", j.getName(), carte.getId().toString()));
     }
    
    
    /**
     * Envoi un son à tous les joueurs
     * @param constante 
     */
    public void sendSongToAll(int constante){
         for(Joueur j :this)
             j.sendMessage(new Message(Message.SOUND, "Partie", j.getName(), constante));
     }
    
    
    /**
     * Intervention d'un joueur dans un combat
     * @param msg 
     */
    public void intervenir(Message msg){
        switch(msg.getAction()){
            case Constante.ACTION_DEFAUSSER:
                if(!msg.getIdCard().equals("")){
                    Integer id = new Integer(msg.getIdCard());
                    this.getJoueurByName(msg.getNick_src()).defausserCarte(Deck.getCardById(id));
                    this.sendCartesJeuxJoueursToAll();
                    this.sendCartesMainToOwner();                    
                } else {
                    this.sendMessageToAllButSender(msg.getNick_src(), msg.getNick_src()+" souhaite defausser une carte ! ");
                    this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à Defausser");
                   /**
                    * TODO
                    * Ici on va executer un algo qui va regarder les cartes du joueur et lui renvoyer les cartes qu'il peut défausser.
                    * Pour le moment on envoi l'ensemble des cartes de la main
                    * TODO 2 : Le joueur ne peut-il pas se défausser de la carte qu'il veut ???
                    */
                    this.getJoueurByName(msg.getNick_src()).sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
                    this.getJoueurByName(msg.getNick_src()).getMain().generateInfos()));
                }
                break;
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
                    
                    this.getJoueurByName(msg.getNick_src()).sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
                                this.getJoueurByName(msg.getNick_src()).getMain().getCartesPosables()));

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
     * Aider un joueur dans un combat
     * @param msg 
     */
    public void aider(Message msg){

    }
    
    
    /**
     * Pourrir un joueur dans un combat
     * @param msg 
     */
    public void pourrir(Message msg){

    }
     
    
    /**
     * Poser une carte (transfert de la main au jeu)
     * @param msg 
     */
    // TODO : Vérifier l'utilité de la méthode
    public void poserCarte(Message msg){

    }
    
    
    /**
     * // TODO : Commenter
     * @param msg
     * @return 
     */
    public boolean answer(Message msg){
        this.answer = msg.getMessage();      
        return true;     
    }    
 
   
    /**
     * // TODO : Commenter
     */
    @Override
    public void run() {    	
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
     * Initialisation de la partie
     */
    private void init() {
        piocheDonjon.init(this.deck);       // Les cartes donjon sont mises dans la pioche donjon
        piocheTresor.init(this.deck);       // Les cartes trésor sont mises dans la pioche trésor
        this.distribuer();                  // Distribution des cartes aux joueurs (4 de chaque)
        this.sendInfosJoueursToAll();       // Echanges des infos joueurs
        this.sendCartesJeuxJoueursToAll();  // Echanges des jeux des joueurs
        this.sendCartesMainToOwner();       // Envoi sa main à chaque joueur
    }

    
    /**
     * Un tour de jeu d'un joueur
     */
    private void tour() {
        // TODO : Phases du tour de jeu
	    	
    		// Chercher la bagarre (pas dans specs)
    			// Jouer un monstre de la main
    			// Combattre
    		// Piller la pièce
    			// Tué monstre ou pas rencontré monstre => Tirer deuxième carte paquet donjon
    			// Sinon pas le droit
    		// Charité
    			// Trop de cartes en main => Défausse
    	// Deguerpir
        Iterator it = this.iterator();
        Carte cartePiochee = null;
        // Pour chaque joueur
        while(it.hasNext()){            	
            enCours = (Joueur) it.next();

            // On test si la piocheDonjon et la piocheTresor est vide
            testPioche(piocheDonjon);
            testPioche(piocheTresor);

            this.sendMessageToAll("Vous ouvrez la porte du donjon...");
            cartePiochee = phaseOuvrirPorte();

            // === MONSTRE ===
            if(cartePiochee.getClass().equals(Monstre.class))
            {
                ChercherLaBagarre();
                this.sendSongToAll(Constante.SOUND_MONSTREFORT);
                Combat combat = new Combat(this);
                combat.getCampGentil().add(enCours.getPersonnage());

                Monstre monstrePioche = (Monstre) cartePiochee;

                combat.getCampMechant().add(monstrePioche);

                System.out.println("Vous avez tiré le monstre :");
                System.out.println(monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")");
                System.out.println(monstrePioche.getDescription());
                this.sendInfosCampsToAll(combat);

                /**
                 * On applique la condition du monstre
                 */
                this.sendMessageToAll(monstrePioche.appliquerCondition(enCours, null, null));

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
                        this.sendMessageToAll(monstrePioche.appliquerMonstreVaincu(enCours, null, null));
                        this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a gagné le combat ! \n");
                        this.sendSongToAll(Constante.SOUND_COMBATGAGNE);
                    } else {
                        System.out.println("Vous avez perdu...");
                        this.sendMessageToAll(monstrePioche.appliquerIncidentFacheux(enCours, null, null));
                        this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a perdu le combat ! \n");
                        this.sendSongToAll(Constante.SOUND_COMBATPERDU);

                    }
                } else if(this.answer.equals("Non")){
                    deguerpir(combat);
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

                this.sendMessageToCurrent("On tente d'appliquer le sort sur vous tout de suite !\n");
                this.sendMessageToAll(sort.appliquerSortilege(enCours, null, null));
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
     * Si une pioche est vide, on tente de la remplacer par la défausse du même type (DONJON ou TRESOR)
     * @param pioche : la pioche à tester
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
                pioche.setPioche(new ArrayList<Pioche>(defausse.getDefausse()));
                defausse.vider();
            } else {
            	System.out.println("Apparemment, il y avait rien dans la défausse... Du coup, ciao !");
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

    
    /**
     * // TODO : Commenter
     * @param txt
     * @param personnage 
     */
    private void sendMessageToPlayer(String txt, Personnage personnage) {
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
        for(Joueur j : this)
            // On test si le joueur a bien le personnage passé en paramètre
            if(j.getPersonnage().equals(personnage))
                j.sendMessage(msg);
    }
    
    
    /**
     * // TODO : Commenter
     * @param txt
     * @param personnage 
     */
    private void sendMessageToAllButPlayer(String txt, Personnage personnage) {
        Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.GREEN);
        for(Joueur j : this)
            // On test si le joueur n'a pas le personnage passé en paramètre
            if(!j.getPersonnage().equals(personnage))
                j.sendMessage(msg);
    }

    /**
     * Méthode permettant de faire le déroulement de la phase déguerpir
     * @param combat
     */
    private void deguerpir(Combat combat) {
        HashMap<Boolean, ArrayList<Personnage>> dicoFuite;

        dicoFuite = combat.tenterDeguerpir();

        // Les joueurs qui ont réussi à fuir sont tranquilles
        for(Personnage persoReussi : dicoFuite.get(Boolean.TRUE))
            sendMessageToPlayer("Vous avez réussi à déguerpir !", persoReussi);

        // Les joueurs qui n'ont pas réussi à fuir se prennent des incident fâcheux
        for(Personnage persoEchec : dicoFuite.get(Boolean.FALSE)){
            sendMessageToPlayer("Vous n'avez pas réussi à déguerpir !", persoEchec);
            sendMessageToAllButPlayer(persoEchec + " n'a pas réussi à déguerpir", persoEchec);
            for(Monstre monstre : combat.getCampMechant())
                this.sendMessageToAll(monstre.appliquerIncidentFacheux(enCours, null, null));
            this.sendSongToAll(Constante.SOUND_INCIDENTFACHEUX);
        }
    }


    // Ouvrir une porte : Tirer carte Donjon
		    	// => Si Monstre => Chercher la bagarre
		    	// => Si Sort => Agit sur le joueur si possible => Défaussée
		    	// => Autre type => Jouer ou mettre dans main
    private Carte phaseOuvrirPorte() {
        Carte cartePiochee;

        cartePiochee = (Carte) piocheDonjon.tirerCarte();

        if(cartePiochee == null){
            System.out.println("Problème lors du tirage dans la pioche donjon");
            return null;
        }

        System.out.println("\n\n" + enCours.getName() + " (Niveau "+ enCours.getPersonnage().getNiveau() + ") : ");
        //envoi du message a tous les client connectÃ©
        this.sendMessageToAll(enCours.getName() + " pioche une carte ! : \n");
        this.sendCarteEnCoursToAll(cartePiochee);

        if(cartePiochee.getClass().equals(Sort.class))
        {
            cartePiochee = (Sort) cartePiochee;
            cartePiochee.appliquerSortilege(enCours, new Exception().getStackTrace(), enCours);
        }

        return cartePiochee;
    }

    private void ChercherLaBagarre() {
        //throw new UnsupportedOperationException("Not yet implemented");
    }
}
