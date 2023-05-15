package com.system559.cms.model;

import java.util.List;

public abstract class ManagedObject implements IdentifiableObject {
    protected String id;
    protected List<String> managerIds;

    public String getId() {
        return this.id;
    }

    public List<String> getManagerIds() {
        return managerIds;
    }

    public boolean isManager(User user) {
        return managerIds.contains(user.getId());
    }

    public boolean isManager(String userId) {
        return managerIds.contains(userId);
    }
}
