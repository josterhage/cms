package com.system559.cms.security.repository;

import com.system559.cms.security.data.AccessControlEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccessControlEntryRepository extends MongoRepository<AccessControlEntry, String> {
}
