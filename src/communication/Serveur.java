/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import joueur.Joueur;
import partie.Partie;

/**
 *
 * @author user
 */
public class Serveur {
    private ServerSocket socket_ecoute;
    private CommunicationList comList= new CommunicationList();
    private Partie partie;
    private int nombreJoueur;

    
    public Serveur(int port,int nombreJoueur) {
        try {
            socket_ecoute = new ServerSocket(port);           
            partie=new Partie();
            this.nombreJoueur=nombreJoueur;
            while (true) {
                Socket st = socket_ecoute.accept();                
               Communication com= new Communication(st, this);
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
    
    public void InterpretMessage(Message msg, Communication com) {
         switch (msg.getType()) {

            case Message.CONNECT:

                if (this.comList.LoginDispo(msg.getNick_src()) && partie.getListeJoueurs().size()<nombreJoueur) {
                    com.setName(msg.getNick_src());
                    this.comList.add(com);
                    partie.getListeJoueurs().add(new Joueur(msg.getNick_src()));
                    String text = msg.getNick_src() + " est maintenant parmis nous \n";
                    Message message = new Message(Message.MESSAGE, "admin", "Partie", text);
                    Message message2 = new Message(Message.MESSAGE, "admin", "connexion", msg.getNick_src());
                    String list = this.comList.getListe();
                    System.out.println(list);
                    for (int i = 0; i < this.comList.size(); i++) {
                        this.comList.get(i).sendList(list);
                        this.comList.get(i).sendMessage(message);
                        this.comList.get(i).sendMessage(message2);
                    }
                }
                else if(partie.getListeJoueurs().size()== nombreJoueur){
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
                comList.remove(com);
                partie.removeJoueurByName(msg.getNick_src());
                Message message2 = new Message(Message.MESSAGE, "admin", "deconnexion", msg.getNick_src());

                Message message = new Message(Message.MESSAGE, "admin", "Partie", msg.getNick_src() + " quitte le serveur !\n");
                String list2 = comList.getListe();
                for (int i = 0; i < comList.size(); i++) {
                    comList.get(i).sendList(list2);
                    comList.get(i).sendMessage(message);
                    comList.get(i).sendMessage(message2);
                }
                break;
            case Message.MESSAGE:
                if (msg.getNick_dest().equals("Partie")) {
                    for (int i = 0; i < comList.size(); i++) {
                        comList.get(i).sendMessage(msg);

                    }
                } else {
                    int indexDest = comList.getCommunication(msg.getNick_dest());

                    if (indexDest == -1) {
                        Message message5 = new Message(Message.MESSAGE, "admin", msg.getNick_src(), "Le destinataire de votre message n'est plus ou pas connecté\n");
                        com.sendMessage(message5);
                    } else {

                        comList.get(indexDest).sendMessage(msg);
                    }
                }

                break;
            case Message.LISTE:
                for (int i = 0; i < comList.size(); i++) {
                    comList.get(i).sendList(msg.getMessage().toString());

                }
                break;
            
        }
    }
    
}
