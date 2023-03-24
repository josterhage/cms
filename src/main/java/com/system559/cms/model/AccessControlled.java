package com.system559.cms.model;

import java.util.EnumSet;
import java.util.Map;

/**
 *
 * An object that requires user-level controlled access.
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
public interface AccessControlled {
    /**
     * Returns the permissions granted to the specified user for this object
     *
     * @param user - the user whose permissions will be returned
     * @return EnumSet of this user's granted permissions or null if this user has no granted permissions
     */
    EnumSet<PermissionType> getPermissions(User user);

    /**
     * Permissions table for all users. This may not include every user with access to the CMS
     *
     * @return Map of this object's permissions table or null if no table has been created
     */
    Map<User, EnumSet<PermissionType>> getAllPermissions();

    /**
     * Sets the permissions on this object for the specified user
     *
     * @param user - the user being granted permissions
     * @param permissions - the specific permissions to be granted
     */
    void setPermissions(User user, EnumSet<PermissionType> permissions);
}
