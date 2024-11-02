package com.exemplo.encomendaService.model;


import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

@MappedSuperclass
public class BaseEntity {

    @Version
    private Integer version;

    public void setVersion(Integer version) {
        this.version = version;
    }
    
    public Integer getVersion() {
        return version;
    }

}
