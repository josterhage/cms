package com.system559.cms.model;

import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;

public class User implements TeamMember {
    private final String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private long birthdate;
    private final long accountCreated;
    private String avatar;
    private boolean isPublicProfile;

    public User() {
        id = UUID.randomUUID().toString();
        accountCreated = System.currentTimeMillis();
    }

    public User(String id, String firstName, String lastName, String middleName, String email, long birthdate, long accountCreated, String avatar, boolean isPublicProfile) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.email = email;
        this.birthdate = birthdate;
        this.accountCreated = accountCreated;
        this.avatar = avatar;
        this.isPublicProfile = isPublicProfile;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    public long getAccountCreated() {
        return accountCreated;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isPublicProfile() {
        return isPublicProfile;
    }

    public void setPublicProfile(boolean isPublicProfile) {
        this.isPublicProfile = isPublicProfile;
    }

    public List<TeamMember> getMembers() {
        return Collections.singletonList(this);
    }
}
