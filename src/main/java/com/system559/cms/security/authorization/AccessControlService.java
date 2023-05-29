package com.system559.cms.security.authorization;

import com.system559.cms.security.data.AccessControlList;
import com.system559.cms.security.data.Permission;
import com.system559.cms.security.repository.AccessControlListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Optional;

/**
 * Verifies whether a given user has the permissions to perform a requested action on a reosurce
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
@Service
public class AccessControlService {
    private final AccessControlListRepository repository;

    /**
     * Default constructor, {@link Autowired} by the framework
     * @param repository framework-injected {@link AccessControlListRepository} instance
     */
    @Autowired
    public AccessControlService(AccessControlListRepository repository) {
        this.repository = repository;
    }

    /**
     * Returns whether a user has specific permissions on an object. Returns false if the user isn't listed on the
     * access control list for the object.
     * @param objectId unique identifier to the object
     * @param userId unique identifier to the user
     * @param permission the single permission to check
     * @return true if the user has the permission, false if not.
     */
    public boolean hasPermission(String objectId, String userId, Permission permission) {
        AccessControlList entry = repository.
                findById(objectId).
                orElseThrow(() -> new AccessControlListNotFoundException(objectId));
        return Optional.ofNullable(
                entry.getGrantedPermissions().get(userId)).orElse(EnumSet.noneOf(Permission.class)).
                contains(permission);
    }

    /**
     * Returns the permissions a user has on an object
     * @param objectId unique identifier to the object
     * @param userId unique identifier to the user
     * @return EnumSet of the permissions user has on an object. Returns an empty set if the user is not in the access
     * control list for the object.
     */
    public EnumSet<Permission> getPermissions(String objectId, String userId) {
        AccessControlList entry = repository.findById(objectId).orElseThrow(() -> new AccessControlListNotFoundException(objectId));
        return Optional.ofNullable(entry.getGrantedPermissions().get(userId)).orElse(EnumSet.noneOf(Permission.class));
    }
}
