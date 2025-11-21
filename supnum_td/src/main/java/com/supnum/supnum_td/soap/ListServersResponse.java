package com.supnum.supnum_td.soap;

import java.util.List;

import com.supnum.supnum_td.model.Server;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "listServersResponse", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListServersResponse {

    @XmlElement(name = "servers", namespace = "http://supnum.com/servers")
    private List<Server> servers;

    public List<Server> getServers() { return servers; }
    public void setServers(List<Server> servers) { this.servers = servers; }
}
