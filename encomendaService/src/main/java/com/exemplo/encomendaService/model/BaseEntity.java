package com.exemplo.encomendaService.model;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public class BaseEntity {

    @Version
    private Integer version;

    public Integer getVersion() {
        return version;
    }

}
