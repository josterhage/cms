package com.system559.cms.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class ManagedObject implements IdentifiableObject {
    protected final String id;
    protected final List<User> managers;

    public ManagedObject() {
        this.id = UUID.randomUUID().toString();
        this.managers = new ArrayList<>();
    }

    public ManagedObject(String id, List<User> managers) {
        this.id = id;
        this.managers = managers;
    }

    public String getId() {
        return this.id;
    }

    public List<User> getManagers() {
        return managers;
    }

    public boolean isManager(User user) {
        return managers.contains(user);
    }
}
