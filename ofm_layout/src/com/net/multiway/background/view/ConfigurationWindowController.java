/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataDevice;

import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataReceiveParametersEvents;
import com.net.multiway.background.model.DeviceComunicator;
import com.net.multiway.background.model.HoveredThresholdNode;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.View;
import com.net.multiway.background.receive.ReceiveParameters;
import com.net.multiway.background.receive.ReceiveValues;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class ConfigurationWindowController implements Initializable, IController {//implements Initializable {

    //device
    @FXML
    private ListView<DataDevice> devicesList;

    private DataParameters parameters;

    // Gráfico
    @FXML
    private LineChart<NumberAxis, NumberAxis> grafico;
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
    private TableView<DataReceiveParametersEvents> resultTable;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Long> numeroColumn;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Integer> typeColumn;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Integer> distanceColumn;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Float> insertLossColumn;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Float> reflectLossColumn;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Float> accumulationColumn;
    @FXML
    private TableColumn<DataReceiveParametersEvents, Float> attenuationCoefficientColumn;
    @FXML
    private Button buttonSave;
    @FXML
    private Button buttonEdit;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parameters = new DataParameters();

        devicesList.setItems(MainApp.getInstance().getDevicesData());
        updateDeviceList();
        mappingParametersTable();
        updateParameters();

        measureRangeField.setValue("0");
        pulseWidthField.setValue("0");
        measureTimeField.setValue("15");
        waveLengthField.setValue("1550");
        measureModeField.setValue("1-Average");
        refractiveIndexField.setText("1.4685");
        nonReflactionThresholdField.setText("0");
        endThresholdField.setText("5.0");
        reflectionThresholdField.setText("65.0");

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

    private void alertDeviceSelection() {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Nenhuma seleção");
        alert.setHeaderText("Nenhum dispositivo foi selecionado");
        alert.setContentText("Por favor, selecione um dispositivo.");

        alert.showAndWait();
    }

    private boolean alertDeviceDeletion(String device) {
        // Nada selecionado.
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Exclusão de dispositivo");
        alert.setHeaderText("Deseja excluir o dispositivo: " + device + "?");

        alert.showAndWait();

        if (alert.getResult() == null) {
            return false;
        }

        return true;
    }

    /**
     * Chamado quando o usuário clica no botão delete.
     */
    @FXML
    private void onHandleDeleteDevice() {

        int selectedIndex = devicesList.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            if (alertDeviceDeletion(devicesList.getSelectionModel().getSelectedItem().getIp())) {
                devicesList.getItems().remove(selectedIndex);
            }

        } else {
            alertDeviceSelection();
        }

        updateDeviceList();
    }

    /**
     * Chamado quando o usuário clica no botão delete.
     */
    @FXML
    private void onHandleAddDevice() {
        DataDevice device = new DataDevice();
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(View.DeviceAddDialog.getResource()));
            AnchorPane page;

            page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar dispositivo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getInstance().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define o device no controller.
            DeviceAddDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDevice(device);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            if (controller.isOkClicked()) {
                MainApp.getInstance().getDevicesData().add(device);
                devicesList.setItems(MainApp.getInstance().getDevicesData());
            }
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }

        updateDeviceList();

    }

    private void alertIncorrectField(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo Incorreto");
        alert.setHeaderText("Por favor, verifique o campo " + text + ".");

        alert.showAndWait();
    }

    private void alertIncorrectRangeField(String text, int min, int max) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo Incorreto");
        alert.setHeaderText("O campo " + text + " deve estar entre " + min + " e " + max);

        alert.showAndWait();
    }

    @FXML
    private void onHandleEditParameters() {
        prepareForm(Mode.EDIT);
    }

    @FXML
    private void onHandleSaveParameters() {
        if (measureRangeField.getValue() == null) {
            alertIncorrectField("Measuring Range Of Test");
        } else if (pulseWidthField.getValue() == null) {
            alertIncorrectField("Test Pulse Width");
        } else if (measureTimeField.getValue() == null) {
            alertIncorrectField("Measuring Time");
        } else if (waveLengthField.getValue() == null) {
            alertIncorrectField("Test Wave Length");
        } else if (measureModeField.getValue() == null) {
            alertIncorrectField("Measure Mode");
        } else if (refractiveIndexField.getText().isEmpty()) {
            alertIncorrectField("Refractive Index");
        } else if (nonReflactionThresholdField.getText().isEmpty()) {
            alertIncorrectField("Non Reflaction Threshold");
        } else if (endThresholdField.getText().isEmpty()) {
            alertIncorrectField("End Threshold");
        } else if (reflectionThresholdField.getText().isEmpty()) {
            alertIncorrectField("Reflection Threshold");
        } else {
            float nonReflThresh = Float.parseFloat(nonReflactionThresholdField.getText());
            float endThresh = Float.parseFloat(endThresholdField.getText());
            float reflectionThresh = Float.parseFloat(reflectionThresholdField.getText());

            if (nonReflThresh < 0 || nonReflThresh > 10) {
                alertIncorrectRangeField("NonReflaction Threshold", 0, 10);
            } else if (endThresh < 0 || endThresh > 10) {
                alertIncorrectRangeField("End Threshold", 0, 10);
            } else if (reflectionThresh < 20 || reflectionThresh > 80) {
                alertIncorrectRangeField("Reflaction Threshold", 20, 80);
            } else {
                parameters.setMeasuringRangeOfTest(Integer.parseInt(measureRangeField.getValue().toString()));
                parameters.setTestPulseWidth(Integer.parseInt(pulseWidthField.getValue().toString()));
                parameters.setMeasuringTime(Integer.parseInt(measureTimeField.getValue().toString()));
                parameters.setTestWaveLength(Integer.parseInt(waveLengthField.getValue().toString()));
                parameters.setMeasureMode((measureModeField.getValue().toString() == "1-Average") ? 1 : 2);
                parameters.setRefractiveIndex(Float.parseFloat(refractiveIndexField.getText()));
                parameters.setNonReflactionThreshold(Float.parseFloat(nonReflactionThresholdField.getText()));
                parameters.setEndThreshold(Float.parseFloat(endThresholdField.getText()));
                parameters.setReflectionThreshold(Float.parseFloat(reflectionThresholdField.getText()));
                prepareForm(Mode.VIEW);
            }

        }
    }

    @FXML
    private void onHandleExecute() {
        DeviceComunicator host = new DeviceComunicator("192.168.4.4", 5000);
        parameters.copy(new DataParameters(Long.parseLong("1"), 1, 0, 65.0f, 1, 1, 1550, 0, 0, 15000, 1.4685f, 5.0f, 0));

        Task execute = new Task() {
            @Override
            protected String call() throws Exception {
                if (resultTable.getItems().size() > 0 && grafico.getData().size() > 0) {
                    resultTable.getItems().remove(0, resultTable.getItems().size());
                }

                host.connect(parameters);

                return "Conex�o realizada";
            }

            @Override
            protected void succeeded() {
                ReceiveParameters r = host.getReceiveParametersData();
                showReceiveParametersTable(r.getData().getEvents());

                plotGraph(host.getReceiveValues());
                grafico.setCreateSymbols(false);

            }
        };

        Thread tr = new Thread(execute);

        tr.start();

    }

    private void plotGraph(ReceiveValues receiveValues) {
        
        grafico.getData().clear();
        
        receiveValues.processData();
        
        ObservableList<XYChart.Data<Integer, Integer>> dataset = FXCollections.observableArrayList();

        int[] data = receiveValues.getDataValues();
        for (int i = 0; i < data.length; i++) {
            XYChart.Data<Integer, Integer> coordData = new XYChart.Data<>(i + 1, data[i]);
            coordData.setNode(
                    new HoveredThresholdNode(
                            (i == 0) ? 0 : data[i - 1],
                            data[i]
                    )
            );
            dataset.add(coordData);

        }

        grafico.getData().add(new XYChart.Series("points", FXCollections.observableArrayList(dataset)));
    }

    @FXML
    private void onHandleExport() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementar");
        alert.setHeaderText("//TO DO");
        alert.showAndWait();
    }

    @FXML
    private void onHandleSetReference() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementar");
        alert.setHeaderText("//TO DO");
        alert.showAndWait();
    }

    @FXML
    private void onHandleChangeToMonitor() throws IOException {
        MainApp.getInstance().showView(View.MonitorWindow, Mode.VIEW);
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
                break;
            case EDIT:
                measureRangeField.setDisable(false);
                pulseWidthField.setDisable(false);
                measureTimeField.setDisable(false);
                waveLengthField.setDisable(false);
                measureModeField.setDisable(false);
                refractiveIndexField.setDisable(false);
                nonReflactionThresholdField.setDisable(false);
                endThresholdField.setDisable(false);
                reflectionThresholdField.setDisable(false);
                buttonSave.setDisable(false);

                break;
        }
    }

    @Override
    public void prepareMenu(Mode mode) {

    }

    private void updateParameters() {

        ObservableList<Integer> param1 = FXCollections.observableArrayList(new Integer[]{0, 1, 5, 10, 30, 60, 80, 120});
        measureRangeField.setItems(param1);

        ObservableList<Integer> param2 = FXCollections.observableArrayList(new Integer[]{10, 20, 50, 100, 200, 500, 1000, 2000, 10000, 20000});
        pulseWidthField.setItems(param2);

        ObservableList<Integer> param3 = FXCollections.observableArrayList(new Integer[]{15, 30, 60, 120, 180});
        measureTimeField.setItems(param3);

        ObservableList<Integer> param4 = FXCollections.observableArrayList(new Integer[]{1310, 1550});
        waveLengthField.setItems(param4);

        ObservableList<String> param5 = FXCollections.observableArrayList(new String[]{"1-Average", "2-Real Time"});
        measureModeField.setItems(param5);

        refractiveIndexField.setText("");

        nonReflactionThresholdField.setText("");

        endThresholdField.setText("");

        reflectionThresholdField.setText("");
    }

    private void updateDeviceList() {
        devicesList.setCellFactory(new Callback<ListView<DataDevice>, ListCell<DataDevice>>() {
            @Override
            public ListCell<DataDevice> call(ListView<DataDevice> p) {

                ListCell<DataDevice> cell = new ListCell<DataDevice>() {

                    @Override
                    protected void updateItem(DataDevice t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getIp());
                        }
                    }

                };

                return cell;
            }
        });
    }

    private void mappingParametersTable() {
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().typeProperty());
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
        insertLossColumn.setCellValueFactory(cellData -> cellData.getValue().insertLossProperty());
        reflectLossColumn.setCellValueFactory(cellData -> cellData.getValue().echoLossProperty());
        accumulationColumn.setCellValueFactory(cellData -> cellData.getValue().acumulativeLossProperty());
        attenuationCoefficientColumn.setCellValueFactory(cellData -> cellData.getValue().averageAttenuationCoefficientProperty());
    }

    private void showReceiveParametersTable(ArrayList<DataReceiveParametersEvents> r) {

        ObservableList<DataReceiveParametersEvents> value = FXCollections.observableArrayList();
        for (int i = 0; i < r.size(); i++) {
            value.add(r.get(i));
        }
        resultTable.setItems(value);

    }

}
