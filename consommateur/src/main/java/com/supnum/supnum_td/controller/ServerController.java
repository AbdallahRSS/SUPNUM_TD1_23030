package com.supnum.supnum_td.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.supnum.supnum_td.model.Server;
import com.supnum.supnum_td.service.ServerService;


@RestController
@RequestMapping("/api/servers")
public class ServerController {

    private final ServerService service;

    public ServerController(ServerService service) {
        this.service = service;
    }

    // créer
    @PostMapping
    public ResponseEntity<Server> create(@RequestBody Server server) {
        Server created = service.createServer(server);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    // lister tous
    @GetMapping
    public List<Server> list() {
        return service.listServers();
    }

    // renommer
    @PutMapping("/{id}/rename")
    public Server rename(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String newName = body.get("name");
        return service.renameServer(id, newName);
    }

    // statut
    @GetMapping("/{id}/status")
    public Map<String, Boolean> status(@PathVariable Long id) {
        boolean running = service.getStatus(id);
        return Map.of("running", running);
    }

    // démarrer
    @PutMapping("/{id}/start")
    public Server start(@PathVariable Long id) {
        return service.startServer(id);
    }

    // arrêter
    @PutMapping("/{id}/stop")
    public Server stop(@PathVariable Long id) {
        return service.stopServer(id);
    }

    // supprimer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteServer(id);
        return ResponseEntity.noContent().build();
    }
}