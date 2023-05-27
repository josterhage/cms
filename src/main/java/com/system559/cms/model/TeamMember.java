package com.system559.cms.model;

import java.util.List;

/**
 * <p>
 * Common attributes for all TeamMember objects
 * </p>
 * <br/>
 * Team Members can be either users or another subordinate team and can have a graphical object that acts as an avatar.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public interface TeamMember extends IdentifiableObject {
    /**
     * Returns a list of this TeamMember's subordinate team members
     * @return list of TeamMembers
     */
    List<TeamMember> getTeamMembers();

    /**
     * Returns a link to this Team Member's avatar
     * @return a link to this Team Member's avatar in a String
     */
    String getAvatar();
}
