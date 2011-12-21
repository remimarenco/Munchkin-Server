/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import joueur.Joueur;
import partie.Partie;

/**
 * 
 * @author Meg4mi
 */
public class Serveur {
     public static boolean runningflag=true;
    private ServerSocket socket_ecoute;
    private Partie partie;
    private  Thread thrd;    
    private int nombreJoueur;

    /**
     * Constructeur
     * @param port
     * @param nombreJoueur 
     */
    public Serveur(int port,int nombreJoueur) {
        try {
            socket_ecoute = new ServerSocket(port);            
            this.nombreJoueur=nombreJoueur;
            this.partie=new Partie();
            while (true) {               
               Socket st = socket_ecoute.accept();                
               Joueur com= new Joueur(st, this,partie);
               com.start();               
            }
        } catch (Exception e) {
            try {
                socket_ecoute.close();
                System.out.println("Exception :" + e.toString());
            } catch (IOException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
   
    
     private void stop() {
             thrd=null;
       
    }
    
    /**
     * Methode appelé par le serveur lors de la reception d'un message.
     * Traite le message en fonction de son type et sa destination
     * @param msg
     * @param com 
     */
    public void interpretMessage(Message msg, Joueur com) {
         switch (msg.getType()) {

            case Message.CONNECT:

                if (this.partie.LoginDispo(msg.getNick_src()) && partie.size()<nombreJoueur) {
                    com.setName(msg.getNick_src());
                    this.partie.add(com);
                    //partie.getListeJoueurs().add(new Joueur(msg.getNick_src()));
                    String text = msg.getNick_src() + " est maintenant parmis nous \n";
                    Message message = new Message(Message.MESSAGE, "admin", "Partie", text);
                    Message message2 = new Message(Message.MESSAGE, "admin", "connexion", msg.getNick_src());
                    Message message3 = new Message(message.MESSAGE,"admin","Partie","La partie est pleine, elle demarre ! \n");
                    String list = this.partie.getListe();
                    System.out.println(list);
                    for(Joueur j : this.partie){
                        j.sendList(list);
                        j.sendMessage(message);
                        j.sendMessage(message2);
                        if(this.partie.size()==this.nombreJoueur){
                            j.sendMessage(message3);
                        }
                    }
                    this.partie.sendInfosJoueursToAll();
                    if(this.partie.size()==this.nombreJoueur){
                        thrd= new Thread(this.partie);  
                        thrd.start();
                    }
                }
                else if(partie.size()== nombreJoueur){
                        Message mesg = new Message(Message.MESSAGE, "admin", "Partie","La partie est pleine !\n");
                        com.sendMessage(mesg);
                    }
                    
                else {

                    Message message = new Message(Message.NICKEXIST, "admin", "Partie", "Ce pseudo est deja utilise, veuillez choisir un autre ! \n");
                    com.sendMessage(message);
                }

                break;
            case Message.DISCONNECT:
                String listeVide = new String("");
                com.setName(msg.getNick_src());
                Message message1 = new Message(Message.MESSAGE, "admin", "Partie", "Vous etes deconnecte du serveur, a bientot !\n");
                com.sendMessage(message1);
                com.sendList(listeVide);
                partie.remove(com);                
                //partie.removeJoueurByName(msg.getNick_src());
                Message message2 = new Message(Message.MESSAGE, "admin", "deconnexion", msg.getNick_src());

                Message message = new Message(Message.MESSAGE, "admin", "Partie", msg.getNick_src() + " quitte le serveur !\n");
                String list2 = partie.getListe();
                for (int i = 0; i < partie.size(); i++) {
                    partie.get(i).sendList(list2);
                    if(partie.size()<nombreJoueur)
                        partie.get(i).sendMessage(message);
                    partie.get(i).sendMessage(message2);
                }
                break;
            case Message.MESSAGE:
                if (msg.getNick_dest().equals("Partie")) {
                    for (int i = 0; i < partie.size(); i++) {
                        partie.get(i).sendMessage(msg);
                    }
                } else {
                    int indexDest = partie.getCommunication(msg.getNick_dest());

                    if (indexDest == -1) {
                        Message message5 = new Message(Message.MESSAGE, "admin", msg.getNick_src(), "Le destinataire de votre message n'est plus ou pas connecté\n");
                        com.sendMessage(message5);
                    } else {

                        partie.get(indexDest).sendMessage(msg);
                    }
                }

                break;
            case Message.LISTE:
                for (int i = 0; i < partie.size(); i++) {
                    partie.get(i).sendList(msg.getMessage().toString());

                }
                break;
            case Message.QUESTION:      
            this.partie.answer(msg);             
            break;
            case Message.INTERVENTION:
                this.partie.intervenir(msg);
                break;
           
            
        }
    }
    
}
