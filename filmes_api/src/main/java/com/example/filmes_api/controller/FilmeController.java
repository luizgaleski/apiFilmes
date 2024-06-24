package com.example.filmes_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.filmes_api.domain.service.FilmeService;
import com.example.filmes_api.domain.dto.filme.FilmeRequestDTO;
import com.example.filmes_api.domain.dto.filme.FilmeResponseDTO;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public ResponseEntity<List<FilmeResponseDTO>> obterTodos() {
        return ResponseEntity.ok(filmeService.obterTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(filmeService.obterPorId(id));
    }

    @PostMapping
    public ResponseEntity<FilmeResponseDTO> cadastrar(@RequestBody FilmeRequestDTO dto) {
        FilmeResponseDTO responseDTO = filmeService.cadastrar(dto);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilmeResponseDTO> atualizar(@PathVariable Long id, @RequestBody FilmeRequestDTO dto) {
        FilmeResponseDTO responseDTO = filmeService.atualizar(id, dto);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        filmeService.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
