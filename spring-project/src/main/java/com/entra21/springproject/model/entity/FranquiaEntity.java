package com.entra21.springproject.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data //gen getter & setter for all methods
@Entity //set Entity
@Table(name = "franquia") //map id table
public class FranquiaEntity {
    @Column(name = "id") //map "id" column
    @Id//primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long id;

    @Column(name = "nome", nullable = false) //map "nome" column
    private String nome;
}
