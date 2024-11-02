package com.example.utilizadoresService.controllers;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.utilizadoresService.Exceptions.InexistentLojaException;
import com.example.utilizadoresService.Exceptions.InvalidJwtException;
import com.example.utilizadoresService.config.auth.TokenProvider;
import com.example.utilizadoresService.dtos.*;
import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.model.Estilista;
import com.example.utilizadoresService.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/utilizadores")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private AuthService service;
    private TokenProvider tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, AuthService service, TokenProvider tokenService) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup/tecnico")
    public ResponseEntity<?> signUpTecnico(@RequestBody @Valid SignUpTecnicoDto data) {
        try{
            service.signUpTecnico(data);
            return ResponseEntity.status(200).body("Conta criada com sucesso!");
        }catch (InvalidJwtException |InexistentLojaException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT,i.getMessage()),HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/signup/user")
    public ResponseEntity<?> signUpUser(@RequestBody @Valid SignUpUserDto data) {
        try{
            UserDetails c = service.signUpUser(data);
            return ResponseEntity.status(200).body(c);
        }catch (InvalidJwtException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT,i.getMessage()),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/signup/estilista")
    public ResponseEntity<?> signUpEstilista(@RequestBody @Valid SignUpEstilistaDto data) {
        try{
            UserDetails c = service.signUpEstilista(data);
            return ResponseEntity.status(200).body(c);
        }catch (InvalidJwtException i){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.CONFLICT,i.getMessage()),HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtDto> signIn(@RequestBody @Valid SignInDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var authUser = authenticationManager.authenticate(usernamePassword);
        var accessToken = tokenService.generateAccessToken((Cliente) authUser.getPrincipal());
        return ResponseEntity.ok(new JwtDto(accessToken));
    }


    @GetMapping("/estilistas")
    public List<EstilistaDto> getEstilistas() {
        return service.getallEstilistas();
    }

    @PostMapping("/verify")
    public ResponseEntity<?> verify(@RequestBody JwtDto token){
        try{
            String v = tokenService.validateToken(token.accessToken());
            return ResponseEntity.status(200).body(v);
        }catch (JWTVerificationException j){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN,j.getMessage()),HttpStatus.FORBIDDEN);
        }
    }
}