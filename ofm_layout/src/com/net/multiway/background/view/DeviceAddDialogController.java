/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.data.Device;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author rafael
 */
public class DeviceAddDialogController {

    @FXML
    private TextField ipField;
    @FXML
    private TextField maskField;
    @FXML
    private TextField gatewayField;

    private Stage dialogStage;
    private Device device;
    private boolean okClicked = false;

    /**
     * Inicializa a classe controlle. Este método é chamado automaticamente após
     * o arquivo fxml ter sido carregado.
     */
    @FXML
    private void initialize() {
    }

    /**
     * Define o palco deste dialog.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Define a pessoa a ser editada no dialog.
     *
     * @param vehicle
     */
    public void setDevice(Device device) {
        this.device = device;

        ipField.setText(device.getIp());
        maskField.setText(device.getMask());
        gatewayField.setText(device.getGateway());
    }

    /**
     * Retorna true se o usuário clicar OK,caso contrário false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    private void alertIncorrectField(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Campo inválido");
        alert.setHeaderText("Por favor, verifique o campo " + text + ".");

        alert.showAndWait();
    }

    /**
     * Chamado quando o usuário clica OK.
     */
    @FXML
    private void onHandleOk() {

        if (ipField.getText() == null) {
            alertIncorrectField("IP");
            return;
        } else {
            device.setIp(ipField.getText());
        }

        if (maskField.getText() == null) {
            alertIncorrectField("Mask");
            return;
        } else {
            device.setMask(maskField.getText());
        }

        if (gatewayField.getText() == null) {
            alertIncorrectField("Gateway");
            return;
        } else {
            device.setGateway(gatewayField.getText());
        }

//        DataDeviceDAO dao = new DataDeviceDAO();
//        dao.create(device);
        okClicked = true;
        dialogStage.close();

    }

    /**
     * Chamado quando o usuário clica Cancel.
     */
    @FXML
    private void onHandleCancel() {
        dialogStage.close();
    }

}
