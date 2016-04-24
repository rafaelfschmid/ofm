/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phelipe
 */
@Entity
@Table(name = "DATAOCCURRENCE")
@XmlRootElement
public class DataOccurrence {

    private StringProperty occurrence;
    private StringProperty description;
    private StringProperty date;

    private Long ID;

    public DataOccurrence() {
        this.occurrence = new SimpleStringProperty();
        this.description = new SimpleStringProperty();
        this.date = new SimpleStringProperty();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Basic(optional = false)
    @Column(name = "OCCURRENCE")
    public String getOccurrence() {
        return occurrence.get();
    }

    public void setOccurrence(String occurrence) {
        this.occurrence.set(occurrence);
    }

    public StringProperty occurrenceProperty() {
        return occurrence;
    }

    @Basic(optional = false)
    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description.get();
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public StringProperty descriptionProperty() {
        return description;
    }

    @Basic(optional = false)
    @Column(name = "DATE")
    public String getDate() {
        return date.get();
    }

    public void setDate(String Date) {
        this.date.set(Date);
    }

    public StringProperty dateProperty() {
        return date;
    }

}
