/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;
import java.io.Serializable;
import javafx.beans.property.StringProperty;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Phelipe
 */
@Entity
@Table(name = "device")
@XmlRootElement
public class Device implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long ID;

    @OneToOne
    @JoinColumn(name = "parameters_id")
    private Parameters parameters;

    @OneToOne
    @JoinColumn(name = "limits_id")
    private Limits limits;

    @OneToOne
    @JoinColumn(name = "data_id")
    private Data data;

    @Basic(optional = false)
    @Column(name = "ip")
    private StringProperty ip;
    
    @Basic(optional = false)
    @Column(name = "mask")
    private StringProperty mask;
    
    @Basic(optional = false)
    @Column(name = "gateway")
    private StringProperty gateway;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters parameters) {
        this.parameters = parameters;
    }

    public Limits getLimits() {
        return limits;
    }

    public void setLimits(Limits limits) {
        this.limits = limits;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data dataReceive) {
        this.data = dataReceive;
    }

    
    public String getIp() {
        return ip.get();
    }

    public StringProperty ipProperty() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip.set(Utils.fillAddress(ip));
    }

    public String getMask() {
        return mask.get();
    }

    public StringProperty maskProperty() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask.set(Utils.fillAddress(mask));
    }

   
    public String getGateway() {
        return gateway.get();
    }

    public StringProperty gatewayProperty() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway.set(Utils.fillAddress(gateway));
    }

    public String takeData() {
        return this.ip.get() + this.mask.get() + this.gateway.get();
    }

    public void copy(Device data) {
        setID(data.getID());
        setIp(data.getIp());
        setMask(data.getMask());
        setGateway(data.getGateway());
    }
}
