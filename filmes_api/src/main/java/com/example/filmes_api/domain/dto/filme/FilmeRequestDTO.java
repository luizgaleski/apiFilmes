package com.example.filmes_api.domain.dto.filme;

import com.example.filmes_api.domain.Enum.EGenero;

public class FilmeRequestDTO {
    private Long id;
    private String titulo;
    private EGenero genero;
    private String diretor;
    private String produtor;
    private int ano;
    private int duracao; // Em minutos


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public EGenero getGenero() {
        return genero;
    }
    public void setGenero(EGenero genero) {
        this.genero = genero;
    }
    public String getDiretor() {
        return diretor;
    }
    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }
    public String getProdutor() {
        return produtor;
    }
    public void setProdutor(String produtor) {
        this.produtor = produtor;
    }
    public int getAno() {
        return ano;
    }
    public void setAno(int ano) {
        this.ano = ano;
    }
    public int getDuracao() {
        return duracao;
    }
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
