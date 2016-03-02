/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATARECEIVE")
@XmlRootElement
public class DataReceive implements Serializable {

    private ObjectProperty<Integer> sampleFrequency;
    private ObjectProperty<Integer> rangeOfTest;
    private ObjectProperty<Integer> pulseWidth;
    private ObjectProperty<Integer> waveLength;
    private ObjectProperty<Integer> testTime;
    private ObjectProperty<Float> groupRefractiveIndex;
    private ObjectProperty<Float> linkLength;
    private ObjectProperty<Float> linkLoss;
    private ObjectProperty<Float> linkAttenuation;
    private ObjectProperty<Float> nonReflectingThreshold;
    private ObjectProperty<Float> endThreshold;
    private ObjectProperty<Float> testMode;
    private ObjectProperty<Integer> testWay;
    private ObjectProperty<Integer> lenght_vec;
    private List<Integer> graphData;
    private List<DataReceiveEvents> events;

    private ObjectProperty<Long> ID;

    public DataReceive() {
        this.sampleFrequency = new SimpleObjectProperty<>();
        this.rangeOfTest = new SimpleObjectProperty<>();
        this.pulseWidth = new SimpleObjectProperty<>();
        this.waveLength = new SimpleObjectProperty<>();
        this.testTime = new SimpleObjectProperty<>();
        this.groupRefractiveIndex = new SimpleObjectProperty<>();
        this.linkLength = new SimpleObjectProperty<>();
        this.linkLoss = new SimpleObjectProperty<>();
        this.linkAttenuation = new SimpleObjectProperty<>();
        this.nonReflectingThreshold = new SimpleObjectProperty<>();
        this.endThreshold = new SimpleObjectProperty<>();
        this.testMode = new SimpleObjectProperty<>();
        this.testWay = new SimpleObjectProperty<>();
        this.lenght_vec = new SimpleObjectProperty<>();
        this.graphData = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public DataReceive(int sampleFrequency, int rangeOfTest, int pulseWidth, int waveLength, int testTime, float groupRefractiveIndex, float linkLength, float linkLoss, float linkAttenuation, float nonReflectingThreshold, float endThreshold, float testMode, int testWay, int lenght_vec, int distance, int type, float echoLoss, float insertionLoss, float averageAttenuationCoefficient, float acumulativeLoss) {
        this.sampleFrequency = new SimpleObjectProperty<>(sampleFrequency);
        this.rangeOfTest = new SimpleObjectProperty<>(rangeOfTest);
        this.pulseWidth = new SimpleObjectProperty<>(pulseWidth);
        this.waveLength = new SimpleObjectProperty<>(waveLength);
        this.testTime = new SimpleObjectProperty<>(testTime);
        this.groupRefractiveIndex = new SimpleObjectProperty<>(groupRefractiveIndex);
        this.linkLength = new SimpleObjectProperty<>(linkLength);
        this.linkLoss = new SimpleObjectProperty<>(linkLoss);
        this.linkAttenuation = new SimpleObjectProperty<>(linkAttenuation);
        this.nonReflectingThreshold = new SimpleObjectProperty<>(nonReflectingThreshold);
        this.endThreshold = new SimpleObjectProperty<>(endThreshold);
        this.testMode = new SimpleObjectProperty<>(testMode);
        this.testWay = new SimpleObjectProperty<>(testWay);
        this.lenght_vec = new SimpleObjectProperty<>(lenght_vec);
        this.graphData = new ArrayList<>();
        this.events = new ArrayList<>();

    }

    @Basic(optional = false)
    @Column(name = "SAMPLEFREQUENCY")
    public Integer getSampleFrequency() {
        return sampleFrequency.get();
    }

    public void setSampleFrequency(int sampleFrequency) {
        this.sampleFrequency.set(sampleFrequency);
    }

    @Basic(optional = false)
    @Column(name = "RANGEOFTEST")
    public Integer getRangeOfTest() {
        return rangeOfTest.get();
    }

    public void setRangeOfTest(int rangeOfTest) {
        this.rangeOfTest.set(rangeOfTest);
    }

    @Basic(optional = false)
    @Column(name = "PULSEWIDTH")
    public Integer getPulseWidth() {
        return pulseWidth.get();
    }

    public void setPulseWidth(int pulseWidth) {
        this.pulseWidth.set(pulseWidth);
    }

    @Basic(optional = false)
    @Column(name = "WAVELENGTH")
    public Integer getWaveLength() {
        return waveLength.get();
    }

    public void setWaveLength(int waveLength) {
        this.waveLength.set(waveLength);
    }

    @Basic(optional = false)
    @Column(name = "TESTTIME")
    public Integer getTestTime() {
        return testTime.get();
    }

    public void setTestTime(int testTime) {
        this.testTime.set(testTime);
    }

    @Basic(optional = false)
    @Column(name = "GROUPREFRACTIVEINDEX")
    public Float getGroupRefractiveIndex() {
        return groupRefractiveIndex.get();
    }

    public void setGroupRefractiveIndex(float groupRefractiveIndex) {
        this.groupRefractiveIndex.set(groupRefractiveIndex);
    }

    @Basic(optional = false)
    @Column(name = "LINKLENGTH")
    public Float getLinkLength() {
        return linkLength.get();
    }

    public void setLinkLength(float linkLength) {
        this.linkLength.set(linkLength);
    }

    @Basic(optional = false)
    @Column(name = "LINKLOSS")
    public Float getLinkLoss() {
        return linkLoss.get();
    }

    public void setLinkLoss(float linkLoss) {
        this.linkLoss.set(linkLoss);
    }

    @Basic(optional = false)
    @Column(name = "LINKATTENUATION")
    public Float getLinkAttenuation() {
        return linkAttenuation.get();
    }

    public void setLinkAttenuation(float linkAttenuation) {
        this.linkAttenuation.set(linkAttenuation);
    }

    @Basic(optional = false)
    @Column(name = "NONREFLECTINGTHRESHOLD")
    public Float getNonReflectingThreshold() {
        return nonReflectingThreshold.get();
    }

    public void setNonReflectingThreshold(float nonReflectingThreshold) {
        this.nonReflectingThreshold.set(nonReflectingThreshold);
    }

    @Basic(optional = false)
    @Column(name = "ENDTHRESHOLD")
    public Float getEndThreshold() {
        return endThreshold.get();
    }

    public void setEndThreshold(float endThreshold) {
        this.endThreshold.set(endThreshold);
    }

    @Basic(optional = false)
    @Column(name = "TESTMODE")
    public Float getTestMode() {
        return testMode.get();
    }

    public void setTestMode(float testMode) {
        this.testMode.set(testMode);
    }

    @Basic(optional = false)
    @Column(name = "TESTWAY")
    public Integer getTestWay() {
        return testWay.get();
    }

    public void setTestWay(int testWay) {
        this.testWay.set(testWay);
    }

    @Basic(optional = false)
    @Column(name = "LENGHT_VEC")
    public Integer getLenght_vec() {
        return lenght_vec.get();
    }

    public void setLenght_vec(int lenght_vec) {
        this.lenght_vec.set(lenght_vec);
    }

    @ElementCollection
    @CollectionTable(
            name = "GRAPH",
            joinColumns = @JoinColumn(name = "OWNER_ID")
    )
    @Column(name = "GRAPH_DATA")
    public List<Integer> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<Integer> graphData) {
        this.graphData = graphData;
    }

