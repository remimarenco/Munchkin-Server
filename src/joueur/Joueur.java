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
 * @author Meg4mi
 */
public class Joueur extends Thread {
	
    private Main main;
    private Jeu jeu;   
    private Personnage personnage;
    private Partie partie;
    private Message msg=new Message();
    private Object parent=null;
    private DataInputStream in=null;
    private DataOutputStream out=null;
    
    
    public Joueur(Socket st,Object parent,Partie partie) {        
        initCommunication(st, parent);
        this.main = new Main();
        this.jeu = new Jeu();        
        this.personnage = new Personnage();
        this.partie=partie;
    }  

    
    public Joueur(Main main, Jeu jeu, Personnage personnage,Partie partie,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main = main;
        this.jeu = jeu;        
        this.personnage = personnage;
        this.partie=partie;
    }

    public Joueur(Main main, Jeu jeu, String nom, Personnage personnage, Partie partie,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main = main;
        this.jeu = jeu;        
        this.personnage = personnage;
        this.partie = partie;
    }
    
    /**
     * Methode d'initialisation pour le thread et les communications reseaux
     * @param st
     * @param parent 
     */
    private void initCommunication(Socket st,Object parent){
        try{
        this.parent=parent;
        in=new DataInputStream(st.getInputStream());
        out= new DataOutputStream(st.getOutputStream());
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
        boolean test=true;
        try{
            while(test){
                this.msg=new Message();//Important pour distinguer les messages
                if(msg.read(in)){
                    if(parent instanceof Serveur){
                        ((Serveur)parent).interpretMessage(msg,this);
                    }                
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
    
    public boolean defausserCarte(Carte c){
        this.jeu.supprimerCarte(c);
        
        if(c.getClass().getName().equals("Donjon")){
            
        }else if(c.getClass().getName().equals("Tresor")){
            
        }else{
            return false;
        }
        
        return true;
    }
    
    public boolean equiperCarte(Carte c){
        this.jeu.ajouterCarte(c);
        return true;
    }
    
    public boolean piocherCarte(Class typePioche){
        if(typePioche == Constante.DONJON){
            this.main.ajouterCarte(this.partie.getPiocheDonjon().tirerCarte());
        }else if(typePioche == Constante.DONJON){
            this.main.ajouterCarte(this.partie.getPiocheTresor().tirerCarte());
        }else{
            System.out.println("Quelque chose a dû merder quelquepart...");
            return false;
        }
        return true;
    }
    
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