package com.system559.cms.model;

import java.util.List;

public class Note extends ManagedObject {
    private String name;
    private String text;

    public Note() {
        super();
    }

    public Note(String id, List<User> managers, String name, String text) {
        super(id, managers);
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
