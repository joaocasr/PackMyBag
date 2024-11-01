package com.example.notificacoesService.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.notificacoesService.repositories.EncomendaRepository;
import com.example.notificacoesService.model.Encomenda;
import com.example.notificacoesService.model.Cliente;



@Service
public class EncomendaService {

    @Autowired
    private EncomendaRepository encomendaRepository;

    public Encomenda updateEncomendaStatus(int idEncomenda, String status) {

        Encomenda encomenda = encomendaRepository.findById(idEncomenda).orElseThrow();
        encomenda.setStatus(status);
        encomendaRepository.save(encomenda);
        encomenda.notifyObservers();
        return encomenda;

    }

    public void notifyClientAboutReturnDate(int idEncomenda, long tempoRestante) {

        Encomenda encomenda = encomendaRepository.findById(idEncomenda).orElseThrow();
        encomenda.notifyObservers();

    }

    public void criarEncomenda(Encomenda encomenda, Cliente cliente) {
        
        encomenda.registerObserver(cliente);
        encomendaRepository.save(encomenda);
    }
    
}
