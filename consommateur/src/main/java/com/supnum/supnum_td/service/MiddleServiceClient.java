package com.supnum.supnum_td.service;

import com.supnum.supnum_td.model.Server;
import com.supnum.supnum_td.model.StatusResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class MiddleServiceClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public MiddleServiceClient(RestTemplate restTemplate,
                               @Value("${middle.service.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public Server createServer(Server request) {
        String url = baseUrl + "/api/servers";
        return restTemplate.postForObject(url, request, Server.class);
    }

    public List<Server> listServers() {
        String url = baseUrl + "/api/servers";
        Server[] response = restTemplate.getForObject(url, Server[].class);
        return response != null ? Arrays.asList(response) : List.of();
    }

    public StatusResponse getStatus(Long id) {
        String url = baseUrl + "/api/servers/" + id + "/status";
        return restTemplate.getForObject(url, StatusResponse.class);
    }

    public Server startServer(Long id) {
        String url = baseUrl + "/api/servers/" + id + "/start";
        return restTemplate.postForObject(url, null, Server.class);
    }

    public Server stopServer(Long id) {
        String url = baseUrl + "/api/servers/" + id + "/stop";
        return restTemplate.postForObject(url, null, Server.class);
    }

    public Server renameServer(Long id, String newName) {
        String url = baseUrl + "/api/servers/" + id + "/name";
        var body = java.util.Map.of("newName", newName);
        return restTemplate
                .exchange(url, HttpMethod.PUT, new HttpEntity<>(body), Server.class)
                .getBody();
    }

    public void deleteServer(Long id) {
        String url = baseUrl + "/api/servers/" + id;
        restTemplate.exchange(url, HttpMethod.DELETE, null, Void.class);
    }
}