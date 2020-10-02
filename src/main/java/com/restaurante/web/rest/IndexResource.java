package com.restaurante.web.rest;

import com.restaurante.domain.Usuario;
import com.restaurante.repository.UsuarioRepository;
import com.restaurante.service.UsuarioService;
import com.restaurante.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/usuario")
@RequiredArgsConstructor
public class IndexResource {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioService usuarioService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new ResponseEntity<>(usuarioSalvo, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> findOne(@PathVariable Long id) {
        return new ResponseEntity<>(usuarioService.findOne(id), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Long> excluirUsuario(@PathVariable Long id) {
        usuarioService.excluirUsuario(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
