/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;

/**
 *
 * @author Phelipe
 */
public class Device {

    private String ip;
    private String mask;
    private String gateway;

    private ObservableList<XYChart.Series<Double, Double>> coordinatesData = FXCollections.observableArrayList();

    public void setCoordinatesData(ObservableList<XYChart.Series<Double, Double>> coordinatesData) {
        this.coordinatesData = coordinatesData;
    }

    public Device() {
    }

    public Device(String ip, String mask, String gateway) {
        this.ip = ip;
        this.mask = mask;
        this.gateway = gateway;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public ObservableList<XYChart.Series<Double, Double>> getCoordinatesData() {
        return coordinatesData;
    }
}
