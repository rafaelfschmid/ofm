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
	//	System.out.println(sampleFrequency);
		in.read(d);
		int rangeOfTest = Utils.byte4ToInt(d);
//		System.out.println(rangeOfTest);

		in.read(d);
		int pulseWidth = Utils.byte4ToInt(d);
	//	System.out.println(pulseWidth);

		in.read(d);
		int waveLength = Utils.byte4ToInt(d);
//		System.out.println(waveLength);

		in.read(d);
		int testTime = Utils.byte4ToInt(d);
	//	System.out.println(testTime);

		in.read(d);
		float groupRefractiveIndex = Utils.byte4ToFloat(d);
//			System.out.println(Integer.toHexString(d[0])+Integer.toHexString(d[1])+Integer.toHexString(d[2])+Integer.toHexString(d[3]));

		in.read(d);
		float linkLength = Utils.byte4ToFloat(d);
//		System.out.println("linkLength ="+linkLength);

		in.read(d);
		float linkLoss = Utils.byte4ToFloat(d);
	//	System.out.println(Integer.toHexString(d[0])+Integer.toHexString(d[1])+Integer.toHexString(d[2])+Integer.toHexString(d[3]));
		in.read(d);
		float linkAttenuation = Utils.byte4ToFloat(d);
		System.out.println("linkAtenuation ="+linkAttenuation);
		in.read(d);
		float nonReflectingThreshold = Utils.byte4ToFloat(d);
		//System.out.println(Integer.toHexString(d[0])+Integer.toHexString(d[1])+Integer.toHexString(d[2])+Integer.toHexString(d[3]));
		in.read(d);
		float endThreshold = Utils.byte4ToFloat(d);
	//	System.out.println(Integer.toHexString(d[0])+Integer.toHexString(d[1])+Integer.toHexString(d[2])+Integer.toHexString(d[3]));
		in.read(d);
		float testMode = Utils.byte4ToFloat(d);
	//	System.out.println(testMode);
		in.read(d);
		int testWay = Utils.byte4ToInt(d);
	//	System.out.println(testWay);
		System.out.println("A");
	}

	public void parserPartB() throws IOException {
		byte[] d = new byte[4];

		in.read(d);
		this.length = Utils.byte4ToInt(d);
		data = new Vector<Integer>(this.length);
		System.out.println(this.length);

		byte[] b = new byte[2];

		for (int i = 0; i < this.length; i++) {
			in.read(b);
			data.add(Utils.byte2ToInt(b));
		//	System.out.println("v = " + Utils.byte2ToInt(b));

		}
		
		System.out.println("B");
	}

	public void parserPartC() throws IOException {
		byte[] d = new byte[4];

		in.read(d);
		this.length = Utils.byte4ToInt(d);
		System.out.println(this.length);
		

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
		System.out.println("C");
	}

	@Override
	public void parser() throws IOException {
		parserPartA();
		parserPartB();
		parserPartC();
		byte[] d = new byte[4];
		in.read(d);
		//for (int i = 0; i < 4; i++)
			//System.out.println(Integer.toHexString(d[i]));
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

}
