/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandonPauly.sharededitor.docserver;

import com.brandonPauly.sharededitor.textUI.CharObject;
import com.brandonPauly.sharededitor.textUI.Client;
import com.brandonPauly.sharededitor.textUI.DeleteObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandon
 */
public class Broadcaster extends Thread{
    ConcurrentLinkedQueue<CharQ> q;
    ArrayList<Client> clients;

    Broadcaster(ArrayList<Client> c) {
        clients = c;
        q = new ConcurrentLinkedQueue();
    }
    
    public void queueModification(CharObject c, int cNum){
        q.add(new CharQ(c, cNum));
    }
    
    public void queueModification(DeleteObject d, int cNum){
        q.add(new CharQ(d, cNum));
    }

    @Override
    public void run() {
        synchronized(this){
            while(true){
                if(!q.isEmpty()){
                CharQ c = q.poll();
                for(Client client : clients){
                    if (client.getClientNumber() != c.getClientNum()){
                        if(c.getChar().getClass() == CharObject.class){
                            try {
                                client.insert((CharObject) c.getChar());
                            } catch (IOException ex) {
                                Logger.getLogger(Broadcaster.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else if(c.getChar().getClass() == DeleteObject.class){
                            try {
                                client.delete((DeleteObject) c.getChar());
                            } catch (IOException ex) {
                                Logger.getLogger(Broadcaster.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    }
                }
                }
            }
        }
    }
    
}
