package com.net.multiway.background.model;

import com.net.multiway.background.MainApp;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.StopTest;
import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.receive.ReceiveParameters;
import com.net.multiway.background.receive.ReceiveValues;
import com.net.multiway.background.receive.ReceiveStatus;
import com.net.multiway.background.send.SendParameters;
import com.net.multiway.background.send.SendStopTest;
import com.net.multiway.background.send.SendDevice;
import com.net.multiway.background.send.SendConfirmationSignal;
import com.net.multiway.background.utils.Utils;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeviceComunicator {

    private int door;
    private String ip;

    private DataInputStream in;
    private DataOutputStream out;
    private Socket client;
    private ReceiveStatus receiveStatusData;
    private ReceiveValues receiveValues;
    private ReceiveParameters receiveParametersData;

    public DeviceComunicator(String i, int d) {
        this.door = d;
        this.ip = i;

        receiveValues = new ReceiveValues(this.in);
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

    public void initialize() throws Exception {
        try {
            this.client = new Socket();
            this.client.connect(new InetSocketAddress(this.ip, this.door), 2000);
            System.out.println("O cliente se conectou ao OTDR!");
            this.in = new DataInputStream(client.getInputStream());
            this.out = new DataOutputStream(client.getOutputStream());
        } catch (Exception ex) {
            throw new Exception("Socket could not connect to the host " + this.ip + ".", ex);
        }
    }

    private void sendPackage(DataParameters data) throws IOException {
        SendParameters s = new SendParameters(this.out, data);
        s.sender();
    }

    private void sendPackage(StopTest data) throws IOException {
        SendStopTest s = new SendStopTest(this.out, data);
        s.sender();
    }

    private void sendPackage(DataDevice data) throws IOException {
        SendDevice s = new SendDevice(this.out, data);
        s.sender();
    }

    private void sendPackage() throws IOException {
        SendConfirmationSignal s = new SendConfirmationSignal(this.out);
        s.sender();
    }

    private boolean receivePackage() throws Exception {

        try {
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
                receiveStatusData = new ReceiveStatus(this.in, Utils.byte4ToInt(DataLen));
                receiveStatusData.parser();
                return true;
            } else if (Utils.byte4ToInt(CMcode) == 0x90000001) {
                receiveValues.setInputStream(this.in);
                receiveValues.parser();
                return true;
            } else if (Utils.byte4ToInt(CMcode) == 0x90000000) {
                receiveParametersData = new ReceiveParameters(this.in);
                receiveParametersData.parser();
                return false;
            } else {

                return false;
            }
        } catch (Exception ex) {
            throw new Exception("Error while reading package.", ex);
        }

    }

    public void connect(DataParameters data) throws Exception {

        this.initialize();
        try {
            sendPackage(data);
            boolean flag = true;
            int i = 0;
            while (flag) {
                String msg = "Receiving frame " + i + ".";
                Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
                i++;

                flag = receivePackage();

                sendPackage();
            }
        } catch (Exception ex) {
            Logger.getLogger(DeviceComunicator.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.client.close();
        }

    }

    public void connect(StopTest data) throws Exception {

        this.initialize();
        try {

            sendPackage(data);
            sendPackage();

        } catch (Exception ex) {
            Logger.getLogger(DeviceComunicator.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.client.close();
        }
    }

    public void connect(DataDevice data) throws Exception {

        this.initialize();
        try {

            sendPackage(data);
            sendPackage();

        } catch (Exception ex) {
            Logger.getLogger(DeviceComunicator.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        } finally {
            this.client.close();
        }
    }

    public ReceiveValues getReceiveValues() {
        return this.receiveValues;
    }

    public ReceiveParameters getReceiveParametersData() {
        return receiveParametersData;
    }

    public void runMonitor(int time, DataParameters data) throws Exception {
        connect(data);
        long ti = System.currentTimeMillis();
        long tf = ti + (time * 1000);
        while (true) {
            System.out.println(tf - ti);
            if (ti > tf) {
                connect(data);
                ti = System.currentTimeMillis();
                tf = ti + (time * 1000);
            }
            ti = System.currentTimeMillis();
        }
    }

}
