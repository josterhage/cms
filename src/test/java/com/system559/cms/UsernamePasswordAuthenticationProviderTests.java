package com.system559.cms;

import com.system559.cms.security.data.AccountLock;
import com.system559.cms.security.data.UsernamePasswordCredential;
import com.system559.cms.security.repository.AccountLockRepository;
import com.system559.cms.security.repository.LoginFailureRepository;
import com.system559.cms.security.repository.UsernamePasswordCredentialRepository;
import com.system559.cms.security.service.UsernamePasswordAuthenticationProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UsernamePasswordAuthenticationProviderTests {
    //Test Data
    private final String USER_ID = "3cfa3547-2220-481d-9cdf-b7465f445bb9";
    private final String VALID_USERNAME = "test_user";
    private final String VALID_PT_PASSWORD = "test_password";
    private final String VALID_CT_PASSWORD = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(VALID_PT_PASSWORD);
    private final String INVALID_USERNAME = "bad_user";
    private final String INVALID_PASSWORD = "bad_password";
    private final UsernamePasswordCredential testCredential =
            new UsernamePasswordCredential(USER_ID,VALID_USERNAME,VALID_CT_PASSWORD);
    private final Authentication INVALID_USERNAME_AUTHENTICATION =
            UsernamePasswordAuthenticationToken.unauthenticated(INVALID_USERNAME, INVALID_PASSWORD);
    private final Authentication INVALID_PASSWORD_AUTHENTICATION =
            UsernamePasswordAuthenticationToken.unauthenticated(VALID_USERNAME, INVALID_PASSWORD);
    private final Authentication VALID_AUTHENTICATION =
            UsernamePasswordAuthenticationToken.unauthenticated(VALID_USERNAME, VALID_PT_PASSWORD);

    //Needed to persist a UsernamePasswordCredential before testing the provider
    @Autowired
    private UsernamePasswordCredentialRepository usernamePasswordCredentialRepository;
    @Autowired
    private LoginFailureRepository loginFailureRepository;
    @Autowired
    private AccountLockRepository accountLockRepository;

    private UsernamePasswordAuthenticationProvider provider;

    @Before
    public void setup() {
        provider = new UsernamePasswordAuthenticationProvider(accountLockRepository,
                usernamePasswordCredentialRepository,
                loginFailureRepository);

        usernamePasswordCredentialRepository.deleteAll();
        loginFailureRepository.deleteAll();
        accountLockRepository.deleteAll();

        usernamePasswordCredentialRepository.save(testCredential);

        setMaxStackTraceElementsDisplayed(1000);
    }

    @Test
    public void invalidUsernameThrowsException() {
        assertThatExceptionOfType(BadCredentialsException.class)
                .isThrownBy(() -> provider.authenticate(INVALID_USERNAME_AUTHENTICATION))
                .withMessage("Invalid username or password");
    }

    @Test
    public void invalidPasswordThrowsExceptionAndUpdatesLoginFailureEntity() {
        assertThatExceptionOfType(BadCredentialsException.class)
                .isThrownBy(() -> provider.authenticate(INVALID_PASSWORD_AUTHENTICATION))
                .withMessage("Invalid username or password");

        assertThat(loginFailureRepository.findById(USER_ID)).isPresent();
    }

    @Test
    public void threeInvalidPasswordAttemptsCauseLock() {
        for(int i = 0; i <3; i++){
            try {
                provider.authenticate(INVALID_PASSWORD_AUTHENTICATION);
            } catch(Exception ex) {
                System.out.println(i);
            }
        }
        Optional<AccountLock> optionalAccountLock = accountLockRepository.findById(USER_ID);
        assertThat(optionalAccountLock).isPresent();
        assertThat(optionalAccountLock.get().isValid()).isTrue();
    }

    @Test
    public void lockedAccountAuthenticationAttemptThrowsException() {
        for(int i = 0; i < 3; i++) {
            try {
                provider.authenticate(INVALID_PASSWORD_AUTHENTICATION);
            } catch(Exception ex) {
                System.out.println(i);
            }
        }

        assertThatExceptionOfType(LockedException.class)
                .isThrownBy(() -> provider.authenticate(VALID_AUTHENTICATION))
                .withMessage("This account is locked");

        assertThatExceptionOfType(LockedException.class)
                .isThrownBy(() -> provider.authenticate(INVALID_PASSWORD_AUTHENTICATION))
                .withMessage("This account is locked");
    }

    @Test
    public void validCredentialsReturnAuthenticatedToken() {
        assertThat(provider.authenticate(VALID_AUTHENTICATION).isAuthenticated()).isTrue();
    }
}
