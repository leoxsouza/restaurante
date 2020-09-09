package com.restaurante.web.rest;

import com.restaurante.dominio.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class IndexResource {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> init() {

        Usuario usuario = new Usuario();

        usuario.setId(1L);
        usuario.setLogin("leo");
        usuario.setNome("leonardo");
        usuario.setSenha("TESTE");
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
