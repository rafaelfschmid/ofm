/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataReceiveEvents;
import com.net.multiway.background.data.DataReference;
import com.net.multiway.background.exception.AlertDialog;
import com.net.multiway.background.model.ControllerExec;
import com.net.multiway.background.model.DeviceComunicator;
import com.net.multiway.background.model.Mode;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class MonitorWindowController extends ControllerExec {

    //device
    @FXML
    private Label ipLabel;
    @FXML
    private Label maskLabel;
    @FXML
    private Label gatewayLabel;

    // Gráfico
    @FXML
    private LineChart<Double, Double> grafico;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    //parameter
    @FXML
    private ComboBox measureRangeField;
    @FXML
    private ComboBox pulseWidthField;
    @FXML
    private ComboBox measureTimeField;
    @FXML
    private ComboBox waveLengthField;
    @FXML
    private ComboBox measureModeField;
    @FXML
    private TextField refractiveIndexField;
    @FXML
    private TextField nonReflactionThresholdField;
    @FXML
    private TextField endThresholdField;
    @FXML
    private TextField reflectionThresholdField;

    //result
    @FXML
    private TableView<DataReceiveEvents> resultTable;
    @FXML
    private TableColumn<DataReceiveEvents, Long> numeroColumn;
    @FXML
    private TableColumn<DataReceiveEvents, Integer> typeColumn;
    @FXML
    private TableColumn<DataReceiveEvents, Integer> distanceColumn;
    @FXML
    private TableColumn<DataReceiveEvents, Float> insertLossColumn;
    @FXML
    private TableColumn<DataReceiveEvents, Float> reflectLossColumn;
    @FXML
    private TableColumn<DataReceiveEvents, Float> accumulationColumn;
    @FXML
    private TableColumn<DataReceiveEvents, Float> attenuationCoefficientColumn;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonEdit;
    @FXML
    private TextField cycleTimeField;
    @FXML
    private Button buttonExport;
    @FXML
    private Button buttonStop;
    @FXML
    private Button buttonExecute;
    @FXML
    private Button buttonConfig;
    @FXML
    private Label executionLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        super.initialize(location, resources);

        prepareForm(Mode.VIEW);
    }

    @FXML
    private void onHandleEditParameters() {

    }

    @FXML
    private void onHandleSaveParameters() {

    }

    @FXML
    private void onHandleExecute() {
        DeviceComunicator host;
        if (device != null) {
            host = new DeviceComunicator(device.getIp().trim(), 5000);

//            if(host.connect(device)) {
//                
//            }
//            else 
            if (buttonSave.isDisable()) {
                executionLabel.setVisible(true);
                String msg = "Receiving data from OTDR...";
                executionLabel.setText(msg);

                buttonExecute.setDisable(true);
                buttonConfig.setDisable(true);
                buttonExport.setDisable(true);
                buttonStop.setDisable(false);

                Task execute = new Task() {
                    @Override
                    protected Void call() throws Exception {

                        if (resultTable.getItems().size() > 0 && grafico.getData().size() > 0) {
                            resultTable.getItems().remove(0, resultTable.getItems().size());

                        }

                        while (!buttonStop.isDisable()) {
                            host.connect(parameters);
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    updateMessage();
                                }
                            });
                            Thread.sleep(1000 * 60);
                        }

                        buttonExecute.setDisable(false);
                        buttonConfig.setDisable(false);
                        buttonExport.setDisable(false);

                        return null;
                    }

                    @Override
                    protected void failed() {
                        super.failed();
                        buttonExecute.setDisable(false);
                        buttonConfig.setDisable(false);
                        buttonStop.setDisable(true);
                        AlertDialog.timeOut(device.getIp());
                    }

                    private void updateMessage() {
                        String msg = "Envio de dados finalizado.";
                        Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
                        executionLabel.setText(msg);

                        receiveParameters = host.getReceiveParametersData();
                        if (receiveParameters != null) {
                            resultTable.setItems(FXCollections.observableArrayList(receiveParameters.getData().getEvents()));

                            msg = "Eventos atualizados na tela de configuração.";
                            Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
                            executionLabel.setText(msg);

                            receiveValues = host.getReceiveValues();
                            grafico.getData().clear();
                            plotGraph();
                            grafico.setCreateSymbols(false);
                            msg = "Gráfico plotado na tela de configuração.";
                            Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
                            executionLabel.setText(msg);
                        }
                    }

                };

                Thread tr = new Thread(execute);
                tr.start();

            } else {
                AlertDialog.SaveParameters();
            }
        } else {
            AlertDialog.DeviceNotFound();
        }
    }

//    private void showReceiveParametersTable(ArrayList<DataReceiveEvents> r) {
//
//        ObservableList<DataReceiveEvents> value = FXCollections.observableArrayList();
//        for (int i = 0; i < r.size(); i++) {
//            value.add(r.get(i));
//        }
//        resultTable.setItems(value);
//
//    }
    @FXML
    private void onHandleExport() {
        exportData();
    }

    @FXML
    private void onHandleChangeToConfiguration() {
        //MainApp.getInstance().showView(View.ConfigurationWindow, Mode.VIEW);
        MainApp.getInstance().showConfiguration();
    }

    @Override
    public void handleSave(ActionEvent event) {
    }

    @Override
    public void prepareForm(Mode mode) {
        switch (mode) {
            case VIEW:
                measureRangeField.setDisable(true);
                pulseWidthField.setDisable(true);
                measureTimeField.setDisable(true);
                waveLengthField.setDisable(true);
                measureModeField.setDisable(true);
                refractiveIndexField.setDisable(true);
                nonReflactionThresholdField.setDisable(true);
                endThresholdField.setDisable(true);
                reflectionThresholdField.setDisable(true);
                buttonSave.setDisable(true);
                buttonEdit.setDisable(true);
                cycleTimeField.setDisable(true);
                buttonStop.setDisable(true);
                buttonExport.setDisable(true);
                break;
        }

    }

    @Override
    public void prepareMenu(Mode mode) {
    }

    @FXML
    private void onHandleStop(ActionEvent event) {
        buttonStop.setDisable(true);
    }

    void setReference(DataReference reference) {

        this.reference = reference;
        this.parameters = reference.getParameters();
        this.device = reference.getDevice();

        this.measureRangeField.setValue(parameters.getMeasuringRangeOfTest());
        this.pulseWidthField.setValue(parameters.getTestPulseWidth());
        this.measureTimeField.setValue(parameters.getMeasuringTime());
        this.waveLengthField.setValue(parameters.getTestWaveLength());
        this.measureModeField.setValue(parameters.getMeasureMode());
        this.refractiveIndexField.setText(parameters.getRefractiveIndex().toString());
        this.nonReflactionThresholdField.setText(parameters.getReflectionThreshold().toString());
        this.endThresholdField.setText(parameters.getEndThreshold().toString());
        this.reflectionThresholdField.setText(parameters.getReflectionThreshold().toString());
        this.cycleTimeField.setText(parameters.getCycleTime().toString());

        ipLabel.setText(device.getIp());
        maskLabel.setText(device.getMask());
        gatewayLabel.setText(device.getGateway());

    }

}
