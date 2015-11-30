package com.net.multiway.background.receive;

import java.io.DataInputStream;
import java.io.IOException;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;
import java.io.FileWriter;
import java.io.PrintWriter;

/*Classe para recebimento de dados com o código: 0x9000001*/
public class Receive0x9001 extends Package {

    private int [] data;
    private DataInputStream in;

    public Receive0x9001(DataInputStream in) {
        this.in = in;

    }

    @Override
    public void parser() throws IOException {
        byte[] d = new byte[4];

        in.read(d);
        this.length = Utils.byte4ToInt(d);
        data = new int[this.length];

        FileWriter file = new FileWriter("resultTest.txt");
        PrintWriter writer = new PrintWriter(file);

        byte[] b = new byte[2];

        for (int i = 0; i < this.length; i++) {
            in.read(b);
            data[i] = Utils.byte2ToInt(b);
            writer.println(data[i]);    
        }
        in.read(d);

    }
    

}
