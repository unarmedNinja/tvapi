package com.tv.models;

public enum ApplicationRole {
    ADMIN, APP1_MEMBER, APP1_CONTRIB;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
