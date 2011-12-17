package joueur;

import carte.Carte;
import communication.Message;
import communication.Serveur;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import partie.Constante;
import partie.Partie;

public class Joueur extends Thread {
	
    private Main main;
    private Jeu jeu;
    private String nom;
    private Personnage personnage;
    private Partie partie;
    private Message msg=new Message();
    private Object parent=null;
    private DataInputStream in=null;
    private DataOutputStream out=null;

    public Joueur(Socket st,Object parent) {        
        initCommunication(st, parent);
        this.main = new Main();
        this.jeu = new Jeu();
        this.nom = new String();
        this.personnage = new Personnage();
    }
    
    public Joueur(String nom) {
        this.main = new Main();
        this.jeu = new Jeu();
        this.nom = nom;
        this.personnage = new Personnage();
    }
     
    public Joueur(String nom, Partie p) {
        this.main = new Main();
        this.jeu = new Jeu();
        this.nom = nom;
        this.personnage = new Personnage();
        this.partie = p;
    }
    
    public Joueur(String nom,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main = new Main();
        this.jeu = new Jeu();
        this.nom = nom;
        this.personnage = new Personnage();
    }
    
    public Joueur(Main main, Jeu jeu, String nom, Personnage personnage,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main = main;
        this.jeu = jeu;
        this.nom = nom;
        this.personnage = personnage;
    }

    public Joueur(Main main, Jeu jeu, String nom, Personnage personnage, Partie partie,Socket st,Object parent) {
        initCommunication(st, parent);
        this.main = main;
        this.jeu = jeu;
        this.nom = nom;
        this.personnage = personnage;
        this.partie = partie;
    }
    
    
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
  
    public void sendList(String list){
        new Message(Message.LISTE,"admin","General",list).write(out);
    }

    public boolean sendMessage(Message message){
        message.write(out); 
        return true;
    }    
  
    public boolean sendMessage(String message,String nick_dest){
        new Message(Message.MESSAGE,getName(),nick_dest,message).write(out);        
        return true;
    }

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
    
    
	
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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
    
    public boolean piocherCarte(int typePioche){
        if(typePioche == Constante.PIOCHE_DONJON){
            this.main.ajouterCarte(this.partie.getPiocheDonjon().tirerCarte());
        }else if(typePioche == Constante.PIOCHE_DONJON){
            this.main.ajouterCarte(this.partie.getPiocheTresor().tirerCarte());
        }else{
            System.out.println("Quelque chose a dû merder quelquepart...");
            return false;
        }
        return true;
    }

}
