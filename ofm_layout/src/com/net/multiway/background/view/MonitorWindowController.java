/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.model.Device;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.Result;
import com.net.multiway.background.model.View;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.jws.WebParam;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class MonitorWindowController implements Initializable,IController {

	 private IController centerController;
    //implements Initializable {
   

    private Stage stage;
    private Device device;

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

    @FXML
    private void onHandleEditParameters() {
       
    }

    @FXML
    private void onHandleSaveParameters() {
        
    }

    @FXML
    private void onHandleExecute() {
       
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
	}

	@Override
	public void prepareMenu(Mode mode) {
	}
}
