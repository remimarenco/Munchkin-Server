/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/**
 *
 * @author user
 */
public class Communication extends Thread{
     private Message msg=new Message();
    private Object parent=null;
    private DataInputStream in=null;
    private DataOutputStream out=null;
    public Communication(Socket st,Object parent){
        try{
        this.parent=parent;
        in=new DataInputStream(st.getInputStream());
        out= new DataOutputStream(st.getOutputStream());
        }
        catch(Exception e){
            
        }
    }

    public void sendList(String list){
        new Message(Message.LISTE,"admin","General",list).write(out);

    }

    public boolean sendMessage(Message message){message.write(out); return true;}
    
  
    public boolean sendMessage(String message,String nick_dest){

        new Message(Message.MESSAGE,getName(),nick_dest,message).write(out);
        return true;
    }

    @Override
  synchronized  public void run(){
        boolean test=true;
       try{
        while(test){
            if(msg.read(in)){
                if(parent instanceof Serveur){

                    ((Serveur)parent).InterpretMessage(msg,this);
                }
                
            }
            else if(!msg.read(in)){
                    Message message=new Message(Message.DISCONNECT,this.getName());                                       
                    ((Serveur)parent).InterpretMessage(message,this);                   
                    this.interrupt();
                    test=false;                    
            }
        }
    }

    catch(Exception e){
       
        System.out.println("Exception com Serv : "+e.toString());}
    }
    
}
