/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATA0X9001")
@XmlRootElement
public class Data0x9001 implements Serializable{
	private int lenght;
	private int data[];
	private Long ID;

	public Data0x9001(int lenght, int[] data) {
		this.lenght = lenght;
		this.data = data;
	}

	@Basic(optional = false)
	@Column(name = "Lenght")
	public int getLenght() {
		return lenght;
	}

	public void setLenght(int lenght) {
		this.lenght = lenght;
	}
	@OneToMany( mappedBy = "DATA0X9001", cascade = CascadeType.ALL)	
	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

	@Id
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}
	public void copy(Data0x9001 data)
	{
		this.setLenght(data.getLenght());
		this.setID(data.getID());
		this.setData(data.getData());
	}
	
}
