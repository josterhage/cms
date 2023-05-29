package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

/**
 * Record of a user's username and password.
 *
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class UsernamePasswordCredential {
    /**
     * Unique identifier of the user.
     */
    @Id
    private String userId;
    private String username;
    private String password;

    /**
     * Returns an empty instance.
     */
    public UsernamePasswordCredential() {
    }

    /**
     * Returns the user's unique identifier.
     *
     * @return unique identifier.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user's unique identifier.
     *
     * @param userId unique identifier.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Returns the username.
     *
     * @return username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username
     * @param username username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the user's password. The security system is required to encode this using appropriate methods.
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the user's password. This should be properly encoded.
     * @param password password.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
