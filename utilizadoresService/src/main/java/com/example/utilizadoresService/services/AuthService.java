package com.example.utilizadoresService.services;

import com.example.utilizadoresService.Exceptions.InexistentLojaException;
import com.example.utilizadoresService.Exceptions.InvalidJwtException;
import com.example.utilizadoresService.dtos.EstilistaDto;
import com.example.utilizadoresService.dtos.SignUpEstilistaDto;
import com.example.utilizadoresService.dtos.SignUpTecnicoDto;
import com.example.utilizadoresService.dtos.SignUpUserDto;
import com.example.utilizadoresService.mapper.EstilistaMapper;
import com.example.utilizadoresService.model.*;
import com.example.utilizadoresService.repositories.ClienteRepository;
import com.example.utilizadoresService.repositories.EstilistaRepository;
import com.example.utilizadoresService.repositories.LojaRepository;
import com.example.utilizadoresService.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    ClienteRepository repository;
    @Autowired
    LojaRepository lojaRepository;
    @Autowired
    EstilistaRepository estilistaRepository;
    @Autowired
    TecnicoRepository tecnicoRepository;

    @Autowired
    EstilistaMapper estilistamapper;


    public AuthService(ClienteRepository repository, LojaRepository lojaRepository, EstilistaRepository estilistaRepository, TecnicoRepository tecnicoRepository, EstilistaMapper estilistamapper) {
        this.repository = repository;
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
        if (repository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        NormalCliente newUser = new NormalCliente(data.nome(), data.username(), data.email(), encryptedPassword, "", data.morada(), "", data.nrTelemovel(), data.genero());
        return repository.save(newUser);
    }

    public UserDetails signUpEstilista(SignUpEstilistaDto data) throws InvalidJwtException {
        if (estilistaRepository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Estilista newUser = new Estilista(data.nome(), data.username(), data.email(), encryptedPassword,"",0, data.genero(),data.bio());
        return estilistaRepository.save(newUser);
    }


    public List<EstilistaDto> getallEstilistas(){
        return estilistaRepository.findAll().stream().map(x -> estilistamapper.EstilistaMapper(x) ).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getClienteByUsername(username);
    }
}
