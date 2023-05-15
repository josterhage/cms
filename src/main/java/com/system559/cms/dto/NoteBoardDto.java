package com.system559.cms.dto;

import java.util.List;

public class NoteBoardDto {
    private List<String> managerIds;
    private String name;
    private List<String> managedObjectIds;

    public NoteBoardDto() {

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

    public List<String> getManagedObjectIds() {
        return managedObjectIds;
    }

    public void setManagedObjectIds(List<String> managedObjectIds) {
        this.managedObjectIds = managedObjectIds;
    }
}
