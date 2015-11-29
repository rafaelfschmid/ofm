package com.net.multiway.background.data;

import com.net.multiway.background.utils.Utils;

import java.io.Serializable;
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
@Table(name = "DATA0X1000")
@XmlRootElement

public class Data0x1002 implements Data, Serializable {

    private String ip;
    private String mask;
    private String gateway;

    private Long ID;

    public Data0x1002() {
    }

    public Data0x1002(String ip, String mask, String gateway) {
        this.ip = Utils.fillAddress(ip);
        this.mask = Utils.fillAddress(mask);
        this.gateway = Utils.fillAddress(gateway);
    }

    public String getData() {

        return this.ip + this.mask + this.gateway;
    }

    @Basic(optional = false)
    @Column(name = "IP")
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Basic(optional = false)
    @Column(name = "MASK")
    public String getMask() {
        return mask;
    }

    public void setMask(String mask) {
        this.mask = mask;
    }

    @Basic(optional = false)
    @Column(name = "GATEWAY")
    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
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
}
