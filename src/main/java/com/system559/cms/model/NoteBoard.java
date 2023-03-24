package com.system559.cms.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;

/**
 * A collaboration space that contains multiple Viewable children
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
@Data
public class NoteBoard implements WorkSpace {
    /**
     * Unique identifier for this board
     */
    private String viewId;
    /**
     * Fully qualified name of this board
     */
    private String name;
    /**
     * Creator of this board. Creators have special permissions over NoteBoards
     */
    private User creator;
    /**
     * Collection of this NoteBoard's viewable children
     */
    private List<Viewable> viewables;
    /**
     * Permissions table for this board
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<User,EnumSet<PermissionType>> permissions;

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
