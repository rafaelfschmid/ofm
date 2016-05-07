package com.net.multiway.background.receive;

import com.net.multiway.background.data.Data;
import com.net.multiway.background.data.DataEvents;
import java.io.DataInputStream;
import java.io.IOException;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*Classe para recebimento de dados com o código: 0x9000000*/
public class ReceiveParameters extends Package {

    private DataInputStream in;
    private Data data;

    public ReceiveParameters() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Data getData() {
        return data;
    }
    
    public void setData(Data data) {
        this.data = data;
    }
    
    public ReceiveParameters(DataInputStream in) {
        this.setIn(in);
        data = new Data();
    }

    public DataInputStream getIn() {
        return in;
    }

    private void setIn(DataInputStream in) {
        this.in = in;
    }

    public void parserPartA() throws IOException {
        byte[] d = new byte[4];
        in.read(d);
        data.setSampleFrequency(Utils.byte4ToInt(d));
        in.read(d);
        data.setRangeOfTest(Utils.byte4ToInt(d));
        in.read(d);
        data.setPulseWidth(Utils.byte4ToInt(d));

        in.read(d);
        data.setWaveLength(Utils.byte4ToInt(d));

        in.read(d);
        data.setTestTime(Utils.byte4ToInt(d));

        in.read(d);
        data.setGroupRefractiveIndex(Utils.byte4ToFloat(d));

        in.read(d);
        data.setLinkLength(Utils.byte4ToFloat(d));

        in.read(d);
        data.setLinkLoss(Utils.byte4ToFloat(d));
        in.read(d);
        data.setLinkAttenuation(Utils.byte4ToFloat(d));
        in.read(d);
        data.setNonReflectingThreshold(Utils.byte4ToFloat(d));
        in.read(d);
        data.setEndThreshold(Utils.byte4ToFloat(d));
        in.read(d);
        data.setTestMode(Utils.byte4ToInt(d));
        in.read(d);
        data.setTestWay(Utils.byte4ToInt(d));

    }

    public void parserPartB() throws IOException {
        byte[] d = new byte[4];

        in.read(d);
        this.length = Utils.byte4ToInt(d);
        List<Integer> dt = new ArrayList<>();
        byte[] b = new byte[2];

        for (int i = 0; i < this.length; i++) {
            in.read(b);
            dt.add(Utils.byte2ToInt(b));
        }
        data.setGraphData(dt);
    }

    public void parserPartC() throws IOException {
        byte[] d = new byte[4];

        in.read(d);
        this.length = Utils.byte4ToInt(d);

        byte[] b = new byte[4];

        for (int i = 0; i < this.length; i++) {
            DataEvents dt = new DataEvents();
            in.read(d);
            dt.setDistance(Utils.byte4ToInt(d));
            in.read(d);
            dt.setType(Utils.byte4ToInt(d));
            in.read(d);
            dt.setEchoLoss(Utils.byte4ToFloat(d));
            in.read(d);
            dt.setInsertionLoss(Utils.byte4ToFloat(d));
            in.read(d);
            dt.setAverageAttenuationCoefficient(Utils.byte4ToFloat(d));
            in.read(d);
            dt.setAcumulativeLoss(Utils.byte4ToFloat(d));
            dt.setData(data);
            data.addEvents(dt);
        }

    }

    @Override
    public void parser() throws IOException {
        parserPartA();
        parserPartB();
        parserPartC();
        byte[] d = new byte[4];
        in.read(d);
    }

    public void print() {
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("DadosExportados.txt", false)));
            printPartA(out);
            printPartC(out);
            printPartB(out);
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void printPartA(PrintWriter out) {
        out.println("/**************************************************************/");
        out.println("/**********************INPUT PARAMETERS************************/");
        out.println("/**************************************************************/");
        out.println("SampleFrequency = " + data.getSampleFrequency());
        out.println("RangeOfTest = " + data.getRangeOfTest());
        out.println("PulseWidth = " + data.getPulseWidth());
        out.println("WaveLength = " + data.getWaveLength());
        out.println("TestTime = " + data.getTestTime());
        out.println("GroupRefractiveIndex = " + data.getGroupRefractiveIndex());
        out.println("LinkLength = " + data.getLinkLength());
        out.println("LinkLoss = " + data.getLinkLoss());
        out.println("LinkAttenuation = " + data.getLinkAttenuation());
        out.println("NonReflectingThreshold = " + data.getNonReflectingThreshold());
        out.println("EndThreshold = " + data.getEndThreshold());
        out.println("TestMode = " + data.getTestMode());
        out.println("TestWay = " + data.getTestWay());
        out.println();
    }

    public void printPartB(PrintWriter out) {

        out.println("/**************************************************************/");
        out.println("/************************MEAN VALUES***************************/");
        out.println("/**************************************************************/");

        for (Integer value : data.getGraphData()) {
            out.println(value);
        }
        out.println();
    }

    public void printPartC(PrintWriter out) {
        List<DataEvents> ar = data.getEvents();
        out.println("/**************************************************************/");
        out.println("/***********************OUTPUT EVENTS**************************/");
        out.println("/**************************************************************/");
        int i = 1;
        for (DataEvents data : ar) {
            out.println("EVENT " + i++);
            out.println("Distance = " + data.getDistance());
            out.println("Type = " + data.getType());
            out.println("Insertion Loss = " + data.getInsertionLoss());
            out.println("Echo Loss = " + data.getEchoLoss());
            out.println("Average Attenuation Coefficient = " + data.getAverageAttenuationCoefficient());
            out.println("Acumulative Loss = " + data.getAcumulativeLoss());
            out.println();
        }
    }
}
