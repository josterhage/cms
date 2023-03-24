package com.system559.cms.model;

/**
 *
 * An object with user-level controlled access that can be rendered in HTML for web viewing.
 *
 * <b>Note:</b> Later versions may take AccessControlled as a composed object rather than an inheritance.
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
public interface Viewable extends AccessControlled{
    /**
     * Returns a unique identifier
     * @return String with this object's unique identifier
     */
    String getViewId();

    /**
     * Returns the object's fully qualified name
     * @return String containing the fully qualified name
     */
    String getName();

    /**
     * The user who created this object. Creators have special permissions on objects.
     * @return User who created the object.
     */
    User getCreator();
}
