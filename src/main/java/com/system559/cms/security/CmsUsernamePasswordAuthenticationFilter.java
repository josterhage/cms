package com.system559.cms.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.system559.cms.security.data.UsernamePasswordDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.BufferedReader;
import java.io.IOException;

public class CmsUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public CmsUsernamePasswordAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
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
