package com.tv.models;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class ApplicationUserRole {
    private int userId;

    @Enumerated(EnumType.STRING)
    private ApplicationRole role;

    public ApplicationUserRole(int userId, ApplicationRole role) {
        this.userId = userId;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public ApplicationRole getRole() {
        return role;
    }

    public void setRole(ApplicationRole role) {
        this.role = role;
    }
}
