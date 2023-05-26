package com.system559.cms.model;

/**
 * <p>
 *     A {@link ManagedObject} that represents an abstraction of a "sticky note" or similar collaborative device. Notes
 *     have a name/title and text that can be sanitized HyperText
 * </p>
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class Note extends ManagedObject {
    /**
     * The name of this note.
     */
    private String name;
    /**
     * The text of this note.
     */
    private String text;

    /**
     * Constructs an empty note
     */
    public Note() {
    }

    /**
     * Returns the note's name
     * @return the note's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the note's name
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the note's text
     * @return the note's text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the note's text
     * @param text the new text
     */
    public void setText(String text) {
        this.text = text;
    }
}
