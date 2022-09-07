package com.entra21.springproject.model.dto;

import lombok.Data;

@Data
public class ItemDTO {
    private Long id;
    private String titulo;
    private Boolean emprestado;
}
