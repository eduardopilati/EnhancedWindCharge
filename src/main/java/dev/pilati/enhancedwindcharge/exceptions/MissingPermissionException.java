package dev.pilati.enhancedwindcharge.exceptions;

public class MissingPermissionException extends Exception {

    private final String permission;

    public MissingPermissionException(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return this.permission;
    }
}
