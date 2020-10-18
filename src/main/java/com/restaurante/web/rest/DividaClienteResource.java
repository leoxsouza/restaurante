package com.restaurante.web.rest;

import com.restaurante.service.DividaClienteService;
import com.restaurante.service.dto.ClienteDividaDTO;
import com.restaurante.service.dto.DividaClienteDTO;
import com.restaurante.service.dto.DividaClienteListDTO;
import com.restaurante.service.dto.QuitarDividaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/divida-cliente")
@RequiredArgsConstructor
@Slf4j
public class DividaClienteResource {

    private final DividaClienteService dividaClienteService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DividaClienteListDTO>> listarDividas() {
        log.info("Request para listar as dívidas dos clientes da empresa logada");
        return new ResponseEntity<>(dividaClienteService.listarDividas(), HttpStatus.OK);
    }

    @PostMapping(value = "/quitar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DividaClienteDTO> quitarDivida(@RequestBody QuitarDividaDTO quitarDividaDTO) {
        log.info("Request para quitar dívida do usuário: {}", quitarDividaDTO.getIdUsuarioCliente());
        return new ResponseEntity<>(dividaClienteService.quitarDivida(quitarDividaDTO), HttpStatus.OK);
    }

    @GetMapping(value = "/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ClienteDividaDTO>> listarDividaPorCliente() {
        log.info("Request para listar as dívidas do cliente logado");
        return new ResponseEntity<>(dividaClienteService.listarDividaPorCliente(), HttpStatus.OK);
    }
}
