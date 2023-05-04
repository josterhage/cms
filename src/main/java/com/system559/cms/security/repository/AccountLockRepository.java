package com.system559.cms.security.repository;

import com.system559.cms.security.data.AccountLock;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountLockRepository extends MongoRepository<AccountLock, String> {
}