    @Id
    @Basic(optional = false)
    @Column(name = "ID")

    public Long getID() {
        return ID.get();
    }

    public void setID(Long ID) {
        this.ID.set(ID);
    }

    public ObjectProperty<Long> idProperty() {
        return ID;
    }

    @OneToMany
    @JoinColumn(name = "EVENT_ID", referencedColumnName = "ID")
    public List<DataReceiveEvents> getEvents() {
        return events;
    }

    public void addEvents(DataReceiveEvents events) {
        this.events.add(events);
    }

    private void setEvents(ArrayList<DataReceiveEvents> events) {
        this.events = events;
    }

//	public void copy(DataReceive data) {
//
//		setGraphData(data.getGraphData());
//		setEvents((ArrayList<DataReceiveEvents>)data.getEvents());
//		setEndThreshold(data.getEndThreshold());
//		setGroupRefractiveIndex(data.getGroupRefractiveIndex());
//		setID(data.getID());
//		setLenght_vec(data.getLenght_vec());
//		setLinkAttenuation(data.getLinkAttenuation());
//		setLinkLength(data.getLinkLength());
//		setLinkLoss(data.getLinkLoss());
//		setNonReflectingThreshold(data.getNonReflectingThreshold());
//		setPulseWidth(data.getPulseWidth());
//		setRangeOfTest(data.getRangeOfTest());
//		setSampleFrequency(data.getSampleFrequency());
//		setTestMode(data.getTestMode());
//		setTestTime(data.getTestTime());
//		setTestWay(data.getTestWay());
//		setWaveLength(data.getWaveLength());
//	}
}
