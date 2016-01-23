/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background;

import com.net.multiway.background.controller.ConfigurationWindowController;
import com.net.multiway.background.controller.DeviceAddController;
import com.net.multiway.background.controller.MonitorWindowController;
import com.net.multiway.background.model.Device;
import com.net.multiway.background.model.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

    private Stage primaryStage;
    private BorderPane mainScene;

    private List<Stage> listWindow = new ArrayList<>();

    private ObservableList<Result> resultsData = FXCollections.observableArrayList();
    private ObservableList<Device> devicesData = FXCollections.observableArrayList();
    private ObservableList<XYChart.Series<Double, Double>> coordinatesData = FXCollections.observableArrayList();

    public MainApp() {
        resultsData.add(new Result(11, 21, 31, 1.1, 2.1, 3.1, 4.1));
        resultsData.add(new Result(12, 22, 32, 1.2, 2.2, 3.2, 4.2));
        resultsData.add(new Result(13, 23, 33, 1.3, 2.3, 3.3, 4.3));

        Device x1 = new Device("192.168.4.2", "192.168.4.0", "192.168.4.1");
        x1.setCoordinatesData(generateDataLineChart());
        devicesData.add(x1);

        Device x2 = new Device("192.168.4.3", "192.168.4.0", "192.168.4.1");
        x2.setCoordinatesData(generateDataLineChart());
        devicesData.add(x2);

        Device x3 = new Device("192.168.4.4", "192.168.4.0", "192.168.4.1");
        x3.setCoordinatesData(generateDataLineChart());
        devicesData.add(x3);

        Device x4 = new Device("192.168.4.5", "192.168.4.0", "192.168.4.1");
        x4.setCoordinatesData(generateDataLineChart());
        devicesData.add(x4);

        Device x5 = new Device("192.168.4.6", "192.168.4.0", "192.168.4.1");
        x5.setCoordinatesData(generateDataLineChart());
        devicesData.add(x5);

        Series<Double, Double> aSeries = new Series<>();
        aSeries.getData().add(new XYChart.Data(1, 23));
        aSeries.getData().add(new XYChart.Data(2, 14));
        aSeries.getData().add(new XYChart.Data(3, 15));
        aSeries.getData().add(new XYChart.Data(4, 24));
        aSeries.getData().add(new XYChart.Data(5, 34));
        aSeries.getData().add(new XYChart.Data(6, 36));

        coordinatesData.addAll(aSeries);
    }

    private ObservableList<XYChart.Series<Double, Double>> generateDataLineChart() {
        ObservableList<XYChart.Series<Double, Double>> data = FXCollections.observableArrayList();
        XYChart.Series<Double, Double> series1 = new XYChart.Series<>();
        series1.getData().add(new XYChart.Data(1, Math.random() * 100 - 50));
        series1.getData().add(new XYChart.Data(2, Math.random() * 100 - 50));
        series1.getData().add(new XYChart.Data(3, Math.random() * 100 - 50));
        series1.getData().add(new XYChart.Data(4, Math.random() * 100 - 50));
        series1.getData().add(new XYChart.Data(5, Math.random() * 100 - 50));
        series1.getData().add(new XYChart.Data(6, Math.random() * 100 - 50));
        data.addAll(series1);

        return data;
    }

    public ObservableList<Result> getResultsData() {
        return resultsData;
    }

    public ObservableList<Device> getDevicesData() {
        return devicesData;
    }

    public ObservableList<XYChart.Series<Double, Double>> getCoordinatesData() {
        return coordinatesData;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Optical Fiber Monitor");

        //set the application icon
        //this.primaryStage.getIcons().add(new Image("file:resources/truck_red.png"));
        initMainScene();

        openConfigurationWindow();
    }

    /**
     * Inicializa o root layout (layout base).
     */
    public void initMainScene() {
        try {
            // Carrega o root layout do arquivo fxml.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MainScene.fxml"));

            //mainScene = (BorderPane) loader.load();
            mainScene = (BorderPane) loader.load();

            // Mostra a scene (cena) contendo oroot layout.
            Scene scene = new Scene(mainScene);
            primaryStage.setScene(scene);
            primaryStage.show();
            //primaryStage.setMaximized(true);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Mostra o person overview dentro do root layout.
     */
    public void openConfigurationWindow() {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/ConfigurationWindow.fxml"));
            AnchorPane configurationWindow = (AnchorPane) loader.load();

            // Define o person overview dentro do root layout.
            mainScene.setCenter(configurationWindow);

            // Dá ao controlador acesso à the main app.
            ConfigurationWindowController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private int findWindow(Device device) {
        for (int i = 0; i < listWindow.size(); i++) {
            if (listWindow.get(i).getTitle() == device.getIp()) {
                return i;
            }
        }

        return -1;
    }

    public void closeWindow(Stage stage) {
        listWindow.remove(stage);
    }

    /**
     * Inicializa o root layout (layout base).
     * @param device
     */
    public void openMonitorWindow(Device device) {
        try {
            // Carrega o person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/MonitorWindow.fxml"));
            AnchorPane monitorWindow = (AnchorPane) loader.load();

            Stage stage;
            int index = findWindow(device);
            if (index >= 0) {
                stage = listWindow.get(index);
                //stage.show();
                stage.toFront();

            } else {
                Scene scene = new Scene(monitorWindow);
                stage = new Stage();

                listWindow.add(stage);
                stage.setTitle("Monitor - " + device.getIp());
                stage.setScene(scene);
                stage.show();
            }

            //Scene node = new Scene(configurationWindow);
//           mainScene.getChildren().add(g);
            // Dá ao controlador acesso à the main app.
            MonitorWindowController controller = loader.getController();
            controller.setStage(stage);
            controller.setMainApp(this);
            controller.setDevice(device);

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Abre uma janela para editar detalhes para o dispositivo especificado. Se
     * o usuário clicar OK, as mudanças são salvas no objeto Device fornecido e
     * retorna true.
     *
     * @param device O objeto device a ser editado
     * @return true Se o usuário clicou OK, caso contrário false.
     */
    public boolean openDeviceDialog(Device device) {
        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/DeviceEditDialog.fxml"));
            AnchorPane page;

            page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar dispositivo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Define o device no controller.
            DeviceAddController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setDevice(device);

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

            return controller.isOkClicked();

        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * Retorna o palco principal.
     *
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
