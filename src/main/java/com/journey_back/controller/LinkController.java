package com.journey_back.controller;


import com.journey_back.model.LinkModel;
import com.journey_back.request.LinkRequest;
import com.journey_back.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin("*")
@RestController
@RequestMapping("/links")
public class LinkController {

    @Autowired
    private LinksService linksService;

    // Construtor
    public LinkController(LinksService service) {
        this.linksService = service;
    }

    // Listar Links
    @GetMapping
    public ResponseEntity<List<LinkModel>> getLinks() {
        return ResponseEntity.ok().body(linksService.getLinks());
    }

    // Cadastrar link
    @PostMapping("{id}")
    public ResponseEntity<LinkModel> registerLink(@RequestBody @Validated LinkModel linkModel) {
        return ResponseEntity.status(201).body(linksService.registerLink(linkModel));
    }

    // Atualizar link
    @PutMapping("{id}")
    public ResponseEntity<LinkModel> updateLink(@RequestBody @Validated UUID id, LinkRequest linkRequest) {
        return ResponseEntity.status(201).body(linksService.updateLink(id, linkRequest));
    }

    // Deletar Link
    @DeleteMapping("{id}")
    public ResponseEntity deleteLink(@RequestBody UUID id) {
        var exists = linksService.deleteLink(id);

        if (!exists) {
            throw new RuntimeException("Link não encontrado");
        } else {
            return ResponseEntity.status(204).build();
        }
    }


}