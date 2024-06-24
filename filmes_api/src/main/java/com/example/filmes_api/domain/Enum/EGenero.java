package com.example.filmes_api.domain.Enum;

public enum EGenero {
    ACAO("Ação"),
    AVENTURA("Aventura"),
    COMEDIA("Comédia"),
    DRAMA("Drama"),
    // ... outros gêneros
    ;

    private String valor;

    private EGenero(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return this.valor;
    }
}
