/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;

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
        
        private String nick_src=new String("");     
        private String nick_dest=new String("");    
        private String message=new String("");
        private int type;
        
        

    public Message(){}



 public Message(int type,String nick_src){
            this.type=type;
            this.nick_src=nick_src;
           
        }
 public Message(int type,String nick_src,String nick_dest,String msg){
            this.type=type;            
            this.nick_src=nick_src;          
            this.nick_dest=nick_dest;        
            this.message=msg;           
        }

    public boolean read(DataInputStream in) {
        try{
                type=in.readInt();
                
                nick_src=new String(in.readUTF());

                if(type>DISCONNECT){                      
                nick_dest=new String(in.readUTF());
                
                message=new String(in.readUTF());                 
                }
                 return true;
            }
            catch(Exception e){
                return false;
            }
    }

    public boolean write(DataOutputStream out) {
        try{
                out.writeInt(type);
                
                out.writeUTF(nick_src);
                if(type>DISCONNECT){
                    out.writeUTF(nick_dest);
                    
                    out.writeUTF(message);
                   
                    
                 
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

    public Object getMessage() {
        return this.message;
    }

    public String getNick_src() {
        return this.nick_src;
    }

    public String getNick_dest() {
        return this.nick_dest;
    }
    
}
