/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataReceiveEvents;
import com.net.multiway.background.model.DeviceComunicator;
import com.net.multiway.background.model.HoveredThresholdNode;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.View;
import com.net.multiway.background.receive.ReceiveParameters;
import com.net.multiway.background.receive.ReceiveValues;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
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
public class MonitorWindowController implements Initializable, IController {

    private IController centerController;
    private DataDevice device;
    private DataParameters parameters;
    private ReceiveParameters receiveParameters;
    private ReceiveValues receiveValues;

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
    
    @FXML
    private TextField cycleTimeField;

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

    //result
//    @FXML
//    private TableView<Warning> warningsTable;
//    @FXML
//    private TableColumn<Warning, Integer> idColumn;
//    @FXML
//    private TableColumn<Warning, String> warningColumn;
//    @FXML
//    private TableColumn<Warning, String> descriptionColumn;
//    @FXML
//    private TableColumn<Warning, Date> dateHourColumn;
    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

    }

    private void alertToSaveParameters() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Parametros não salvos");
        alert.setHeaderText("O parâmetros não foram salvos.");

        alert.showAndWait();
    }

    private void alertIncorrectTypeParameters(String ex) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Tipo incorreto");
        alert.setHeaderText("Por favor, verifique os parâmetros.");
        alert.setContentText(ex);

        alert.showAndWait();
    }

    void setDevice(DataDevice device) {
        this.device = device;

        ipLabel.setText(device.getIp());
        maskLabel.setText(device.getMask());;
        gatewayLabel.setText(device.getGateway());;
    }

    void setParameters(DataParameters parameters) {
        this.parameters = parameters;

        this.measureRangeField.setValue(parameters.getMeasuringRangeOfTest());
        this.pulseWidthField.setValue(parameters.getTestPulseWidth());
        this.measureTimeField.setValue(parameters.getMeasuringTime());
        this.waveLengthField.setValue(parameters.getTestWaveLength());
        this.measureModeField.setValue(parameters.getMeasureMode());
        this.refractiveIndexField.setText(parameters.getRefractiveIndex().toString());
        this.nonReflactionThresholdField.setText(parameters.getReflectionThreshold().toString());
        this.endThresholdField.setText(parameters.getEndThreshold().toString());
        this.reflectionThresholdField.setText(parameters.getReflectionThreshold().toString());
    }

    @FXML
    private void onHandleEditParameters() {

    }

    @FXML
    private void onHandleSaveParameters() {

    }

    @FXML
    private void onHandleExecute() {
        DeviceComunicator host = new DeviceComunicator(this.ipLabel.getText().trim(), 5000);
        System.out.println(this.ipLabel.getText().trim());
        Task execute = new Task() {
            @Override
            protected String call() throws Exception {
                if (resultTable.getItems().size() > 0 && grafico.getData().size() > 0) {
                    resultTable.getItems().remove(0, resultTable.getItems().size());
                }

                host.connect(parameters);

                return "Conexcao realizada";
            }

            @Override
            protected void succeeded() {
                ReceiveParameters r = host.getReceiveParametersData();
                showReceiveParametersTable((ArrayList<DataReceiveEvents>) r.getData().getEvents());

                plotGraph(host.getReceiveValues());
                grafico.setCreateSymbols(false);

            }
        };

        Thread tr = new Thread(execute);

        tr.start();
    }

    private void plotGraph(ReceiveValues receiveValues) {

        ObservableList<XYChart.Data<Integer, Integer>> dataset = FXCollections.observableArrayList();

        //int[] data = receiveValues.getDataValues();
        int i = 0;
        Integer dataPrevious = 0;
        for (Integer data : receiveParameters.getData().getGraphData()) {
            XYChart.Data<Integer, Integer> coordData = new XYChart.Data<>(i + 1, data);
            coordData.setNode(
                    new HoveredThresholdNode(dataPrevious, data));
            dataset.add(coordData);
            dataPrevious = data;
        }

        grafico.getData().add(new XYChart.Series("My portfolio", FXCollections.observableArrayList(dataset)));
    }

    private void showReceiveParametersTable(ArrayList<DataReceiveEvents> r) {

        ObservableList<DataReceiveEvents> value = FXCollections.observableArrayList();
        for (int i = 0; i < r.size(); i++) {
            value.add(r.get(i));
        }
        resultTable.setItems(value);

    }

    @FXML
    private void onHandleExport() {

    }

    @FXML
    private void onHandleSetReference() {

    }

    @FXML
    private void onHandleChangeToConfiguration() {
        MainApp.getInstance().showView(View.ConfigurationWindow, Mode.VIEW);
    }

    public void setCenterController(IController controller) {
        this.centerController = controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @Override
    public void handleSave(ActionEvent event) {
    }

    @Override
    public void prepareForm(Mode mode) {
        measureRangeField.setDisable(true);
        pulseWidthField.setDisable(true);
        measureTimeField.setDisable(true);
        waveLengthField.setDisable(true);
        measureModeField.setDisable(true);
        refractiveIndexField.setDisable(true);
        nonReflactionThresholdField.setDisable(true);
        endThresholdField.setDisable(true);
        reflectionThresholdField.setDisable(true);

    }

    @Override
    public void prepareMenu(Mode mode) {
    }

}
