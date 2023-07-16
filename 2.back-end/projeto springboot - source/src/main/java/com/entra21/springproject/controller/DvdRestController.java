package com.entra21.springproject.controller;

import com.entra21.springproject.model.entity.DvdEntity;
import com.entra21.springproject.view.repository.DvdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dvds")
public class DvdRestController {
    @Autowired
    private DvdRepository dvdRepository;

    @GetMapping
    public List<DvdEntity> getAllDvds(){
        return dvdRepository.findAll();
    }
}
