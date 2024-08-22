package com.example.demo.model.dto;

// @Data
public class ItemDTO {
    private Long id;
    private String localizador;
    private String statys;
    private String localEntrega;
    private String nomeRecebedor;
    public Long getId() {
        return id;
    }
    public String getLocalizador() {
        return localizador;
    }
    public String getStatys() {
        return statys;
    }
    public String getLocalEntrega() {
        return localEntrega;
    }
    public String getNomeRecebedor() {
        return nomeRecebedor;
    }
}
