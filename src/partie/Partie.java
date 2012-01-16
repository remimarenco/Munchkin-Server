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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import joueur.Joueur;
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
	//private Answer           answer;
	private int               phaseTour;
	private Combat           combat;
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

	/**
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
	 * 
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
	 * @param msg 
	 */
	public void intervenir(Message msg){
                Joueur emetteur=this.getJoueurByName(msg.getNick_src());
		switch(msg.getAction()){
                case Constante.ACTION_PRET:
                       if(!msg.getIdCard().equals("")){
								              
			} 
                       else { 
                           this.answer(msg);
                       }
                       break;
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
				emetteur.defausserCarte(Deck.getCardById(id));
				// Activation de la carte
				appliquerCartePoseMainSurJoueur(emetteur,Deck.getCardById(id));            
                                this.sendInfos();
                                if(joueurIntervenant!=null){                              
                                    joueurIntervenant.sendMessage(new Message(Message.CARTES_JOUABLES, "Partie" ,
                                            joueurIntervenant.getName(), joueurIntervenant.getMain().generateInfos()));
                                    this.sendMessageBackToSender(joueurIntervenant.getName(),"Choisissez la carte pour intervenir");
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
						emetteur.getMain().getCartesJouablePourPourrir()));
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
		}
	}

	/**
	 * Methode qui est appelé par le serveur lorsque la reponse attendu est reçu
	 * Permet de débloquer la partie lorsque celle ci attends la reponse d'un joueur
	 * @param msg
	 * @return 
	 * @throws Exception 
	 */
	public boolean answer(Message msg){
		this.getJoueurByName(msg.getNick_src()).setAnswer(msg.getMessage());
		return true;     
	}

	public void resetAnswers(){
		for(Joueur j:this) 
			j.setAnswer(null);
	}
        
        public Joueur onePlayerHasAnsweredExceptThose(ArrayList<Joueur> jList){
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
			// Si la partie est terminée, on stop le jeu
			if(PartieTerminee()) {
				// TODO Faire ce qu'il se passe en fin de partie
				finPartie();
				break;
			}
			try {
				tour();
			} catch (Exception e) {				
				e.printStackTrace();
			}
		}
	}


	/**
	 * Initialisation de la partie
	 */
	private void init() {
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

			this.sendMessageToAll("Vous ouvrez la porte du donjon...");
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
		this.sendMessageToAll("Changement de phase : "+getPhaseTour()+" => "+Constante.PHASE_OUVRIR_PORTE);
		this.setPhaseTour(Constante.PHASE_OUVRIR_PORTE);
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

		if(cartePiochee.getClass().equals(Malediction.class)){
			Malediction carteMaledictionPiochee = (Malediction) cartePiochee;
			this.sendMessageToAll("C'est un sort !!\n");
                        this.sendMessageToAll("Suspense, quelqu'un va peut etre intervenir..");
			this.sendMessageToAllButCurrent("Le joueur "+enCours.getName()+" vient de piocher une carte Sort !");
			this.sendMessageToAllButCurrent("Que va-t-il faire ?\n");
			jouerCarteMalediction(carteMaledictionPiochee);
			this.sendMessageToCurrent("Vous venez de piocher la carte Sort : ");
			this.sendMessageToCurrent(cartePiochee.getNom());
			this.sendMessageToCurrent(cartePiochee.getDescription());			
			this.sendSongToAll(Constante.jouerSon(Constante.SOUND_SORT));

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
	private void jouerCarteMalediction(Malediction cartePiochee) throws Exception {
		this.sendMessageToAllButCurrent(enCours.getName()+" va lancer un sort. Voulez vous, auparavant, intervenir ?");
		// Si le joueur est tout seul à jouer, ce qui ne devrait jamais arriver sur la version finale, on ne demande pas d'intervention
		if(this.size() != 1)
		{
			demanderIntervenir(new ArrayList<Joueur>(){{add(enCours);}});
		}
		this.sendMessageToCurrent("On tente d'appliquer le sort sur vous tout de suite !\n");
		cartePiochee.appliquerSortilege(enCours, new ArrayList<Joueur>(){{add(enCours);}}, this);
	}

	/**
	 * Méthode qui permet de lancer la combat
	 * @param monstrePioche
	 * @return boolean, si le joueur a gagné le combat ou perdu
	 */
	private boolean ChercherLaBagarre(Monstre monstrePioche) {
		boolean gagne = true;
		this.sendMessageToAll("Changement de phase : "+getPhaseTour()+" => "+Constante.PHASE_CHERCHER_LA_BAGARRE);
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
		

			// Si le joueur gagne le combat, on lance MonstreVaincu pour connaitre
			// le nb de niveau gagné et les cartes trésors qu'il peut tirer
			if(getCombat().combattre()){
				combatGagne(monstrePioche);
				gagne = true;
			} else {
				combatPerdu(monstrePioche);
				gagne = false;
			}		

		} else {

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

	private void sendInfos() {
		this.sendInfosCampsToAll(getCombat());
		this.sendInfosJoueursToAll();
		this.sendCartesJeuxJoueursToAll();
		this.sendCartesMainToOwner();
	}
	
	/**
	 * Fonction permettant de gérer la demande d'intervention des joueurs
	 */
	private void demanderIntervenir(ArrayList<Joueur> joueursNonConcernes) throws Exception{
		for(Joueur j:this)  
                    if(!joueursNonConcernes.contains(j))
			j.sendMessage(new Message(Message.QUESTION, "Partie", j.getName(), "Voulez vous intervenir"));
		// Tant que les joueurs n'ont pas répondu, on attends
		this.resetAnswers();
		while(!this.allPlayersAnsweredButThose(joueursNonConcernes)){
			try {
				Thread.sleep(200);
                                joueurIntervenant=null;
                                while((joueurIntervenant=this.onePlayerHasAnsweredExceptThose(joueursNonConcernes)) == null){
                                    Thread.sleep(200);
                                }
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
                                    carteChoisie=intervention(joueurIntervenant);
                                    ArrayList<Joueur> joueurDest= new ArrayList<Joueur>();
                                    joueurDest.add(enCours);
                                    if(carteChoisie instanceof Malediction || carteChoisie instanceof Sort)
                                    {
					// On applique le sortilege pour la malediction et le sortilege
					// TODO : Faire le ciblage
					if(carteChoisie instanceof Malediction)
					{
						Malediction carteMaledictionChoisie = (Malediction) carteChoisie;
						this.sendMessageToAll(carteMaledictionChoisie.appliquerSortilege(joueurIntervenant, joueurDest, this));
					}
					else
					{
						Sort carteSortChoisie = (Sort) carteChoisie;
						this.sendMessageToAll(carteSortChoisie.appliquerSortilege(joueurIntervenant, joueurDest, this));
					}
					if(joueurIntervenant.defausserCarte(carteChoisie))
					{
						this.SendDebugMessage("La carte "+carteChoisie.getNom()+" a été correctement supprimé de la main");
					}
					else
					{
						this.SendDebugMessage("La carte "+carteChoisie.getNom()+" n'a pas été correctement supprimé de la main !!!");
						throw new Exception("Probleme dans demanderIntervenirSaufJoueurs : impossible de supprimer la carte de la main");
					}
                                    }
                                    // Si on a voulu utiliser un objet
                                    else if(carteChoisie.getClass().equals(Objet.class))
                                    {
                                            // On applique le UtiliserObjet
                                    }
                                    joueurIntervenant.setAnswer(null);
                                    for(Joueur j:this)  
                                        if(!joueursNonConcernes.contains(j)){                                                                                            
                                                j.sendMessage(new Message(Message.QUESTION, "Partie", j.getName(), "Voulez vous intervenir"));
                                                j.sendMessage(new Message(Message.MESSAGE, "Partie", "Partie", "Le joueur: "+joueurIntervenant.getName()+" est intervenu, voules vous intervenir ?",Color.GREEN));
                                        }
                                }
                                else{
                                    this.sendMessageToAll("Le joueur : "+joueurIntervenant.getName()+" ne souhaite pas intervenir");
                                }                                
                                
                                
			} catch (InterruptedException ex) {
				Logger.getLogger(Partie.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
                joueurIntervenant.setCarteClickee(null);
                joueurIntervenant=null;
                this.sendMessageToAll("Tous les joueurs ont répondu a la demande d'intervention");		
                this.sendInfos();
	}	


	/**
	 * Méthode permettant à un joueur de 
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
		this.sendMessageToAll("Changement de phase : "+getPhaseTour()+" => "+Constante.PHASE_PILLER_LA_PIECE);
		this.setPhaseTour(Constante.PHASE_PILLER_LA_PIECE);
		// On pioche une carte du donjon
		enCours.piocherCarte(Constante.DONJON);
	}

	/**
	 * Méthode permettant de dérouler la phase de la Charité
	 */
	private void Charite() {
		this.sendMessageToAll("Changement de phase : "+getPhaseTour()+" => "+Constante.PHASE_CHARITE_SIOUPLAIT);
		this.setPhaseTour(Constante.PHASE_CHARITE_SIOUPLAIT);
		int nbCartesADefausser = 0;
		// On vérifie la main du joueur et on demande au joueur de choisir les cartes à défausser
		// TODO :
		if((nbCartesADefausser = enCours.verifieMain()) > 0)
		{   
                        this.sendMessageToAllButCurrent("Le joueur: "+enCours.getName()+" doit defausser "+nbCartesADefausser+" Cartes !");
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
		
		return j.getCarteClickee();
	}
}
