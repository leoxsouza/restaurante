package com.restaurante.service;

import com.restaurante.repository.UsuarioRepository;
import com.restaurante.service.dto.UsuarioDTO;
import com.restaurante.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findOne(Long idUsuario) {
        return usuarioMapper.toDto(usuarioRepository.findById(idUsuario).get());
    }

    public void excluirUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}
