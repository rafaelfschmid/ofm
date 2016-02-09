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
public class DataReceiveValues implements Serializable {

    private int lenght;
    private int data[];
    private Long ID;
    private int numberOfCalls;

    public DataReceiveValues() {
        this.lenght = 0;
        this.numberOfCalls = 0;
    }

    @Basic(optional = false)
    @Column(name = "Lenght")
    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
        data = new int[this.lenght];
    }

    @OneToMany(mappedBy = "DATA0X9001", cascade = CascadeType.ALL)
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

    public int getNumberOfCalls() {
        return numberOfCalls;
    }

    public void setNumberOfCalls(int numberOfCalls) {
        this.numberOfCalls = numberOfCalls;
    }

    public void addValueDataIndex(int i, int value) {
        this.data[i] += value;
    }
    
    public void processData() {
        for(int i = 0; i < data.length; i++){
            data[i] = data[i]/numberOfCalls;
        }
    }

    public void copy(DataReceiveValues data) {
        this.setLenght(data.getLenght());
        this.setID(data.getID());
        this.setData(data.getData());
    }
}
