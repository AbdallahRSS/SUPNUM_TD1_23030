package com.supnum.supnum_td.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.supnum.supnum_td.model.Server;
import com.supnum.supnum_td.model.StatusResponse;

@Service
public class ServerService {

    private final MiddleServiceClient middleClient;

    public ServerService(MiddleServiceClient middleClient) {
        this.middleClient = middleClient;
    }

    // créer un serveur (via middle-service)
    public Server createServer(Server server) {
        return middleClient.createServer(server);
    }

    // lister tous les serveurs
    public List<Server> listServers() {
        return middleClient.listServers();
    }

    // renommer un serveur
    public Server renameServer(Long id, String newName) {
        return middleClient.renameServer(id, newName);
    }

    // récupérer le statut
    public boolean getStatus(Long id) {
        StatusResponse status = middleClient.getStatus(id);
        return status != null && status.isRunning();
    }

    // démarrer
    public Server startServer(Long id) {
        return middleClient.startServer(id);
    }

    // arrêter
    public Server stopServer(Long id) {
        return middleClient.stopServer(id);
    }

    // supprimer (la logique "interdit si running" peut être gérée côté middle-service ou SOAP)
    public void deleteServer(Long id) {
        middleClient.deleteServer(id);
    }
}