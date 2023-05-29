package com.system559.cms.security.data;

/**
 * Descriptive set of permissions a user can be granted on a resource.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public enum Permission {
    /**
     * User can read values from the resource and see any sub-resources.
     */
    READ,
    /**
     * User can write to the resource.
     */
    WRITE,
    /**
     * User can create new sub-resources. Only valid for resources than can contain other resources.
     */
    CREATE,
    /**
     * User can destroy this resource or any sub-resources.
     */
    DESTROY,
    /**
     * User has administrative privileges on this and all sub-resources. Admin privileges include modifying permissions for
     * other users.
     */
    ADMIN
}
