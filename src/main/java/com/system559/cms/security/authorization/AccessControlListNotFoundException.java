package com.system559.cms.security.authorization;

/**
 * Unchecked exception thrown when an AccessControlRepository call returns no records. <br/>
 * This should be thrown when an access control check finds no record for an object. Simply returning <b>false</b> or
 * an empty permissions set would mask the serious error of the Access Control System not having a record matching
 * an entity from the domain space.
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
public class AccessControlListNotFoundException extends RuntimeException {
    /**
     * Calls the base class ({@link RuntimeException}) with a descriptive error message including the objectId
     * @param objectId unique identifier of the object that could not be found
     */
    public AccessControlListNotFoundException(String objectId) {
        super("AccessControlEntry for resource with id: " + objectId + " not found");
    }
}
