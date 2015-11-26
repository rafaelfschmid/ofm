package com.net.multiway.background.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.net.multiway.background.data.Data;
import com.net.multiway.background.data.Data0x1000;
import com.net.multiway.background.data.Data0x1001;
import com.net.multiway.background.data.Data0x1002;
import com.net.multiway.background.receive.Receive0x9000;
import com.net.multiway.background.receive.Receive0x9001;
import com.net.multiway.background.receive.Receive0xA000;
import com.net.multiway.background.send.Send0x1000;
import com.net.multiway.background.send.Send0x1001;
import com.net.multiway.background.send.Send0x1002;
import com.net.multiway.background.send.Send0x1004;
import com.net.multiway.background.utils.Utils;

public class Host {

	private int door;
	private String ip;

	public Host(String i, int d) {
		this.door = d;
		this.ip = i;
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

	private void sendAPackage(int code, DataOutputStream os, Data data) throws IOException {
		

		if (code == 0x10000000) {
			Send0x1000 s = new Send0x1000(os, (Data0x1000) data);
			s.sender();
		} else if (code == 0x10000001) {
			Send0x1001 s = new Send0x1001(os, Data0x1001.CANCEL_TEST);
			s.sender();
		} else if (code == 0x10000002) {
			Send0x1002 s = new Send0x1002(os, (Data0x1002) data);
			s.sender();
		} else if (code == 0x10000004) {
			Send0x1004 s = new Send0x1004(os);
			s.sender();
		}

		// os.close();

	}

	private void receiveAPackage(DataInputStream is) throws IOException {

		byte[] b = new byte[60];
		try {

			// ignorando bytes inuteis
			is.read(b);
			while (true) {
				b = new byte[44];
				// ignorando cabecario
				is.read(b);
				byte[] CMcode = new byte[4];
				byte[] DataLen = new byte[4];
				is.read(CMcode);
				// System.out.println(Utils.byte4ToInt(DataLen));
				is.read(DataLen);
				// codigo de resposta padao
				if (Utils.byte4ToInt(CMcode) == 0xA0000000) {
					Receive0xA000 r = new Receive0xA000(is, Utils.byte4ToInt(DataLen));
					r.parser();
					break;
				}
				if (Utils.byte4ToInt(CMcode) == 0x90000001) {
					Receive0x9001 r = new Receive0x9001(is);
					r.parser();
					break;
				}
				if (Utils.byte4ToInt(CMcode) == 0x90000000) {
					Receive0x9000 r = new Receive0x9000(is);
					r.parser();
					break;
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void conection() {
		try {
			Socket client = new Socket(this.ip, this.door);
			System.out.println("O cliente se conectou ao OTDR!");
			DataInputStream is = null;
			is = new DataInputStream(client.getInputStream());
			DataOutputStream os = null;
			os = new DataOutputStream(client.getOutputStream());
			// BD
			Data0x1000 data = new Data0x1000(1, 0, 65.0f, 1, 1000, 1550, 0, 0, 15000, 1.4685f, 5.0f, 0);

			sendAPackage(0x10000000, os, data);
			for (int i = 0; i < 10; i++) {
				receiveAPackage(is);

				sendAPackage(0x10000004, os, data);

				System.out.println(i + 1);
			}
			


			client.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
