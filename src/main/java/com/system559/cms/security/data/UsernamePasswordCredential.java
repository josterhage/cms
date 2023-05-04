package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

public class UsernamePasswordCredential {
    @Id
    private final String userId;
    private String username;
    private String password;

    public UsernamePasswordCredential(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public String getUserId() {
        return userId;
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
