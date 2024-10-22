package com.example.catalogService.model;

import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;

@Getter
@MappedSuperclass
public class BaseEntity {

    @Version
    private Integer version;

}
