package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

import java.util.EnumSet;
import java.util.Map;

public class AccessControlList {
    @Id
    private String objectId;
    private Map<String, EnumSet<Permission>> grantedPermissions;

    public AccessControlList() {

    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setGrantedPermissions(Map<String, EnumSet<Permission>> grantedPermissions) {
        this.grantedPermissions = grantedPermissions;
    }

    public Map<String, EnumSet<Permission>> getGrantedPermissions() {
        return grantedPermissions;
    }
}
