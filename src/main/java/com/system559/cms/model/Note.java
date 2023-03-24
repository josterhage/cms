package com.system559.cms.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.EnumSet;
import java.util.Map;

/**
 * A Viewable Rich-Text object
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
@Data
public class Note implements Viewable{
    /**
     * Unique identifier for this note
     */
    private String viewId;
    /**
     * Permissions table for this note
     */
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Map<User,EnumSet<PermissionType>> permissions;
    /**
     * Fully qualified name of this note
     */
    private String name;
    /**
     * Rich-text of this note
     */
    private String text;
    /**
     * User who created this note. Creators have special permissions over notes.
     */
    private User creator;

    @Override
    public EnumSet<PermissionType> getPermissions(User user) {
        return null;
    }

    @Override
    public Map<User, EnumSet<PermissionType>> getAllPermissions() {
        return null;
    }
    @Override
    public void setPermissions(User user, EnumSet<PermissionType> permissions) {
        this.permissions.put(user,permissions);
    }
}
