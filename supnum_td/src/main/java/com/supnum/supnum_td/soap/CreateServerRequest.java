package com.supnum.supnum_td.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "createServerRequest", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"name", "ipAddress"})
public class CreateServerRequest {

    @XmlElement(name = "name", namespace = "http://supnum.com/servers", required = true)
    private String name;

    @XmlElement(name = "ipAddress", namespace = "http://supnum.com/servers", required = true)
    private String ipAddress;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getIpAddress() { return ipAddress; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
}

