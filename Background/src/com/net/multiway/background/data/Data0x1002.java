package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;

public class Data0x1002 implements Data{

	private String ip;
	private String mask;
	private String gateway;

	public Data0x1002(String ip, String mask, String gateway)
	{
		this.ip = Utils.fillAddress(ip);
		this.mask = Utils.fillAddress(mask);
		this.gateway = Utils.fillAddress(gateway);
	}
	
	public String getData(){

		return this.ip + this.mask + this.gateway;
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
}
