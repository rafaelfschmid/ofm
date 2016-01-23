/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import java.util.Vector;
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
@Table(name = "DATA0X9000")
@XmlRootElement
public class Data0x9000 implements Serializable {

	private int sampleFrequency;
	private int rangeOfTest;
	private int pulseWidth;
	private int waveLength;
	private int testTime;
	private float groupRefractiveIndex;
	private float linkLength;
	private float linkLoss;
	private float linkAttenuation;
	private float nonReflectingThreshold;
	private float endThreshold;
	private float testMode;
	private int testWay;
	private int lenght_vec;
	private Vector<Integer> data;
	private int distance;
	private int type;
	private float echoLoss;
	private float insertionLoss;
	private float averageAttenuationCoefficient;
	private float acumulativeLoss;

	private Long ID;

	public Data0x9000(int sampleFrequency, int rangeOfTest, int pulseWidth, int waveLength, int testTime, float groupRefractiveIndex, float linkLength, float linkLoss, float linkAttenuation, float nonReflectingThreshold, float endThreshold, float testMode, int testWay, int lenght_vec, Vector<Integer> data, int distance, int type, float echoLoss, float insertionLoss, float averageAttenuationCoefficient, float acumulativeLoss) {
		this.sampleFrequency = sampleFrequency;
		this.rangeOfTest = rangeOfTest;
		this.pulseWidth = pulseWidth;
		this.waveLength = waveLength;
		this.testTime = testTime;
		this.groupRefractiveIndex = groupRefractiveIndex;
		this.linkLength = linkLength;
		this.linkLoss = linkLoss;
		this.linkAttenuation = linkAttenuation;
		this.nonReflectingThreshold = nonReflectingThreshold;
		this.endThreshold = endThreshold;
		this.testMode = testMode;
		this.testWay = testWay;
		this.lenght_vec = lenght_vec;
		this.data = data;
		this.distance = distance;
		this.type = type;
		this.echoLoss = echoLoss;
		this.insertionLoss = insertionLoss;
		this.averageAttenuationCoefficient = averageAttenuationCoefficient;
		this.acumulativeLoss = acumulativeLoss;
	}

	@Basic(optional = false)
	@Column(name = "SampleFrequency")
	public int getSampleFrequency() {
		return sampleFrequency;
	}

	public void setSampleFrequency(int sampleFrequency) {
		this.sampleFrequency = sampleFrequency;
	}

	@Basic(optional = false)
	@Column(name = "RangeOfTest")
	public int getRangeOfTest() {
		return rangeOfTest;
	}

	public void setRangeOfTest(int rangeOfTest) {
		this.rangeOfTest = rangeOfTest;
	}

	@Basic(optional = false)
	@Column(name = "PulseWidth")
	public int getPulseWidth() {
		return pulseWidth;
	}

	public void setPulseWidth(int pulseWidth) {
		this.pulseWidth = pulseWidth;
	}

	@Basic(optional = false)
	@Column(name = "WaveLength")
	public int getWaveLength() {
		return waveLength;
	}

	public void setWaveLength(int waveLength) {
		this.waveLength = waveLength;
	}

	@Basic(optional = false)
	@Column(name = "TestTime")
	public int getTestTime() {
		return testTime;
	}

	public void setTestTime(int testTime) {
		this.testTime = testTime;
	}

	@Basic(optional = false)
	@Column(name = "GroupRefractiveIndex")
	public float getGroupRefractiveIndex() {
		return groupRefractiveIndex;
	}

	public void setGroupRefractiveIndex(float groupRefractiveIndex) {
		this.groupRefractiveIndex = groupRefractiveIndex;
	}

	@Basic(optional = false)
	@Column(name = "LinkLength")
	public float getLinkLength() {
		return linkLength;
	}

	public void setLinkLength(float linkLength) {
		this.linkLength = linkLength;
	}

	@Basic(optional = false)
	@Column(name = "LinkLoss")
	public float getLinkLoss() {
		return linkLoss;
	}

	public void setLinkLoss(float linkLoss) {
		this.linkLoss = linkLoss;
	}

