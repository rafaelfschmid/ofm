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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATARECEIVEPARAMETERSEVENTS")
@XmlRootElement
public class DataReceiveParametersEvents implements Serializable {

	private ObjectProperty<Integer> distance;
	private ObjectProperty<Integer> type;
	private ObjectProperty<Float> echoLoss;
	private ObjectProperty<Float> insertionLoss;
	private ObjectProperty<Float> averageAttenuationCoefficient;
	private ObjectProperty<Float> acumulativeLoss;
	private Long ID;

	public DataReceiveParametersEvents() {

		this.distance = new SimpleObjectProperty<>();
		this.type = new SimpleObjectProperty<>();
		this.echoLoss = new SimpleObjectProperty<>();
		this.insertionLoss = new SimpleObjectProperty<>();
		this.averageAttenuationCoefficient = new SimpleObjectProperty<>();
		this.acumulativeLoss = new SimpleObjectProperty<>();

	}

	public DataReceiveParametersEvents(int distance, int type, float echoLoss, float insertionLoss, float averageAttenuationCoefficient, float acumulativeLoss) {

		this.distance = new SimpleObjectProperty<>(distance);
		this.type = new SimpleObjectProperty<>(type);
		this.echoLoss = new SimpleObjectProperty<>(echoLoss);
		this.insertionLoss = new SimpleObjectProperty<>(insertionLoss);
		this.averageAttenuationCoefficient = new SimpleObjectProperty<>(averageAttenuationCoefficient);
		this.acumulativeLoss = new SimpleObjectProperty<>(acumulativeLoss);
	}

	@Basic(optional = false)
	@Column(name = "Distance")
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
	@Column(name = "Type")
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
	@Column(name = "EchoLoss")
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
	@Column(name = "InsertionLoss")
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
	@Column(name = "AverageAttenuationCoefficient")
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
	@Column(name = "AcumulativeLoss")
	public Float getAcumulativeLoss() {
		return acumulativeLoss.get();
	}

	public void setAcumulativeLoss(float acumulativeLoss) {
		this.acumulativeLoss.set(acumulativeLoss);
	}

	public ObjectProperty<Float> acumulativeLossProperty() {
		return this.acumulativeLoss;
	}

	public void copy(DataReceiveParametersEvents data) {
		setAcumulativeLoss(data.getAcumulativeLoss());
		setAverageAttenuationCoefficient(data.getAverageAttenuationCoefficient());
		setDistance(data.getDistance());
		setEchoLoss(data.getEchoLoss());
		setInsertionLoss(data.getInsertionLoss());
		setType(data.getType());
	}

	@Id
	public Long getID() {
		return ID;
	}

	public void setID(Long ID) {
		this.ID = ID;
	}

}
