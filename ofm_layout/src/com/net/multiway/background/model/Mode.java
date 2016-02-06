/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.model;

/**
 *
 * @author Phelipe
 */
public enum Mode {
	/**
     * Represents a screen being opened in order to view operations <br />
     * Intended to Read-only operations
     */
    VIEW,
    /**
     * Represents a screen being opened in order to edit items managed in such
     * screen <br />
     * Intended to Read and Write operations
     */
    EDIT,
    /**
     * Represents a screen being opened in order to create new items managed in
     * such screen <br />
     * Intended to Write operations
     */
    NEW;

    /**
     * VIEW returns "Visualização"<br />
     * EDIT returns "Edição" <br />
     * NEW returns "Criação"
     *
     * @return String representation of the Mode Action
     */
    public String getActionName() {
        switch (this) {
            case VIEW:
                return "Visualização";
            case EDIT:
                return "Edição";
            case NEW:
                return "Criação";
            default:
                throw new AssertionError(this.name());

        }
    }
}
