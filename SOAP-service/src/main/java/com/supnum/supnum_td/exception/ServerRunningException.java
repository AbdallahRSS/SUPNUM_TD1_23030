package com.supnum.supnum_td.exception;

public class ServerRunningException extends RuntimeException {
    public ServerRunningException(Long id) {
        super("Impossible de supprimer le serveur " + id + " car il est en cours d'exécution");
    }
}
