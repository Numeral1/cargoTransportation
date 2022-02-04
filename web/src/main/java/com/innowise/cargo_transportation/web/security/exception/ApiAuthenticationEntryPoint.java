package com.innowise.cargo_transportation.web.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.cargo_transportation.web.exception.ErrorResponse;
import lombok.Data;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Data
@Component
public class ApiAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {
        response.setStatus(401);
        response.setContentType("application/json");
        String body = objectMapper.writeValueAsString(ErrorResponse.of(401, authException.getMessage()));
        PrintWriter writer = response.getWriter();
        writer.println(body);
        writer.flush();
    }
}
