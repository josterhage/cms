package com.system559.cms.security.authorization;

import com.system559.cms.security.data.AccessControlList;
import com.system559.cms.security.data.Permission;
import com.system559.cms.security.repository.AccessControlListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.Optional;

@Service
public class AccessControlService {
    private final AccessControlListRepository repository;

    @Autowired
    public AccessControlService(AccessControlListRepository repository) {
        this.repository = repository;
    }
    //TODO: implement meaningful exception classes
    public boolean hasPermission(String objectId, String userId, Permission permission) {
        AccessControlList entry = repository.findById(objectId).orElseThrow(RuntimeException::new);
        return Optional.ofNullable(entry.getGrantedPermissions().get(userId)).orElse(EnumSet.noneOf(Permission.class)).contains(permission);
    }

    public EnumSet<Permission> getPermissions(String userId, String objectId) {
        AccessControlList entry = repository.findById(userId).orElseThrow(RuntimeException::new);
        return entry.getGrantedPermissions().get(objectId);
    }
}
