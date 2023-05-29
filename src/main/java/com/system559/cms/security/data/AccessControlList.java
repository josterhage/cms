package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

import java.util.EnumSet;
import java.util.Map;

/**
 * A list of permissions granted to specified users for a given resource.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class AccessControlList {
    /**
     * The unique identifier of the specified resource.
     */
    @Id
    private String objectId;
    /**
     * Map of the permissions each specified user has.
     */
    private Map<String, EnumSet<Permission>> grantedPermissions;

    /**
     * Returns an empty AccessControlList instance.
     */
    public AccessControlList() {
    }

    /**
     * Sets the unique identifier of the specified resource.
     * @param objectId unique object identifier.
     */
    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    /**
     * Returns the unique identifier of the specified resource.
     * @return unique object identifier.
     */
    public String getObjectId() {
        return objectId;
    }

    /**
     * Sets the grantedPermissions instance value.
     * @param grantedPermissions Map of granted permissions.
     */
    public void setGrantedPermissions(Map<String, EnumSet<Permission>> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }

    /**
     * Returns the map of granted permissions.
     * @return Map of granted permissions.
     */
    public Map<String, EnumSet<Permission>> getGrantedPermissions() {
        return grantedPermissions;
    }
}
