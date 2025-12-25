package com.photosharing.exceptions;

public class NotFoundException extends PhotoSharingException {
    public NotFoundException(String entityType, String entityId) {
        super(entityType + " not found with ID: " + entityId);
    }
    
    public NotFoundException(String message) {
        super(message);
    }
}

