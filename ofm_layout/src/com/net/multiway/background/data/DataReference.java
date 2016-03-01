/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATAREFERENCE")
@XmlRootElement
public class DataReference implements Serializable {

	private Long ID;

	private DataDevice device;
	private DataParameters parameters;
	private DataReceive dataReceive;

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID")	
	public DataDevice getDevice() {
		return device;
	}

	public void setDevice(DataDevice device) {
		this.device = device;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID")	
	public DataParameters getParameters() {
		return parameters;
	}

	public void setParameters(DataParameters parameters) {
		this.parameters = parameters;
	}

	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ID")	
	public DataReceive getDataReceive() {
		return dataReceive;
	}

	public void setDataReceive(DataReceive dataReceive) {
		this.dataReceive = dataReceive;
	}

	@Id
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

}
