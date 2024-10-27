package com.example.utilizadoresService.controllers;

import com.example.utilizadoresService.config.auth.TokenProvider;
import com.example.utilizadoresService.dtos.JwtDto;
import com.example.utilizadoresService.dtos.SignInDto;
import com.example.utilizadoresService.dtos.SignUpTecnicoDto;
import com.example.utilizadoresService.dtos.SignUpUserDto;
import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/utilizadores")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthService service;
    @Autowired
    private TokenProvider tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService service, TokenProvider tokenService) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup/tecnico")
    public ResponseEntity<?> signUpTecnico(@RequestBody @Valid SignUpTecnicoDto data) {
        service.signUpTecnico(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/signup/user")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpUserDto data) {
        UserDetails c = service.signUpUser(data);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((Cliente) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }
}