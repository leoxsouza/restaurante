package com.restaurante.web.rest;

import com.restaurante.service.ComprasClienteService;
import com.restaurante.service.dto.ComprasClienteDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/compras-cliente")
@RequiredArgsConstructor
public class ComprasClienteResource {

    private final ComprasClienteService comprasClienteService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComprasClienteDTO> cadastrar(@RequestBody ComprasClienteDTO comprasClienteDTO) {
        return new ResponseEntity<>(comprasClienteService.cadastrar(comprasClienteDTO), HttpStatus.OK);
    }
}
