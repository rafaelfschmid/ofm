/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background;

import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.View;
import com.net.multiway.background.view.MainSceneController;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
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


	public static MainApp getInstance() {
		return instance;
	}

	public MainApp() {
		
	}


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

			// Loads Configuration view
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource(View.ConfigurationWindow.getResource()));
			Node node = loader.load();
			IController controller = (IController) loader.getController();
			controller.prepareForm(Mode.VIEW);
			controller.prepareMenu(Mode.VIEW);
			rootController.setCenterController(controller);
			rootLayout.setCenter((AnchorPane) node);
		} catch (IOException ex) {
			Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	
	
	/**
	 * Abre uma janela para editar detalhes para o dispositivo especificado. Se
	 * o usuário clicar OK, as mudanças são salvas no objeto Device fornecido
	 * e retorna true.
	 *
	 * @param device O objeto device a ser editado
	 * @return true Se o usuário clicou OK, caso contrário false.
	 */ 
	// Mandar para o ConfigurationWindowController
//	public boolean openDeviceDialog(Device device) {
//		try {
//			// Carrega o arquivo fxml e cria um novo stage para a janela popup.
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(MainApp.class.getResource("view/DeviceEditDialog.fxml"));
//			AnchorPane page;
//
//			page = (AnchorPane) loader.load();
//
//			// Cria o palco dialogStage.
//			Stage dialogStage = new Stage();
//			dialogStage.setTitle("Editar dispositivo");
//			dialogStage.initModality(Modality.WINDOW_MODAL);
//			dialogStage.initOwner(primaryStage);
//			Scene scene = new Scene(page);
//			dialogStage.setScene(scene);
//
//			// Define o device no controller.
//			DeviceAddDialogController controller = loader.getController();
//			controller.setDialogStage(dialogStage);
//			controller.setDevice(device);
//
//			// Mostra a janela e espera até o usuário fechar.
//			dialogStage.showAndWait();
//
//			return controller.isOkClicked();
//
//		} catch (IOException ex) {
//			Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
//			return false;
//		}
//
//	}

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
