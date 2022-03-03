package com.innowise.cargo_transportation.web.configuration;

import com.innowise.cargo_transportation.core.entity.UserRole;
import com.innowise.cargo_transportation.web.security.filter.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtFilter jwtFilter;
    private final AccessDeniedHandler accessDeniedHandler;
    private final AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                        .antMatchers(HttpMethod.POST, "/api/users").hasAuthority(UserRole.SYS_ADMIN.name())
                        .antMatchers(HttpMethod.DELETE, "/api/users/**").hasAuthority(UserRole.SYS_ADMIN.name())
                        .antMatchers(HttpMethod.GET,"/api/users/**").hasAuthority(UserRole.SYS_ADMIN.name())
                        .antMatchers(HttpMethod.PUT,"/api/users/**").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.POST,"/api/clients").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.GET,"/api/clients/**").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.PUT,"/api/clients/**").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.DELETE,"/api/clients/**").hasAuthority(UserRole.SYS_ADMIN.name())
                .antMatchers(HttpMethod.POST,"/api/storages").hasAuthority(UserRole.SYS_ADMIN.name())
                .antMatchers(HttpMethod.GET,"/api/storages/**").hasAuthority(UserRole.SYS_ADMIN.name())
                .antMatchers(HttpMethod.PUT,"/api/storages/**").hasAuthority(UserRole.SYS_ADMIN.name())
                .antMatchers(HttpMethod.DELETE,"/api/storages").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.POST,"/api/product-owners").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.GET,"/api/product-owners/**").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.PUT,"/api/product-owners/**").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers(HttpMethod.DELETE,"/api/product-owners").hasAuthority(UserRole.SYS_ADMIN.name())
                    .antMatchers("/api/sign-in", "/api/refresh", "/api/logout", "/api/about").permitAll()
            .anyRequest().denyAll()
                .and()
                .addFilterAt(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authenticationEntryPoint);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("Content-Type", "Authentication","Origin"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        return source;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

