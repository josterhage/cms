package com.system559.cms;

import com.system559.cms.security.repository.UsernamePasswordCredentialRepository;
import com.system559.cms.security.WebSecurityConfig;
import com.system559.cms.security.service.UsernamePasswordAuthenticationProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UsernamePasswordAuthenticationProvider.class, WebSecurityConfig.class})
@WebAppConfiguration
public class SecuritySystemTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private UsernamePasswordCredentialRepository repository;

    private MockMvc mvc;

    @BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    @WithAnonymousUser
    public void testAnonymousDenied() throws Exception {
        mvc.perform(get("/api/user")).andExpect(status().isUnauthorized());
    }
}
