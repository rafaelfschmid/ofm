/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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
    private void onMenuExportLastRead(ActionEvent event) {
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

}
