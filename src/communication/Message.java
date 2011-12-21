/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.awt.Color;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class Message {
    
    
      public static final int CONNECT=0;
        public static final int DISCONNECT=1;
        public static final int MESSAGE=2;
        public static final int LISTE=3;       
        public static final int NICKEXIST=5;        
        public static final int QUESTION=7;
        public static final int INTERVENTION=8;
        public static final int INFO_JOUEUR=80;
        public static final int CARTES_JOUEUR=81;
        private String nick_src=new String("");     
        private String nick_dest=new String("");    
        private String message=new String("");
        private int type;
        private Color color;
        private HashMap<String,String> map;
        
        

 public Message(){}

 public Message(int type,String nick_src){
            this.type=type;
            this.nick_src=nick_src;           
        }
  public Message(int type,String nick_src,String nick_dest){
            this.type=type;
            this.nick_src=nick_src; 
            this.nick_dest=nick_dest;
        }
 /**
  * 
  * @param type
  * @param nick_src
  * @param nick_dest
  * @param msg 
  */
 public Message(int type,String nick_src,String nick_dest,String msg){
            this.type=type;            
            this.nick_src=nick_src;          
            this.nick_dest=nick_dest;        
            this.message=msg;
            this.color=Color.BLACK;
        }
 /**
  * 
  * @param type
  * @param nick_src
  * @param nick_dest
  * @param map 
  */
  public Message(int type,String nick_src,String nick_dest,HashMap<String,String> map){
            this.type=type;            
            this.nick_src=nick_src;          
            this.nick_dest=nick_dest;        
            this.map=map;            
        }
 /**
  * 
  * @param type
  * @param nick_src
  * @param nick_dest
  * @param msg
  * @param color 
  */
   public Message(int type,String nick_src,String nick_dest,String msg,Color color){
            this.type=type;            
            this.nick_src=nick_src;           
            this.color=color;        
            this.nick_dest=nick_dest;       
            this.message=msg;
           
        }

   /**
    * Methode qui lit un message qui arrive sur le socket
    * @param in
    * @return vrai si message bien lu
    */
    public boolean read(DataInputStream in) {
        try{
            ObjectInputStream ois= new ObjectInputStream(in);
                type=in.readInt();
                
                nick_src=new String(in.readUTF());

                if(type>DISCONNECT){                      
                    nick_dest=new String(in.readUTF());  
                    if(type<INFO_JOUEUR){
                    message=new String(in.readUTF());                  
                    color=(Color) ois.readObject();
                    }
                    if(type>=INFO_JOUEUR)
                        this.map=(HashMap<String,String>)ois.readObject();
                }
                
                 return true;
            }
            catch(Exception e){
                return false;
            }
    }

    /**
     * Methode qui ecrit un message sur le socket
     * @param out
     * @return vrai si le message est bien envoyé
     */
    public boolean write(DataOutputStream out) {
        try{
            ObjectOutputStream oos=new ObjectOutputStream(out);
                out.writeInt(type);
                
                out.writeUTF(nick_src);
                if(type>DISCONNECT){
                    out.writeUTF(nick_dest);
                    if(type<INFO_JOUEUR){
                    out.writeUTF(message);                    
                    oos.writeObject(color);   
                    }
                    if(type>=INFO_JOUEUR){
                        oos.writeObject(this.map);
                    }
                }                 
                   
               return true;
            }
            catch(Exception e){
            return false;
            }
    }

    public int getType() {
       return this.type;
    }

    public String getMessage() {
        return this.message;
    }

    public String getNick_src() {
        return this.nick_src;
    }

    public String getNick_dest() {
        return this.nick_dest;
    }

    public Color getColor() {
        return color;
    }

    public HashMap<String, String> getMap() {
        return map;
    }
    
    
    
}
