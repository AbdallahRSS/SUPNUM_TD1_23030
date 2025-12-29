package com.supnum.middle_service.dto;

import com.supnum.supnum_td.soap.Server;

public class ServerMapper {

    public static ServerDto toDto(Server server) {
        ServerDto dto = new ServerDto();
        dto.setId(server.getId());
        dto.setName(server.getName());
        dto.setIpAddress(server.getIpAddress());
        dto.setRunning(server.isRunning());
        return dto;
    }
}