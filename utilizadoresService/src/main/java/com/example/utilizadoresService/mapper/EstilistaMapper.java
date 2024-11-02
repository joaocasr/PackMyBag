package com.example.utilizadoresService.mapper;

import com.example.utilizadoresService.dtos.EstilistaDto;
import com.example.utilizadoresService.model.Estilista;
import org.springframework.stereotype.Component;

@Component
public class EstilistaMapper {


    public EstilistaDto EstilistaMapper(Estilista estilista) {

        return new EstilistaDto(estilista.getUsername(), estilista.getProfileImage(), estilista.getNome(), estilista.getBio(), estilista.getRating());
    }
}
