/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataReceiveEvents;
import com.net.multiway.background.receive.ReceiveParameters;
import com.net.multiway.background.receive.ReceiveValues;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author rafael
 */
public abstract class ControllerExec implements Initializable, IController {

    protected DataParameters parameters;
    protected DataDevice device;

    protected ReceiveParameters receiveParameters;
    protected ReceiveValues receiveValues;

    // Gráfico
    @FXML
    protected LineChart<NumberAxis, NumberAxis> grafico;
    @FXML
    protected NumberAxis xAxis;
    @FXML
    protected NumberAxis yAxis;

    //result
    @FXML
    protected TableView<DataReceiveEvents> resultTable;
    @FXML
    protected TableColumn<DataReceiveEvents, Long> numeroColumn;
    @FXML
    protected TableColumn<DataReceiveEvents, Integer> typeColumn;
    @FXML
    protected TableColumn<DataReceiveEvents, Integer> distanceColumn;
    @FXML
    protected TableColumn<DataReceiveEvents, Float> insertLossColumn;
    @FXML
    protected TableColumn<DataReceiveEvents, Float> reflectLossColumn;
    @FXML
    protected TableColumn<DataReceiveEvents, Float> accumulationColumn;
    @FXML
    protected TableColumn<DataReceiveEvents, Float> attenuationCoefficientColumn;

    protected void plotGraph() {

        ObservableList<XYChart.Data<Integer, Integer>> dataset = FXCollections.observableArrayList();

        //int[] data = receiveValues.getDataValues();
        int i = 1;
        Integer dataPrevious = 0;
        for (Integer data : receiveParameters.getData().getGraphData()) {
            XYChart.Data<Integer, Integer> coordData = new XYChart.Data<>(i++, data);
            coordData.setNode(
                    new HoveredThresholdNode(dataPrevious, data));
            dataset.add(coordData);
            dataPrevious = data;
        }

        grafico.getData().add(new XYChart.Series("My portfolio", FXCollections.observableArrayList(dataset)));
    }

    protected void showReceiveEventsTable(ArrayList<DataReceiveEvents> r) {

        ObservableList<DataReceiveEvents> value = FXCollections.observableArrayList();
        for (int i = 0; i < r.size(); i++) {
            value.add(r.get(i));
        }
        resultTable.setItems(value);

    }
}
