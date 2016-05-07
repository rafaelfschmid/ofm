/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.Limits;
import com.net.multiway.background.data.Parameters;
import com.net.multiway.background.data.DataEvents;
import com.net.multiway.background.data.Device;
import com.net.multiway.background.receive.ReceiveParameters;
import com.net.multiway.background.receive.ReceiveValues;
import com.net.multiway.background.exception.AlertDialog;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    protected Parameters parameters;
    protected Device device;
    protected Limits limits;

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
    protected TableView<DataEvents> resultTable;
    @FXML
    protected TableColumn<DataEvents, Long> numeroColumn;
    @FXML
    protected TableColumn<DataEvents, Integer> typeColumn;
    @FXML
    protected TableColumn<DataEvents, Integer> distanceColumn;
    @FXML
    protected TableColumn<DataEvents, Float> insertLossColumn;
    @FXML
    protected TableColumn<DataEvents, Float> reflectLossColumn;
    @FXML
    protected TableColumn<DataEvents, Float> accumulationColumn;
    @FXML
    protected TableColumn<DataEvents, Float> attenuationCoefficientColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       mappingParametersTable();
    }
    
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

    protected void exportData() {
        if ((receiveParameters == null) || (receiveValues == null)) {
            String msg = "Não há dados a serem exportados.";
            Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
            AlertDialog.NothingToExport();
        } else {
            receiveParameters.print();
            receiveValues.print();
            String msg = "Dados exportados com sucesso.";
            Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
            AlertDialog.ExportSuccess();
        }
    }

    protected void mappingParametersTable() {
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
        insertLossColumn.setCellValueFactory(cellData -> cellData.getValue().insertLossProperty());
        reflectLossColumn.setCellValueFactory(cellData -> cellData.getValue().echoLossProperty());
        accumulationColumn.setCellValueFactory(cellData -> cellData.getValue().acumulativeLossProperty());
        attenuationCoefficientColumn.setCellValueFactory(cellData -> cellData.getValue().averageAttenuationCoefficientProperty());
    }
}
