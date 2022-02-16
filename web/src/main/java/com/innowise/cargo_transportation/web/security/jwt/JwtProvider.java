package com.innowise.cargo_transportation.web.security.jwt;

import com.innowise.cargo_transportation.core.entity.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class JwtProvider {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String TOKEN_START_OF_LINE = "Bearer ";
    private static final String CLIENT_ID_CLAIMS_KEY = "clientId";
    private static final String USER_ROLES_CLAIMS_KEY = "auth";
    private static final String USER_ID_CLAIMS_KEY = "userId";

    @Value("${jwt.token.secret}")
    private String secret;
    private SecretKey secretKey;
    @Value("${jwt.token.access.expired}")
    private long accessTokenValidityInMilliseconds;
    @Value("${jwt.token.refresh.expired}")
    private long refreshTokenValidityInMilliSeconds;

    private final JwtUserDetailsService jwtUserDetailsService;

    @PostConstruct
    protected void init() {
        secret = Base64.getEncoder().encodeToString(secret.getBytes());
        secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(String login, Long userId, Set<UserRole> userRoles) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put(CLIENT_ID_CLAIMS_KEY, userId);
        claims.put(USER_ROLES_CLAIMS_KEY, userRoles);

        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenValidityInMilliseconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(String login, Long userId) {
        Claims claims = Jwts.claims().setSubject(login);
        claims.put(USER_ID_CLAIMS_KEY, userId);
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenValidityInMilliSeconds);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    public String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody().getSubject();
    }

    public Long getUserId(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(USER_ID_CLAIMS_KEY, Long.class);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_START_OF_LINE)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims =
                    Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return !claims.getBody().getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            throw new JwtAuthenticationException("JWT token is expired or invalid");
        }
    }

}
