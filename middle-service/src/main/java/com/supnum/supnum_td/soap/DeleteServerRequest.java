package com.supnum.supnum_td.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteServerRequest", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteServerRequest {

    @XmlElement(name = "id", namespace = "http://supnum.com/servers", required = true)
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
}
