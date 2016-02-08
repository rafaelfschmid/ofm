package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "DATA0X1002")
@XmlRootElement

public class DataDevice implements Data, Serializable {

    private StringProperty ip;
    private StringProperty mask;
    private StringProperty gateway;

    private Long ID;

    public DataDevice() {
        this.ip = new SimpleStringProperty();
        this.mask = new SimpleStringProperty();
        this.gateway = new SimpleStringProperty();
    }

    public DataDevice(Long id, String ip, String mask, String gateway) {
        this.ID = id;
        this.ip = new SimpleStringProperty(Utils.fillAddress(ip));
        this.mask = new SimpleStringProperty(Utils.fillAddress(mask));
        this.gateway = new SimpleStringProperty(Utils.fillAddress(gateway));
    }

    public String takeData() {

        return this.ip.get() + this.mask.get() + this.gateway.get();
    }

    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    @Basic(optional = false)
    @Column(name = "IP")
    public String getIp() {
        return ip.get();
    }

    public StringProperty ipProperty() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip.set(Utils.fillAddress(ip));
    }

    @Basic(optional = false)
    @Column(name = "MASK")
    public String getMask() {
        return mask.get();
    }

    public StringProperty maskProperty() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask.set(Utils.fillAddress(mask));
    }

    @Basic(optional = false)
    @Column(name = "GATEWAY")
    public String getGateway() {
        return gateway.get();
    }

    public StringProperty gatewayProperty() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway.set(Utils.fillAddress(gateway));
    }

    public void copy(DataDevice data) {
        setID(data.getID());
        setIp(data.getIp());
        setMask(data.getMask());
        setGateway(data.getGateway());
    }

}
