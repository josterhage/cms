package com.system559.cms.model;

import java.util.List;

/**
 * An abstraction of a collaborative space with multiple Viewable objects
 *
 * @author James Osterhage
 * @version 0.1
 * @since 0.1
 */
public interface WorkSpace extends Viewable {
    /**
     * Returns a collection of the Viewable objects contained in this workspace.
     * @return List of Viewables in this WorkSpace
     */
    List<Viewable> getViewables();
}
