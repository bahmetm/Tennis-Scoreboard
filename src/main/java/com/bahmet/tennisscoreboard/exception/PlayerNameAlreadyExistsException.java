package com.bahmet.tennisscoreboard.exception;

public class PlayerNameAlreadyExistsException extends DatabaseException {
    public PlayerNameAlreadyExistsException(String message) {
        super(message);
    }
}
