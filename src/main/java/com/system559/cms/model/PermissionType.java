package com.system559.cms.model;

/**
 *
 * The permissions a user can have on an {@link com.system559.cms.model.AccessControlled} object.
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
public enum PermissionType {
    /**
     * User can create child objects
     */
    CREATE,
    /**
     * User can read the contents of this object or its children unless those children have modified permissions
     */
    READ,
    /**
     * User can modify the contents of this object or its children unless those children have modified permissions
     */
    WRITE,
    /**
     * User can delete this object or its children unless those children have modified permissions
     */
    DELETE
}
