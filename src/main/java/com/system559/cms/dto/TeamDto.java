package com.system559.cms.dto;

import com.system559.cms.model.TeamMember;

import java.util.List;

public class TeamDto {
    private String name;
    private long teamCreated;
    private List<TeamMember> members;
    private String avatar;

    public TeamDto() {

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

    public void setTeamCreated(long teamCreated) {
        this.teamCreated = teamCreated;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
