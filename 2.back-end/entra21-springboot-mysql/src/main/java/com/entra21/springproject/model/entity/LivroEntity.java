package com.entra21.springproject.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "livro")
@PrimaryKeyJoinColumn(name = "id_item", referencedColumnName = "id")
public class LivroEntity extends ItemEntity{
    @Column(name = "autor")
    private String autor;

    @Column(name = "qtde_paginas")
    private Integer qtdePaginas;

    @Column(name = "ano_publicacao")
    private Integer anoPublicacao;

    @Column(name = "edicao")
    private Integer edicao;

    @Override
    public String getType() {
        return "Livro";
    }
}
