package com.net.multiway.background.receive;

import com.net.multiway.background.data.DataReceiveValues;
import java.io.DataInputStream;
import java.io.IOException;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Classe para recebimento de dados com o código: 0x9000001*/
public class ReceiveValues extends Package {

    private DataReceiveValues dataReceiveValues;
    private DataInputStream in;

    public ReceiveValues(DataInputStream in) {
        this.in = in;
        dataReceiveValues = new DataReceiveValues();
    }

    public ReceiveValues() {
        dataReceiveValues = new DataReceiveValues();
    }

    public void setInputStream(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void parser() throws IOException {
        byte[] d = new byte[4];

        dataReceiveValues.setNumberOfCalls(dataReceiveValues.getNumberOfCalls() + 1);

        in.read(d);
        if (dataReceiveValues.getLenght() == 0) {
            this.length = Utils.byte4ToInt(d);
            dataReceiveValues.setLenght(length);
        }

        byte[] b = new byte[2];

        for (int i = 0; i < this.length; i++) {
            in.read(b);
            dataReceiveValues.addValueDataIndex(i, Utils.byte2ToInt(b));
        }

        in.read(d);
    }

    public void print() {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ResultTest.txt", true)));
            out.println("Frame");
            for (int i = 0; i < this.length; i++) {
                out.println(dataReceiveValues.getData()[i]);
            }
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int[] getDataValues() {
        return this.dataReceiveValues.getData();
    }

    public void processData() {
        this.dataReceiveValues.processData();
    }
}
