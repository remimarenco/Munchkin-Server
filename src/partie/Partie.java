package partie;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import carte.Objet;
import carte.Malediction;
import carte.Sort;
import carte.Tresor;
import communication.Message;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import joueur.CartesJoueur;
import joueur.Jeu;
import joueur.Joueur;
import joueur.Main;
import joueur.Personnage;

/**
 * Classe principale du jeu.
 * Gére l'ensemble du déroulement de la partie.
 * Cette classe est à la fois une liste de joueur & un thread
 * @author All
 */
public final class Partie extends ArrayList<Joueur> implements Runnable{

	private Deck             deck;
	private Pioche<Tresor>   piocheTresor;
	private Pioche<Donjon>   piocheDonjon;
	private Defausse<Tresor> defausseTresor;
	private Defausse<Donjon> defausseDonjon;
	private Joueur           enCours;
	private Color            Color;	
	private int              phaseTour;
	private Combat           combat;
        private String           campCible;
        private Joueur           joueurIntervenant=null;
        private Joueur           joueurCible=null;   
        
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

        public Joueur getJoueurCible() {
            return joueurCible;
        }

        public void setJoueurCible(Joueur joueurCible) {
            this.joueurCible = joueurCible;
        }

	public Pioche<Donjon> getPiocheDonjon() {
		return piocheDonjon;
	}

        public String getCampCible() {
            return campCible;
        }

