/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.controller;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.model.Device;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.Result;
import com.net.multiway.background.model.View;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;


/**
 * FXML Controller class
 *
 * @author rafael
 */
public class ConfigurationWindowController implements Initializable,IController {//implements Initializable {

    //device
    @FXML
    private ListView<Device> devicesList;

    // Gráfico
    @FXML
    private LineChart<Double, Double> grafico;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    //parameter
    @FXML
    private TextField measureModeField;
    @FXML
    private TextField optimizeModeField;
    @FXML
    private TextField reflectionThresholdField;
    @FXML
    private TextField enabledRefreshField;
    @FXML
    private TextField refreshCycleField;
    @FXML
    private TextField testWaveLengthField;
    @FXML
    private TextField measuringRangeOfTestField;
    @FXML
    private TextField testPulseWidthField;
    @FXML
    private TextField measuringTimeField;
    @FXML
    private TextField refractiveIndexField;
    @FXML
    private TextField endThresholdField;
    @FXML
    private TextField nonReflactionThresholdField;

    //result
    @FXML
    private TableView<Result> resultTable;
    @FXML
    private TableColumn<Result, Integer> numeroColumn;
    @FXML
    private TableColumn<Result, Integer> typeColumn;
    @FXML
    private TableColumn<Result, Integer> distanceColumn;
    @FXML
    private TableColumn<Result, Double> insertLossColumn;
    @FXML
    private TableColumn<Result, Double> reflectLossColumn;
    @FXML
    private TableColumn<Result, Double> accumulationColumn;
    @FXML
    private TableColumn<Result, Double> attenuationCoefficientColumn;

// Reference to the main application.
    private MainApp mainApp;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        devicesList.setCellFactory(new Callback<ListView<Device>, ListCell<Device>>() {

            @Override
            public ListCell<Device> call(ListView<Device> p) {

                ListCell<Device> cell = new ListCell<Device>() {

                    @Override
                    protected void updateItem(Device t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getIp());
                        }
                    }

                };

                return cell;
            }
        });

        // Inicializa grafico
        // Inicializa a tabela de parametros
        measureModeField.setText("1");
        optimizeModeField.setText("2");
        reflectionThresholdField.setText("3.783");
        enabledRefreshField.setText("4");
        refreshCycleField.setText("5");
        testWaveLengthField.setText("6");
        measuringRangeOfTestField.setText("7");
        testPulseWidthField.setText("8");
        measuringTimeField.setText("9");
        refractiveIndexField.setText("10.4898");
        endThresholdField.setText("11.2672");
        nonReflactionThresholdField.setText("12.3783");

        // Inicializa a tabela de resultados
        numeroColumn.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
        distanceColumn.setCellValueFactory(cellData -> cellData.getValue().distanceProperty());
        insertLossColumn.setCellValueFactory(cellData -> cellData.getValue().insertLossProperty());
        reflectLossColumn.setCellValueFactory(cellData -> cellData.getValue().reflectLossProperty());
        accumulationColumn.setCellValueFactory(cellData -> cellData.getValue().accumulationLossProperty());
        attenuationCoefficientColumn.setCellValueFactory(cellData -> cellData.getValue().attenuationCoefficientProperty());

        numeroColumn.setMinWidth(40);
        typeColumn.setMinWidth(55);
        distanceColumn.setMinWidth(90);
        insertLossColumn.setMinWidth(105);
        reflectLossColumn.setMinWidth(110);
        accumulationColumn.setMinWidth(160);
        attenuationCoefficientColumn.setMinWidth(190);

    }

    /**
     * É chamado pela aplicação principal para dar uma referência de volta a si
     * mesmo.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        // Adiciona os dados da observable list na tabela
        resultTable.setItems(mainApp.getResultsData());
        devicesList.setItems(mainApp.getDevicesData());
        grafico.setData(mainApp.getCoordinatesData());
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
    private void OnDeleteDevice() {

        int selectedIndex = devicesList.getSelectionModel().getSelectedIndex();

        if (selectedIndex >= 0) {
            if (alertDeviceDeletion(devicesList.getSelectionModel().getSelectedItem().getIp())) {
                devicesList.getItems().remove(selectedIndex);
            }

        } else {
            alertDeviceSelection();
        }

        devicesList.setCellFactory(new Callback<ListView<Device>, ListCell<Device>>() {

            @Override
            public ListCell<Device> call(ListView<Device> p) {

                ListCell<Device> cell = new ListCell<Device>() {

                    @Override
                    protected void updateItem(Device t, boolean bln) {
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

    /**
     * Chamado quando o usuário clica no botão delete.
     */
    @FXML
    private void OnAddDevice() {
        Device tempDevice = new Device();
        boolean okClicked = mainApp.openDeviceDialog(tempDevice);
        if (okClicked) {
            mainApp.getDevicesData().add(tempDevice);
        }
    }

    @FXML
    private void OnEditParameters() {
        measureModeField.setOpacity(1);
        measureModeField.setEditable(true);

        optimizeModeField.setOpacity(1);
        optimizeModeField.setEditable(true);

        reflectionThresholdField.setOpacity(1);
        reflectionThresholdField.setEditable(true);

        enabledRefreshField.setOpacity(1);
        enabledRefreshField.setEditable(true);

        refreshCycleField.setOpacity(1);
        refreshCycleField.setEditable(true);

        testWaveLengthField.setOpacity(1);
        testWaveLengthField.setEditable(true);

        measuringRangeOfTestField.setOpacity(1);
        measuringRangeOfTestField.setEditable(true);

        testPulseWidthField.setOpacity(1);
        testPulseWidthField.setEditable(true);

        measuringTimeField.setOpacity(1);
        measuringTimeField.setEditable(true);

        refractiveIndexField.setOpacity(1);
        refractiveIndexField.setEditable(true);

        endThresholdField.setOpacity(1);
        endThresholdField.setEditable(true);

        nonReflactionThresholdField.setOpacity(1);
        nonReflactionThresholdField.setEditable(true);
    }

    @FXML
    private void OnSaveParameters() {
        try {
            int measureMode = Integer.parseInt(measureModeField.getText());
            int optimizeMode = Integer.parseInt(measureModeField.getText());

            /**
             * PARSEFLOAT NÃO FUNCIONA -- ENTENDER POR QUE
             */
            float reflectionThreshold = Float.parseFloat(measureModeField.getText());
            int enabledRefresh = Integer.parseInt(measureModeField.getText());
            int refreshCycle = Integer.parseInt(measureModeField.getText());
            int testWaveLength = Integer.parseInt(measureModeField.getText());
            int measuringRangeOfTest = Integer.parseInt(measureModeField.getText());
            int testPulseWidth = Integer.parseInt(measureModeField.getText());
            int measuringTime = Integer.parseInt(measureModeField.getText());
            float refractiveIndex = Float.parseFloat(measureModeField.getText());
            float endThreshold = Float.parseFloat(measureModeField.getText());
            float nonReflactionThreshold = Float.parseFloat(measureModeField.getText());

        } catch (NumberFormatException ex) {
            alertIncorrectTypeParameters(ex.getMessage());
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }

        measureModeField.setOpacity(0.4);
        measureModeField.setEditable(false);

        optimizeModeField.setOpacity(0.4);
        optimizeModeField.setEditable(false);

        reflectionThresholdField.setOpacity(0.4);
        reflectionThresholdField.setEditable(false);

        enabledRefreshField.setOpacity(0.4);
        enabledRefreshField.setEditable(false);

        refreshCycleField.setOpacity(0.4);
        refreshCycleField.setEditable(false);

        testWaveLengthField.setOpacity(0.4);
        testWaveLengthField.setEditable(false);

        measuringRangeOfTestField.setOpacity(0.4);
        measuringRangeOfTestField.setEditable(false);

        testPulseWidthField.setOpacity(0.4);
        testPulseWidthField.setEditable(false);

        measuringTimeField.setOpacity(0.4);
        measuringTimeField.setEditable(false);

        refractiveIndexField.setOpacity(0.4);
        refractiveIndexField.setEditable(false);

        endThresholdField.setOpacity(0.4);
        endThresholdField.setEditable(false);

        nonReflactionThresholdField.setOpacity(0.4);
        nonReflactionThresholdField.setEditable(false);

    }

    @FXML
    private void OnExecute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementar");
        alert.setHeaderText("//TO DO");
        alert.showAndWait();
    }

    @FXML
    private void OnExport() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementar");
        alert.setHeaderText("//TO DO");
        alert.showAndWait();
    }

    @FXML
    private void OnSetReference() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementar");
        alert.setHeaderText("//TO DO");
        alert.showAndWait();
    }

    @FXML
    private void OnChangeToMonitor() throws IOException {
//		int selectedIndex = devicesList.getSelectionModel().getSelectedIndex();
//
//        if (selectedIndex >= 0) {
//            mainApp.openMonitorWindow(devicesList.getSelectionModel().getSelectedItem());
//        } else {
//            mainApp.openMonitorWindow(devicesList.getItems().get(0));
//        }
		MainApp.getInstance().showView(View.MonitorWindow, Mode.VIEW);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

	@Override
	public void handleSave(ActionEvent event) {

	}

	@Override
	public void prepareForm(Mode mode) {

	}

	@Override
	public void prepareMenu(Mode mode) {

	}

}
