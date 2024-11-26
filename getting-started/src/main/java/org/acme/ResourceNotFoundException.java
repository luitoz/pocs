package org.acme;

public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;

    public ResourceNotFoundException(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }
}

 class InvalidInputException extends RuntimeException {}

 class AccessDeniedException extends RuntimeException {}