        public void setCampCible(String campCible) {
            this.campCible = campCible;
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
	
	/**
	 * @return the combat
	 */
	public Combat getCombat() {
		return combat;
	}

	/**
	 * @param combat the combat to set
	 */
	public void setCombat(Combat combat) {
		this.combat = combat;
	}

	public Joueur getEnCours() {
		return enCours;
	}

	public void setEnCours(Joueur enCours) {
		this.enCours = enCours;
	}

	/**
         * Méthode permettant de retourner l'entier correspondant à la phase du tour
	 * @return the phaseTour
	 */
	public int getPhaseTour() {
		return phaseTour;
	}

	/**
	 * @param phaseTour the phaseTour to set
	 */
	public void setPhaseTour(int phaseTour) {
		this.phaseTour = phaseTour;
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
				// On désactive la pioche de carte Monstre pour le moment
				// TODO : Réactiver
				Carte carte = piocheDonjon.tirerCarte();
				if(!carte.getClass().equals(Monstre.class))
				{
					j.getMain().ajouterCarte(carte);
					j.getMain().ajouterCarte((Carte) piocheTresor.tirerCarte());
				}
				// On est bon pour refaire un tour et on remet la carte dans la pioche
                                else
				{
					piocheDonjon.poserEnDessousPioche((Donjon)carte);
					i--;
				}			
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
	public boolean loginDispo(String log){
                boolean ret=true;
		LinkedHashMap<String,JLabel> l = getListe();
                for(Map.Entry<String,JLabel> m :l.entrySet())
                    if(m.getKey().equals(log) || log.equals("Partie"))
                        ret=false;
                
                return ret;	
	}


	/**
	 * Renvoi la liste de nom des joueurs
	 * @return 
	 */
	public LinkedHashMap<String,JLabel> getListe(){
            LinkedHashMap<String,JLabel> liste=new LinkedHashMap<String, JLabel>();
            for(Joueur j:this)                    
                liste.put(j.getName(),j.getAvatar());                    

            return liste;
	}


	/**
	 * Cette methode permet de renvoyer l'index du Thread joueur
	 * @param nick_dest
	 * @return -1 si le Joueur n'existe pas (en cas de deco)
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
		Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.ORANGE);
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
	 * Renvoi un message à l'utilisateur qui a initié l'echange
	 * @param source
	 * @param txt 
	 */
	public void sendMessageBackToSender(String source,String txt){
		Message msg=new Message(Message.MESSAGE,"Partie","Partie",txt,Color.BLUE);
		this.getJoueurByName(source).sendMessage(msg);        
	}
	/**
	 * Envoi un message à l'utilisateur indiquer par son nom 
	 * @param source
	 * @param txt 
	 */
	public void sendQuestionChoixJoueurBackTo(String source,String txt){
		Message msg=new Message(Message.CHOIXJOUEUR,"Partie","Partie",txt,Color.BLUE);
		this.getJoueurByName(source).sendMessage(msg);        
	}
	/**
	 * Envoi un message à l'utilisateur indiquer par son nom 
	 * @param source
	 * @param txt 
	 */
	public void sendQuestionChoixCAMPSBackTo(String source,String txt){
		Message msg=new Message(Message.CHOIXCAMP,"Partie","Partie",txt,Color.BLUE);
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
	 * Envoi les infos de chaque camp à tous les joueurs
	 */
	public void sendInfosCampsToAll(Combat c){
		if(c != null)
			for(Joueur j :this){
				j.sendMessage(new Message(Message.INFO_CAMPS, "Partie", j.getName(), c.generateInfos()));
			}
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
	 * Cette Methode permet d'envoyer la carte en cours, a tous les joueurs connectés
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
         * L'intervention est géré differement selon le type d'action
         * A chaque fois, l'on a deux cas : - le message contient un id de carte,--> traitement
         *                                  - le message contient pas d'id de carte --> on demande au joueur de choisir la carte
         * (envoi des cartes qu'il peut jouer)
	 * @param msg 
	 */
	public void intervenir(Message msg){
                Joueur emetteur=this.getJoueurByName(msg.getNick_src());
		switch(msg.getAction()){                
		case Constante.ACTION_DEFAUSSER:
			if(!msg.getIdCard().equals("")){
				Integer id = new Integer(msg.getIdCard());
				this.setCarteClickee(Deck.getCardById(id),emetteur);               
			} else {
				this.sendMessageToAllButSender(msg.getNick_src(), msg.getNick_src()+" souhaite defausser une carte ! ");
				this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à Defausser");                   
				emetteur.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
						emetteur.getMain().generateInfos()));
			}
			break;           
		case Constante.ACTION_DESEQUIPER:
			if(!msg.getIdCard().equals("")){
				Integer id = new Integer(msg.getIdCard());
				final Joueur j= emetteur;
				// On vérifie que la carte passée est un objet, puis on le déséquipe et on la supprime
				if(Deck.getCardById(id).getClass().equals(Objet.class))
				{
					Objet obj = (Objet) Deck.getCardById(id);
					obj.desequiper(j,new ArrayList<Joueur>(){{add(j);}}, this);
					emetteur.getMain().ajouterCarte(obj);
					emetteur.getJeu().supprimerCarte(obj);                                        
				}
				else
				{
					try {
						throw new Exception("Erreur dans intervenir, Carte passée non Objet");
					} catch (Exception e) {						
						e.printStackTrace();
					}
				}
				this.sendInfos();
                                if(joueurIntervenant!=null){                              
                                    joueurIntervenant.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie" ,
                                            joueurIntervenant.getName(), joueurIntervenant.getMain().generateInfos()));
                                    this.sendMessageBackToSender(joueurIntervenant.getName(),"Choisissez la carte pour intervenir");
                                }
			} else {
				this.sendMessageToAllButSender(msg.getNick_src(), msg.getNick_src()+" souhaite se desequiper d'une carte ! ");
				this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à Désequiper");                   
				emetteur.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
						emetteur.getJeu().generateInfos()));
			}
			break;           
		case Constante.ACTION_POSERCARTE:
			if(!msg.getIdCard().equals("")){ //Le joueur a envoyé la carte
				Integer id= new Integer(msg.getIdCard());
				emetteur.getJeu().ajouterCarte(Deck.getCardById(id));
                                this.defausserCarte(emetteur, emetteur.getMain(), Deck.getCardById(id));
				//emetteur.defausserCarte(Deck.getCardById(id));
				// Activation de la carte
				appliquerCartePoseMainSurJoueur(emetteur,Deck.getCardById(id));            
                                this.sendInfos();
                                if(joueurIntervenant!=null){                              
                                    joueurIntervenant.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie" ,
                                            joueurIntervenant.getName(), joueurIntervenant.getMain().generateInfos()));
                                    this.sendMessageBackToSender(joueurIntervenant.getName(),"Choisissez la carte pour intervenir");
                                }
                                if(joueurIntervenant!=null && joueurIntervenant.getName().equals(msg.getNick_src()) && this.phaseTour==Constante.PHASE_CHARITE_SIOUPLAIT ){
                                    joueurIntervenant.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie" ,
                                            joueurIntervenant.getName(), joueurIntervenant.getMain().generateInfos()));
                                    this.sendMessageBackToSender(joueurIntervenant.getName(),"Choisissez la carte a");
                                }
                                
			} else {   //Le joueur informe qu'il veut poser une carte
				this.sendMessageToAllButSender(msg.getNick_src(), msg.getNick_src()+" souhaite poser une carte");
				this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à  poser");                    
				emetteur.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
						emetteur.getMain().getCartesPosables()));

			}
			break;
		case Constante.ACTION_INTERVENIR:
			if(!msg.getIdCard().equals("")){
				Integer id = new Integer(msg.getIdCard());
			}
			else{
				this.sendMessageToAllButSender(msg.getNick_src(),"Le joueur :" +msg.getNick_src()+" souhaite pourrir le joueur "+this.enCours.getName());
				this.sendMessageBackToSender(msg.getNick_src(),"Choisissez la carte à utiliser pour POURRIR le joueur : "+this.enCours.getName());
				emetteur.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", msg.getNick_src(),
						emetteur.getMain().getCartesJouablePourIntervenir(this.phaseTour)));
			}
			break;
		// Si un joueur a envoyé un message d'intervention ACTION_CARTE_INTERVENTION_CHOISIE, on change la valeur de la carte cliquée
		case Constante.ACTION_CARTE_INTERVENTION_CHOISIE:
			if(!msg.getIdCard().equals("")){
				Integer id = new Integer(msg.getIdCard());
				this.setCarteClickee(Deck.getCardById(id),emetteur);
				this.sendInfos();                  
			} else {
				this.sendMessageBackToSender(msg.getNick_src(),"Aucune carte choisie");                   
			}
			break;
		case Constante.ACTION_CHOIX_CAMP:
			this.sendMessageToAll("On a recu le choix du camp");
			if(msg.getMessage().equals(""))
			{
				this.sendMessageToAll("Action choix camp est vide !");
			}
			else
			{
				campCible = msg.getMessage();
			}
			break;
		}
	}

	/**
         * Cette methode est appelé lorsqu' un joueur a repondu a une question posé par le serveur
         * Elle retourne le joueur qui a repondu
         * @param msg
         * @return 
         */
	public Joueur answer(Message msg){
                Joueur emetteur=this.getJoueurByName(msg.getNick_src());
		emetteur.setAnswer(msg.getMessage());
                if(this.phaseTour==Constante.PHASE_INIT)
                    this.sendMessageToAll("Le joueur : "+emetteur.getName()+" a indiqué qu'il est pret !");
		return emetteur;     
	}

	public void resetAnswers(){
		for(Joueur j:this) 
			j.setAnswer(null);
	}
        
        public Joueur onePlayerHasAnsweredExceptThose(ArrayList<Joueur> jList){;
            Joueur ret=null;
		for(Joueur j2:this)   
			if(!jList.contains(j2))
                            if(j2.getAnswer()!=null)
					ret=j2;
                return ret;
        }

	public boolean allPlayersAnsweredButThose(ArrayList<Joueur> jList){
		boolean ret=true;
		for(Joueur j2:this)   
			if(!jList.contains(j2))                    
				if(j2.getAnswer()==null)
					ret=false;
		return ret;
	}

	public boolean allPlayersAreReady(){
		boolean ret=true;
		for(Joueur j:this)            
			if(j.getAnswer()==null)
				ret=false;
		return ret;
	}


	/**
	 * Methode run du thread Partie
	 */
	@Override
	public void run() {    	
		// Initialisation de la partie       
		init();                
		while(true){
			try {
				tour();
			} catch (Exception e) {				
				e.printStackTrace();
			}
			// Si la partie est terminée, on stop le jeu
			if(PartieTerminee()) {
				// TODO Faire ce qu'il se passe en fin de partie
				finPartie();
				break;
			}			
		}
	}


	/**
	 * Initialisation de la partie
         * Attente que tous les joueurs aient clické sur pret.
	 */
	private void init() {
                this.phaseTour=Constante.PHASE_INIT;
		piocheDonjon.init(this.deck);       // Les cartes donjon sont mises dans la pioche donjon
		piocheTresor.init(this.deck);       // Les cartes trésor sont mises dans la pioche trésor
		this.distribuer();                  // Distribution des cartes aux joueurs (4 de chaque)
		this.sendInfos();
                for(Joueur j:this)
                    j.sendMessage(new Message(Message.INTERVENTION, "Partie",j.getName(),Constante.ACTION_PRET));
                
                this.sendMessageToAll("Vous pouvez posez des cartes dans votre Jeux, "
                        + "la partie demarrera lorsque tous les joueurs auront indiquer qu'ils sont prêts.");
                //on attend que tous les joueurs soient pret.
                
                while(!this.allPlayersAreReady()){
                    try {
                        Thread.sleep(200);                        
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
	}


	/**
	 * Un tour de jeu d'un joueur
	 * @throws Exception 
	 */
	private void tour() throws Exception {
		// Deguerpir
		Iterator it = this.iterator();
		Carte cartePiochee = null;
		// Pour chaque joueur
		while(it.hasNext()){            	
			enCours = (Joueur) it.next();

			// On test si la piocheDonjon et la piocheTresor est vide
			testPioche(piocheDonjon);
			testPioche(piocheTresor);
                        this.sendMessageToAllButCurrent(enCours.getName()+" ouvre la porte du donjon...");
			this.sendMessageToCurrent("Vous ouvrez la porte du donjon...");
			cartePiochee = phaseOuvrirPorte();

			// === MONSTRE ===
			if(cartePiochee.getClass().equals(Monstre.class))
			{
				// On cherche la bagarre, si on a gagné, on pille la pièce
				this.sendMessageToAll("Au combat !!");
				if(ChercherLaBagarre((Monstre)cartePiochee))
				{
					this.sendMessageToAll("Vous pillez la pièce !");
					PillerLaPiece();
				}
			}

			// On applique la phase Charité pour défausser les cartes
			Charite();
			// On annule les bonus temporaires
			annulationBonusTemporaire(cartePiochee);
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
            boolean ret=false;
		if(enCours != null){
			if(enCours.getPersonnage().getNiveau() >= 10){
				this.sendMessageToAll("La partie est terminée !!\n Le joueur "+enCours.getName()+" est passé niveau 10 !!");
				ret=true;
			}
		} else {
			System.out.println("Partie Terminee : Joueur en cours est null"); 
			ret = true;
		}
		return ret;
	}


	/**
	 * Méthode permettant d'envoyer des messages au joueur passé en paramètre
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
	 * Méthode permettant d'envoyer des messages à tous les joueurs sauf celui passé en paramètre
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
		for(Personnage persoReussi : dicoFuite.get(Boolean.TRUE)){
			sendMessageToPlayer("Vous avez réussi à déguerpir !", persoReussi);
			this.sendSongToAll(Constante.jouerSon(Constante.SOUND_DEGUERPIR));
		}

		// Les joueurs qui n'ont pas réussi à fuir se prennent des incident fâcheux
		for(Personnage persoEchec : dicoFuite.get(Boolean.FALSE)){
			sendMessageToPlayer("Vous n'avez pas réussi à déguerpir !", persoEchec);
			sendMessageToAllButPlayer(persoEchec + " n'a pas réussi à déguerpir", persoEchec);
			for(Monstre monstre : combat.getCampMechant())
				this.sendMessageToAll(monstre.appliquerIncidentFacheux(enCours, new ArrayList<Joueur>(){{add(enCours);}}, this));
			this.sendSongToAll(Constante.jouerSon(Constante.SOUND_INCIDENTFACHEUX));
		}
	}


	// Ouvrir une porte : Tirer carte Donjon
	// => Si Monstre => Chercher la bagarre
	// => Si Sort => Agit sur le joueur si possible => Défaussée
	// => Autre type => Jouer ou mettre dans main
	private Carte phaseOuvrirPorte() throws Exception {
		this.sendMessageToAll("Changement de phase : "+getPhaseTourString()+" => "+getPhaseTourString(Constante.PHASE_OUVRIR_PORTE));
		this.setPhaseTour(Constante.PHASE_OUVRIR_PORTE);
		Carte cartePiochee;
		
		cartePiochee = (Carte) piocheDonjon.tirerCarte();

		if(cartePiochee == null){
			System.out.println("Problème lors du tirage dans la pioche donjon");
			return null;
		}

		System.out.println("\n\n" + enCours.getName() + " (Niveau "+ enCours.getPersonnage().getNiveau() + ") : ");
		//envoi du message a tous les client connectÃ©
		
                this.sendMessageToAllButCurrent("Avant que le joueur "+enCours.getName()+" ne pioche une carte, vous pouvez intervenir");
                this.sendMessageToCurrent("Avant de piocher une carte, vous pouvez intervenir");
		// Avant que le joueur pioche, on peut intervenir
		// Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
		if(this.size() != 1)
		{
			// On applique le sort sans que le joueur n'ait pu faire quelque chose
			demanderIntervenir(new ArrayList<Joueur>());
		}
		this.sendMessageToAll(enCours.getName() + " pioche une carte ! : \n");
		this.sendCarteEnCoursToAll(cartePiochee);
		
		if(cartePiochee.getClass().equals(Malediction.class)){
			Malediction carteMaledictionPiochee = (Malediction) cartePiochee;
			this.sendMessageToAll("C'est un sort !!\n");
			this.sendMessageToAllButCurrent("Le joueur "+enCours.getName()+" vient de piocher une carte Sort !");
			this.sendMessageToAllButCurrent("Que va-t-il faire ?\n");
			this.sendMessageToCurrent("Vous venez de piocher la carte Sort : ");
			this.sendMessageToCurrent(cartePiochee.getNom());
			this.sendMessageToCurrent(cartePiochee.getDescription());
			jouerCarteMaledictionOuvrirPorte(carteMaledictionPiochee);			

			// On pille la piece
			PillerLaPiece();
			// On rafraichit
			this.sendInfos();
		}

		return cartePiochee;
	}
	
	/**
	 * Méthode permettant jouer la carte sort piochée
	 * @throws Exception 
	 */
	private void jouerCarteMaledictionOuvrirPorte(Malediction cartePiochee) throws Exception {
		this.sendMessageToAllButCurrent(enCours.getName()+" va lancer un sort. Voulez vous, auparavant, intervenir ?");
		this.sendMessageToAll("Suspense, quelqu'un va peut etre intervenir..");
		// Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
		if(this.size() != 1)
		{
			// On applique le sort sans que le joueur n'ait pu faire quelque chose
			demanderIntervenir(new ArrayList<Joueur>(){{add(enCours);}});
		}
		this.sendMessageToCurrent("On applique le sort !\n");
		this.sendSongToAll(Constante.jouerSon(Constante.SOUND_SORT));
		// On applique le sortilege sur soi
		this.sendMessageToAll(cartePiochee.appliquerSortilege(enCours, new ArrayList<Joueur>(){{add(enCours);}}, this));
		this.sendMessageToAll("Avant que la phase ouvrirPorte ne se termine, vous pouvez intervenir");
                // Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
		if(this.size() != 1)
		{
			demanderIntervenir(new ArrayList<Joueur>());
		}
	}

	/**
	 * Méthode qui permet de lancer la combat
	 * @param monstrePioche
	 * @return boolean, si le joueur a gagné le combat ou perdu
	 */
	private boolean ChercherLaBagarre(Monstre monstrePioche) {
		boolean gagne = true;
		this.sendMessageToAll("Changement de phase : "+getPhaseTourString()+" => "+getPhaseTourString(Constante.PHASE_CHERCHER_LA_BAGARRE));
		this.setPhaseTour(Constante.PHASE_CHERCHER_LA_BAGARRE);
		setCombat(new Combat(this));

		getCombat().getCampGentil().add(enCours.getPersonnage());
		getCombat().getCampMechant().add(monstrePioche);

		System.out.println("Vous avez tiré le monstre :");
		System.out.println(monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")");
		System.out.println(monstrePioche.getDescription());
		this.sendInfos();
		this.sendSongToAll(Constante.jouerSon(Constante.SOUND_MONSTRE));

		/**
		 * On applique la condition du monstre
		 */
		this.sendMessageToAll(monstrePioche.appliquerCondition(null, new ArrayList<Joueur>(){{add(enCours);}}, this));
		this.sendInfos();
                
                this.sendMessageToAllButCurrent("Avant que le joueur "+enCours.getName()+" ne choisisse d'attaquer ou de déguerpir, voulez vous intervenir ?");
                this.sendMessageToCurrent("Avant que vous ne choisissez d'attaquer ou de déguerpir, voulez intervenir ?");
                if(this.size() != 1)
			{
				try {
					demanderIntervenir(new ArrayList<Joueur>());
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}

		System.out.println("Combattre ? (o/n)");
		this.sendMessageToAll("Le joueur : " +enCours.getName() + " a tiré le monstre : \n"
				+ monstrePioche.getNom() + "(Puissance : " + monstrePioche.getPuissance() + ")\n"
				+ monstrePioche.getDescription() +"\n"
				+" Va-t il combattre ?\n");
		this.sendQuestionToEnCours("Combattre ?");
                this.enCours.setAnswer(null);
                while( this.enCours.getAnswer()==null )
			try {
				Thread.currentThread().sleep(200);//sleep for 200 ms
			} catch (InterruptedException ex) {
				Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
			}


		if(this.enCours.getAnswer().equals("Yes")){
                    this.sendMessageToAll("Quelle vaillance, "+enCours.getName()+" attaque le montre !");
		
			// On demande à tous les joueurs si ils veulent intervenir ici avant que le combat ne commence
			// Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
			// TODO : Autoriser les cartes valables ici
                    this.sendMessageToAllButCurrent("Maintenant que "+enCours.getName()+" a choisi d'attaquer le monstre...voulez vous intervenir ?");
                    this.sendMessageToCurrent("Maintenant que vous avez choisi d'attaquer le monstre...voulez vous intervenir ?");
                    if(this.size() != 1)
			{
				try {
					demanderIntervenir(new ArrayList<Joueur>());
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
			// Si le joueur gagne le combat, on lance MonstreVaincu pour connaitre
			// le nb de niveau gagné et les cartes trésors qu'il peut tirer
			if(getCombat().combattre()){
				combatGagne(monstrePioche);
				gagne = true;
			} else {
				combatPerdu(monstrePioche);
				gagne = false;
			}
                        
                        this.sendMessageToAll("Maintenant que le combat est terminé, voulez vous intervenir ?");
			try {
				demanderIntervenir(new ArrayList<Joueur>());
			} catch (Exception e) {				
				e.printStackTrace();
			}

		} else {
                    this.sendMessageToAll("Quelle déception, "+enCours.getName()+" fuit le montre...");
                    this.sendMessageToAllButCurrent("Avant que " + enCours.getName() + " ne lance le dé pour déguerpir, voulez vous intervenir ?");
                    this.sendMessageToCurrent("Avant que vous ne lanciez le dé pour déguerpir, voulez vous intervenir ?");
                        // On demande si tous les joueurs veulent intervenir une fois le combat fini
			// Avant que le joueur ne puisse intervenir on demande si on intervient
			try {
				demanderIntervenir(new ArrayList<Joueur>());
			} catch (Exception e) {				
				e.printStackTrace();
			}
			deguerpir(getCombat());
			gagne = false;
		}

		this.defausseDonjon.ajouterCarte(monstrePioche);

		this.sendInfos();				
		return gagne;
	}

	private void appliquerCartePoseMainSurJoueur(Joueur joueur,Carte cardById) {
		if(cardById.getClass().equals(Objet.class))
		{   
			Objet carteObjet = (Objet) cardById;
			final Joueur j=joueur;
			this.SendDebugMessage("Dans appliquerCartePoseMain, on vient de voir que c'est une carte Objet");
			this.SendDebugMessage(carteObjet.equiper(j, new ArrayList<Joueur>(){{add(j);}}, this));
		}
	}

	/**
	 * Méthode permettant de dérouler le code sur un combat gagné
	 * @param monstrePioche
	 */
	private void combatGagne(Monstre monstrePioche) {
		System.out.println("Vous avez gagné !");
		this.sendMessageToAll(monstrePioche.appliquerMonstreVaincu(null, new ArrayList<Joueur>(){{add(enCours);}}, this));
		this.sendMessageToAll("Le joueur : " +enCours.getName() + "  a gagné le combat ! \n");
		this.sendSongToAll(Constante.jouerSon(Constante.SOUND_VICTOIRE));
	}

	/**
	 * Méthode permettant de dérouler le code sur un combat perdu
	 * @param monstrePioche
	 */
	private void combatPerdu(Monstre monstrePioche) {
		System.out.println("Vous avez perdu...");
		this.sendMessageToAll(monstrePioche.appliquerIncidentFacheux(null, new ArrayList<Joueur>(){{add(enCours);}}, this));
		this.sendMessageToAll("Le joueur : " + enCours.getName() + "  a perdu le combat ! \n");
		this.sendSongToAll(Constante.jouerSon(Constante.SOUND_INCIDENTFACHEUX));
	}

	/**
	 * Méthode permettant d'annuler tous les bonus temporaires donnés dans le combat
	 * TODO : Voir si à mettre plutôt dans le combat
	 */
	private void annulationBonusTemporaire(Carte cartePiochee) {
		if(cartePiochee.getClass().equals(Monstre.class))
			((Monstre) cartePiochee).setBonusPuissance(0);
		this.enCours.getPersonnage().setBonusCapaciteFuite(0);
		this.enCours.getPersonnage().setBonusPuissance(0);
	}
        /**
         * Methode qui envois toutes les infos necessaires a tous les joueurs
         */
	public void sendInfos() {		
		this.sendInfosJoueursToAll();
		this.sendCartesJeuxJoueursToAll();
		this.sendCartesMainToOwner();
                this.sendInfosCampsToAll(getCombat());
	}
	
	/**
         * Fonction permettant de gérer la demande d'intervention des joueurs sauf ceux contenu dans joueursNonConcernes
         * @param joueursNonConcernes
         * @throws Exception 
         */
	private void demanderIntervenir(ArrayList<Joueur> joueursNonConcernes) throws Exception{
                ArrayList<Joueur> joueursAyantRepondu=new ArrayList<Joueur>(joueursNonConcernes);
		//Envoi de la demande a tous les joueur sauf ceux contenu dans joueursNonConcernes
                this.sendInfos();
                for(Joueur j:this)  
			if(!joueursNonConcernes.contains(j))
				j.sendMessage(new Message(Message.QUESTION, "Partie", j.getName(), "Voulez vous intervenir"));
		
		this.resetAnswers();
                // Tant que les joueurs n'ont pas répondu, on attends
		while(!this.allPlayersAnsweredButThose(joueursNonConcernes)){
                    this.sendInfos();
			try {
				Thread.sleep(200);       //permet de laisser le temps au serveur d'interpreter les reponses de joueurs                         
				joueurIntervenant=null;
                                //On attends une reponse
				while((joueurIntervenant=this.onePlayerHasAnsweredExceptThose(joueursAyantRepondu)) == null){
					Thread.sleep(200);
				}
                                //on ajoute le joueurs qui a répondu dans la liste des joueurs qui ont répondu
                                joueursAyantRepondu.add(joueurIntervenant);
                                //si la reponse est oui
				if(joueurIntervenant.getAnswer().equals("Yes")){                                    
					this.sendMessageToAll("Le joueur : "+joueurIntervenant.getName()+" souhaite intervenir");
					for(Joueur j:this){
						// On envoi à tous les joueurs non concernés
						if(!joueursNonConcernes.contains(j))
						{
							j.sendMessage(new Message(Message.STOP_QUESTION_INTERVENTION, "Partie", j.getName(), ""));
						}
					}
					Carte carteChoisie;
                                        //on demande au joueur de clické sur un carte
					carteChoisie=intervention(joueurIntervenant);
					ArrayList<Joueur> joueurDest= new ArrayList<Joueur>();
					//joueurDest.add(enCours);
					if(carteChoisie instanceof Malediction || carteChoisie instanceof Sort)
					{
						// On applique le sortilege pour la malediction et le sortilege
						// TODO : Faire le ciblage
						if(carteChoisie instanceof Malediction)
						{
							Malediction carteMaledictionChoisie = (Malediction) carteChoisie;
							this.sendMessageToAll(carteMaledictionChoisie.appliquerSortilege(joueurIntervenant, joueurDest, this, true));
						}
						else
						{
							Sort carteSortChoisie = (Sort) carteChoisie;
							this.sendMessageToAll(carteSortChoisie.appliquerSortilege(joueurIntervenant, joueurDest, this, true));
						}
                                                // On suppose que cela vient de la main
                                                this.defausserCarte(joueurIntervenant, joueurIntervenant.getMain(), carteChoisie);
						/*if(joueurIntervenant.defausserCarte(carteChoisie))
						{
							this.SendDebugMessage("La carte "+carteChoisie.getNom()+" a été correctement supprimé de la main");
						}
						else
						{
							this.SendDebugMessage("La carte "+carteChoisie.getNom()+" n'a pas été correctement supprimé de la main !!!");
							throw new Exception("Probleme dans demanderIntervenirSaufJoueurs : impossible de supprimer la carte de la main");
						}*/
					}
					// TODO : Si on a voulu utiliser un objet
					else if(carteChoisie.getClass().equals(Objet.class))
					{
						// On applique le UtiliserObjet
					}
                                        //on renvoi la demande a tous les joueurs sauf ceux NonCOncernes et on reset la liste des joueurs ayant repondu
					for(Joueur j:this)  
						if(!joueursNonConcernes.contains(j)){                                                                                            
							j.sendMessage(new Message(Message.QUESTION, "Partie", j.getName(), "Voulez vous intervenir"));
							j.sendMessage(new Message(Message.MESSAGE, "Partie", "Partie", "Le joueur: "+joueurIntervenant.getName()+" est intervenu, voules vous intervenir ?",Color.GREEN));
                                                        j.setAnswer(null);
                                                        joueursAyantRepondu.clear();
                                                        joueursAyantRepondu.addAll(joueursNonConcernes);
                                                }
				}
                                // Si c'est non, on affiche qu'il ne souhaite pas intervenir
                                else{
					this.sendMessageToAll("Le joueur : "+joueurIntervenant.getName()+" ne souhaite pas intervenir");                                        
                                }                                
				       

			} catch (InterruptedException ex) {
				Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
			}
			this.sendInfos();
		}
		joueurIntervenant.setCarteClickee(null);
		joueurIntervenant=null;
		this.sendMessageToAll("Tous les joueurs ont répondu a la demande d'intervention");		
		this.sendInfos();
	}	


	/**
	 * Méthode permettant d'attendre qu'un joueur passé en parammetre est clické sur une carte pour intervenir
	 * @param emetteur
	 */
	private Carte intervention(Joueur emetteur) {
		this.sendMessageBackToSender(emetteur.getName(),"Choisissez la carte pour intervenir");
		emetteur.sendMessage(new Message(Message.INTERVENTION, "Partie",emetteur.getName(),Constante.ACTION_CARTE_INTERVENTION_CHOISIE));
		// On autorise la joueur a sélectionner des cartes
		// TODO : Selection des cartes jouables
		emetteur.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", emetteur.getName(),
				emetteur.getMain().generateInfos()));
                emetteur.setCarteClickee(null);		
		// On attends que le joueur ait choisi une carte
		while(emetteur.getCarteClickee()==null)
		{
			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
			}
		}                   
		
		return emetteur.getCarteClickee();
	}

	/**
	 * Phase de pillage de la piece, on pioche une carte et on change la phaseTour
	 */
	private void PillerLaPiece() {
		this.sendMessageToAll("Changement de phase : "+getPhaseTourString()+" => "+getPhaseTourString(Constante.PHASE_PILLER_LA_PIECE));
		this.setPhaseTour(Constante.PHASE_PILLER_LA_PIECE);
                this.sendMessageToAllButCurrent("Avant que le joueur "+enCours.getName()+" ne pioche une carte, vous pouvez intervenir");
		this.sendMessageToCurrent("Avant que vous ne piochez une carte, vous pouvez intervenir");
                // On pioche une carte du donjon
		// Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
		if(this.size() != 1)
		{
			// On applique le sort sans que le joueur n'ait pu faire quelque chose
			try {
				demanderIntervenir(new ArrayList<Joueur>());
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
                this.sendMessageToAllButCurrent("Le joueur : "+enCours.getName()+" pioche une carte donjon !");
                this.sendMessageToCurrent("Vous piochez une carte donjon !");
		enCours.piocherCarte(Constante.DONJON);
		this.sendMessageToAllButCurrent("Maintenant que le joueur "+enCours.getName()+" a pioché sa carte...va t il ou allez vous intervenir ?");
                this.sendMessageToCurrent("Maintenant que vous avez pioché une carte...voulez vous intervenir ?");
                // Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
		if(this.size() != 1)
		{
			// On applique le sort sans que le joueur n'ait pu faire quelque chose
			try {
				demanderIntervenir(new ArrayList<Joueur>());
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}

	/**
	 * Méthode permettant de dérouler la phase de la Charité
	 */
	private void Charite() {
		this.sendMessageToAll("Changement de phase : "+getPhaseTourString()+" => "+getPhaseTourString(Constante.PHASE_CHARITE_SIOUPLAIT));
		this.setPhaseTour(Constante.PHASE_CHARITE_SIOUPLAIT);
		int nbCartesADefausser = 0;

		
		if((nbCartesADefausser = enCours.verifieMain()) > 0)
		{   
			
                        // On vérifie la main du joueur et on demande au joueur de choisir les cartes à défausser		
                        this.sendMessageToAllButCurrent("Le joueur: "+enCours.getName()+" doit defausser "+nbCartesADefausser+" Cartes ! Vous pouvez intervenir pour modifier cela...");
                        this.sendMessageToCurrent("Vous devez vous défausser de "+ nbCartesADefausser + ". Voulez vous intervenir pour modifier cela ?");
                        // Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
                        if(this.size() != 1)
                        {
                                // On applique le sort sans que le joueur n'ait pu faire quelque chose
                                try {
                                        demanderIntervenir(new ArrayList<Joueur>());
                                } catch (Exception e) {				
                                        e.printStackTrace();
                                }
                        }
                        nbCartesADefausser = enCours.verifieMain();
                        // On appelle plusieurs fois la demande de défausse selon le nbCartesADefausser
			// Oui c'est fait exprès pour etre le plus flexible possible
			// On récupère la carte choisie par l'utilisateur que l'on veut défausser
			for(int i = 0; i < nbCartesADefausser; i++)
			{
				// Test si on a modifié les paramètres : ex : recalculer nbCartesADefausser ?
				Carte carteADefausser = demandeDefausseCarte(enCours);
				try{
					if(carteADefausser != null)
					{
						// On la supprime de sa main
						if(enCours.defausserCarte(carteADefausser))
						{
							this.SendDebugMessage("La carte "+carteADefausser.getNom()+" a été correctement supprimé de la main");
						}
						else
						{
							this.SendDebugMessage("La carte "+carteADefausser.getNom()+" n'a pas été correctement supprimé de la main !!!");
							throw new Exception();
						}
					}
					// Si la carte est null, il y a eu une erreur
					else{
						throw new Exception();
					}
				}
				catch(Exception ex){
					Exception e = new Exception();
					this.SendDebugMessage("Une erreur est intervenue dans "+e.getStackTrace().toString()+"\n, on continue mais c'est pas normal !");
				}
				this.sendInfos();
				enCours.setCarteClickee(null);
			}
                        enCours.sendMessage(new Message(Message.INTERVENTION,"Partie",enCours.getName(),Constante.ACTION_FIN_CHARITE));
			this.sendMessageToAllButCurrent("Le joueur "+enCours.getName()+" a fini de défausser ses cartes. Voulez vous maintenant intervenir ?");
                        this.sendMessageToCurrent("Vous avez terminé de defausser vos cartes...voulez vous maintenant intervenir ?");
                        // Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
			if(this.size() != 1)
			{
				// On applique le sort sans que le joueur n'ait pu faire quelque chose
				try {
					demanderIntervenir(new ArrayList<Joueur>());
				} catch (Exception e) {					
					e.printStackTrace();
				}
			}
		}
	}

	public void setCarteClickee(Carte carteClickee,Joueur emetteur) {
		emetteur.setCarteClickee(carteClickee);
	}



	/**
	 * Méthode permettant de demander au joueur de défausser une carte. Une fois la carte choisie, elle est renvoyée.
	 * @param j
	 * @return
	 */
	private Carte demandeDefausseCarte(Joueur j) {
                this.joueurIntervenant=j;
		this.sendMessageBackToSender(j.getName(),"Choisissez la carte à Defausser");
		j.sendMessage(new Message(Message.INTERVENTION, "Partie",j.getName(),Constante.ACTION_DEFAUSSER));
		j.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie", j.getName(),
				j.getMain().generateInfos()));
		j.setCarteClickee(null);
		while(j.getCarteClickee()==null)
		{
			try {
				Thread.sleep(200);
			} catch (InterruptedException ex) {
				Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
			}
		}                   
		this.joueurIntervenant=null;
		return j.getCarteClickee();
	}
        
        /**
         * Méthode permettant de retourner le nom de la phase en cours
         * @return 
         */
        private String getPhaseTourString()
        {
            switch(getPhaseTour())
            {
                case Constante.PHASE_INIT:
                    return "Initiale";
                case Constante.PHASE_OUVRIR_PORTE:
                    return "Ouvrir la porte";
                case Constante.PHASE_CHERCHER_LA_BAGARRE:
                    return "Chercher la bagarre";
                case Constante.PHASE_PILLER_LA_PIECE:
                    return "Piller la pièce";
                case Constante.PHASE_CHARITE_SIOUPLAIT:
                    return "Charité siouplait";
                default:
                    return "Phase non renseigée";
            }
        }
        
        /**
         * Méthode permettant de retourner le nom de la phase en paramètre
         * @return 
         */
        private String getPhaseTourString(int phase)
        {
            switch(phase)
            {
                case Constante.PHASE_INIT:
                    return "Initiale";
                case Constante.PHASE_OUVRIR_PORTE:
                    return "Ouvrir la porte";
                case Constante.PHASE_CHERCHER_LA_BAGARRE:
                    return "Chercher la bagarre";
                case Constante.PHASE_PILLER_LA_PIECE:
                    return "Piller la pièce";
                case Constante.PHASE_CHARITE_SIOUPLAIT:
                    return "Charité siouplait";
                default:
                    return "Phase non renseigée";
            }
        }
        
        /**
         * Méthode permettant de défausser une carte de la partie, d'un joueur, d'un tas spécifique
         * @param piocheOrigine
         * @param carteADefausser
         * @return 
         */
        public boolean defausserCarte(Joueur joueur, CartesJoueur tas, Carte carteADefausser)
        {
            // On teste si le tas concerné par la défausse est la main ou le jeu
            if(tas.getClass().equals(Main.class))
            {
                if(joueur.getMain().supprimerCarte(carteADefausser))
                {
                    System.out.println("On a réussi a défausser la carte de la main");
                }
                else
                {
                    System.out.println("Erreur dans la fonction defausserCarte => Defausse d'une main");
                    return false;
                }
            }
            else if(tas.getClass().equals(Jeu.class))
            {
                if(joueur.getJeu().supprimerCarte(carteADefausser))
                {
                    System.out.println("On a réussi a défausser la carte du jeu");
                    // On applique le comportement de défausse d'une carte selon la carte
                    // Ici seulement pour les objets
                    if(carteADefausser.getClass().equals(Objet.class))
                    {
                        Objet obj = (Objet) carteADefausser;
                        ArrayList<Joueur> joueurDestination = new ArrayList<Joueur>();
                        joueurDestination.add(joueur);
                        obj.desequiper(joueur, joueurDestination , this);
                    }
                    else
                    {
                        System.out.println("Problème dans la fonction de defausserCarte : la carte a defausser n'est pas un objet");
                    }
                }
                else
                {
                    System.out.println("Erreur dans la fonction defausserCarte => Defausse d'un jeu");
                    return false;
                }
            }
            else
            {
                return false;
            }
            
            // On teste si la carte a defausser est une carte donjon ou une carte tresor
            // TODO : Attention à l'héritage n+2 en profondeur sur les cartes...non géré
            if(carteADefausser.getClass().getSuperclass().equals(Donjon.class))
            {
                return this.defausseDonjon.ajouterCarte((Donjon)carteADefausser);
            }
            else if(carteADefausser.getClass().getSuperclass().equals(Tresor.class))
            {
                return this.defausseTresor.ajouterCarte((Tresor)carteADefausser);
            }
            else
            {
                System.out.println("Le type de carte n'a pas été détecté");
            }
            
            return false;
        }
        
        public boolean defausserTout(Joueur joueur, CartesJoueur tas)
        {
            // On teste si le tas concerné par la défausse est la main ou le jeu
            if(tas.getClass().equals(Main.class))
            {
                // On les supprime toutes du jeu, et on les mets dans la défausse correspondante
                for (Carte carte : joueur.getJeu().getCartes()) {
                    joueur.getMain().supprimerCarte(carte);
                    // On teste si la carte a defausser est une carte donjon ou une carte tresor
                    // TODO : Attention à l'héritage n+2 en profondeur sur les cartes...non géré
                    if (carte.getClass().getSuperclass().equals(Donjon.class)) {
                        return this.defausseDonjon.ajouterCarte((Donjon) carte);
                    } else if (carte.getClass().getSuperclass().equals(Tresor.class)) {
                        return this.defausseTresor.ajouterCarte((Tresor) carte);
                    } else {
                        System.out.println("Le type de carte n'a pas été détecté");
                    }
                }
            }
            else if(tas.getClass().equals(Jeu.class))
            {
                for (Carte carte : joueur.getJeu().getCartes()) {
                    if (joueur.getJeu().supprimerCarte(carte)) {
                        System.out.println("On a réussi a défausser la carte du jeu");
                        // On applique le comportement de défausse d'une carte selon la carte
                        // Ici seulement pour les objets
                        if (carte.getClass().equals(Objet.class)) {
                            Objet obj = (Objet) carte;
                            ArrayList<Joueur> joueurDestination = new ArrayList<Joueur>();
                            joueurDestination.add(joueur);
                            obj.desequiper(joueur, joueurDestination, this);
                        } else {
                            System.out.println("Problème dans la fonction de defausserCarte : la carte a defausser n'est pas un objet");
                        }

                        // On teste si la carte a defausser est une carte donjon ou une carte tresor
                        // TODO : Attention à l'héritage n+2 en profondeur sur les cartes...non géré
                        if (carte.getClass().getSuperclass().equals(Donjon.class)) {
                            return this.defausseDonjon.ajouterCarte((Donjon) carte);
                        } else if (carte.getClass().getSuperclass().equals(Tresor.class)) {
                            return this.defausseTresor.ajouterCarte((Tresor) carte);
                        } else {
                            System.out.println("Le type de carte n'a pas été détecté");
                        }
                    }
                }
            } else {
                return false;
            }
            
            
            
            return false;
        }
}
