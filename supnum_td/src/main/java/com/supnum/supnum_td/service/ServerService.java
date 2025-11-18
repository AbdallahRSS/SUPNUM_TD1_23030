package com.supnum.supnum_td.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.supnum.supnum_td.exception.ServerNotFoundException;
import com.supnum.supnum_td.exception.ServerRunningException;
import com.supnum.supnum_td.model.Server;
import com.supnum.supnum_td.repository.ServerRepository;

@Service
public class ServerService {

    private final ServerRepository repository;

    public ServerService(ServerRepository repository) {
        this.repository = repository;
    }

    // créer un serveur
    public Server createServer(Server server) {
        server.setId(null);
        server.setRunning(false); // par défaut arrêté
        return repository.save(server);
    }

    // lister tous les serveurs
    public List<Server> listServers() {
        return repository.findAll();
    }

    // renommer un serveur
    public Server renameServer(Long id, String newName) {
        Server server = repository.findById(id).orElseThrow(() -> new ServerNotFoundException(id));
        server.setName(newName);
        return repository.save(server);
    }

    // récupérer le statut
    public boolean getStatus(Long id) {
        Server server = repository.findById(id).orElseThrow(() -> new ServerNotFoundException(id));
        return server.isRunning();
    }

    // démarrer
    public Server startServer(Long id) {
        Server server = repository.findById(id).orElseThrow(() -> new ServerNotFoundException(id));
        server.setRunning(true);
        return repository.save(server);
    }

    // arrêter
    public Server stopServer(Long id) {
        Server server = repository.findById(id).orElseThrow(() -> new ServerNotFoundException(id));
        server.setRunning(false);
        return repository.save(server);
    }

    // supprimer (interdit if it's running)
    public void deleteServer(Long id) {
        Server server = repository.findById(id).orElseThrow(() -> new ServerNotFoundException(id));
        if (server.isRunning()) {
            throw new ServerRunningException(id);
        }
        repository.delete(server);
    }
}
