package com.system559.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

/**
 * Entity representing a system user
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class User implements TeamMember {
    /**
     * Unique identifier of this User
     */
    private String id;
    /**
     * User's first name
     */
    private String firstName;
    /**
     * User's last name
     */
    private String lastName;
    /**
     * User's middle name
     */
    private String middleName;
    /**
     * User's email address
     */
    private String email;
    /**
     * User's birthdate expressed as milliseconds since the Unix Epoch UTC
     */
    //TODO: refactor to use a simple date
    private long birthdate;
    /**
     * Time of account created expressed as milliseconds since the Unix Epoch UTC
     */
    private long accountCreated;
    /**
     * URL to this User's avatar
     */
    private String avatar;
    /**
     * Flag for whether this profile can be viewed by any User or only Team Members
     */
    private boolean isPublicProfile;

    /**
     * Returns an empty User object
     */
    public User() {
    }

    /**
     * Returns this User's unique identifier
     * @return unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets this User's unique identifier
     * @param id unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns this User's first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets this User's first name
     * @param firstName first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns this User's last name
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets this User's last name
     * @param lastName last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Returns this User's middle name
     * @return middle name
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * Sets this User's middle name
     * @param middleName middle name
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * Returns this User's email address
     * @return email address
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets this User's email address
     * @param email email address
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns this User's birthdate
     * @return birthdate
     */
    public long getBirthdate() {
        return birthdate;
    }

    /**
     * Sets this User's birthdate
     * @param birthdate birthdate
     */
    public void setBirthdate(long birthdate) {
        this.birthdate = birthdate;
    }

    /**
     * Returns the time this User account was created in milliseconds since the Unix Epoch UTC
     * @return account creation time
     */
    public long getAccountCreated() {
        return accountCreated;
    }

    /**
     * Sets the time this account was created
     * @param accountCreated time created in milliseconds since the Unix Epoch UTC
     */
    public void setAccountCreated(Long accountCreated) {
        this.accountCreated = accountCreated;
    }

    /**
     * Returns a URL to this User's avatar
     * @return URL to the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the URL to this User's avatar
     * @param avatar URL to the avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * Returns if this profile is publicly accessible
     * @return accessibility
     */
    public boolean isPublicProfile() {
        return isPublicProfile;
    }

    /**
     * Sets whether this profile is publicly accessible
     * @param isPublicProfile public profile flag
     */
    public void setPublicProfile(boolean isPublicProfile) {
        this.isPublicProfile = isPublicProfile;
    }

    /**
     * Because User extends {@link TeamMember} it has to implement {@link TeamMember#getTeamMembers()}.
     * However, Users aren't teams, so this will always return an empty List.
     * <br/>
     * This method is not serialized to JSON for persistence or REST transfer
     * @return empty list
     */
    @JsonIgnore
    public List<TeamMember> getTeamMembers() {
        return List.of();
    }
}
