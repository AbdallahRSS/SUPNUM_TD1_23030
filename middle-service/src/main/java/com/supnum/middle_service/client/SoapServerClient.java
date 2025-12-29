package com.supnum.middle_service.client;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.supnum.supnum_td.soap.CreateServerRequest;
import com.supnum.supnum_td.soap.CreateServerResponse;
import com.supnum.supnum_td.soap.DeleteServerRequest;
import com.supnum.supnum_td.soap.DeleteServerResponse;
import com.supnum.supnum_td.soap.GetServerStatusRequest;
import com.supnum.supnum_td.soap.GetServerStatusResponse;
import com.supnum.supnum_td.soap.ListServersRequest;
import com.supnum.supnum_td.soap.ListServersResponse;
import com.supnum.supnum_td.soap.RenameServerRequest;
import com.supnum.supnum_td.soap.RenameServerResponse;
import com.supnum.supnum_td.soap.Server;
import com.supnum.supnum_td.soap.StartServerRequest;
import com.supnum.supnum_td.soap.StartServerResponse;
import com.supnum.supnum_td.soap.StopServerRequest;
import com.supnum.supnum_td.soap.StopServerResponse;

@Service
public class SoapServerClient {

    private final WebServiceTemplate webServiceTemplate;

    public SoapServerClient(WebServiceTemplate webServiceTemplate) {
        this.webServiceTemplate = webServiceTemplate;
    }

    public Server createServer(String name, String ipAddress, Boolean running) {
        CreateServerRequest request = new CreateServerRequest();
        request.setName(name);
        request.setIpAddress(ipAddress);
        
        if (running != null) {
            
        }

        CreateServerResponse response = (CreateServerResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.getServer();
    }

    public List<Server> listServers() {
        ListServersRequest request = new ListServersRequest();
        ListServersResponse response = (ListServersResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.getServers();
    }

    public boolean getStatus(Long id) {
        GetServerStatusRequest request = new GetServerStatusRequest();
        request.setId(id);
        GetServerStatusResponse response = (GetServerStatusResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.isRunning();
    }

    public Server startServer(Long id) {
        StartServerRequest request = new StartServerRequest();
        request.setId(id);
        StartServerResponse response = (StartServerResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.getServer();
    }

    public Server stopServer(Long id) {
        StopServerRequest request = new StopServerRequest();
        request.setId(id);
        StopServerResponse response = (StopServerResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.getServer();
    }

    public Server renameServer(Long id, String newName) {
        RenameServerRequest request = new RenameServerRequest();
        request.setId(id);
        request.setNewName(newName);
        RenameServerResponse response = (RenameServerResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.getServer();
    }

    public boolean deleteServer(Long id) {
        DeleteServerRequest request = new DeleteServerRequest();
        request.setId(id);
        DeleteServerResponse response = (DeleteServerResponse)
                webServiceTemplate.marshalSendAndReceive(request);
        return response.isSuccess();
    }
}