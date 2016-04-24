/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

/**
 *
 * @author phelipe
 */
public enum DisplayError {
    GREEN,
    YELLOW,
    RED;

    /**
     * @return File path to FXML resource of the view
     */
    public String getError(String type) {
        String err = "";
        switch (this) {
            case GREEN:
                err = "";
            case YELLOW:
                switch (type) {
                    case "DistanceError":
                        err = "view/ConfigurationWindow.fxml";
                    case "InsertionLossError":
                        err = "view/MonitorWindow.fxml";
                    case "ReflexLossError":
                        err = "view/MainScene.fxml";
                    case "AcumulationLossError":
                        err = "view/DeviceAddDialog.fxml";
                    case "AtenuationLossError":
                        err = "view/DeviceAddDialog.fxml";
                    default:
                        err = null;
                }
            case RED:
                switch (type) {
                    case "DistanceError":
                        err = "view/ConfigurationWindow.fxml";
                    case "InsertionLossError":
                        err = "view/MonitorWindow.fxml";
                    case "ReflexLossError":
                        err = "view/MainScene.fxml";
                    case "AcumulationLossError":
                        err = "view/DeviceAddDialog.fxml";
                    case "AtenuationLossError":
                        err = "view/DeviceAddDialog.fxml";
                    default:
                        err = null;
                }
        }

        return err;
    }
}
