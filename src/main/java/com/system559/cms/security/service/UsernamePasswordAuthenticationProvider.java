package com.system559.cms.security.service;

import com.system559.cms.security.data.AccountLock;
import com.system559.cms.security.data.LoginFailure;
import com.system559.cms.security.data.UsernamePasswordCredential;
import com.system559.cms.security.repository.AccountLockRepository;
import com.system559.cms.security.repository.UsernamePasswordCredentialRepository;
import com.system559.cms.security.repository.LoginFailureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final AccountLockRepository accountLockRepository;
    private final UsernamePasswordCredentialRepository credentialRepository;
    private final LoginFailureRepository loginFailureRepository;

    @Autowired
    public UsernamePasswordAuthenticationProvider(AccountLockRepository accountLockRepository,
                                                  UsernamePasswordCredentialRepository credentialRepository,
                                                  LoginFailureRepository loginFailureRepository) {
        this.accountLockRepository = accountLockRepository;
        this.credentialRepository = credentialRepository;
        this.loginFailureRepository = loginFailureRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String password = authentication.getCredentials().toString();

        UsernamePasswordCredential credential = getCredentialFromUsername(authentication.getName());

        checkForLockedAccount(credential.getUserId());

        if (doesPasswordMatch(password, credential)) {
            return UsernamePasswordAuthenticationToken.authenticated(credential.getUserId(),password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        }

        throw new AuthenticationServiceException("The authentication service could not verify the credentials presented.");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    private UsernamePasswordCredential getCredentialFromUsername(String username) {
        UsernamePasswordCredential credential = credentialRepository.findCredentialByUsername(username)
                .orElse(null);

        if(credential == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return credential;
    }

    private void checkForLockedAccount(String userId) {
        AccountLock lock = accountLockRepository.findById(userId).orElse(null);

        if(lock == null) {
            return;
        }

        if(lock.isValid()) {
            throw new LockedException("This account is locked");
        }
    }

    private boolean doesPasswordMatch(String rawPassword, UsernamePasswordCredential credential) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        if(encoder.matches(rawPassword, credential.getPassword())) {
            return true;
        }

        LoginFailure failure = loginFailureRepository.findById(credential.getUserId())
                .orElse(new LoginFailure(credential.getUserId()));

        failure.registerFailure();

        loginFailureRepository.save(failure);

        if(failure.getCount() >= LoginFailure.getMaxFailures()) {
            accountLockRepository.save(new AccountLock(credential.getUserId()));
        }

        throw new BadCredentialsException("Invalid username or password");
    }
}
