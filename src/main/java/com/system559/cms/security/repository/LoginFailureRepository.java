package com.system559.cms.security.repository;

import com.system559.cms.security.data.LoginFailure;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginFailureRepository extends MongoRepository<LoginFailure, String> {
}
