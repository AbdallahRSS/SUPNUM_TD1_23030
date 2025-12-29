package com.supnum.supnum_td.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "getServerStatusResponse", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class GetServerStatusResponse {

    @XmlElement(name = "running", namespace = "http://supnum.com/servers")
    private boolean running;

    public boolean isRunning() { return running; }
    public void setRunning(boolean running) { this.running = running; }
}
