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
public class DeleteObject implements Serializable{
    int location;

    DeleteObject(int inputLoc) {
        location = inputLoc;
    }
    
    public int getLocation(){
        return location;
    }
    
}
