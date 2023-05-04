package com.system559.cms.security.repository;

import com.system559.cms.security.data.UsernamePasswordCredential;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsernamePasswordCredentialRepository extends MongoRepository<UsernamePasswordCredential, String> {
    Optional<UsernamePasswordCredential> findCredentialByUsername(String username);
}
