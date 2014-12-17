/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandonPauly.sharededitor.docserver;

import com.brandonPauly.sharededitor.textUI.CharObject;
import com.brandonPauly.sharededitor.textUI.DeleteObject;

/**
 *
 * @author Brandon
 */
class CharQ {
    private int clientNum;
    private Object charObject;

    public CharQ(Object c, int cNum) {
        clientNum = cNum;
        charObject = c;
    }
    
//    public CharQ(DeleteObject d, int cNum){
//        clientNum = cNum;
//        delObject = d;
//    }
    
    public Object getChar(){
        return charObject;
    }
    
    public int getClientNum(){
        return clientNum;
    }
    
}
