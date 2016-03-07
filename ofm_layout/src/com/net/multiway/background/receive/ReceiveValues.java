package com.net.multiway.background.receive;

import com.net.multiway.background.data.DataReceiveValues;
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

/*Classe para recebimento de dados com o código: 0x9000001*/
public class ReceiveValues extends Package {

    private DataInputStream in;
    private List<int[]> lstValues;
    //private DataReceiveValues dataReceiveValues;

    public ReceiveValues(DataInputStream in) {
        this.in = in;
        lstValues = new ArrayList<>();
        //dataReceiveValues = new DataReceiveValues();
    }

    public ReceiveValues() {
        //dataReceiveValues = new DataReceiveValues();
        lstValues = new ArrayList<>();
    }

    public void setInputStream(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void parser() throws IOException {
        byte[] d = new byte[4];

        in.read(d);
        this.length = Utils.byte4ToInt(d);
        int[] values = new int[length];

        byte[] b = new byte[2];

        for (int i = 0; i < this.length; i++) {
            in.read(b);
            values[i] = Utils.byte2ToInt(b);
        }

        //this.dataReceiveValues.add(values);
        this.lstValues.add(values);
        in.read(d);
    }

    public void print() {

        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("DadosExportados.txt", true)));
            int i = 1;
            out.println("/**************************************************************/");
            out.println("/**********************REAL TIME VALUES************************/");
            out.println("/**************************************************************/");
            for (int[] values : this.lstValues) {
                out.println("Frame " + i);
                for (int value : values) {
                    out.println(value);
                }
                i++;
            }
            out.println();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(ReceiveValues.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int[] getDataValues() {
        int[] mediaValues = new int[this.length];
        int count = 0;

        for (int[] values : this.lstValues) {
            for (int i = 0; i < this.length; i++) {
                mediaValues[i] += values[i];
            }
            count++;
        }

        for (int i = 0; i < this.length; i++) {
            mediaValues[i] = mediaValues[i] / count;
        }

        return mediaValues;
    }
}
