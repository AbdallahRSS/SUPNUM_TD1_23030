package com.supnum.supnum_td.soap;

import java.util.List;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.supnum.supnum_td.model.Server;
import com.supnum.supnum_td.service.ServerService;

@Endpoint
public class ServerEndpoint {

    private static final String NAMESPACE = "http://supnum.com/servers";

    private final ServerService service;

    public ServerEndpoint(ServerService service) {
        this.service = service;
    }

    // CREATE SERVER
    @PayloadRoot(namespace = NAMESPACE, localPart = "createServerRequest")
    @ResponsePayload
    public CreateServerResponse createServer(@RequestPayload CreateServerRequest request) {
        System.out.println("SOAP name = " + request.getName());
        System.out.println("SOAP ip   = " + request.getIpAddress());

        Server server = new Server(
            request.getName(),
            request.getIpAddress(),
            false // always start stopped
        );
        Server saved = service.createServer(server);

        CreateServerResponse response = new CreateServerResponse();
        response.setServer(saved);
        return response;
    }

    // LIST SERVERS
    @PayloadRoot(namespace = NAMESPACE, localPart = "listServersRequest")
    @ResponsePayload
    public ListServersResponse listServers(@RequestPayload ListServersRequest request) {
        List<Server> servers = service.listServers();

        ListServersResponse response = new ListServersResponse();
        response.setServers(servers);
        return response;
    }

    // GET STATUS
    @PayloadRoot(namespace = NAMESPACE, localPart = "getServerStatusRequest")
    @ResponsePayload
    public GetServerStatusResponse getStatus(@RequestPayload GetServerStatusRequest request) {
        Boolean running = service.getStatus(request.getId());

        GetServerStatusResponse response = new GetServerStatusResponse();
        response.setRunning(running);
        return response;
    }

    // START SERVER
    @PayloadRoot(namespace = NAMESPACE, localPart = "startServerRequest")
    @ResponsePayload
    public StartServerResponse startServer(@RequestPayload StartServerRequest request) {
        Server server = service.startServer(request.getId());

        StartServerResponse response = new StartServerResponse();
        response.setServer(server);
        return response;
    }

    // STOP SERVER
    @PayloadRoot(namespace = NAMESPACE, localPart = "stopServerRequest")
    @ResponsePayload
    public StopServerResponse stopServer(@RequestPayload StopServerRequest request) {
        Server server = service.stopServer(request.getId());

        StopServerResponse response = new StopServerResponse();
        response.setServer(server);
        return response;
    }

    // RENAME SERVER
    @PayloadRoot(namespace = NAMESPACE, localPart = "renameServerRequest")
    @ResponsePayload
    public RenameServerResponse renameServer(@RequestPayload RenameServerRequest request) {
        Server server = service.renameServer(
                request.getId(),
                request.getNewName()
        );

        RenameServerResponse response = new RenameServerResponse();
        response.setServer(server);
        return response;
    }

    // DELETE SERVER
    @PayloadRoot(namespace = NAMESPACE, localPart = "deleteServerRequest")
    @ResponsePayload
    public DeleteServerResponse deleteServer(@RequestPayload DeleteServerRequest request) {
        service.deleteServer(request.getId());

        DeleteServerResponse response = new DeleteServerResponse();
        response.setSuccess(true);
        return response;
    }
}
