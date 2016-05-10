/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.MainApp;
import com.net.multiway.background.exception.AlertDialog;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import com.net.multiway.background.model.View;
import com.net.multiway.background.report.Reports;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author Phelipe
 */
public class MainSceneController implements Initializable, IController {

    private IController centerController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setCenterController(IController controller) {
        this.centerController = controller;
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

    @FXML
    private void onMenuPrint(ActionEvent event) {
    }

    @FXML
    private void onMenuExit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void onMenuExportLastRead(ActionEvent event) throws SQLException, JRException {
        Reports report = new Reports();

        HashMap	paramRel = new HashMap();
        String nomeRelatorio = "Relatório 1";
       report.gerarRelatorio(nomeRelatorio, paramRel);

    }

    @FXML
    private void onMenuExportEventsList(ActionEvent event) {
    }

    @FXML
    private void onMenuExportOcurrencesList(ActionEvent event) {
    }

    @FXML
    private void onMenuHelp(ActionEvent event) {
    }

    @FXML
    private void onMenuAbout(ActionEvent event) {
    }

    @FXML
    private void onMenuConfigureLimits(ActionEvent event) {

        try {
            // Carrega o arquivo fxml e cria um novo stage para a janela popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource(View.RangeDialog.getResource()));
            AnchorPane page;

            page = (AnchorPane) loader.load();

            // Cria o palco dialogStage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Editar Limites");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(MainApp.getInstance().getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

//            // Define o device no controller.
            RangeDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setRange();

            // Mostra a janela e espera até o usuário fechar.
            dialogStage.showAndWait();

//            if (controller.isOkClicked()) {
////                DataDeviceDAO dao = new DataDeviceDAO();
////                dao.create(device);
//                devicesData.add(device);
//                devicesList.setItems(devicesData);
//                this.device = device;
//            }
        } catch (IOException ex) {
            Logger.getLogger(ConfigurationWindowController.class.getName()).log(Level.SEVERE, null, ex);
            AlertDialog.exception(ex);
        }

    }

}
