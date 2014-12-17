/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.brandonPauly.sharededitor.docserver;

import com.brandonPauly.sharededitor.textUI.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;




/**
 *
 * @author Brandon Pauly
 */
public class TextServer {
    
    public static void main(String args[]) throws IOException {
        int clientNum = 0;
        ArrayList<Client> clients = new ArrayList();
        ServerSocket ss = new ServerSocket(7000);
        Broadcaster bc = new Broadcaster(clients);
        bc.start();
        while(true){
            Socket clientSocket = ss.accept();
            System.out.println("Created socket with client");
            Client c = new Client(clientSocket, bc, clientNum);
            clients.add(c);
            c.start();
            clientNum++;
        }
    }
}
