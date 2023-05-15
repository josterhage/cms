package com.system559.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system559.cms.dto.UserDto;
import java.util.List;
import java.util.UUID;

public class User implements TeamMember {
    private String id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private long birthdate;
    private long accountCreated;
    private String avatar;
    private boolean isPublicProfile;

    public User() {
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

    @JsonIgnore
    public List<TeamMember> getTeamMembers() {
        return List.of();
    }

    public static User create(String firstName, String lastName, String middleName, String email, long birthdate, String avatar, boolean isPublicProfile) {
        User newUser = new User();

        newUser.id=UUID.randomUUID().toString();
        newUser.firstName=firstName;
        newUser.lastName=lastName;
        newUser.middleName=middleName;
        newUser.email=email;
        newUser.birthdate=birthdate;
        newUser.accountCreated=System.currentTimeMillis();
        newUser.avatar=avatar;
        newUser.isPublicProfile=isPublicProfile;

        return newUser;
    }

    public static User create(UserDto dto) {
        return User.create(dto.getFirstName(),dto.getLastName(),dto.getMiddleName(),dto.getEmail(),dto.getBirthdate(),dto.getAvatar(),dto.isPublicProfile());
    }
}
