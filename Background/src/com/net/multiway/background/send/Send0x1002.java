package com.net.multiway.background.send;

import java.io.DataOutputStream;
import java.io.IOException;

import com.net.multiway.background.data.Data0x1002;
import com.net.multiway.background.model.Header;
import com.net.multiway.background.utils.Utils;

/*Classe para envio de dados do código: 0x10000002*/
public class Send0x1002 {
	private DataOutputStream os;
	private Header header;
	private Data0x1002 data;

	public Send0x1002(DataOutputStream out, Data0x1002 data) {
		this.os = out;
		this.header = new Header(0x00000068, 0x10000002, 0x00000030);
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

		// data
		os.writeBytes(this.data.getData());

		// rsvd_4
		os.write(Utils.intToByteArray(this.header.getRSVD2_ui4()));
	}
}
