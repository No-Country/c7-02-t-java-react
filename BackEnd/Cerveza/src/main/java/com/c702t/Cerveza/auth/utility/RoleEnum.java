package com.c702t.Cerveza.auth.utility;

public enum RoleEnum {
    USER, ADMIN, BUSINESS;

    private static final String PREFIX = "ROLE_";
    public String getFullRoleName() {
        return PREFIX + name();
    }
    public String getSimpleRoleName() {
        return name();
    }
}