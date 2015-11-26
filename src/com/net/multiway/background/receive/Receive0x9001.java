package com.net.multiway.background.receive;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;

/*Classe para recebimento de dados com o código: 0x9000001*/

public class Receive0x9001 extends Package {

	private Vector<Integer> data;
	private DataInputStream in;

	public Receive0x9001(DataInputStream in) {
		this.in = in;

	}

	@Override
	public void parser() throws IOException {
		byte[] d = new byte[4];

		in.read(d);
		this.length = Utils.byte4ToInt(d);
		data = new Vector<Integer>(this.length);

		byte[] b = new byte[2];

		for (int i = 0; i < this.length; i++) {
			in.read(b);
			data.add(Utils.byte2ToInt(b));	
			System.out.println("v = "+Utils.byte2ToInt(b));

		}
		in.read(d);

	}

}
