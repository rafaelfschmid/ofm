package com.net.multiway.background.send;

import java.io.DataOutputStream;
import java.io.IOException;

import com.net.multiway.background.data.StopTest;
import com.net.multiway.background.model.Header;
import com.net.multiway.background.utils.Utils;

/*Classe para envio de dados do código: 0x10000001*/
public class SendStopTest {
	private DataOutputStream os;
	private Header header;
	private StopTest data;
	
	public SendStopTest(DataOutputStream out, StopTest data) {
		this.os = out;
		this.header = new Header(0x0000003c, 0x10000001, 0x00000004);
		this.data = data;
	}

	public void sender() throws IOException {
		// strpkstr_16
		os.writeBytes(this.header.getSTPKSTR_s16());

		// pklen_4
		os.write(Utils.intToByteArray(this.header.getPKLEN_ui4()));
		// rev_4
		os.write(Utils.intToByteArray(this.header.getREV_ui4()));

		// pktpy_4
		os.write(Utils.intToByteArray(this.header.getPKTPY_ui4()));

		// src_4
		os.write(Utils.intToByteArray(this.header.getSRC_ui4()));

		// dst_4
		os.write(Utils.intToByteArray(this.header.getDST_ui4()));

		// pkid_4
		os.write(Utils.intToByteArray(this.header.getPKID_ui4()));

		// rsvd_4
		os.write(Utils.intToByteArray(this.header.getRSVD1_ui4()));

		// cmcode_4
		os.write(Utils.intToByteArray(this.header.getCMDCODE_ui4()));

		// datalen_4
		os.write(Utils.intToByteArray(this.header.getDATALEN_4()));

		// Data
		os.write(Utils.intToByteArray(data.getControlMode()));

		// rsvd_4
		os.write(Utils.intToByteArray(this.header.getRSVD2_ui4()));
	}
}
