package com.system559.cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team implements TeamMember{
    private User teamLeader;
    private String teamName;
    private List<TeamMember> members;

    @Override
    public List<TeamMember> getMembers() {
        return members;
    }

    @Override
    public String getName() {
        return teamName;
    }

    @Override
    public String getType() {
        return "team";
    }
}
