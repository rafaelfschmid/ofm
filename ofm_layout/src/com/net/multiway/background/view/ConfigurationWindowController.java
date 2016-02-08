/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataDevice;

import com.net.multiway.background.data.DataParameters;
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
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
    @FXML
    private Button buttonSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parameters = new DataParameters();

        devicesList.setItems(MainApp.getInstance().getDevicesData());
        updateDeviceList();
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

    @FXML
    private void onHandleEditParameters() {
        prepareForm(Mode.EDIT);
    }

    @FXML
    private void onHandleSaveParameters() {
        if (measureModeField.getText().isEmpty()) {
            alertIncorrectField("Measure Mode");
        } else if (optimizeModeField.getText().isEmpty()) {
            alertIncorrectField("Optimize Mode");
        } else if (reflectionThresholdField.getText().isEmpty()) {
            alertIncorrectField("Reflection Threshold");
        } else if (enabledRefreshField.getText().isEmpty()) {
            alertIncorrectField("Enabled Refresh");
        } else if (refreshCycleField.getText().isEmpty()) {
            alertIncorrectField("Refresh Cycle");
        } else if (testWaveLengthField.getText().isEmpty()) {
            alertIncorrectField("Test Wave Length");
        } else if (measuringRangeOfTestField.getText().isEmpty()) {
            alertIncorrectField("Measuring Range Of Test");
        } else if (testPulseWidthField.getText().isEmpty()) {
            alertIncorrectField("Test Pulse Width");
        } else if (measuringTimeField.getText().isEmpty()) {
            alertIncorrectField("Measuring Time");
        } else if (refractiveIndexField.getText().isEmpty()) {
            alertIncorrectField("Refractive Index");
        } else if (endThresholdField.getText().isEmpty()) {
            alertIncorrectField("End Threshold");
        } else if (nonReflactionThresholdField.getText().isEmpty()) {
            alertIncorrectField("Non Reflaction Threshold");

        } else {
            parameters.setMeasureMode(Integer.parseInt(measureModeField.getText()));
            parameters.setOptimizeMode(Integer.parseInt(optimizeModeField.getText()));
            parameters.setReflectionThreshold(Float.parseFloat(reflectionThresholdField.getText()));
            parameters.setEnabledRefresh(Integer.parseInt(enabledRefreshField.getText()));
            parameters.setRefreshCycle(Integer.parseInt(refreshCycleField.getText()));
            parameters.setTestWaveLength(Integer.parseInt(testWaveLengthField.getText()));
            parameters.setMeasuringRangeOfTest(Integer.parseInt(measuringRangeOfTestField.getText()));
            parameters.setTestPulseWidth(Integer.parseInt(testPulseWidthField.getText()));
            parameters.setMeasuringTime(Integer.parseInt(measuringTimeField.getText()));
            parameters.setRefractiveIndex(Float.parseFloat(refractiveIndexField.getText()));
            parameters.setEndThreshold(Float.parseFloat(endThresholdField.getText()));
            parameters.setNonReflactionThreshold(Float.parseFloat(nonReflactionThresholdField.getText()));
            prepareForm(Mode.VIEW);
        }
    }

    @FXML
    private void onHandleExecute() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Implementar");
        alert.setHeaderText("//TO DO");
        alert.showAndWait();
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
                measureModeField.setDisable(true);
                optimizeModeField.setDisable(true);
                reflectionThresholdField.setDisable(true);
                enabledRefreshField.setDisable(true);
                refreshCycleField.setDisable(true);
                testWaveLengthField.setDisable(true);
                measuringRangeOfTestField.setDisable(true);
                testPulseWidthField.setDisable(true);
                measuringTimeField.setDisable(true);
                refractiveIndexField.setDisable(true);
                endThresholdField.setDisable(true);
                nonReflactionThresholdField.setDisable(true);
                buttonSave.setDisable(true);
                break;
            case EDIT:
                measureModeField.setDisable(false);
                optimizeModeField.setDisable(false);
                reflectionThresholdField.setDisable(false);
                enabledRefreshField.setDisable(false);
                refreshCycleField.setDisable(false);
                testWaveLengthField.setDisable(false);
                measuringRangeOfTestField.setDisable(false);
                testPulseWidthField.setDisable(false);
                measuringTimeField.setDisable(false);
                refractiveIndexField.setDisable(false);
                endThresholdField.setDisable(false);
                nonReflactionThresholdField.setDisable(false);
                buttonSave.setDisable(false);

                break;
        }
    }

    @Override
    public void prepareMenu(Mode mode) {

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

}
