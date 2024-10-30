package com.example.favoritosService.model;

/**
 * "Visual Paradigm: DO NOT MODIFY THIS FILE!"
 *
 * This is an automatic generated file. It will be regenerated every time
 * you generate persistence class.
 *
 * Modifying its content may cause the program not work, or your work may lost.
 */

/**
 * Licensee: joao(Universidade do Minho)
 * License Type: Academic
 */

import java.io.Serializable;
import jakarta.persistence.*;
@Entity
@org.hibernate.annotations.Proxy(lazy=false)
@Table(name="Loja")
public class Loja implements Serializable {
    public Loja() {
    }

    @Column(name="IdLoja", nullable=false, length=10)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FAVORITOSSERVICE_LOJA_IDLOJA_GENERATOR")
    @SequenceGenerator(name="FAVORITOSSERVICE_LOJA_IDLOJA_GENERATOR", sequenceName="FAVORITOSSERVICE_LOJA_IDLOJA_SEQ")
    private int idLoja;


    @Column(name="Identificador", nullable=true)
    private int identificador;

    public Loja(int id) {
        this.identificador = id;
    }

    private void setIdLoja(int value) {
        this.idLoja = value;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public int getORMID() {
        return getIdLoja();
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public String toString() {
        return String.valueOf(getIdLoja());
    }

}