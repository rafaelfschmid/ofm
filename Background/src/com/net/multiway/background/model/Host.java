package com.net.multiway.background.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.net.multiway.background.data.Data0x1000;
import com.net.multiway.background.data.Data0x1001;
import com.net.multiway.background.data.Data0x1002;
import com.net.multiway.background.receive.Receive0x9000;
import com.net.multiway.background.receive.Receive0x9001;
import com.net.multiway.background.receive.Receive0xA000;
import com.net.multiway.background.send.Send0x1000;
import com.net.multiway.background.send.Send0x1001;
import com.net.multiway.background.send.Send0x1002;
import com.net.multiway.background.send.Send0x1004;
import com.net.multiway.background.utils.Utils;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Host {

    private int door;
    private String ip;

    private DataInputStream in;
    private DataOutputStream out;
    private Socket client;

    public Host(String i, int d) {
        this.door = d;
        this.ip = i;
    }

    public int getDoor() {
        return door;
    }

    public void setDoor(int door) {
        this.door = door;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void initialize() {
        try {
            this.client = new Socket(this.ip, this.door);
            System.out.println("O cliente se conectou ao OTDR!");
            this.in = new DataInputStream(client.getInputStream());
            this.out = new DataOutputStream(client.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendPackage(Data0x1000 data) throws IOException {
        Send0x1000 s = new Send0x1000(this.out, data);
        s.sender();
    }

    private void sendPackage(Data0x1001 data) throws IOException {
        Send0x1001 s = new Send0x1001(this.out, data);
        s.sender();
    }

    private void sendPackage(Data0x1002 data) throws IOException {
        Send0x1002 s = new Send0x1002(this.out, data);
        s.sender();
    }

    private void sendPackage() throws IOException {
        Send0x1004 s = new Send0x1004(this.out);
        s.sender();
    }

    private void receivePackage() throws IOException {

        // ignorando bytes inuteis
        byte[] b = new byte[60];
        this.in.read(b);
        
        // ignorando cabecario
        b = new byte[44];
        this.in.read(b);

        byte[] CMcode = new byte[4];
        byte[] DataLen = new byte[4];
        this.in.read(CMcode);
        this.in.read(DataLen);

        // codigo de resposta padao
        if (Utils.byte4ToInt(CMcode) == 0xA0000000) {
            Receive0xA000 r = new Receive0xA000(this.in, Utils.byte4ToInt(DataLen));
            r.parser();
        } else if (Utils.byte4ToInt(CMcode) == 0x90000001) {
            Receive0x9001 r = new Receive0x9001(this.in);
            r.parser();
        } else if (Utils.byte4ToInt(CMcode) == 0x90000000) {
            Receive0x9000 r = new Receive0x9000(this.in);
            r.parser();
        }
    }

    public void connect(Data0x1000 data) {

        this.initialize();
        try {
            
            sendPackage(data);
            
            for (int i = 0; i < 10; i++) {
                receivePackage();
                sendPackage();

                System.out.println(i + 1);
            }
            this.client.close();

        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void connect(Data0x1001 data) {

        this.initialize();
        try {
            
            sendPackage(data);
            sendPackage();
            this.client.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void connect(Data0x1002 data) {

        this.initialize();
        try {
            
            sendPackage(data);
            sendPackage();
            this.client.close();
        
        } catch (IOException ex) {
            Logger.getLogger(Host.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
