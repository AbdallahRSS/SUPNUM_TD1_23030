package com.supnum.middle_service.controller;

import com.supnum.middle_service.client.SoapServerClient;
import com.supnum.middle_service.dto.*;
import com.supnum.supnum_td.soap.Server;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/servers")
public class ServerRestController {

    private final SoapServerClient soapClient;

    public ServerRestController(SoapServerClient soapClient) {
        this.soapClient = soapClient;
    }

    // CREATE SERVER
    @PostMapping
    public ResponseEntity<ServerDto> create(@RequestBody ServerDto request) {
        Server server = soapClient.createServer(
                request.getName(),
                request.getIpAddress(),
                request.isRunning()
        );
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ServerMapper.toDto(server));
    }

    // LIST ALL SERVERS
    @GetMapping
    public List<ServerDto> list() {
        List<Server> servers = soapClient.listServers();
        return servers.stream()
                .map(ServerMapper::toDto)
                .collect(Collectors.toList());
    }

    // GET STATUS
    @GetMapping("/{id}/status")
    public StatusResponseDto status(@PathVariable Long id) {
        boolean running = soapClient.getStatus(id);
        StatusResponseDto dto = new StatusResponseDto();
        dto.setId(id);
        dto.setRunning(running);
        return dto;
    }

    // START SERVER
    @PostMapping("/{id}/start")
    public ServerDto start(@PathVariable Long id) {
        Server server = soapClient.startServer(id);
        return ServerMapper.toDto(server);
    }

    // STOP SERVER
    @PostMapping("/{id}/stop")
    public ServerDto stop(@PathVariable Long id) {
        Server server = soapClient.stopServer(id);
        return ServerMapper.toDto(server);
    }

    // RENAME SERVER
    @PutMapping("/{id}/name")
    public ServerDto rename(@PathVariable Long id,
                            @RequestBody RenameRequestDto renameRequest) {
        Server server = soapClient.renameServer(id, renameRequest.getNewName());
        return ServerMapper.toDto(server);
    }

    // DELETE SERVER
    @DeleteMapping("/{id}")
    public DeleteResponseDto delete(@PathVariable Long id) {
        boolean success = soapClient.deleteServer(id);
        DeleteResponseDto dto = new DeleteResponseDto();
        dto.setSuccess(success);
        return dto;
    }
}