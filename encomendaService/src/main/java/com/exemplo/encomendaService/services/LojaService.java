package com.exemplo.encomendaService.services;

import com.exemplo.encomendaService.dto.LojaDTO;
import com.exemplo.encomendaService.mapper.LojaMapper;
import com.exemplo.encomendaService.model.Loja;

import com.exemplo.encomendaService.repositories.LojaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LojaService {

    @Autowired
    private LojaRepository lojaRepository;

    public LojaDTO getLojaByEncomendaId(Integer encomendaId) {
        Loja loja = lojaRepository.findLojaByEncomendaId(encomendaId);
        return loja != null ? LojaMapper.toDTO(loja) : null;
    }
}