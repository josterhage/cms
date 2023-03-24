package com.system559.cms.model;

import lombok.Data;

import java.util.List;

/**
 *
 * A set of users with permission to use the CMS
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
@Data
public class Team implements TeamMember{
    /**
     * A unique identifier
     */
    private String teamMemberId;
    /**
     * The User who manages this team
     */
    private User teamLeader;
    /**
     * This team's name.
     */
    private String name;
    /**
     * The members of this team.
     */
    private List<TeamMember> members;

    @Override
    public String getTeamMemberId() {
        return teamMemberId;
    }

    @Override
    public List<TeamMember> getMembers() {
        return members;
    }

    @Override
    public String getName() {
        return name;
    }
}
