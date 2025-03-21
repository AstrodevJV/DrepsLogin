package com.example.drepslogin.controller;


import com.example.drepslogin.model.UsuarioModel;
import com.example.drepslogin.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/all")
    public List<UsuarioModel> findAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/findid/{id}")
    public ResponseEntity<UsuarioModel> findById(@PathVariable Long id) {
        Optional<UsuarioModel> usuarioModel = usuarioService.findById(id);

        return usuarioModel.isPresent() ? ResponseEntity.ok(usuarioModel.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping("/findemail/{email}")
    public ResponseEntity<UsuarioModel> findByEmail(@PathVariable String email) {
        Optional<UsuarioModel> usuarioModel = usuarioService.findByEmail(email);

        return usuarioModel.isPresent() ? ResponseEntity.ok(usuarioModel.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/add")
    public ResponseEntity<UsuarioModel> add(@RequestBody UsuarioModel usuarioModel, UriComponentsBuilder uriBuilder) {
        UsuarioModel savedUser = usuarioService.save(usuarioModel);

        URI uri = uriBuilder
                .path("/api/v1/usuarios/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(uri).body(savedUser);
    }

}
