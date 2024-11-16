package com.example.utilizadoresService.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Map;

import com.example.utilizadoresService.Exceptions.InexistentImage;
import com.example.utilizadoresService.dtos.*;
import com.example.utilizadoresService.model.Estilista;
import com.example.utilizadoresService.model.NormalCliente;
import com.example.utilizadoresService.model.Tecnico;
import com.example.utilizadoresService.repositories.ClienteRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.utilizadoresService.Exceptions.InexistentLojaException;
import com.example.utilizadoresService.Exceptions.InvalidJwtException;
import com.example.utilizadoresService.config.auth.TokenProvider;
import com.example.utilizadoresService.dtos.ErrorResponse;
import com.example.utilizadoresService.dtos.EstilistaDto;
import com.example.utilizadoresService.dtos.JwtDto;
import com.example.utilizadoresService.dtos.SignInDto;
import com.example.utilizadoresService.dtos.SignUpEstilistaDto;
import com.example.utilizadoresService.dtos.SignUpTecnicoDto;
import com.example.utilizadoresService.dtos.SignUpUserDto;
import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.services.AuthService;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

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
    public ResponseEntity<?> signIn(@RequestBody @Valid SignInDto data) {
        try{
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
            var authUser = authenticationManager.authenticate(usernamePassword);
            var accessToken = tokenService.generateAccessToken((Cliente) authUser.getPrincipal());
            return ResponseEntity.ok(new JwtDto(accessToken));
        }catch(AuthenticationException e ){
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.FORBIDDEN,e.getMessage()),HttpStatus.FORBIDDEN);
        }
    }


    @GetMapping("/estilistas")
    public List<EstilistaDto> getEstilistas(@RequestParam int page,@RequestParam int number) {
        return service.getallEstilistas(page,number);
    }

    @GetMapping("/estilistas/{id}")
    public UserDetails getSingleEstilista(@PathVariable String id) {
        return service.loadStylistByUsername(id);
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

    @PostMapping("/image")
    public ResponseEntity<String> uploadImage(@ModelAttribute UploadProfileImageDto data) {
        try {

            service.saveUserImagePath(data);
            return ResponseEntity.ok("Image uploaded successfully: " + data.getProfile_image());
        } catch (InexistentImage e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error uploading image");
        }
    }



    @GetMapping("/image/{username}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String username) {
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(service.getImage(username));
    }




    @GetMapping("/userinfo/{username}")
    public ResponseEntity<?> getUserInfo(@PathVariable String username) {
        try {
            Cliente user = service.getUserInfo(username);


            if (user instanceof Tecnico tecnico) {
                return ResponseEntity.ok(new TecnicoDto(tecnico.getUsername(), tecnico.getProfileImage(), tecnico.getNome(), tecnico.getLoja()));
            } else if (user instanceof NormalCliente normalCliente) {
                return ResponseEntity.ok(new NormalClienteDto(normalCliente.getUsername(), normalCliente.getProfileImage(), normalCliente.getNome(), normalCliente.getMorada(), normalCliente.getCartaoCredito(), normalCliente.getNrTelemovel(), normalCliente.getGenero()));
            } else if (user instanceof Estilista estilista) {
                return ResponseEntity.ok(new EstilistaDto(estilista.getUsername(), estilista.getProfileImage(), estilista.getNome(), estilista.getBio(), estilista.getRating()));
            } else {
                return ResponseEntity.ok(user);
            }
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping("/edit-profile")
    public ResponseEntity<?> editProfile(@RequestBody EditProfileDto data) {
        try {
            String profileImagePath = null;

            // Handle profile image upload via UploadProfileImageDto
            if (data.newProfileImage() != null && !data.newProfileImage().isEmpty()) {
                UploadProfileImageDto uploadDto = new UploadProfileImageDto(data.username(), data.newProfileImage());
                UserDetails updatedUser = service.saveUserImagePath(uploadDto); // Save image and update path
                profileImagePath = updatedUser instanceof Cliente ? ((Cliente) updatedUser).getProfileImage() : null;
            }

            // Update other user details
            UserDetails updatedUser = service.editProfile(
                    data.username(),
                    data.newName(),
                    data.newEmail(),
                    profileImagePath,
                    data.additionalFields()
            );

            return ResponseEntity.ok("Profile updated successfully for user: " + updatedUser.getUsername());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error updating profile: " + e.getMessage());
        }
    }







}