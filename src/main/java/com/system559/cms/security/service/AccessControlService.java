package com.system559.cms.security.service;

import com.system559.cms.security.data.AccessControlEntry;
import com.system559.cms.security.data.Permission;
import com.system559.cms.model.ManagedObject;
import com.system559.cms.model.User;
import com.system559.cms.security.repository.AccessControlEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class AccessControlService {
    private final AccessControlEntryRepository repository;

    @Autowired
    public AccessControlService(AccessControlEntryRepository repository) {
        this.repository = repository;
    }
    //TODO: implement meaningful exception classes
    public boolean hasPermission(String userId, String objectId, Permission permission) {
        AccessControlEntry entry = repository.findById(userId).orElseThrow(RuntimeException::new);
        return entry.getGrantedPermissions().containsKey(objectId) && entry.getGrantedPermissions().get(objectId).contains(permission);
    }

    public boolean hasPermission(User user, ManagedObject object, Permission permission) {
        if(user == null || object == null) {
            return false;
        }
        return hasPermission(user.getId(),object.getId(),permission);
    }

    public EnumSet<Permission> getPermissions(String userId, String objectId) {
        AccessControlEntry entry = repository.findById(userId).orElseThrow(RuntimeException::new);
        return entry.getGrantedPermissions().get(objectId);
    }

    public EnumSet<Permission> getPermissions(User user, ManagedObject object) {
        if(user == null || object == null) {
            return EnumSet.noneOf(Permission.class);
        }
        return getPermissions(user.getId(),object.getId());
    }
}
