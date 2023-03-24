package com.system559.cms.model;

import java.util.List;

/**
 *
 * Represents users (individually or collectively) with permissions to
 * use the CMS.
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
public interface TeamMember {
    /**
     * Returns the unique identifier of this team member
     * @return a String of the team member's unique identifier
     */
    String getTeamMemberId();

    /**
     * Returns a {@link java.util.List} of TeamMembers in this object<br/>
     * Some TeamMembers are expected to be singleton instances and some collection instances<br/>
     * @return {@link java.util.List} containing TeamMembers
     */
    List<TeamMember> getMembers();

    /**
     * Returns this TeamMember's fully qualified name
     * @return a String of the TeamMember's name
     */
    String getName();
}
