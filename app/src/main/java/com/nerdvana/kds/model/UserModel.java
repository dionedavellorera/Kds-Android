package com.nerdvana.kds.model;

public class UserModel {

    private int userId;
    private String username;
    private String name;
    private String role;

    public UserModel(int userId, String username,
                     String name, String role) {
        this.userId = userId;
        this.username = username;
        this.name = name;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }
}
