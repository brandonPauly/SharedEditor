/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandonPauly.sharededitor.textUI;

import com.brandonPauly.sharededitor.docserver.TextServer;
import com.brandonPauly.sharededitor.docserver.Broadcaster;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon
 */
public class Client extends Thread{
    private Socket socket;
    private Broadcaster broadcaster;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private int clientNumber;
    
    public Client(Socket s, Broadcaster b, int num) throws IOException{
        broadcaster = b;
        socket = s;
        in = new ObjectInputStream(s.getInputStream());
        out = new ObjectOutputStream(s.getOutputStream());
        clientNumber = num;
    }
    
    public void insert(CharObject c) throws IOException{
        //System.out.println(c.getChar());
        out.writeObject(c);
    }
    
    public void delete(DeleteObject d) throws IOException{
        out.writeObject(d);
    }
    
    public int getClientNumber(){
        return clientNumber;
    }

    @Override
    public void run() {
        synchronized(this){
        try {
            Object c;
            while((c = in.readObject()) != null){
                if(c.getClass() == CharObject.class){
                    broadcaster.queueModification((CharObject) c, clientNumber);
                }
                else if(c.getClass() == DeleteObject.class){
                    broadcaster.queueModification((DeleteObject) c, this.clientNumber);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
}
