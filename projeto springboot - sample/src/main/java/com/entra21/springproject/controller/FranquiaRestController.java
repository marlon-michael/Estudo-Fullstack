package com.entra21.springproject.controller;

import com.entra21.springproject.model.dto.FranquiaDTO;
import com.entra21.springproject.model.dto.FranquiaPayloadDTO;
import com.entra21.springproject.view.service.FranquiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/franquias")
public class FranquiaRestController {
    @Autowired // create a singleton of franquiaService
    private FranquiaService franquiaService;

    @GetMapping
    public List<FranquiaDTO> getFranquias(){
        return franquiaService.getAll();
    }

    @PostMapping("/new")
    public void NEWaddFranquia(@RequestBody FranquiaPayloadDTO franquia){
        franquiaService.save(franquia);
    }

    @GetMapping("/{id}")
    public FranquiaDTO getFranquia(@PathVariable(name = "id") Long id){
        return franquiaService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteFranquia(@PathVariable(name = "id") Long id){
        franquiaService.deleteById(id);
    }

    @PutMapping("/{id}")
    public FranquiaDTO updateFranquia(@PathVariable(name = "id") Long id, @RequestBody String novoNome){
        return franquiaService.update(id, novoNome);
    }
}
