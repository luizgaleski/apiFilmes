package com.example.filmes_api.domain.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.filmes_api.domain.model.Filme;
import com.example.filmes_api.domain.model.Usuario;

public interface FilmeRepository extends JpaRepository<Filme, Long>{
    
    List<Filme> findByUsuario(Usuario usuario);
}