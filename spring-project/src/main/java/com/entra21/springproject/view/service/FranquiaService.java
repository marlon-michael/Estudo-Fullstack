package com.entra21.springproject.view.service;

import com.entra21.springproject.model.dto.FranquiaDTO;
import com.entra21.springproject.model.dto.FranquiaPayloadDTO;
import com.entra21.springproject.model.entity.FranquiaEntity;
import com.entra21.springproject.view.repository.FranquiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FranquiaService {
    @Autowired
    private FranquiaRepository franquiaRepository;

    public List<FranquiaDTO> getAll(){
        return franquiaRepository.findAll().stream().map(fr->{
            FranquiaDTO dto = new FranquiaDTO();
            dto.setId(fr.getId());
            dto.setNome(fr.getNome());
            return dto;
        }).collect(Collectors.toList());
    }

    public FranquiaDTO findById(Long id){
        FranquiaEntity e = franquiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquia não encontrada!"));
        FranquiaDTO dto = new FranquiaDTO();
        dto.setId(e.getId());
        dto.setNome(e.getNome());
        return dto;
    }

    public void save(FranquiaPayloadDTO franquia){
        FranquiaEntity entity = new FranquiaEntity();
        entity.setNome(franquia.getNome());
        franquiaRepository.save(entity);
    }

    public FranquiaDTO update(Long id, String novoNome){
        FranquiaEntity e = franquiaRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Franquia não encontrada!"));
        e.setNome(novoNome);
        e = franquiaRepository.save(e);
        FranquiaDTO dto = new FranquiaDTO();
        dto.setNome(e.getNome());
        dto.setId(e.getId());
        return dto;
    }

    public void deleteById(Long id){
        franquiaRepository.deleteById(id);
    }

}
