package com.supnum.supnum_td.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "renameServerRequest", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class RenameServerRequest {

    @XmlElement(name = "id", namespace = "http://supnum.com/servers", required = true)
    private Long id;

    @XmlElement(name = "newName", namespace = "http://supnum.com/servers", required = true)
    private String newName;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNewName() { return newName; }
    public void setNewName(String newName) { this.newName = newName; }
}