	@Basic(optional = false)
	@Column(name = "LinkAttenuation")
	public float getLinkAttenuation() {
		return linkAttenuation;
	}

	public void setLinkAttenuation(float linkAttenuation) {
		this.linkAttenuation = linkAttenuation;
	}

	@Basic(optional = false)
	@Column(name = "NonReflectingThreshold")
	public float getNonReflectingThreshold() {
		return nonReflectingThreshold;
	}

	public void setNonReflectingThreshold(float nonReflectingThreshold) {
		this.nonReflectingThreshold = nonReflectingThreshold;
	}

	@Basic(optional = false)
	@Column(name = "EndThreshold")
	public float getEndThreshold() {
		return endThreshold;
	}

	public void setEndThreshold(float endThreshold) {
		this.endThreshold = endThreshold;
	}

	@Basic(optional = false)
	@Column(name = "TestMode")
	public float getTestMode() {
		return testMode;
	}

	public void setTestMode(float testMode) {
		this.testMode = testMode;
	}

	@Basic(optional = false)
	@Column(name = "TestWay")
	public int getTestWay() {
		return testWay;
	}

	public void setTestWay(int testWay) {
		this.testWay = testWay;
	}

	@Basic(optional = false)
	@Column(name = "Lenght_vec")
	public int getLenght_vec() {
		return lenght_vec;
	}

	public void setLenght_vec(int lenght_vec) {
		this.lenght_vec = lenght_vec;
	}

	@OneToMany( mappedBy = "DATA0X9000", cascade = CascadeType.ALL)
	public Vector<Integer> getData() {
		return data;
	}

	public void setData(Vector<Integer> data) {
		this.data = data;
	}

	@Basic(optional = false)
	@Column(name = "Distance")
	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Basic(optional = false)
	@Column(name = "Type")
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Basic(optional = false)
	@Column(name = "EchoLoss")
	public float getEchoLoss() {
		return echoLoss;
	}

	public void setEchoLoss(float echoLoss) {
		this.echoLoss = echoLoss;
	}

	@Basic(optional = false)
	@Column(name = "InsertionLoss")
	public float getInsertionLoss() {
		return insertionLoss;
	}

	public void setInsertionLoss(float insertionLoss) {
		this.insertionLoss = insertionLoss;
	}

	@Basic(optional = false)
	@Column(name = "AverageAttenuationCoefficient")
	public float getAverageAttenuationCoefficient() {
		return averageAttenuationCoefficient;
	}

	public void setAverageAttenuationCoefficient(float averageAttenuationCoefficient) {
		this.averageAttenuationCoefficient = averageAttenuationCoefficient;
	}

	@Basic(optional = false)
	@Column(name = "AcumulativeLoss")
	public float getAcumulativeLoss() {
		return acumulativeLoss;
	}

	public void setAcumulativeLoss(float acumulativeLoss) {
		this.acumulativeLoss = acumulativeLoss;
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
	public void copy(Data0x9000 data)
	{
		setAcumulativeLoss(data.getAcumulativeLoss());
		setAverageAttenuationCoefficient(data.getAverageAttenuationCoefficient());
		setData(data.getData());
		setDistance(data.getDistance());
		setEchoLoss(data.getEchoLoss());
		setEndThreshold(data.getEndThreshold());
		setGroupRefractiveIndex(data.getGroupRefractiveIndex());
		setID(data.getID());
		setInsertionLoss(data.getInsertionLoss());
		setLenght_vec(data.getLenght_vec());
		setLinkAttenuation(data.getLinkAttenuation());
		setLinkLength(data.getLinkLength());
		setLinkLoss(data.getLinkLoss());
		setNonReflectingThreshold(data.getNonReflectingThreshold());
		setPulseWidth(data.getPulseWidth());
		setRangeOfTest(data.getRangeOfTest());
		setSampleFrequency(data.getSampleFrequency());
		setTestMode(data.getTestMode());
		setTestTime(data.getTestTime());
		setTestWay(data.getTestWay());
		setType(data.getType());
		setWaveLength(data.getWaveLength());
	}

}
