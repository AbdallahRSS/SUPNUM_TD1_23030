package com.supnum.supnum_td.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "servers")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @NotBlank
    private String ipAddress;

    // true = running, false = stopped
    private boolean running;

    public Server() {}

    public Server(String name, String ipAddress, boolean running) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.running = running;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getIpAddress() { return ipAddress; }
    public boolean isRunning() { return running; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }
    public void setRunning(boolean running) { this.running = running; }
}
