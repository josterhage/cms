package com.system559.cms.security.data;

import com.system559.cms.security.dto.UsernamePasswordDto;
import org.springframework.data.annotation.Id;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

public class UsernamePasswordCredential {
    @Id
    private String userId;
    private String username;
    private String password;

    public UsernamePasswordCredential() {

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

    public static UsernamePasswordCredential credential(String userId, String username, String password) {
        UsernamePasswordCredential newCredential = new UsernamePasswordCredential();
        newCredential.userId=userId;
        newCredential.username=username;
        newCredential.password=PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(password);
        return newCredential;
    }

    public static UsernamePasswordCredential credential(String userId, UsernamePasswordDto dto) {
        return credential(userId,dto.getUsername(),dto.getPassword());
    }
}
