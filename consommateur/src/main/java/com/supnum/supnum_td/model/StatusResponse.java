package com.supnum.supnum_td.model;

public class StatusResponse {
    private Long id;
    private boolean running;

    public StatusResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}