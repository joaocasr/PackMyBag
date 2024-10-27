package com.example.utilizadoresService.config.auth;

import ch.qos.logback.core.net.server.Client;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.model.Tecnico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenProvider {
    @Value("${security.jwt.secrets-key}")
    private String JWT_SECRET;

    public String generateAccessToken(Cliente user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            if( user instanceof Tecnico) {
                return JWT.create()
                        .withSubject(user.getUsername())
                        .withClaim("username", user.getUsername())
                        .withClaim("nome", user.getNome())
                        .withClaim("password", user.getPassword())
                        .withClaim("email", user.getEmail())
                        .withClaim("profileImage", user.getProfileImage())
                        .withClaim("idloja",  ((Tecnico) user).getLoja().getIDLoja())
                        .withExpiresAt(genAccessExpirationDate())
                        .sign(algorithm);
            } else{
                return JWT.create()
                        .withSubject(user.getUsername())
                        .withClaim("username", user.getUsername())
                        .withClaim("nome", user.getNome())
                        .withClaim("password", user.getPassword())
                        .withClaim("email", user.getEmail())
                        .withClaim("profileImage", user.getProfileImage())
                        .withExpiresAt(genAccessExpirationDate())
                        .sign(algorithm);
            }
        } catch (JWTCreationException exception) {
            throw new JWTCreationException("Error while generating token", exception);
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWT_SECRET);
            return JWT.require(algorithm)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new JWTVerificationException("Error while validating token", exception);
        }
    }

    private Instant genAccessExpirationDate() {
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.of("-03:00"));
    }
}