/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.data.DataDevice;

import com.net.multiway.background.data.DataParameters;
import com.net.multiway.background.data.DataReceive;
import com.net.multiway.background.data.DataReceiveEvents;
import com.net.multiway.background.data.DataReference;
import com.net.multiway.background.data.dao.DataDeviceDAO;
import com.net.multiway.background.data.dao.DataParametersDAO;
import com.net.multiway.background.data.dao.DataReceiveDAO;
import com.net.multiway.background.data.dao.DataReceiveEventsDAO;
import com.net.multiway.background.data.dao.DataReferenceDAO;
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

	private DataDevice device;

	private ObservableList<DataDevice> devicesData = FXCollections.observableArrayList();

	private ReceiveParameters receiveParameters;

	private ReceiveValues receiveValues;

	private DataReference reference;

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		DataDeviceDAO dao = new DataDeviceDAO();
		devicesData.addAll(dao.getDevices());
		devicesList.setItems(devicesData);
		updateDeviceList();

		if (devicesList.getItems().size() > 0) {
			device = devicesList.getItems().get(0);
		} else {
			device = null;
		}

		String msg = "Devices carregados na tela...";
		Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);

		mappingParametersTable();
		DataParametersDAO daop = new DataParametersDAO();

		parameters = daop.findDataParameters(Long.parseLong("1"));

		if (parameters == null) {
			parameters = new DataParameters(0, 0, 15000, 1550, 1, 1.4685f, 0, 5.0f, 65.0f, 0, 1, 1);
		}

		updateParameters();

		reference = new DataReference();

		measureRangeField.setValue(parameters.getMeasuringRangeOfTest());
		pulseWidthField.setValue(parameters.getTestPulseWidth());
		measureTimeField.setValue(parameters.getMeasuringTime() / 1000);
		waveLengthField.setValue(parameters.getTestWaveLength());
		if (parameters.getMeasureMode() == 1) {
			measureModeField.setValue("1-Average");
		} else {
			measureModeField.setValue("2-Real Time");
		}
		refractiveIndexField.setText(parameters.getRefractiveIndex().toString());
		nonReflactionThresholdField.setText(parameters.getNonReflactionThreshold().toString());
		endThresholdField.setText(parameters.getEndThreshold().toString());
		reflectionThresholdField.setText(parameters.getReflectionThreshold().toString());

		msg = "Parâmetros carregados na tela...";
		Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
	}

	private void alertToSaveParameters() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Parametros não salvos");
		alert.setHeaderText("Por favor, salvar os parâmetros antes de prosseguir.");

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

	private boolean alertNothingToExport() {
		// Nada selecionado.
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Nada a ser exportado.");
		alert.setHeaderText("Os objetos estão vazios. Execute antes de exportar.");

		alert.showAndWait();

		if (alert.getResult() == null) {
			return false;
		}

		return true;
	}

	private void alertNothingToReference() {
		// Nada selecionado.
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Nada a ser referenciado.");
		alert.setHeaderText("Realize uma execu��o antes de ter uma referencia!");

		alert.showAndWait();

	}

	/**
	 * Chamado quando o usuário clica no botão delete.
	 */
	@FXML
	private void onHandleDeleteDevice() {

		int selectedIndex = devicesList.getSelectionModel().getSelectedIndex();

		if (selectedIndex >= 0) {
			if (alertDeviceDeletion(devicesList.getSelectionModel().getSelectedItem().getIp())) {
				DataDeviceDAO dao = new DataDeviceDAO();
				dao.deleteData(devicesList.getSelectionModel().getSelectedItem());
				devicesList.getItems().remove(selectedIndex);

				if (devicesList.getItems().size() > 0) {
					device = devicesList.getItems().get(0);
				}
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
//                DataDeviceDAO dao = new DataDeviceDAO();
//                dao.create(device);
				devicesData.add(device);
				devicesList.setItems(devicesData);
				this.device = device;
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

	private void alertDeviceNotFound() {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Device Not Found");
		alert.setHeaderText("Por favor, adicione um dispositivo antes de continuar.");

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

	private boolean validateParametersField() {
		if (measureRangeField.getValue() == null) {
			alertIncorrectField("Measuring Range Of Test");
			return false;
		} else if (pulseWidthField.getValue() == null) {
			alertIncorrectField("Test Pulse Width");
			return false;
		} else if (measureTimeField.getValue() == null) {
			alertIncorrectField("Measuring Time");
			return false;
		} else if (waveLengthField.getValue() == null) {
			alertIncorrectField("Test Wave Length");
			return false;
		} else if (measureModeField.getValue() == null) {
			alertIncorrectField("Measure Mode");
			return false;
		} else if (refractiveIndexField.getText().isEmpty()) {
			alertIncorrectField("Refractive Index");
			return false;
		} else if (nonReflactionThresholdField.getText().isEmpty()) {
			alertIncorrectField("Non Reflaction Threshold");
			return false;
		} else if (endThresholdField.getText().isEmpty()) {
			alertIncorrectField("End Threshold");
			return false;
		} else if (reflectionThresholdField.getText().isEmpty()) {
			alertIncorrectField("Reflection Threshold");
			return false;
		} else {
			return true;
		}
	}

	@FXML
	private void onHandleSaveParameters() {
		if (validateParametersField()) {
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
				parameters.setMeasuringTime(Integer.parseInt(measureTimeField.getValue().toString()) * 1000);
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

		DeviceComunicator host;
		if (device != null) {
			host = new DeviceComunicator(device.getIp().trim(), 5000);

			if (validateParametersField()) {

				Task execute = new Task() {
					@Override
					protected String call() throws Exception {
						if (resultTable.getItems().size() > 0 && grafico.getData().size() > 0) {
							resultTable.getItems().remove(0, resultTable.getItems().size());

						}

						host.connect(parameters);

						String msg = "Envio de dados finalizado.";
						Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);
						return "Conexao realizada";
					}

					@Override
					protected void succeeded() {
						receiveParameters = host.getReceiveParametersData();
						showReceiveParametersTable((ArrayList<DataReceiveEvents>) receiveParameters.getData().getEvents());
						String msg = "Parametros atualizados na tela de configuração.";
						Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);

						receiveValues = host.getReceiveValues();
						grafico.getData().clear();
						plotGraph();
						grafico.setCreateSymbols(false);
						msg = "Gráfico plotado na tela de configuração.";
						Logger.getLogger(MainApp.class.getName()).log(Level.INFO, msg);

					}
				};

				Thread tr = new Thread(execute);

				tr.start();
			}
		} else {
			alertDeviceNotFound();

		}

	}

	private void plotGraph() {

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

	@FXML
	private void onHandleExport() {
		if ((receiveParameters == null) || (receiveValues == null)) {
			alertNothingToExport();
		} else {
			receiveParameters.print();
			receiveValues.print();
		}
	}

	@FXML
	private void onHandleSetReference() {
		if (receiveParameters != null && (receiveValues != null) && device != null) {
			reference.setDataReceive(receiveParameters.getData());
			reference.setDevice(device);
			reference.setParameters(parameters);

			Logger.getLogger(MainApp.class.getName()).log(Level.INFO, reference.getDevice().getIp());
			
			DataReceive dtR = new DataReceive();
			dtR = reference.getDataReceive();
			dtR.setEvents(null);
			dtR.setID(null);
			
			DataReceiveDAO rdao = new DataReceiveDAO();
			rdao.create(dtR);
			
			DataReceiveEventsDAO edao = new DataReceiveEventsDAO();
			for (DataReceiveEvents receiveEvents : reference.getDataReceive().getEvents()) {
				edao.create(receiveEvents);
			}
			DataReferenceDAO dao = new DataReferenceDAO();
			dao.create(reference);

		} else {
			alertNothingToReference();
		}
	}

	@FXML
	private void onHandleChangeToMonitor() throws IOException {

		if (devicesList.getItems().size() > 0) {
			int selectedIndex = devicesList.getSelectionModel().getSelectedIndex();

			MonitorWindowController controller
					= (MonitorWindowController) MainApp.getInstance().showView(View.MonitorWindow, Mode.VIEW);

			DataDevice device;
			if (selectedIndex >= 0) {
				device = devicesList.getSelectionModel().getSelectedItem();
			} else {
				device = devicesList.getItems().get(0);
			}

			controller.setDevice(device);
			controller.setParameters(parameters);
		}
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

	private void showReceiveParametersTable(ArrayList<DataReceiveEvents> r) {

		ObservableList<DataReceiveEvents> value = FXCollections.observableArrayList();
		for (int i = 0; i < r.size(); i++) {
			value.add(r.get(i));
		}
		resultTable.setItems(value);

	}

}
