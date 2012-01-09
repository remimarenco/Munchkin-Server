package joueur;

import carte.Carte;
import communication.Message;
import communication.Serveur;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;
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
    
    private Message msg          = new Message();
    private Object parent        = null;
    private DataInputStream in   = null;
    private DataOutputStream out = null;
    private int sexe             = Constante.SEXE_M;
    
    
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

    
    /**
     * Constructeur
     * @param main
     * @param jeu
     * @param personnage
     * @param partie
     * @param st
     * @param parent 
     */
    public Joueur(Main main, Jeu jeu, Personnage personnage,Partie partie,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main       = main;
        this.jeu        = jeu;        
        this.personnage = personnage;
        this.partie     = partie;
    }

    
    /**
     * Constructeur
     * @param main
     * @param jeu
     * @param nom
     * @param personnage
     * @param partie
     * @param st
     * @param parent 
     */
    public Joueur(Main main, Jeu jeu, String nom, Personnage personnage, Partie partie,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main       = main;
        this.jeu        = jeu;        
        this.personnage = personnage;
        this.partie     = partie;
    }
    
    
    
    // ===== ACCESSEURS & MUTATEURS ===== //
    public Jeu getJeu() {
        return jeu;
    }

    public void setJeu(Jeu jeu) {
        this.jeu = jeu;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public Personnage getPersonnage() {
        return personnage;
    }

    public void setPersonnage(Personnage personnage) {
        this.personnage = personnage;
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
    public void sendList(String list){
        new Message(Message.LISTE,"admin","General",list).write(out);
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
     * Ajoute une carte au jeu d'un joueur
     * @param c
     * @return 
     */
    public boolean equiperCarte(Carte c){
        this.jeu.ajouterCarte(c);
        return true;
    }
    
    
    /**
     * Pioche une carte & l'ajoute dans la main d'un joueur
     * @param typePioche : type de pioche (DONJON ou TRESOR)
     * @return boolean : la pioche s'est bien passée ou non
     */ 
    public boolean piocherCarte(Class typePioche){
        if(typePioche == Constante.DONJON){
            this.main.ajouterCarte(this.partie.getPiocheDonjon().tirerCarte());
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
    public boolean defausserCarte(Carte c){
        if(this.getMain().contains(c)){
            return this.getMain().supprimerCarte(c);
        }else if(this.getJeu().contains(c)){
            return this.getJeu().supprimerCarte(c);
        }else{
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
    public HashMap<String,String> generateInfos(){
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("Nom", this.getName());
        map.put("Niveau Personnage",Integer.toString(this.getPersonnage().getNiveau()));
        if(this.getPersonnage().getSexe()==Constante.SEXE_F)
            map.put("Sexe","Feminin");
        else
             map.put("Sexe","Masculin");
        map.put("Capacité de fuite",Integer.toString(this.getPersonnage().getCapaciteFuite()));
        map.put("Puissance",Integer.toString(this.getPersonnage().getPuissance()));
        return map;
    }

}