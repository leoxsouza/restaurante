package com.restaurante.service;

import com.restaurante.domain.Usuario;
import com.restaurante.repository.UsuarioRepository;
import com.restaurante.service.dto.UsuarioDTO;
import com.restaurante.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public Usuario cadastrar(Usuario usuario) throws Exception {
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        if (usuario.getId() == null && usuarioRepository.existsByLoginIgnoreCase(usuario.getLogin())) {
            //TODO criar parametrized exception
            throw new Exception("Usuário já existe!");
        }
        return usuarioRepository.save(usuario);
    }
}
