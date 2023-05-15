package com.system559.cms.dto;

import java.util.List;

import com.system559.cms.model.ManagedObject;

public class TeamSpaceDto {
    private List<String> managerIds;
    private String name;
    private List<ManagedObject> managedObjects;

    public TeamSpaceDto() {

    }

    public List<String> getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(List<String> managerIds) {
        this.managerIds = managerIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManagedObject> getManagedObjects() {
        return managedObjects;
    }

    public void setManagedObjects(List<ManagedObject> managedObjects) {
        this.managedObjects = managedObjects;
    }
}
