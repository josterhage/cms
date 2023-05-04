package com.system559.cms.security.data;

import org.springframework.data.annotation.Id;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class AccessControlEntry {
    @Id
    private final String userId;
    private final Map<String, EnumSet<Permission>> grantedPermissions;

    public AccessControlEntry(String userId) {
        this.userId = userId;
        this.grantedPermissions = new HashMap<>();
    }

    public AccessControlEntry(String userId, Map<String, EnumSet<Permission>> grantedPermissions) {
        this.userId = userId;
        this.grantedPermissions = grantedPermissions;
    }

    public String getUserId() {
        return userId;
    }

    public Map<String, EnumSet<Permission>> getGrantedPermissions() {
        return grantedPermissions;
    }
}
