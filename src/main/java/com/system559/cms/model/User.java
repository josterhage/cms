package com.system559.cms.model;

import lombok.Data;

import java.util.List;

/**
 *
 * A single person with permissions to use the CMS
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
@Data
public class User implements TeamMember{
    /**
     * A unique identifier
     */
    private String teamMemberId;
    /**
     * The user's first name
     */
    private String firstName;
    /**
     * The user's last name
     */
    private String lastName;
    /**
     * The user's middle name
     */
    private String middleName;
    /**
     * The user's email address
     */
    private String email;

    @Override
    public String getTeamMemberId() {
        return teamMemberId;
    }

    @Override
    public List<TeamMember> getMembers() {
        return List.of(this);
    }

    @Override
    public String getName() {
        return String.format("%s %s %s",firstName,middleName,lastName);
    }
}
