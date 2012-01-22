package joueur;

import carte.Carte;
import carte.Donjon;
import carte.Monstre;
import communication.Message;
import communication.Serveur;
import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.swing.JLabel;
import partie.Constante;
import partie.Partie;


/**
 * Un Joueur est un Thread de communication
 * @author Guillaume Renoult
 */
public class Joueur extends Thread {
	
    private Main main;
    private Jeu jeu;
    private Personnage personnage;
    private Partie partie;
    private JLabel avatar = null;
    private Message msg          = new Message();
    private Object parent        = null;
    private DataInputStream in   = null;
    private DataOutputStream out = null;
    private int sexe             = Constante.SEXE_M;
    private String answer        = null;
    private Carte carteClickee  = null;
    
    /**
     * Constructeur
     * @param st
     * @param parent
     * @param partie 
     */
    public Joueur(Socket st,Object parent,Partie partie) {        
        initCommunication(st, parent);
        this.main       = new Main();
        this.jeu        = new Jeu();        
        this.personnage = new Personnage(1, sexe, false, 5, 0, 0, 0, 0);
        this.partie     = partie;
    }  

    
    // ===== ACCESSEURS & MUTATEURS ===== //
    
    public JLabel getAvatar() {
        return avatar;
    }

    public void setAvatar(JLabel avatar) {
        this.avatar = avatar;
    }
    
    public Jeu getJeu() {
        return jeu;
    }

    public Main getMain() {
        return main;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public Carte getCarteClickee() {
        return carteClickee;
    }

    public void setCarteClickee(Carte carteClickee) {
        this.carteClickee = carteClickee;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
        this.personnage.setSexe(sexe);
    }    
    
    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    // ================================== // 

  
    
    
    
    /**
     * Methode d'initialisation pour le thread et les communications reseaux
     * @param st
     * @param parent 
     */
    private void initCommunication(Socket st,Object parent){
        try{
            this.parent = parent;
            in          = new DataInputStream(st.getInputStream());
            out         = new DataOutputStream(st.getOutputStream());
        }
        catch(Exception e){
            System.exit(1);
        }
    }
  
    
    /**
     * Envoi la liste des joueurs connectés aux clients
     * @param list 
     */
    public void sendList(LinkedHashMap<String,JLabel> list){
        new Message(Message.LISTE,"Partie",getName(),list).write(out);
    }

    
    /**
     * Envoi un message via le socket via la methode write
     * @param message
     * @return vrai quand le message est envoyé
     */
    public boolean sendMessage(Message message){
        message.write(out); 
        return true;
    }   
   
    
    /**
     * Methode run du thread;
     */
    @Override
    synchronized  public void run(){
        boolean test = true;
        try{
            while(test){
                this.msg = new Message();   //Important pour distinguer les messages
                if(msg.read(in)){
                    if(parent instanceof Serveur)
                        ((Serveur)parent).interpretMessage(msg,this);    
                }
                else if(!msg.read(in)){
                    Message message=new Message(Message.DISCONNECT,this.getName());                                       
                    ((Serveur)parent).interpretMessage(message,this);                   
                    this.interrupt();
                    test=false;                   
                }
            }
        }
        catch(Exception e){       
            System.out.println("Exception com Serv : "+e.toString());
        }
    }
    
    
    /**
     * Pioche une carte & l'ajoute dans la main d'un joueur
     * @param typePioche : type de pioche (DONJON ou TRESOR)
     * @return boolean : la pioche s'est bien passée ou non
     */ 
    public boolean piocherCarte(Class typePioche){
        if(typePioche == Constante.DONJON){
        	// J'annule le fait de pouvoir piocher en pillant une piece une carte de monstre
        	Donjon carte = this.partie.getPiocheDonjon().tirerCarte();
                // TODO : Gérer le cas ou il ne reste plus que des monstres dans la pioche donjon
                // On boucle tant qu'on a pas pioché une autre carte qu'un monstre
                    if(carte instanceof Monstre)
                    {
                        System.out.println("On a pioché un monstre, on le remet en dessous de la pile");
                            this.partie.getPiocheDonjon().poserEnDessousPioche(carte);
                    }
                    else
                    {
                            this.main.ajouterCarte(carte);
                    }
            
        }else if(typePioche == Constante.TRESOR){
            this.main.ajouterCarte(this.partie.getPiocheTresor().tirerCarte());
        }else{
            System.out.println("Quelque chose a dû merder quelquepart...");
            return false;
        }
        return true;
    }
    
    
    /**
     * Defausse d'une carte dans la main ou dans le jeu (en fonction de où on la trouve)
     * @param c : la carte à rechercher
     * @return 
     */
    public boolean defausserCarte(Carte c) {
        if (this.getMain().contains(c)) {
            System.out.println("On supprime la carte " + c.getNom() + " de la main");
            return this.getMain().supprimerCarte(c);
        } else if (this.getJeu().contains(c)) {
            System.out.println("On supprime la carte " + c.getNom() + " du jeu");
            return this.getJeu().supprimerCarte(c);
        } else {
            return false;
        }
    }
    
    
   public void setNom(String name){
       super.setName(name);
       this.personnage.setNom(name);
   }
    
    
    /**
     * Génère les infos du joueur à envoyer aux clients
     * @return 
     */
    public LinkedHashMap<String,String> generateInfos(){
    	LinkedHashMap<String,String> map=new LinkedHashMap<String, String>();
        map.put("Nom", this.getName());
        map.put("Niveau Personnage",Integer.toString(this.getPersonnage().getNiveau()));
        if(this.getPersonnage().getSexe()==Constante.SEXE_F)
            map.put("Sexe","Feminin");
        else
             map.put("Sexe","Masculin");
        map.put("Capacité de fuite",Integer.toString(this.getPersonnage().getCapaciteFuite()));
        map.put("Puissance",Integer.toString(this.getPersonnage().getPuissance()));
        if(this.getPersonnage().getClasse()!=null)
        {
            map.put("Classe",this.getPersonnage().getClasse().toString());
        }
        // Si le joueur n'a pas de classe, on affiche qu'il n'en a aucune
        else
        {
        	map.put("Classe","Aucune");
        }
        map.put("Race",this.getPersonnage().getRace().toString());
        map.put("Nombre equipement",Integer.toString(this.getPersonnage().getNbEquipement()));
        map.put("Bonus cap fuite",Integer.toString(this.getPersonnage().getBonusCapaciteFuite()));
        return map;
    }

    /**
     * Méthode permettant de vérifier la main
     * @return renvoi un entier correspond au nombre de cartes en trop dans la main
     */
    public int verifieMain() {
        // On retourne la soustraction de la taille avec le nombre de carte max
        int deltaCarte = 0;
        try{
            deltaCarte = main.cartes.size() - personnage.getMaxCartes();
        }
        catch(Exception ex){
            System.out.println("Une erreur a été détectée dans "+ex.getStackTrace().toString()+".\n voici le détail : "+ex.getMessage());
        }
        if(deltaCarte < 0)
        {
            return 0;
        }
        else
        {
            return deltaCarte;
        }
    }
}