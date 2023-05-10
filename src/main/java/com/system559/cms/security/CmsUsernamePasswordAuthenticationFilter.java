package com.system559.cms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system559.cms.security.data.UsernamePasswordDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;

@Component
public class CmsUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Autowired
    public CmsUsernamePasswordAuthenticationFilter(AuthenticationProvider authenticationProvider) {
        super(new ProviderManager(authenticationProvider));
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request, HttpServletResponse response) {

        try (BufferedReader reader = request.getReader()) {
            String inputString = reader.readLine();

            UsernamePasswordDto credentials = new ObjectMapper().readValue(inputString, UsernamePasswordDto.class);

            return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return this.getAuthenticationManager().authenticate(null);
    }
}
