package com.system559.cms.model;

import java.util.List;
import java.util.UUID;

public class NoteBoard extends ManagedObject {
    private String name;

    private List<ManagedObject> managedObjects;

    public NoteBoard() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ManagedObject> getObjects() {
        return managedObjects;
    }

    public static NoteBoard createNoteBoard(List<String> managerIds, String name, List<ManagedObject> managedObjects) {
        NoteBoard newNoteBoard = new NoteBoard();
        newNoteBoard.id = UUID.randomUUID().toString();
        newNoteBoard.managerIds = managerIds;
        newNoteBoard.name = name;
        newNoteBoard.managedObjects = managedObjects;
        return  newNoteBoard;
    }
}
