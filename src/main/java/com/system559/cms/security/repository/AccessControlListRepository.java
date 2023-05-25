package com.system559.cms.security.repository;

import com.system559.cms.security.data.AccessControlList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessControlListRepository extends MongoRepository<AccessControlList, String> {
}
