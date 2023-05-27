package com.system559.cms.model;

import java.util.List;

/**
 * Note Boards are containers for holding notes in a collaborative space.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class NoteBoard extends ManagedObject {
    /**
     * The name of this Note board
     */
    private String name;

    /**
     * List of the ManagedObjects held by this board
     */
    private List<ManagedObject> managedObjects;

    /**
     * Returns an empty NoteBoard object
     */
    public NoteBoard() {
    }

    /**
     * Returns the name of this Note Board
     * @return name of this NoteBoard
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this NoteBoard
     * @param name new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the List of this NoteBoard's contained objects
     * @return list of contained objects
     */
    public List<ManagedObject> getManagedObjects() {
        return managedObjects;
    }

    /**
     * Sets the List of ManagedObjects
     * @param managedObjects List of ManagedObjects
     */
    public void setManagedObjects(List<ManagedObject> managedObjects) {
        this.managedObjects = managedObjects;
    }
}
