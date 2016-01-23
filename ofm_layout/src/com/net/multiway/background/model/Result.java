/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

/**
 *
 * @author rafael
 */
public class Result {

    private final ObjectProperty<Integer> number;
    private final ObjectProperty<Integer> type;
    private final ObjectProperty<Integer> distance;
    private final ObjectProperty<Double> insertLoss;
    private final ObjectProperty<Double> reflectLoss;
    private final ObjectProperty<Double> accumulationLoss;
    private final ObjectProperty<Double> attenuationCoefficient;

    public Result(int number, int type, int distance, double insertLoss, double reflectLoss, double accumulationLoss, double attenuationCoefficient) {
        this.number = new SimpleObjectProperty(number);
        this.type = new SimpleObjectProperty(type);
        this.distance = new SimpleObjectProperty(distance);
        this.insertLoss = new SimpleObjectProperty(insertLoss);
        this.reflectLoss = new SimpleObjectProperty(reflectLoss);
        this.accumulationLoss = new SimpleObjectProperty(accumulationLoss);
        this.attenuationCoefficient = new SimpleObjectProperty(attenuationCoefficient);
    }

    public ObjectProperty<Integer> numberProperty() {
        return number;
    }

    public ObjectProperty<Integer> typeProperty() {
        return type;
    }

    public ObjectProperty<Integer> distanceProperty() {
        return distance;
    }

    public ObjectProperty<Double> insertLossProperty() {
        return insertLoss;
    }

    public ObjectProperty<Double> reflectLossProperty() {
        return reflectLoss;
    }

    public ObjectProperty<Double> accumulationLossProperty() {
        return accumulationLoss;
    }

    public ObjectProperty<Double> attenuationCoefficientProperty() {
        return attenuationCoefficient;
    }

    public int getNumber() {
        return number.get();
    }

    public void setNumber(int number) {
        this.number.set(number);
    }

    public int getType() {
        return type.get();
    }

    public void setType(int type) {
        this.type.set(type);
    }

    public int getDistance() {
        return distance.get();
    }

    public void setDistance(int distance) {
        this.distance.set(distance);
    }

    public double getInsertLoss() {
        return insertLoss.get();
    }

    public void setInsertLoss(double insertLoss) {
        this.insertLoss.set(insertLoss);
    }

    public double getReflectLoss() {
        return reflectLoss.get();
    }

    public void setReflectLoss(double reflectLoss) {
        this.reflectLoss.set(reflectLoss);
    }

    public double getAccumulationLoss() {
        return accumulationLoss.get();
    }

    public void setAccumulationLoss(double accumulationLoss) {
        this.accumulationLoss.set(accumulationLoss);
    }

    public double getAttenuationCoefficient() {
        return attenuationCoefficient.get();
    }

    public void setAttenuationCoefficient(double attenuationCoefficient) {
        this.attenuationCoefficient.set(attenuationCoefficient);
    }

}
