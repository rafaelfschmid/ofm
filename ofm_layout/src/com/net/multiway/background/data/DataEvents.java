/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "data_events")
@XmlRootElement
public class DataEvents implements Serializable {

    @Basic(optional = false)
    @Column(name = "distance")
    private ObjectProperty<Integer> distance;

    @Basic(optional = false)
    @Column(name = "type")
    private ObjectProperty<Integer> type;

    @Basic(optional = false)
    @Column(name = "echo_loss")
    private ObjectProperty<Float> echoLoss;

    @Basic(optional = false)
    @Column(name = "insertion_loss")
    private ObjectProperty<Float> insertionLoss;

    @Basic(optional = false)
    @Column(name = "average_attenuation_coefficient")
    private ObjectProperty<Float> averageAttenuationCoefficient;

    @Basic(optional = false)
    @Column(name = "acumulative_loss")
    private ObjectProperty<Float> acumulativeLoss;


    @ManyToOne
    //@JoinColumn( name="DATARECEIVE_ID", nullable = false )
    private Data data;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long ID;
    private Long event_id;

    public DataEvents() {

        this.distance = new SimpleObjectProperty<>();
        this.type = new SimpleObjectProperty<>();
        this.echoLoss = new SimpleObjectProperty<>();
        this.insertionLoss = new SimpleObjectProperty<>();
        this.averageAttenuationCoefficient = new SimpleObjectProperty<>();
        this.acumulativeLoss = new SimpleObjectProperty<>();

    }

    public DataEvents(int distance, int type, float echoLoss, float insertionLoss, float averageAttenuationCoefficient, float acumulativeLoss) {

        this.distance = new SimpleObjectProperty<>(distance);
        this.type = new SimpleObjectProperty<>(type);
        this.echoLoss = new SimpleObjectProperty<>(echoLoss);
        this.insertionLoss = new SimpleObjectProperty<>(insertionLoss);
        this.averageAttenuationCoefficient = new SimpleObjectProperty<>(averageAttenuationCoefficient);
        this.acumulativeLoss = new SimpleObjectProperty<>(acumulativeLoss);
    }

    public Integer getDistance() {
        return distance.get();
    }

    public void setDistance(int distance) {
        this.distance.set(distance);
    }

    public ObjectProperty<Integer> distanceProperty() {
        return this.distance;
    }

    public Integer getType() {
        return type.get();
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public ObjectProperty<Integer> typeProperty() {
        return this.type;
    }

    public Float getEchoLoss() {
        return echoLoss.get();
    }

    public void setEchoLoss(float echoLoss) {
        this.echoLoss.set(echoLoss);
    }

    public ObjectProperty<Float> echoLossProperty() {
        return this.echoLoss;
    }

    public Float getInsertionLoss() {
        return insertionLoss.get();
    }

    public void setInsertionLoss(float insertionLoss) {
        this.insertionLoss.set(insertionLoss);
    }

    public ObjectProperty<Float> insertLossProperty() {
        return this.insertionLoss;
    }

    public Float getAverageAttenuationCoefficient() {
        return averageAttenuationCoefficient.get();
    }

    public void setAverageAttenuationCoefficient(float averageAttenuationCoefficient) {
        this.averageAttenuationCoefficient.set(averageAttenuationCoefficient);
    }

    public ObjectProperty<Float> averageAttenuationCoefficientProperty() {
        return this.averageAttenuationCoefficient;
    }

    public Float getAcumulativeLoss() {
        return acumulativeLoss.get();
    }

    public void setAcumulativeLoss(float acumulativeLoss) {
        this.acumulativeLoss.set(acumulativeLoss);
    }

    public ObjectProperty<Float> acumulativeLossProperty() {
        return this.acumulativeLoss;
    }

    public void copy(DataEvents data) {
        setAcumulativeLoss(data.getAcumulativeLoss());
        setAverageAttenuationCoefficient(data.getAverageAttenuationCoefficient());
        setDistance(data.getDistance());
        setEchoLoss(data.getEchoLoss());
        setInsertionLoss(data.getInsertionLoss());
        setType(data.getType());
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data dataReceive) {
        this.data = dataReceive;
    }

}
