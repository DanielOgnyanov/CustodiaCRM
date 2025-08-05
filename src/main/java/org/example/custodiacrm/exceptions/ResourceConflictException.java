package org.example.custodiacrm.exceptions;

public class ResourceConflictException extends ServiceException{
    public ResourceConflictException(String message) {
        super(message, 409);
    }
}
