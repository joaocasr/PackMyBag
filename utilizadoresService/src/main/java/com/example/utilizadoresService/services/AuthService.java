package com.example.utilizadoresService.services;

import com.example.utilizadoresService.Exceptions.InvalidJwtException;
import com.example.utilizadoresService.dtos.SignUpTecnicoDto;
import com.example.utilizadoresService.dtos.SignUpUserDto;
import com.example.utilizadoresService.model.Cliente;
import com.example.utilizadoresService.model.Loja;
import com.example.utilizadoresService.model.Tecnico;
import com.example.utilizadoresService.repositories.ClienteRepository;
import com.example.utilizadoresService.repositories.LojaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    ClienteRepository repository;
    @Autowired
    LojaRepository lojaRepository;

    public AuthService(LojaRepository lojaRepository, ClienteRepository repository) {
        this.lojaRepository = lojaRepository;
        this.repository = repository;
    }


    public UserDetails signUpTecnico(SignUpTecnicoDto data) throws InvalidJwtException {
        if (repository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        Loja loja = lojaRepository.getLojaByNome(data.nomeLoja());
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Tecnico newUser = new Tecnico(data.nome(), data.username(), data.email(), encryptedPassword, loja);
        return repository.save(newUser);
    }

    public UserDetails signUpUser(SignUpUserDto data) throws InvalidJwtException {
        if (repository.getClienteByUsername(data.username()) != null) {
            throw new InvalidJwtException("Username already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        Cliente newUser = new Cliente(data.nome(), data.username(), data.email(), encryptedPassword);
        return repository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.getClienteByUsername(username);
    }
}
