package com.supnum.supnum_td.model;

/**
 * DTO utilisé par le consommateur pour échanger avec middle-service.
 * Plus de JPA ici (pas de @Entity, @Table, etc.).
 */
public class Server {

    private Long id;
    private String name;
    private String ipAddress;
    // true = running, false = stopped
    private boolean running;

    public Server() {}

    public Server(String name, String ipAddress, boolean running) {
        this.name = name;
        this.ipAddress = ipAddress;
        this.running = running;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getIpAddress() { 
        return ipAddress; 
    }

    public void setIpAddress(String ipAddress) { 
        this.ipAddress = ipAddress; 
    }

    public boolean isRunning() { 
        return running; 
    }

    public void setRunning(boolean running) { 
        this.running = running; 
    }
}