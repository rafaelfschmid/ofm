package com.net.multiway.background.receive;

import com.net.multiway.background.data.DataReceive;
import com.net.multiway.background.data.DataReceiveEvents;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Vector;

import com.net.multiway.background.model.Package;
import com.net.multiway.background.utils.Utils;
import java.util.ArrayList;

/*Classe para recebimento de dados com o código: 0x9000000*/
public class ReceiveParameters extends Package {

	private DataInputStream in;
	private DataReceive data;

	public DataReceive getData() {
		return data;
	}

	public ReceiveParameters(DataInputStream in) {
		this.setIn(in);
		data = new DataReceive();
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
		data.setTestMode(Utils.byte4ToFloat(d));
		in.read(d);
		data.setTestWay(Utils.byte4ToInt(d));

	}

	public void parserPartB() throws IOException {
		byte[] d = new byte[4];

		in.read(d);
		this.length = Utils.byte4ToInt(d);
		ArrayList<Integer> dt = new ArrayList<Integer>(this.length);
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
		System.out.println("Length = " + this.length);
		byte[] b = new byte[4];

		for (int i = 0; i < this.length; i++) {
			DataReceiveEvents dt = new DataReceiveEvents();
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
			data.addEvents(dt);
		}

	}

	@Override
	public void parser() throws IOException {
		parserPartA();
		//printPartA();
		parserPartB();
		parserPartC();
		printPartC();
		byte[] d = new byte[4];
		in.read(d);
	}

	public void printPartA() {

		System.out.println("SampleFrequency = " + data.getSampleFrequency());
		System.out.println("RangeOfTest = " + data.getRangeOfTest());
		System.out.println("PulseWidth = " + data.getPulseWidth());
		System.out.println("WaveLength = " + data.getWaveLength());
		System.out.println("TestTime = " + data.getTestTime());
		System.out.println("GroupRefractiveIndex = " + data.getGroupRefractiveIndex());
		System.out.println("LinkLength = " + data.getLinkLength());
		System.out.println("LinkLoss = " + data.getLinkLoss());
		System.out.println("LinkAttenuation = " + data.getLinkAttenuation());
		System.out.println("NonReflectingThreshold = " + data.getNonReflectingThreshold());
		System.out.println("EndThreshold = " + data.getEndThreshold());
		System.out.println("TestMode = " + data.getTestMode());
		System.out.println("TestWay = " + data.getTestWay());

	}

	public void printPartC() {
		ArrayList<DataReceiveEvents> ar = (ArrayList<DataReceiveEvents>)data.getEvents();
		for (int i = 0; i < ar.size(); i++) {
			System.out.println("Distance = " + ar.get(i).getDistance());
			System.out.println("Type = " + ar.get(i).getType());
			System.out.println("Insertion Loss = " + ar.get(i).getInsertionLoss());
			System.out.println("Echo Loss = " + ar.get(i).getEchoLoss());
			System.out.println("Average Attenuation Coefficient = " + ar.get(i).getAverageAttenuationCoefficient());
			System.out.println("Acumulative Loss = " + ar.get(i).getAcumulativeLoss());
		}

	}

}
