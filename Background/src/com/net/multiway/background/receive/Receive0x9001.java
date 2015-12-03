package com.net.multiway.background.receive;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

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
public class Receive0x9001 extends Package {

	private int[] data;
	private DataInputStream in;

	public Receive0x9001(DataInputStream in) {
		this.in = in;

	}

	public Receive0x9001() {

	}

	@Override
	public void parser() throws IOException {
		byte[] d = new byte[4];

		in.read(d);
		this.length = Utils.byte4ToInt(d);
		data = new int[this.length];

		byte[] b = new byte[2];

		for (int i = 0; i < this.length; i++) {
			in.read(b);
			data[i] = Utils.byte2ToInt(b);

		}
		in.read(d);

	}

	public void print() {

		try {
			PrintWriter out	= new PrintWriter(new BufferedWriter(new FileWriter("ResultTest.txt", true)));
			out.println("Frame");
			for (int i = 0; i < this.length; i++) {
				out.println(data[i]);
			}
			out.flush();
		} catch (IOException ex) {
			Logger.getLogger(Receive0x9001.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
