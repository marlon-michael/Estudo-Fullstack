package com.entra21.springproject.controller;

import com.entra21.springproject.model.dto.GeneroDTO;
import com.entra21.springproject.model.dto.GeneroPayloadDTO;
import com.entra21.springproject.view.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroRestController {

    @Autowired
    private GeneroService generoService;

    @GetMapping
    public List<GeneroDTO> getGeneros() {
        return generoService.findAll();
    }

    @PostMapping
    public void addGenero(@RequestBody GeneroPayloadDTO genero) {
        generoService.save(genero);
    }

    @GetMapping("/{id}")
    public GeneroDTO getGenero(@PathVariable(name = "id") Long id) {
        return generoService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteGenero(@PathVariable(name = "id") Long id) {
        generoService.deleteById(id);
    }

    @PutMapping("/{id}")
    public GeneroDTO updateGenero(@PathVariable(name = "id") Long id, @RequestBody String novoNome) {
        return generoService.updateGenero(id, novoNome);
    }
}