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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "data")
@XmlRootElement
public class Data implements Serializable {

    @Basic(optional = false)
    @Column(name = "sample_frequency")
    private ObjectProperty<Integer> sampleFrequency;

    @Basic(optional = false)
    @Column(name = "range_test")
    private ObjectProperty<Integer> rangeOfTest;

    @Basic(optional = false)
    @Column(name = "pulse_width")
    private ObjectProperty<Integer> pulseWidth;

    @Basic(optional = false)
    @Column(name = "wave_length")
    private ObjectProperty<Integer> waveLength;

    @Basic(optional = false)
    @Column(name = "test_time")
    private ObjectProperty<Integer> testTime;

    @Basic(optional = false)
    @Column(name = "refractive_index")
    private ObjectProperty<Float> groupRefractiveIndex;

    @Basic(optional = false)
    @Column(name = "link_length")
    private ObjectProperty<Float> linkLength;

    @Basic(optional = false)
    @Column(name = "link_loss")
    private ObjectProperty<Float> linkLoss;

    @Basic(optional = false)
    @Column(name = "link_attenuation")
    private ObjectProperty<Float> linkAttenuation;

    @Basic(optional = false)
    @Column(name = "nonreflecting_threshold")
    private ObjectProperty<Float> nonReflectingThreshold;

    @Basic(optional = false)
    @Column(name = "end_threshold")
    private ObjectProperty<Float> endThreshold;

    @Basic(optional = false)
    @Column(name = "test_mode")
    private ObjectProperty<Float> testMode;

    @Basic(optional = false)
    @Column(name = "test_way")
    private ObjectProperty<Integer> testWay;

    @ElementCollection
    @CollectionTable(
            name = "graphic",
            joinColumns = @JoinColumn(name = "data_id")
    )
    @Column(name = "graphic_data")
    private List<Integer> graphData;

    @OneToMany(mappedBy = "data")
    private List<DataEvents> events;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long ID;

    public Data() {
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
        this.graphData = new ArrayList<>();
        this.events = new ArrayList<>();
    }

    public Data(int sampleFrequency, int rangeOfTest, int pulseWidth, int waveLength, int testTime, float groupRefractiveIndex, float linkLength, float linkLoss, float linkAttenuation, float nonReflectingThreshold, float endThreshold, float testMode, int testWay, int lenght_vec, int distance, int type, float echoLoss, float insertionLoss, float averageAttenuationCoefficient, float acumulativeLoss) {
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
        this.graphData = new ArrayList<>();
        this.events = new ArrayList<>();

    }

    public Integer getSampleFrequency() {
        return sampleFrequency.get();
    }

    public void setSampleFrequency(int sampleFrequency) {
        this.sampleFrequency.set(sampleFrequency);
    }

    public ObjectProperty<Integer> sampleFrequencyProperty() {
        return sampleFrequency;
    }

    public Integer getRangeOfTest() {
        return rangeOfTest.get();
    }

    public void setRangeOfTest(int rangeOfTest) {
        this.rangeOfTest.set(rangeOfTest);
    }

    public ObjectProperty<Integer> rangeOfTestProperty() {
        return rangeOfTest;
    }

    public Integer getPulseWidth() {
        return pulseWidth.get();
    }

    public void setPulseWidth(int pulseWidth) {
        this.pulseWidth.set(pulseWidth);
    }

    public ObjectProperty<Integer> pulseWidthProperty() {
        return pulseWidth;
    }

    public Integer getWaveLength() {
        return waveLength.get();
    }

    public void setWaveLength(int waveLength) {
        this.waveLength.set(waveLength);
    }

    public ObjectProperty<Integer> waveLengthProperty() {
        return waveLength;
    }

    public Integer getTestTime() {
        return testTime.get();
    }

    public void setTestTime(int testTime) {
        this.testTime.set(testTime);
    }

    public ObjectProperty<Integer> testTimeProperty() {
        return testTime;
    }

    public Float getGroupRefractiveIndex() {
        return groupRefractiveIndex.get();
    }

    public void setGroupRefractiveIndex(float groupRefractiveIndex) {
        this.groupRefractiveIndex.set(groupRefractiveIndex);
    }

    public ObjectProperty<Float> groupRefractiveIndexProperty() {
        return groupRefractiveIndex;
    }

    public Float getLinkLength() {
        return linkLength.get();
    }

    public void setLinkLength(float linkLength) {
        this.linkLength.set(linkLength);
    }

    public ObjectProperty<Float> linkLengthProperty() {
        return linkLength;
    }

    public Float getLinkLoss() {
        return linkLoss.get();
    }

    public void setLinkLoss(float linkLoss) {
        this.linkLoss.set(linkLoss);
    }

    public ObjectProperty<Float> linkLossProperty() {
        return linkLoss;
    }

    public Float getLinkAttenuation() {
        return linkAttenuation.get();
    }

    public void setLinkAttenuation(float linkAttenuation) {
        this.linkAttenuation.set(linkAttenuation);
    }

    public ObjectProperty<Float> linkAttenuationProperty() {
        return linkAttenuation;
    }

    public Float getNonReflectingThreshold() {
        return nonReflectingThreshold.get();
    }

    public void setNonReflectingThreshold(float nonReflectingThreshold) {
        this.nonReflectingThreshold.set(nonReflectingThreshold);
    }

    public ObjectProperty<Float> nonReflectingThresholdProperty() {
        return nonReflectingThreshold;
    }

    public Float getEndThreshold() {
        return endThreshold.get();
    }

    public void setEndThreshold(float endThreshold) {
        this.endThreshold.set(endThreshold);
    }

    public ObjectProperty<Float> endThresholdProperty() {
        return endThreshold;
    }

    public Float getTestMode() {
        return testMode.get();
    }

    public void setTestMode(float testMode) {
        this.testMode.set(testMode);
    }

    public ObjectProperty<Float> testModeProperty() {
        return testMode;
    }

    public Integer getTestWay() {
        return testWay.get();
    }

    public void setTestWay(int testWay) {
        this.testWay.set(testWay);
    }

    public ObjectProperty<Integer> testWayProperty() {
        return testWay;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public List<Integer> getGraphData() {
        return graphData;
    }

    public void setGraphData(List<Integer> graphData) {
        this.graphData = graphData;
    }

    public List<DataEvents> getEvents() {
        return events;
    }

    public void addEvents(DataEvents events) {
        this.events.add(events);
    }

    public void setEvents(List<DataEvents> events) {
        this.events = events;
    }


    public void copy(Data data) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
