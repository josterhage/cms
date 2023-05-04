package com.system559.cms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Team implements TeamMember {
    private final String id;
    private String name;
    private final long teamCreated;
    private final List<TeamMember> members;
    private String avatar;

    public Team() {
        this.id = UUID.randomUUID().toString();
        this.teamCreated = System.currentTimeMillis();
        this.members = new ArrayList<>();
    }

    public Team(String id, String name, long teamCreated, List<TeamMember> members, String avatar) {
        this.id = id;
        this.name = name;
        this.teamCreated = teamCreated;
        this.members = members;
        this.avatar = avatar;
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

    public long getTeamCreated() {
        return teamCreated;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
