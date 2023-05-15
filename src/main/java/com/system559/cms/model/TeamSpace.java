package com.system559.cms.model;

import java.util.List;
import java.util.UUID;

public class TeamSpace extends ManagedObject {
    private String name;

    private List<ManagedObject> managedObjects;

    public TeamSpace() {
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

    public static TeamSpace createTeamSpace(List<String> managerIds, String name, List<ManagedObject> managedObjects) {
        TeamSpace newTeamSpace = new TeamSpace();
        newTeamSpace.id = UUID.randomUUID().toString();
        newTeamSpace.managerIds = managerIds;
        newTeamSpace.name = name;
        newTeamSpace.managedObjects = managedObjects;
        return newTeamSpace;
    }
}
