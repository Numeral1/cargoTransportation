package com.innowise.cargo_transportation.web.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.cargo_transportation.web.exception.ErrorResponse;
import lombok.Data;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Data
@Component
public class ApiAccessDeniedHandler implements AccessDeniedHandler {
    private final ObjectMapper objectMapper;


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(403);
        response.setContentType("application/json");
        PrintWriter writer = response.getWriter();
        String body = objectMapper.writeValueAsString(ErrorResponse.of(403, accessDeniedException.getMessage()));
        writer.println(body);
        writer.close();
    }
}
