package com.example.utilizadoresService.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.example.utilizadoresService.dtos.*;
import com.example.utilizadoresService.model.*;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.utilizadoresService.Exceptions.InexistentLojaException;
import com.example.utilizadoresService.Exceptions.InvalidJwtException;
import com.example.utilizadoresService.dtos.EstilistaDto;
import com.example.utilizadoresService.dtos.SignUpEstilistaDto;
import com.example.utilizadoresService.dtos.SignUpTecnicoDto;
import com.example.utilizadoresService.dtos.SignUpUserDto;
import com.example.utilizadoresService.mapper.EstilistaMapper;
import com.example.utilizadoresService.model.Estilista;
import com.example.utilizadoresService.model.Loja;
import com.example.utilizadoresService.model.NormalCliente;
import com.example.utilizadoresService.model.Tecnico;
import com.example.utilizadoresService.repositories.ClienteRepository;
import com.example.utilizadoresService.repositories.EstilistaRepository;
import com.example.utilizadoresService.repositories.LojaRepository;
import com.example.utilizadoresService.repositories.NormalClienteRepository;
import com.example.utilizadoresService.repositories.TecnicoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    ClienteRepository clienteRepository;
    @Autowired
    NormalClienteRepository clienteNormalrepository;
    @Autowired
    LojaRepository lojaRepository;
    @Autowired
    EstilistaRepository estilistaRepository;
    @Autowired
    TecnicoRepository tecnicoRepository;
    @Autowired
    EstilistaMapper estilistamapper;

    @Value("${file.upload-dir}")
    private String uploadDir;



    public AuthService(ClienteRepository clienteRepository,NormalClienteRepository clienteNormalrepository, LojaRepository lojaRepository, EstilistaRepository estilistaRepository, TecnicoRepository tecnicoRepository, EstilistaMapper estilistamapper) {
        this.clienteNormalrepository = clienteNormalrepository;
        this.clienteRepository = clienteRepository;
        this.lojaRepository = lojaRepository;
        this.estilistaRepository = estilistaRepository;
        this.tecnicoRepository = tecnicoRepository;
        this.estilistamapper = estilistamapper;
    }

    public UserDetails signUpTecnico(SignUpTecnicoDto data) throws InvalidJwtException, InexistentLojaException {
        if (tecnicoRepository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        Optional<Loja> loja = lojaRepository.getLojaByNome(data.nomeLoja());
        Loja l;
        if (loja.isEmpty()) {throw new InexistentLojaException(data.nomeLoja());} else {l = loja.get();}
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Tecnico newUser = new Tecnico(data.nome(), data.username(), data.email(), encryptedPassword,"", l);
        return tecnicoRepository.save(newUser);
    }

    public UserDetails signUpUser(SignUpUserDto data) throws InvalidJwtException {
        if (clienteNormalrepository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        NormalCliente newUser = new NormalCliente(data.nome(), data.username(), data.email(), encryptedPassword, "", data.morada(), "", data.nrTelemovel(), data.genero());
        return clienteNormalrepository.save(newUser);
    }

    public UserDetails signUpEstilista(SignUpEstilistaDto data) throws InvalidJwtException {
        if (estilistaRepository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Estilista newUser = new Estilista(data.nome(), data.username(), data.email(), encryptedPassword,"",0, data.genero(),data.bio());
        return estilistaRepository.save(newUser);
    }


    public List<EstilistaDto> getallEstilistas(int page, int number){
        return estilistaRepository.findAll(PageRequest.of(page, number)).stream().map(x -> estilistamapper.EstilistaMapper(x) ).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clienteRepository.getClienteByUsername(username);
    }

    public UserDetails saveUserImagePath(UploadProfileImageDto data) {
        Cliente user = clienteRepository.getClienteByUsername(data.getUsername());
        user.setProfileImage(data.getProfile_image().getOriginalFilename());
        clienteRepository.save(user);
        try {
            saveImage(data.getProfile_image());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return user;

    }

    private String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }


    public UrlResource getImage( String username) {
        try {
            Cliente user = clienteRepository.getClienteByUsername(username);
            Path filePath = Paths.get(uploadDir).resolve(Objects.requireNonNull(user.getProfileImage()));
            UrlResource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return resource;
            } else {
                return null;
            }
        } catch (MalformedURLException e) {
            return null;
        }
    }

    public UserDetails loadStylistByUsername(String username) throws UsernameNotFoundException {
        return estilistaRepository.getClienteByUsername(username);
    }
}
