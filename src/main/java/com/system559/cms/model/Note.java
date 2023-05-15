package com.system559.cms.model;

import com.system559.cms.dto.NoteDto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Note extends ManagedObject {
    private String name;
    private String text;

    public Note() {
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

    public static Note createNote(String name, String text, List<String> managerIds) {
        Note newNote = new Note();
        newNote.id = UUID.randomUUID().toString();
        newNote.name=name;
        newNote.text=text;
        newNote.managerIds = (managerIds != null) ? managerIds : new ArrayList<>();
        return newNote;
    }

    public static Note createNoteFromDto(NoteDto dto) {
        return createNote(dto.getName(),dto.getText(),dto.getManagerIds());
    }
}
