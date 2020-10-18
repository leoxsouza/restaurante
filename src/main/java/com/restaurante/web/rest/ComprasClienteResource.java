package com.restaurante.web.rest;

import com.restaurante.service.ComprasClienteService;
import com.restaurante.service.dto.ComprasClienteDTO;
import com.restaurante.service.dto.ComprasClienteListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/compras-cliente")
@RequiredArgsConstructor
@Slf4j
public class ComprasClienteResource {

    private final ComprasClienteService comprasClienteService;

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ComprasClienteDTO> cadastrar(@RequestBody ComprasClienteDTO comprasClienteDTO) {
        log.info("Request para cadastrar uma compra: {}", comprasClienteDTO.toString());
        return new ResponseEntity<>(comprasClienteService.cadastrar(comprasClienteDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/{idEmpresa}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ComprasClienteListDTO>> getComprasPorEmpresa(@PathVariable Long idEmpresa) {
        log.info("Request para listar as compras por empresa: {}", idEmpresa);
        return new ResponseEntity<>(comprasClienteService.getComprasPorEmpresa(idEmpresa), HttpStatus.OK);
    }
}
