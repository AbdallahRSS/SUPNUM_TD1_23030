package com.supnum.supnum_td.soap;


import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "createServerResponse", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class CreateServerResponse {

    @XmlElement(name = "server", namespace = "http://supnum.com/servers")
    private Server server;

    public Server getServer() { return server; }
    public void setServer(Server server) { this.server = server; }
}
