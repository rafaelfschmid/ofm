/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import java.util.ArrayList;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATARECEIVEEVENTS")
@XmlRootElement
public class DataReceiveEvents implements Serializable {

    private ObjectProperty<Integer> distance;
    private ObjectProperty<Integer> type;
    private ObjectProperty<Float> echoLoss;
    private ObjectProperty<Float> insertionLoss;
    private ObjectProperty<Float> averageAttenuationCoefficient;
    private ObjectProperty<Float> acumulativeLoss;

    private DataReceive dataReceive;

    private Long ID;
    private Long event_id;

    public DataReceiveEvents() {

        this.distance = new SimpleObjectProperty<>();
        this.type = new SimpleObjectProperty<>();
        this.echoLoss = new SimpleObjectProperty<>();
        this.insertionLoss = new SimpleObjectProperty<>();
        this.averageAttenuationCoefficient = new SimpleObjectProperty<>();
        this.acumulativeLoss = new SimpleObjectProperty<>();

    }

    public DataReceiveEvents(int distance, int type, float echoLoss, float insertionLoss, float averageAttenuationCoefficient, float acumulativeLoss) {

        this.distance = new SimpleObjectProperty<>(distance);
        this.type = new SimpleObjectProperty<>(type);
        this.echoLoss = new SimpleObjectProperty<>(echoLoss);
        this.insertionLoss = new SimpleObjectProperty<>(insertionLoss);
        this.averageAttenuationCoefficient = new SimpleObjectProperty<>(averageAttenuationCoefficient);
        this.acumulativeLoss = new SimpleObjectProperty<>(acumulativeLoss);
    }

    @Basic(optional = false)
    @Column(name = "DISTANCE")
    public Integer getDistance() {
        return distance.get();
    }

    public void setDistance(int distance) {
        this.distance.set(distance);
    }

    public ObjectProperty<Integer> distanceProperty() {
        return this.distance;
    }

    @Basic(optional = false)
    @Column(name = "TYPE")
    public Integer getType() {
        return type.get();
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public ObjectProperty<Integer> typeProperty() {
        return this.type;
    }

    @Basic(optional = false)
    @Column(name = "ECHOLOSS")
    public Float getEchoLoss() {
        return echoLoss.get();
    }

    public void setEchoLoss(float echoLoss) {
        this.echoLoss.set(echoLoss);
    }

    public ObjectProperty<Float> echoLossProperty() {
        return this.echoLoss;
    }

    @Basic(optional = false)
    @Column(name = "INSERTIONLOSS")
    public Float getInsertionLoss() {
        return insertionLoss.get();
    }

    public void setInsertionLoss(float insertionLoss) {
        this.insertionLoss.set(insertionLoss);
    }

    public ObjectProperty<Float> insertLossProperty() {
        return this.insertionLoss;
    }

    @Basic(optional = false)
    @Column(name = "AVERAGEATTENUATIONCOEFFICIENT")
    public Float getAverageAttenuationCoefficient() {
        return averageAttenuationCoefficient.get();
    }

    public void setAverageAttenuationCoefficient(float averageAttenuationCoefficient) {
        this.averageAttenuationCoefficient.set(averageAttenuationCoefficient);
    }

    public ObjectProperty<Float> averageAttenuationCoefficientProperty() {
        return this.averageAttenuationCoefficient;
    }

    @Basic(optional = false)
    @Column(name = "ACUMULATIVELOSS")
    public Float getAcumulativeLoss() {
        return acumulativeLoss.get();
    }

    public void setAcumulativeLoss(float acumulativeLoss) {
        this.acumulativeLoss.set(acumulativeLoss);
    }

    public ObjectProperty<Float> acumulativeLossProperty() {
        return this.acumulativeLoss;
    }

//	@Basic(optional = false)
//	@Column(name = "EVENT_ID")
//	public Long getEvent_id() {
//		return event_id;
//	}
//
//	public void setEvent_id(Long event_id) {
//		this.event_id = event_id;
//	}
    public void copy(DataReceiveEvents data) {
        setAcumulativeLoss(data.getAcumulativeLoss());
        setAverageAttenuationCoefficient(data.getAverageAttenuationCoefficient());
        setDistance(data.getDistance());
        setEchoLoss(data.getEchoLoss());
        setInsertionLoss(data.getInsertionLoss());
        setType(data.getType());
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVENT_ID")
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    //@JoinColumn(name = "DATA_RECEIVE")
//	@ManyToOne
//        @PrimaryKeyJoinColumn(name="OWNER_ID", referencedColumnName="EMP_ID")
    @ManyToOne
//    @Id
//    @JoinColumn(name = "DATA_ID", referencedColumnName = "ID")
    public DataReceive getDataReceive() {
        return dataReceive;
    }

    public void setDataReceive(DataReceive dataReceive) {
        this.dataReceive = dataReceive;
    }

}
