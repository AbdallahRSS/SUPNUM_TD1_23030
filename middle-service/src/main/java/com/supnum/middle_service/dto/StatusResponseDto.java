package com.supnum.middle_service.dto;

public class StatusResponseDto {

    private Long id;
    private boolean running;

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