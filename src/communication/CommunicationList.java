/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package communication;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Vector;

/**
 *
 * @author user
 */
public class CommunicationList extends ArrayList<Communication> {
    
    
    
      public String addLogin(String log){
        String l = "";
        l= this.getListe() + log;
        return l;
    }
    


    public boolean LoginDispo(String log){
        String l = getListe();
        boolean k =true;
        StringTokenizer l2=new StringTokenizer(l,";");
        while(l2.hasMoreTokens()){
            try{
            if(l2.nextToken().equals(log)){
              k = false;
             }
        }
        catch(Exception e){System.out.println("Exception :" + e.toString()); }
        }
        return(k);
    }
    
      public String getListe(){
        String liste="";
        for(int i=0;i<size();i++){
            liste+=get(i).getName() + ";";
           
        }
        return liste;
    }
      
      public Integer getCommunication(String nick_dest){
         int i=0;
         int j=0;
        
        while(i<this.size()){

            if(this.get(i).getName().equals(nick_dest)){
                j=i;
                i=this.size() +1;
            }
            else{
                i++;
                j=-1;
            }
        }
       return j;
    }
}
