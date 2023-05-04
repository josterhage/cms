package com.system559.cms.model;

import java.util.ArrayList;
import java.util.List;

public class TeamSpace extends ManagedObject {
    private String name;

    private final List<ManagedObject> objects;

    public TeamSpace() {
        super();
        this.objects = new ArrayList<>();
    }

    public TeamSpace(String id, List<User> managers, String name, List<ManagedObject> objects) {
        super(id,managers);
        this.name = name;
        this.objects = objects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManagedObject> getObjects() {
        return objects;
    }
}
