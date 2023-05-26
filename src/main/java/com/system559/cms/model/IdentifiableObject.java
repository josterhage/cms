package com.system559.cms.model;

import java.io.Serializable;

/**
 * <p>
 * An object that can be identified by a unique String.
 * </p>
 * <br/>
 * All objects that will be persisted should derive from this interface to ensure:
 * <ol>
 *     <li>Persistable items are {@link Serializable}</li>
 *     <li>Persistable items use a String for their unique identifier</li>
 * </ol>
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public interface IdentifiableObject extends Serializable {
    /**
     * Return this object's unique identifier String
     * @return unique identifier String
     */
    String getId();
}
