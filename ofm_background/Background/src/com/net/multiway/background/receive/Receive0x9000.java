package com.net.multiway.background.receive;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;

/*Classe para recebimento de dados com o código: 0x9000000*/
public class Receive0x9000 extends Package {

    private DataInputStream in;
    private Vector<Integer> data;

    public Receive0x9000(DataInputStream in) {
        this.setIn(in);

    }

    public void parserPartA() throws IOException {
        byte[] d = new byte[4];
        in.read(d);
        int sampleFrequency = Utils.byte4ToInt(d);
        in.read(d);
        int rangeOfTest = Utils.byte4ToInt(d);
        in.read(d);
        int pulseWidth = Utils.byte4ToInt(d);

        in.read(d);
        int waveLength = Utils.byte4ToInt(d);

        in.read(d);
        int testTime = Utils.byte4ToInt(d);

        in.read(d);
        float groupRefractiveIndex = Utils.byte4ToFloat(d);

        in.read(d);
        float linkLength = Utils.byte4ToFloat(d);

        in.read(d);
        float linkLoss = Utils.byte4ToFloat(d);
        in.read(d);
        float linkAttenuation = Utils.byte4ToFloat(d);
        in.read(d);
        float nonReflectingThreshold = Utils.byte4ToFloat(d);
        in.read(d);
        float endThreshold = Utils.byte4ToFloat(d);
        in.read(d);
        float testMode = Utils.byte4ToFloat(d);
        in.read(d);
        int testWay = Utils.byte4ToInt(d);
    }

    public void parserPartB() throws IOException {
        byte[] d = new byte[4];

        in.read(d);
        this.length = Utils.byte4ToInt(d);
        data = new Vector<Integer>(this.length);
        byte[] b = new byte[2];

        for (int i = 0; i < this.length; i++) {
            in.read(b);
            data.add(Utils.byte2ToInt(b));
        }
    }

    public void parserPartC() throws IOException {
        byte[] d = new byte[4];

        in.read(d);
        this.length = Utils.byte4ToInt(d);

        byte[] b = new byte[4];

        for (int i = 0; i < this.length; i++) {
            in.read(d);
            int distance = Utils.byte4ToInt(d);
            in.read(d);
            int type = Utils.byte4ToInt(d);
            in.read(d);
            float echoLoss = Utils.byte4ToFloat(d);
            in.read(d);
            float insertionLoss = Utils.byte4ToFloat(d);
            in.read(d);
            float averageAttenuationCoefficient = Utils.byte4ToFloat(d);
            in.read(d);
            float acumulativeLoss = Utils.byte4ToFloat(d);
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

    public DataInputStream getIn() {
        return in;
    }

    private void setIn(DataInputStream in) {
        this.in = in;
    }

}
