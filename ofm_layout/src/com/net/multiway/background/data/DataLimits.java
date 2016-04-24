/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import java.io.Serializable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phelipe
 */
@Entity
@Table(name = "DATALIMITS")
@XmlRootElement
public class DataLimits implements Serializable {

    private ObjectProperty<Float> insertionGreen;
    private ObjectProperty<Float> reflectionGreen;
    private ObjectProperty<Float> distanceGreen;
    private ObjectProperty<Float> distanceYellow;
    private ObjectProperty<Float> insertionYellow;
    private ObjectProperty<Float> reflectionYellow;
    private ObjectProperty<Float> attenuationGreen;
    private ObjectProperty<Float> attenuationYellow;
    private ObjectProperty<Float> acumulationYellow;
    private ObjectProperty<Float> acumulationGreen;
    private Long ID;
    
     public DataLimits() {
        this.insertionGreen = new SimpleObjectProperty<>();
        this.reflectionGreen = new SimpleObjectProperty<>();
        this.distanceGreen = new SimpleObjectProperty<>();
        this.distanceYellow = new SimpleObjectProperty<>();
        this.insertionYellow = new SimpleObjectProperty<>();
        this.reflectionYellow = new SimpleObjectProperty<>();
        this.attenuationGreen = new SimpleObjectProperty<>();
        this.attenuationYellow = new SimpleObjectProperty<>();
        this.acumulationYellow = new SimpleObjectProperty<>();
        this.acumulationGreen = new SimpleObjectProperty<>();

    }

    public DataLimits(float insertionGreen, float reflectionGreen, float distanceGreen,
            float distanceYellow, float insertionYellow, float reflectionYellow, float attenuationGreen,
            float attenuationYellow, float acumulationYellow, float acumulationGreen) {
        this.insertionGreen = new SimpleObjectProperty<>(insertionGreen);
        this.reflectionGreen = new SimpleObjectProperty<>(reflectionGreen);
        this.distanceGreen = new SimpleObjectProperty<>(distanceGreen);
        this.distanceYellow = new SimpleObjectProperty<>(distanceYellow);
        this.insertionYellow = new SimpleObjectProperty<>(insertionYellow);
        this.reflectionYellow = new SimpleObjectProperty<>(reflectionYellow);
        this.attenuationGreen = new SimpleObjectProperty<>(attenuationGreen);
        this.attenuationYellow = new SimpleObjectProperty<>(attenuationYellow);
        this.acumulationYellow = new SimpleObjectProperty<>(acumulationYellow);
        this.acumulationGreen = new SimpleObjectProperty<>(acumulationGreen);

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Basic(optional = false)
    @Column(name = "INSERTION_GREEN")
    public Float getInsertionGreen() {
        return insertionGreen.get();
    }

    public void setInsertionGreen(float insertionGreen) {
        this.insertionGreen.set(insertionGreen);
    }

    @Basic(optional = false)
    @Column(name = "REFLECTION_GREEN")
    public Float getReflectionGreen() {
        return reflectionGreen.get();
    }

    public void setReflectionGreen(float reflectionGreen) {
        this.reflectionGreen.set(reflectionGreen);
    }

    @Basic(optional = false)
    @Column(name = "DISTANCE_GREEN")
    public Float getDistanceGreen() {
        return distanceGreen.get();
    }

    public void setDistanceGreen(float distanceGreen) {
        this.distanceGreen.set(distanceGreen);
    }

    @Basic(optional = false)
    @Column(name = "DISTANCE_YELLOW")
    public Float getDistanceYellow() {
        return distanceYellow.get();
    }

    public void setDistanceYellow(float distanceYellow) {
        this.distanceYellow.set(distanceYellow);
    }

    @Basic(optional = false)
    @Column(name = "INSERTION_YELLOW")
    public Float getInsertionYellow() {
        return insertionYellow.get();
    }

    public void setInsertionYellow(float insertionYellow) {
        this.insertionYellow.set(insertionYellow);
    }

    @Basic(optional = false)
    @Column(name = "REFLECTION_YELLOW")
    public Float getReflectionYellow() {
        return reflectionYellow.get();
    }

    public void setReflectionYellow(float reflectionYellow) {
        this.reflectionYellow.set(reflectionYellow);
    }

    @Basic(optional = false)
    @Column(name = "ATTENUATION_GREEN")
    public Float getAttenuationGreen() {
        return attenuationGreen.get();
    }

    public void setAttenuationGreen(float attenuationGreen) {
        this.attenuationGreen.set(attenuationGreen);
    }

    @Basic(optional = false)
    @Column(name = "ATTENUATION_YELLOW")
    public Float getAttenuationYellow() {
        return attenuationYellow.get();
    }

    public void setAttenuationYellow(float attenuationYellow) {
        this.attenuationYellow.set(attenuationYellow);
    }

    @Basic(optional = false)
    @Column(name = "ACUMULATION_YELLOW")
    public Float getAcumulationYellow() {
        return acumulationYellow.get();
    }

    public void setAcumulationYellow(float acumulationYellow) {
        this.acumulationYellow.set(acumulationYellow);
    }

    @Basic(optional = false)
    @Column(name = "ACUMULATION_GREEN")
    public Float getAcumulationGreen() {
        return acumulationGreen.get();
    }

    public void setAcumulationGreen(float acumulationGreen) {
        this.acumulationGreen.set(acumulationGreen);
    }

}
