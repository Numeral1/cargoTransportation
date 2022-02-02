package com.innowise.cargo_transportation.web.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.innowise.cargo_transportation.web.security.jwt.JwtAuthenticationException;
import com.innowise.cargo_transportation.web.security.jwt.JwtProvider;
import com.innowise.cargo_transportation.web.exception.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = jwtTokenProvider.resolveToken(request);
        try {
            if (token != null && jwtTokenProvider.validateToken(token)) {
                Authentication authentication = jwtTokenProvider.getAuthentication(token);
                if (authentication != null) {
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (JwtAuthenticationException e) {
            response.setStatus(403);
            response.setContentType("application/json");
            PrintWriter writer = response.getWriter();
            String body = objectMapper.writeValueAsString(ErrorResponse.of(403, e.getMessage()));
            writer.println(body);
            writer.close();
        }
        filterChain.doFilter(request, response);
    }

}