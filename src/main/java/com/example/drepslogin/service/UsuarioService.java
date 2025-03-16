package com.example.drepslogin.service;


import com.example.drepslogin.model.UsuarioModel;
import com.example.drepslogin.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public List<UsuarioModel> findAll() {
        return usuarioRepository.findAll();
    }

    public Optional<UsuarioModel> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioModel> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioModel save(UsuarioModel usuarioModel) {
        return usuarioRepository.save(usuarioModel);
    }
}
