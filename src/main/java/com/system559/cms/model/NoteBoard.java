package com.system559.cms.model;

import java.util.ArrayList;
import java.util.List;

public class NoteBoard extends ManagedObject {
    private String name;

    private final List<ManagedObject> objects;

    public NoteBoard() {
        super();
        this.objects = new ArrayList<>();
    }

    public NoteBoard(String id, List<User> managers, String name, List<ManagedObject> objects) {
        super(id, managers);
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
