/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.view;

import com.net.multiway.background.data.DataDevice;
import com.net.multiway.background.data.DataLimits;
import com.net.multiway.background.data.dao.DataLimitsDAO;
import com.net.multiway.background.model.IController;
import com.net.multiway.background.model.Mode;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author phelipe
 */
public class RangeDialogController implements Initializable, IController {

    private Stage dialogStage;
    @FXML
    private TextField insertionGreenField;
    @FXML
    private TextField reflectionGreenField;
    @FXML
    private TextField distanceGreenField;
    @FXML
    private TextField distanceYellowField;
    @FXML
    private TextField insertionYellowField;
    @FXML
    private TextField reflectionYellowField;
    @FXML
    private TextField distanceRedField;
    @FXML
    private TextField insertionRedField;
    @FXML
    private TextField reflectionRedField;
    @FXML
    private TextField attenuationGreenField;
    @FXML
    private TextField attenuationYellowField;
    @FXML
    private TextField attenuationRedField;
    @FXML
    private TextField acumulationRedField;
    @FXML
    private TextField acumulationYellowField;
    @FXML
    private TextField acumulationGreenField;

    private DataLimits limits;

    public DataLimits getLimits() {
        return limits;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        DataLimitsDAO dao = new DataLimitsDAO();
        limits = dao.find(Long.parseLong("1"));
        if (limits == null) {
            limits = new DataLimits(3, 3, 3, 5, 5, 5, 3, 5, 5, 3);
        }

        distanceGreenField.setText(limits.getDistanceGreen().toString());
        distanceYellowField.setText(limits.getDistanceYellow().toString());
        distanceRedField.setText(limits.getDistanceYellow().toString());

        insertionGreenField.setText(limits.getInsertionGreen().toString());
        insertionYellowField.setText(limits.getInsertionYellow().toString());
        insertionRedField.setText(limits.getInsertionYellow().toString());

        reflectionGreenField.setText(limits.getReflectionGreen().toString());
        reflectionYellowField.setText(limits.getReflectionYellow().toString());
        reflectionRedField.setText(limits.getReflectionYellow().toString());

        acumulationGreenField.setText(limits.getAcumulationGreen().toString());
        acumulationYellowField.setText(limits.getAcumulationYellow().toString());
        acumulationRedField.setText(limits.getAcumulationYellow().toString());

        attenuationGreenField.setText(limits.getAttenuationGreen().toString());
        attenuationYellowField.setText(limits.getAttenuationYellow().toString());
        attenuationRedField.setText(limits.getAttenuationYellow().toString());

        prepareForm(Mode.VIEW);

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
    public void setRange() {
    }

    @FXML
    private void onHandleEdit(ActionEvent event) {
        prepareForm(Mode.EDIT);
    }

    @FXML
    private void onHandleSave(ActionEvent event) throws Exception {
        limits.setAcumulationGreen(Float.parseFloat(acumulationGreenField.getText()));
        limits.setAcumulationYellow(Float.parseFloat(acumulationYellowField.getText()));

        limits.setAttenuationGreen(Float.parseFloat(attenuationGreenField.getText()));
        limits.setAttenuationYellow(Float.parseFloat(attenuationYellowField.getText()));

        limits.setDistanceGreen(Float.parseFloat(distanceGreenField.getText()));
        limits.setDistanceYellow(Float.parseFloat(distanceYellowField.getText()));

        limits.setInsertionGreen(Float.parseFloat(insertionGreenField.getText()));
        limits.setInsertionYellow(Float.parseFloat(insertionYellowField.getText()));

        limits.setReflectionGreen(Float.parseFloat(reflectionGreenField.getText()));
        limits.setReflectionYellow(Float.parseFloat(reflectionYellowField.getText()));

        DataLimitsDAO dao = new DataLimitsDAO();
        if (limits.getID() != null) {
            dao.edit(limits);
        } else {
            dao.create(limits);
        }
        prepareForm(Mode.VIEW);
    }

    @Override
    public void handleSave(ActionEvent event) {

    }

    @Override
    public void prepareForm(Mode mode) {
        switch (mode) {
            case VIEW:
                distanceGreenField.setDisable(true);
                distanceYellowField.setDisable(true);
                distanceRedField.setDisable(true);

                insertionGreenField.setDisable(true);
                insertionYellowField.setDisable(true);
                insertionRedField.setDisable(true);

                reflectionGreenField.setDisable(true);
                reflectionYellowField.setDisable(true);
                reflectionRedField.setDisable(true);

                acumulationGreenField.setDisable(true);
                acumulationYellowField.setDisable(true);
                acumulationRedField.setDisable(true);

                attenuationGreenField.setDisable(true);
                attenuationYellowField.setDisable(true);
                attenuationRedField.setDisable(true);
                break;
            case EDIT:
                distanceGreenField.setDisable(false);
                distanceYellowField.setDisable(false);

                insertionGreenField.setDisable(false);
                insertionYellowField.setDisable(false);

                reflectionGreenField.setDisable(false);
                reflectionYellowField.setDisable(false);

                acumulationGreenField.setDisable(false);
                acumulationYellowField.setDisable(false);

                attenuationGreenField.setDisable(false);
                attenuationYellowField.setDisable(false);
                break;
        }
    }

    @Override
    public void prepareMenu(Mode mode) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
