package com.supnum.supnum_td.exception;

public class ServerNotFoundException extends RuntimeException {
    public ServerNotFoundException(Long id) {
        super("Serveur non trouvé : " + id);
    }
}
