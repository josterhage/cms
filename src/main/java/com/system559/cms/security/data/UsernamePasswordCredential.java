package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class UsernamePasswordCredential {
    @Id
    private String userId;
    private String username;
    private String password;

    public UsernamePasswordCredential() {

    }

    public UsernamePasswordCredential(String userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }

    public UsernamePasswordCredential(String userId,
                                      UsernamePasswordDto dto) {
        this.userId = userId;
        this.username = dto.getUsername();
        this.password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(dto.getPassword());
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
