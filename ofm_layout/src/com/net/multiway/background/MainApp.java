/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background;

import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.View;
import com.net.multiway.background.view.MainSceneController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Node;
import static javafx.application.Application.launch;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class MainApp extends Application {

	private static MainApp instance;
	private Stage primaryStage;
	private BorderPane rootLayout;
	private MainSceneController rootController;

	private List<Stage> listWindow = new ArrayList<>();

//	private ObservableList<Result> resultsData = FXCollections.observableArrayList();
	private ObservableList<DataDevice> devicesData = FXCollections.observableArrayList();
//	private ObservableList<XYChart.Series<Double, Double>> coordinatesData = FXCollections.observableArrayList();

	public static MainApp getInstance() {
		return instance;
	}

	public MainApp() {
//		resultsData.add(new Result(11, 21, 31, 1.1, 2.1, 3.1, 4.1));
//		resultsData.add(new Result(12, 22, 32, 1.2, 2.2, 3.2, 4.2));
//		resultsData.add(new Result(13, 23, 33, 1.3, 2.3, 3.3, 4.3));

		DataDevice x1 = new DataDevice(new Long(1), "192.168.4.2", "192.168.4.0", "192.168.4.1");
		devicesData.add(x1);

//		Series<Double, Double> aSeries = new Series<>();
//		aSeries.getData().add(new XYChart.Data(1, 23));
//		aSeries.getData().add(new XYChart.Data(2, 14));
//		aSeries.getData().add(new XYChart.Data(3, 15));
//		aSeries.getData().add(new XYChart.Data(4, 24));
//		aSeries.getData().add(new XYChart.Data(5, 34));
//		aSeries.getData().add(new XYChart.Data(6, 36));
//
//		coordinatesData.addAll(aSeries);
	}

//	private ObservableList<XYChart.Series<Double, Double>> generateDataLineChart() {
//		ObservableList<XYChart.Series<Double, Double>> data = FXCollections.observableArrayList();
//		XYChart.Series<Double, Double> series1 = new XYChart.Series<>();
//		series1.getData().add(new XYChart.Data(1, Math.random() * 100 - 50));
//		series1.getData().add(new XYChart.Data(2, Math.random() * 100 - 50));
//		series1.getData().add(new XYChart.Data(3, Math.random() * 100 - 50));
//		series1.getData().add(new XYChart.Data(4, Math.random() * 100 - 50));
//		series1.getData().add(new XYChart.Data(5, Math.random() * 100 - 50));
//		series1.getData().add(new XYChart.Data(6, Math.random() * 100 - 50));
//		data.addAll(series1);
//
//		return data;
//	}

//	public ObservableList<Result> getResultsData() {
//		return resultsData;
//	}

	public ObservableList<DataDevice> getDevicesData() {
		return devicesData;
	}
//
//	public ObservableList<XYChart.Series<Double, Double>> getCoordinatesData() {
//		return coordinatesData;
//	}

//    @Override
//    public void start(Stage primaryStage) {
//        this.primaryStage = primaryStage;
//        this.primaryStage.setTitle("Optical Fiber Monitor");
//
//        //set the application icon
//        //this.primaryStage.getIcons().add(new Image("file:resources/truck_red.png"));
//   //     initMainScene();
//
//        openConfigurationWindow();
//    }
//
//    /**
//     * Inicializa o root layout (layout base).
//     */
//    public void initMainScene() {
//        try {
//            // Carrega o root layout do arquivo fxml.
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource("view/MainScene.fxml"));
//
//            //mainScene = (BorderPane) loader.load();
//            rootLayout = (BorderPane) loader.load();
//
//            // Mostra a scene (cena) contendo oroot layout.
//            Scene scene = new Scene(rootLayout);
//            primaryStage.setScene(scene);
//            primaryStage.show();
//            //primaryStage.setMaximized(true);
//
//        } catch (IOException ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//
//    /**
//     * Mostra o person overview dentro do root layout.
//     */
//    public void openConfigurationWindow() {
//        try {
//          // Loads the root layout from fxml file
//            FXMLLoader rootLoader = new FXMLLoader();
//            rootLoader.setLocation(MainApp.class.getResource(View.MainScene.getResource()));
//            rootLayout = (BorderPane) rootLoader.load();
//
//            // Gets RootLayoutController
//            rootController = rootLoader.getController();
//            rootController.prepareForm(null);
//            rootController.prepareMenu(null);
//
//            // Loads welcome view
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(MainApp.class.getResource(View.ConfigurationWindow.getResource()));
//            Node node = loader.load();
//            IController controller = (IController) loader.getController();
//            controller.prepareForm(null);
//            controller.prepareMenu(null);
//            rootController.setCenterController(controller);
//      //      rootLayout.setCenter((AnchorPane) node);
//
//        } catch (IOException ex) {
//            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
	/**
	 * Shows the stage with the Voting Process Insert View
	 *
	 * @param primaryStage stage to show
	 * @throws Exception
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		instance = this;

		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Optical Fiber Monitor");
		this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
});
		initRootLayout();
// Shows the scene containing RootLayout
		Scene scene = new Scene(rootLayout);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void initRootLayout() {
		try {
			// Loads the root layout from fxml file
			FXMLLoader rootLoader = new FXMLLoader();
			rootLoader.setLocation(MainApp.class.getResource(View.MainScene.getResource()));
			rootLayout = (BorderPane) rootLoader.load();

			// Gets RootLayoutController
			rootController = rootLoader.getController();
			rootController.prepareForm(null);
			rootController.prepareMenu(null);

			// Loads welcome view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(View.ConfigurationWindow.getResource()));
			Node node = loader.load();
			IController controller = (IController) loader.getController();
			controller.prepareForm(null);
			controller.prepareMenu(null);
			rootController.setCenterController(controller);
			rootLayout.setCenter((AnchorPane) node);
		} catch (IOException ex) {
			Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
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

	/**
	 * Shows a view on screen <br />
	 * - loads the FXML via its resource (String file name); <br />
	 * - creates a reference to the view's controller; <br />
	 * - prepares the controller according to <code>Mode</code> passed (Default:
	 * VIEW) - positions the view in root layout's center; <br />
	 * If an exception is caused, logs it.
	 *
	 * @see Mode
	 * @see View
	 * @param mode Preparation mode of view: NEW, EDIT or VIEW
	 * @param view view to be shown
	 */
	public void showView(View view, Mode mode) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(view.getResource()));
			Node node = loader.load();
			IController controller = (IController) loader.getController();

			controller.prepareForm(mode);
			controller.prepareMenu(mode);

			rootController.setCenterController(controller);
			rootLayout.setCenter((AnchorPane) node);
		} catch (IOException ex) {
			Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
