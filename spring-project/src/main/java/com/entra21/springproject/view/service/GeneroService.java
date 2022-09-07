package com.entra21.springproject.view.service;

import com.entra21.springproject.model.dto.FranquiaDTO;
import com.entra21.springproject.model.dto.GeneroDTO;
import com.entra21.springproject.model.dto.GeneroPayloadDTO;
import com.entra21.springproject.model.entity.FranquiaEntity;
import com.entra21.springproject.model.entity.GeneroEntity;
import com.entra21.springproject.view.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<GeneroDTO> findAll(){
        return generoRepository.findAll().stream().map(genero -> {
            GeneroDTO dto = new GeneroDTO();
            dto.setId(genero.getId());
            dto.setNome(genero.getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    public GeneroDTO findById(Long id){
        GeneroEntity e = generoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquia não encontrada!"));
        GeneroDTO dto = new GeneroDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        return dto;
    }

    public void save(GeneroPayloadDTO genero){
        GeneroEntity entity = new GeneroEntity();
        entity.setNome(genero.getNome());
        generoRepository.save(entity);
    }

    public GeneroDTO updateGenero(Long id, String nome){
        GeneroEntity e = generoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquia não encontrada!"));
        e.setNome(nome);
        e = generoRepository.save(e);
        GeneroDTO dto = new GeneroDTO();
        dto.setNome(e.getNome());
        dto.setId(e.getId());
        return dto;
    }

    public void deleteById(Long id){
        generoRepository.deleteById(id);
    }
}
