package com.tv.models;

import java.util.List;

public class ApplicationUser {
    private long id;
    private String username;
    private String password;
    private List<ApplicationUserRole> roles;

    public List<ApplicationUserRole> getRoles() {
        return roles;
    }

    public void setRoles(List<ApplicationUserRole> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
