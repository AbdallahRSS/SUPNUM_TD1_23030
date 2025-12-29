package com.supnum.supnum_td.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

/**
 * نسخة SOAP من Server (مطابقة للـ XSD).
 * لا تحتوي على أي أنوتيشن JPA أو منطق قاعدة بيانات.
 */
@XmlRootElement(name = "server", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "name", "ipAddress", "running"})
public class Server {

    @XmlElement(name = "id", namespace = "http://supnum.com/servers")
    private Long id;

    @XmlElement(name = "name", namespace = "http://supnum.com/servers", required = true)
    private String name;

    @XmlElement(name = "ipAddress", namespace = "http://supnum.com/servers", required = true)
    private String ipAddress;

    @XmlElement(name = "running", namespace = "http://supnum.com/servers")
    private boolean running;

    public Server() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}