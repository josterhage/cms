package com.system559.cms.model;

import java.util.List;

/**
 * <p>
 * An IdentifiableObject that is managed by a List of Users
 * </p>
 * <br/>
 * Objects used in the collaborative space should extend this class.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public abstract class ManagedObject implements IdentifiableObject {
    /**
     * This object's unique String identifier.
     */
    protected String id;
    /**
     * List of identifiers that refer to the Users who manage this object.
     */
    protected List<String> managerIds;

    /**
     * Sets this object's unique identifier
     * @param id unique identifier String
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * {@inheritDoc}
     */
    public String getId() {
        return this.id;
    }

    /**
     * Sets this object's List of manager userIds
     * @param managerIds <code>List{@literal <String>}</code> of manager userIds
     */
    public void setManagerIds(List<String> managerIds) {
        this.managerIds = managerIds;
    }

    /**
     * Get the List of manger userIds
     * @return {@code List{@literal <String>}} of manager userIds
     */
    public List<String> getManagerIds() {
        return managerIds;
    }

    /**
     * Confirms if the presented User is a manager of this object.
     * Use {@link #isManager(String)} to check by userId
     * @param user to check
     * @return true if user is a manager; false if not
     */
    public boolean isManager(User user) {
        return managerIds.contains(user.getId());
    }

    /**
     * Confirms if the userId represents a manager of this object.
     * Use {@link #isManager(User)} to check with a User object
     * @param userId to check
     * @return true if user is a manager; false if not
     */
    public boolean isManager(String userId) {
        return managerIds.contains(userId);
    }
}
