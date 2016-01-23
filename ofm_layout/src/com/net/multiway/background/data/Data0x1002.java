package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATA0X1002")
@XmlRootElement

public class Data0x1002 implements Data, Serializable {

	private String ip;
	private String mask;
	private String gateway;

	private Long ID;

	public Data0x1002() {
	}

	public Data0x1002(Long id, String ip, String mask, String gateway) {
		this.ID = id;
		this.ip = Utils.fillAddress(ip);
		this.mask = Utils.fillAddress(mask);
		this.gateway = Utils.fillAddress(gateway);
	}

	public String takeData() {

		return this.ip + this.mask + this.gateway;
	}
	
	
	@Id
	@Basic(optional = false)
	@Column(name = "ID")
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

	@Basic(optional = false)
	@Column(name = "IP")
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	@Basic(optional = false)
	@Column(name = "MASK")
	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	@Basic(optional = false)
	@Column(name = "GATEWAY")
	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	
	public void copy(Data0x1002 data)
	{
		setID(data.getID());
		setIp(data.getIp());
		setMask(data.getMask());
		setGateway(data.getGateway());
	}

}
