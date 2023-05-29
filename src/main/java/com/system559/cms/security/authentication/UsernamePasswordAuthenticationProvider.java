package com.system559.cms.security.authentication;

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

/**
 * <p>
 * UsernamePasswordAuthenticationProvider checks a {@link com.system559.cms.security.dto.UsernamePasswordDto} against the
 * security database, throwing appropriate Exceptions on receiving invalid credentials.
 * </p>
 * <br/>
 * Roughly, execution flow is as follows:<br/>
 * <ul>
 * <li>Spring's Security Filter Chain receives a request for a secured resource from an unauthenticated User</li>
 * <li>Spring calls {@link #authenticate(Authentication)}</li>
 * <li>{@link #authenticate(Authentication)} checks the credential against the security database
 * <ul>
 *     <li>Returns an authenticated {@link Authentication} if the credentials match</li>
 *     <li>Throws {@link BadCredentialsException} and tracks login failures if credentials don't match</li>
 *     <li>Throws {@link LockedException} after a third failure and creates LockedAccount token</li>
 * </ul>
 * </li>
 * </ul>
 * @author James Osterhage
 * @version 0.0.1
 * @since 0.0.1
 */
@Component
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
    private final AccountLockRepository accountLockRepository;
    private final UsernamePasswordCredentialRepository credentialRepository;
    private final LoginFailureRepository loginFailureRepository;

    /**
     * Only constructor, {@link Autowired} by the framework
     * @param accountLockRepository framework-injected {@link AccountLockRepository} instance
     * @param credentialRepository framework-injected {@link UsernamePasswordCredentialRepository} instance
     * @param loginFailureRepository framework-injected {@link LoginFailureRepository} instance
     */
    @Autowired
    public UsernamePasswordAuthenticationProvider(AccountLockRepository accountLockRepository,
                                                  UsernamePasswordCredentialRepository credentialRepository,
                                                  LoginFailureRepository loginFailureRepository) {
        this.accountLockRepository = accountLockRepository;
        this.credentialRepository = credentialRepository;
        this.loginFailureRepository = loginFailureRepository;
    }

    /**
     * Attempts to authenticate a presented credential against the security database
     * @param authentication the authentication request object.
     * @return an authenticated Authentication
     * @throws AuthenticationException either {@link BadCredentialsException} or {@link LockedException} depending on
     * how many times the method receives bad credentials
     */
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

    /**
     * Returns whether this class can authenticate a given Authentication class
     * @param authentication the Authentication instance to check
     * @return true if authentication is an instance of {@link UsernamePasswordAuthenticationToken}
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    /**
     * Returns a UsernamePasswordCredential pulled from the security database
     * @param username username to search by
     * @return valid UsernamePasswordCredential if found
     * @throws BadCredentialsException when no record is found
     */
    private UsernamePasswordCredential getCredentialFromUsername(String username) {
        UsernamePasswordCredential credential = credentialRepository.findCredentialByUsername(username)
                .orElse(null);

        if(credential == null) {
            throw new BadCredentialsException("Invalid username or password");
        }

        return credential;
    }

    /**
     * Checks the database for a locked account entry
     * @param userId userId to check for a lock
     * @throws LockedException if a valid lock is found
     */
    private void checkForLockedAccount(String userId) {
        AccountLock lock = accountLockRepository.findById(userId).orElse(null);

        if(lock == null) {
            return;
        }

        if(lock.isValid()) {
            throw new LockedException("This account is locked");
        }
    }

    /**
     * Checks the presented password against the database using a framework provided {@link PasswordEncoder}
     * @param rawPassword password presented by the client
     * @param credential the credential loaded from the database
     * @return true if passwords match
     * @throws BadCredentialsException if passwords don't match
     */
    private boolean doesPasswordMatch(String rawPassword, UsernamePasswordCredential credential) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        if(encoder.matches(rawPassword, credential.getPassword())) {
            return true;
        }

        LoginFailure failure = loginFailureRepository.findById(credential.getUserId())
                .orElse(new LoginFailure(credential.getUserId()));

        failure.registerFailure();

        loginFailureRepository.save(failure);

        if(failure.getFailureCount() >= LoginFailure.getMaxFailures()) {
            accountLockRepository.save(new AccountLock(credential.getUserId()));
        }

        throw new BadCredentialsException("Invalid username or password");
    }
}
