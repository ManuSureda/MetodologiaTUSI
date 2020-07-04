package edu.utn.Exceptions;

public class UserNotExistsException extends Throwable {


    public UserNotExistsException() {
    }

    public UserNotExistsException(String message) {
        super(message);
    }
}
