package com.system559.cms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system559.cms.security.CmsUsernamePasswordAuthenticationFilter;
import com.system559.cms.security.data.AccountLock;
import com.system559.cms.security.data.UsernamePasswordCredential;
import com.system559.cms.security.data.UsernamePasswordDto;
import com.system559.cms.security.repository.AccountLockRepository;
import com.system559.cms.security.repository.LoginFailureRepository;
import com.system559.cms.security.repository.UsernamePasswordCredentialRepository;
import com.system559.cms.security.service.UsernamePasswordAuthenticationProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CmsUsernamePasswordAuthenticationFilterTests {
    //Test Data
    private final String USER_ID = "3cfa3547-2220-481d-9cdf-b7465f445bb9";
    private final String VALID_USERNAME = "test_user";
    private final String VALID_PT_PASSWORD = "test_password";
    private final String VALID_CT_PASSWORD = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(VALID_PT_PASSWORD);
    private final String INVALID_USERNAME = "bad_user";
    private final String INVALID_PASSWORD = "bad_password";
    private final UsernamePasswordCredential testCredential =
            new UsernamePasswordCredential(USER_ID, VALID_USERNAME, VALID_CT_PASSWORD);
    private final UsernamePasswordDto VALID_USERNAME_PASSWORD_DTO = new UsernamePasswordDto(VALID_USERNAME, VALID_PT_PASSWORD);
    private final UsernamePasswordDto INVALID_USERNAME_DTO = new UsernamePasswordDto(INVALID_USERNAME, INVALID_PASSWORD);
    private final UsernamePasswordDto INVALID_PASSWORD_DTO = new UsernamePasswordDto(VALID_USERNAME, INVALID_PASSWORD);

    //Repositories
    @Autowired
    private UsernamePasswordCredentialRepository usernamePasswordCredentialRepository;
    @Autowired
    private LoginFailureRepository loginFailureRepository;
    @Autowired
    private AccountLockRepository accountLockRepository;

    private CmsUsernamePasswordAuthenticationFilter filter;

    @Before
    public void setup() {
        usernamePasswordCredentialRepository.deleteAll();
        loginFailureRepository.deleteAll();
        accountLockRepository.deleteAll();

        usernamePasswordCredentialRepository.save(testCredential);

        setMaxStackTraceElementsDisplayed(1000);

        filter = new CmsUsernamePasswordAuthenticationFilter(
                new ProviderManager(
                        List.of(
                                new UsernamePasswordAuthenticationProvider(
                                        accountLockRepository,
                                        usernamePasswordCredentialRepository,
                                        loginFailureRepository))));
    }

    @After
    public void tearDown() {
        usernamePasswordCredentialRepository.deleteAll();
        loginFailureRepository.deleteAll();
        accountLockRepository.deleteAll();
    }

    private MockHttpServletRequest createMockRequest(UsernamePasswordDto dto) {
        MockHttpServletRequest req = new MockHttpServletRequest();
        req.setContentType("application/json");
        try {
            req.setContent(new ObjectMapper().writer().writeValueAsBytes(dto));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return req;
    }

    @Test
    public void invalidUsernameThrowsException() {
        assertThatExceptionOfType(BadCredentialsException.class)
                .isThrownBy(() -> filter.attemptAuthentication(createMockRequest(INVALID_USERNAME_DTO), new MockHttpServletResponse()))
                .withMessage("Invalid username or password");
    }

    @Test
    public void invalidPasswordThrowsExceptionAndUpdatesLoginFailureEntity() {

        assertThatExceptionOfType(BadCredentialsException.class)
                .isThrownBy(() -> filter.attemptAuthentication(createMockRequest(INVALID_PASSWORD_DTO), new MockHttpServletResponse()))
                .withMessage("Invalid username or password");

        assertThat(loginFailureRepository.findById(USER_ID)).isPresent();
    }

    @Test
    public void threeInvalidPasswordAttemptsCauseLock() {
        for (int i = 0; i < 3; i++) {
            try {
                filter.attemptAuthentication(createMockRequest(INVALID_PASSWORD_DTO), new MockHttpServletResponse());
            } catch (BadCredentialsException ex) {
                System.out.println(i);
            }
        }
        Optional<AccountLock> optionalAccountLock = accountLockRepository.findById(USER_ID);
        assertThat(optionalAccountLock).isPresent();
        assertThat(optionalAccountLock.get().isValid()).isTrue();
    }

    @Test
    public void lockedAccountAuthenticationAttemptThrowsException() {
        accountLockRepository.save(new AccountLock(USER_ID));

        assertThatExceptionOfType(LockedException.class)
                .isThrownBy(() -> filter.attemptAuthentication(createMockRequest(VALID_USERNAME_PASSWORD_DTO), new MockHttpServletResponse()))
                .withMessage("This account is locked");

        assertThatExceptionOfType(LockedException.class)
                .isThrownBy(() -> filter.attemptAuthentication(createMockRequest(INVALID_PASSWORD_DTO), new MockHttpServletResponse()))
                .withMessage("This account is locked");
    }

    @Test
    public void validCredentialInValidAuthenticationOut() {
        assertThat(filter.attemptAuthentication(createMockRequest(VALID_USERNAME_PASSWORD_DTO), new MockHttpServletResponse()).isAuthenticated()).isTrue();
    }
}
