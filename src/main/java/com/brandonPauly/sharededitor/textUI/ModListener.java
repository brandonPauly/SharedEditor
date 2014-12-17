/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandonPauly.sharededitor.textUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Brandon
 */
class ModListener extends Thread{
    ObjectInputStream in;
    ClientUI cUI;

    public ModListener(ObjectInputStream i, ClientUI c){
        in = i;
        cUI = c;
    }
    
    @Override
    public void run(){
        while(true){
            Object co;
            try {
                while((co = in.readObject()) != null){
                    if(co.getClass() == CharObject.class){
                        cUI.insert((CharObject) co);
                    }
                    else if(co.getClass() == DeleteObject.class){
                        cUI.delete((DeleteObject) co);
                    }
                    
                }
            } catch (IOException ex) {
                Logger.getLogger(ModListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ModListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException ex) {
                Logger.getLogger(ModListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
