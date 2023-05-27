package com.system559.cms.model;

import org.springframework.data.annotation.Id;

import java.util.List;

/**
 * <p>
 *     Abstraction of a Team
 * </p>
 * <br/>
 * <b>Teams</b> have one or more members, any of which can be another team.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class Team implements TeamMember {
    /**
     * Unique identifier for this team
     */
    @Id
    private String id;

    /**
     * Name of this team
     */
    private String name;

    /**
     * The time this team was created in local milliseconds since the Unix Epoch
     */
    private long timeCreatedInMilliseconds;

    /**
     * This team's leader
     */
    private User teamLeader;

    /**
     * This team's members
     */
    private List<TeamMember> teamMembers;

    /**
     * This teams avatar expressed as a URL to a resource
     */
    private String avatar;

    /**
     * Returns an empty Team object
     */
    public Team() {
    }

    /**
     * Returns this Team's unique identifier
     * @return unique identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets this Team's unique identifier
     * @param id unique identifier
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns this Team's name
     * @return team name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this Team's name
     * @param name team name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the time this Team was created in milliseconds
     * @return creation time in milliseconds since Unix Epoch
     */
    public long getTimeCreatedInMilliseconds() {
        return timeCreatedInMilliseconds;
    }

    /**
     * Sets the time this Team was created in milliseconds
     * @param timeCreated creation time in milliseconds since Unix epoch
     */
    public void setTimeCreates(long timeCreated) {
        this.timeCreatedInMilliseconds = timeCreated;
    }

    /**
     * Returns this Team's leader
     * @return team leader
     */
    public User getTeamLeader() {
        return teamLeader;
    }

    /**
     * Sets this Team's leader
     * @param teamLeader new Team leader
     */
    public void setTeamLeader(User teamLeader) {
        this.teamLeader = teamLeader;
    }

    /**
     * Returns this team's list of members
     * @return list of TeamMembers
     */
    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    /**
     * Sets this team's list of members
     * @param teamMembers list of TeamMembers
     */
    public void setTeamMembers(List<TeamMember> teamMembers) {
        this.teamMembers = teamMembers;
    }

    /**
     * Returns the URL to this Team's avatar
     * @return URL to avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * Sets the URL to this Team's avatar
     * @param avatar URL to avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
