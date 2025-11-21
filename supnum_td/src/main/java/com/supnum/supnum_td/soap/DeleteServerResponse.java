package com.supnum.supnum_td.soap;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "deleteServerResponse", namespace = "http://supnum.com/servers")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeleteServerResponse {

    @XmlElement(name = "success", namespace = "http://supnum.com/servers")
    private boolean success;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
}
