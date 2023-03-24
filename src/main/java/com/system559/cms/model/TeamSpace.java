package com.system559.cms.model;

import lombok.*;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * A segregated area that can contain multiple collaboration tools
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
@Data
public class TeamSpace implements AccessControlled {
    /**
     * Unique identifier for this space
     */
    private String spaceId;
    /**
     * Fully qualified name of the TeamSpace
     */
    private String spaceName;
    /**
     * Collection of TeamMembers with access to this space
     */
    private List<TeamMember> members;
    /**
     * The creator of this space. Creators have special permissions over TeamSpaces
     */
    private User creator;
    /**
     * Collection of collaboration spaces contained in this TeamSpace
     */
    private List<WorkSpace> workSpaces;
    /**
     * Permissions table for this TeamSpace
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<User, EnumSet<PermissionType>> permissions;

    @Override
    public EnumSet<PermissionType> getPermissions(User user) {
        return permissions.get(user);
    }

    @Override
    public Map<User, EnumSet<PermissionType>> getAllPermissions() {
        return permissions;
    }

    @Override
    public void setPermissions(User user, EnumSet<PermissionType> permissions) {
        this.permissions.put(user,permissions);
    }
}
