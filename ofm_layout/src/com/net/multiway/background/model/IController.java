/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

import com.net.multiway.background.model.Mode;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

/**
 *
 * @author Phelipe
 */
public interface IController {

    /**
     * Handles the save action properly, according to each view
     *
     * @param event
     */
    public void handleSave(ActionEvent event);

    /**
     * Prepares the form fields according to the mode it is called
     *
     * @param mode VIEW, NEW or EDIT
     */
    public void prepareForm(Mode mode);

    /**
     * Prepares the menu items according to the mode it is called
     *
     * @param mode VIEW, NEW or EDIT
     */
    public void prepareMenu(Mode mode);

}
