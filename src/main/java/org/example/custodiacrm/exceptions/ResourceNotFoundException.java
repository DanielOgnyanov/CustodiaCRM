package org.example.custodiacrm.exceptions;

public class ResourceNotFoundException extends ServiceException{
    public ResourceNotFoundException(String message) {
        super(message, 404);
    }
}
