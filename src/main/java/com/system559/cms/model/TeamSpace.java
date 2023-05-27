package com.system559.cms.model;

import java.util.List;

/**
 * <p>
 * The primary collaboration space for a Team, can contain collaborative objects or other spaces, anything
 * derived from {@link ManagedObject}
 * </p>
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class TeamSpace extends ManagedObject {
    /**
     * Name of this TeamSpace
     */
    private String name;

    /**
     * List of this TeamSpace's ManagedObjects
     */
    private List<ManagedObject> managedObjects;

    /**
     * Returns an empty TeamSpace object
     */
    public TeamSpace() {
    }

    /**
     * Returns this TeamSpace's name
     * @return TeamSpace name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets this TeamSpace's name
     * @param name TeamSpace name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns this TeamSpace's list of ManagedObjects
     * @return list of ManagedObjects
     */
    public List<ManagedObject> getManagedObjects() {
        return managedObjects;
    }

    /**
     * Sets this TeamSpace's list of ManagedObjects
     * @param managedObjects list of ManagedObjects
     */
    public void setManagedObjects(List<ManagedObject> managedObjects) {
        this.managedObjects = managedObjects;
    }
}
