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
        public static int NICKEXIST=4;
        
        

    public Message(){}
        
    public Message(int DISCONNECT, String name) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public Message(int MESSAGE, String name, String nick_dest, String message) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    boolean read(DataInputStream in) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    void write(DataOutputStream out) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    int getType() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Object getMessage() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String getNick_src() {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    Object getNick_dest() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    
}
