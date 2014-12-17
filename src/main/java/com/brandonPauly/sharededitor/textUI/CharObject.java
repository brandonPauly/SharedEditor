/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandonPauly.sharededitor.textUI;

import java.io.Serializable;

/**
 *
 * @author Brandon
 */
public class CharObject implements Serializable{
    private final char letter;
    private final int position;
    
    CharObject(int p, char l){
        letter = l;
        position = p;
    }
    
    public char getChar(){
        return letter;
    }
    
    public int getPosition(){
        return position;
    }
}
