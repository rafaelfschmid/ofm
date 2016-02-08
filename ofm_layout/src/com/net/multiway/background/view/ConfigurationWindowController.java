/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataDevice;
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

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {

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

    @FXML
    private void onHandleEditParameters() {

    }

    @FXML
    private void onHandleSaveParameters() {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        devicesList.setItems(MainApp.getInstance().getDevicesData());

        updateDeviceList();
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
