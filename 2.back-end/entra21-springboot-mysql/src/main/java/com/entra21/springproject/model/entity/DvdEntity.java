package com.entra21.springproject.model.entity;


import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@Table(name = "dvd")
@PrimaryKeyJoinColumn(name = "id_item", referencedColumnName = "id")
public class DvdEntity extends ItemEntity{
    @Column(name = "diretor")
    private String diretor;

    @Column(name = "duracao")
    private LocalTime duracao;

    @Column(name = "ano_lancamento")
    private Integer anoLancamento;

    @Override
    public String getType() {
        return "DVD";
    }
}
