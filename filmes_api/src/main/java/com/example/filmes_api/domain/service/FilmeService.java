package com.example.filmes_api.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.filmes_api.domain.exception.ResourceBadRequestException;
import com.example.filmes_api.domain.exception.ResourceNotFoundException;
import com.example.filmes_api.domain.model.Filme;
import com.example.filmes_api.domain.model.Usuario;
import com.example.filmes_api.domain.repository.FilmeRepository;
import com.example.filmes_api.domain.dto.filme.FilmeRequestDTO;
import com.example.filmes_api.domain.dto.filme.FilmeResponseDTO;

@Service
public class FilmeService implements ICRUDService<FilmeRequestDTO, FilmeResponseDTO> {

    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<FilmeResponseDTO> obterTodos() {
        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Filme> filmes = filmeRepository.findByUsuario(usuario);
        return filmes.stream()
                .map(filme -> mapper.map(filme, FilmeResponseDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FilmeResponseDTO obterPorId(Long id) {
        Optional<Filme> optFilme = filmeRepository.findById(id);
        if (optFilme.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível encontrar o filme com id: " + id);
        }
        return mapper.map(optFilme.get(), FilmeResponseDTO.class);
    }

    @Override
    public FilmeResponseDTO cadastrar(FilmeRequestDTO dto) {
        validarFilme(dto);

        Usuario usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Filme filme = mapper.map(dto, Filme.class);
        filme.setUsuario(usuario); // Associa o filme ao usuário logado
        filme.setDataCadastro(new Date()); // Define a data de cadastro

        filme = filmeRepository.save(filme);
        return mapper.map(filme, FilmeResponseDTO.class);
    }

    @Override
    public FilmeResponseDTO atualizar(Long id, FilmeRequestDTO dto) {
        obterPorId(id); // Verifica se o filme existe

        validarFilme(dto);

        Filme filme = mapper.map(dto, Filme.class);
        filme.setId(id);

        filme = filmeRepository.save(filme);
        return mapper.map(filme, FilmeResponseDTO.class);
    }

    @Override
    public void deletar(Long id) {
        obterPorId(id); // Verifica se o filme existe
        filmeRepository.deleteById(id);
    }

    private void validarFilme(FilmeRequestDTO dto) {
        if (dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            throw new ResourceBadRequestException("O título do filme é obrigatório!");
        }
        if (dto.getGenero() == null) {
            throw new ResourceBadRequestException("O gênero do filme é obrigatório!");
        }
        if (dto.getDiretor() == null || dto.getDiretor().isBlank()) {
            throw new ResourceBadRequestException("O diretor do filme é obrigatório!");
        }
        if (dto.getProdutor() == null || dto.getProdutor().isBlank()) {
            throw new ResourceBadRequestException("O produtor do filme é obrigatório!");
        }
        if (dto.getAno() <= 0) {
            throw new ResourceBadRequestException("O ano do filme deve ser maior que zero!");
        }
        if (dto.getDuracao() <= 0) {
            throw new ResourceBadRequestException("A duração do filme deve ser maior que zero!");
        }
    }
}
