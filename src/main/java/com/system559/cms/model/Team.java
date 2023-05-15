package com.system559.cms.model;

import java.util.List;
import java.util.UUID;

public class Team implements TeamMember {
    private String id;
    private String name;
    private long timeCreated;
    private List<TeamMember> teamMembers;
    private String avatar;

    public Team() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeCreated() {
        return timeCreated;
    }

    public List<TeamMember> getTeamMembers() {
        return teamMembers;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static Team createTeam(String name, List<TeamMember> teamMembers, String avatar) {
        Team newTeam = new Team();
        newTeam.id = UUID.randomUUID().toString();
        newTeam.name = name;
        newTeam.timeCreated = System.currentTimeMillis();
        newTeam.teamMembers = teamMembers;
        newTeam.avatar = avatar;
        return newTeam;
    }
}
