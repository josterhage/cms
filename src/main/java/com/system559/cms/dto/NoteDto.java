package com.system559.cms.dto;

import java.util.List;

public class NoteDto {
    private String name;
    private String text;
    private List<String> managerIds;

    public NoteDto() {

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

    public List<String> getManagerIds() {
        return managerIds;
    }

    public void setManagerIds(List<String> managerIds) {
        this.managerIds = managerIds;
    }
}
