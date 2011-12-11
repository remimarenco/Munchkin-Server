/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class Serveur {
    private ServerSocket socket_ecoute;
    private CommunicationList comList= new CommunicationList();
    

    
    public Serveur(int port,int nombreJoueur) {
        try {
            socket_ecoute = new ServerSocket(port);
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

                if (this.comList.LoginDispo(msg.getNick_src()) == true) {
                    com.setName(msg.getNick_src());
                    this.comList.add(com);
                    String text = msg.getNick_src() + " est maintenant parmis nous";
                    Message message = new Message(Message.MESSAGE, "admin", "General", text);
                    Message message2 = new Message(Message.MESSAGE, "admin", "connexion", msg.getNick_src());
                    String list = this.comList.getListe();
                    System.out.println(list);
                    for (int i = 0; i < this.comList.size(); i++) {
                        this.comList.get(i).sendList(list);
                        this.comList.get(i).sendMessage(message);
                        this.comList.get(i).sendMessage(message2);
                    }

                } else {

                    Message message = new Message(Message.NICKEXIST, "admin", "General", "Ce pseudo est deja utilise, veuillez choisir un autre !");
                    com.sendMessage(message);

                }

                break;
            case Message.DISCONNECT:
                String listeVide = new String("");
                com.setName(msg.getNick_src());
                Message message1 = new Message(Message.MESSAGE, "admin", "General", "Vous etes deconnecte du serveur, a bientot !");
                com.sendMessage(message1);
                com.sendList(listeVide);
                comList.remove(com);
                Message message2 = new Message(Message.MESSAGE, "admin", "deconnexion", msg.getNick_src());

                Message message = new Message(Message.MESSAGE, "admin", "General", msg.getNick_src() + " quitte le serveur !");
                String list2 = comList.getListe();
                for (int i = 0; i < comList.size(); i++) {
                    comList.get(i).sendList(list2);
                    comList.get(i).sendMessage(message);
                    comList.get(i).sendMessage(message2);
                }
                break;
            case Message.MESSAGE:
                if (msg.getNick_dest().equals("General")) {
                    for (int i = 0; i < comList.size(); i++) {
                        comList.get(i).sendMessage(msg);

                    }
                } else {
                    int indexDest = comList.getCommunication(msg.getNick_src());

                    if (indexDest == -1) {
                        Message message5 = new Message(Message.MESSAGE, "admin", msg.getNick_src(), "Le destinataire de votre message n'est plus ou pas connecté");
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
