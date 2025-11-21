package com.supnum.supnum_td.soap;

import com.supnum.supnum_td.model.Server;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "startServerResponse", namespace = "http://supnum.com/servers")
public class StartServerResponse {

    private Server server;

    public Server getServer() { return server; }
    public void setServer(Server server) { this.server = server; }
}
