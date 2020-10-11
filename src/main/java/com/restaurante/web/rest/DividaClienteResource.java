package com.restaurante.web.rest;

import com.restaurante.service.DividaClienteService;
import com.restaurante.service.dto.DividaClienteDTO;
import com.restaurante.service.dto.QuitarDividaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/divida-cliente")
@RequiredArgsConstructor
@Slf4j
public class DividaClienteResource {

    private final DividaClienteService dividaClienteService;

    @PostMapping(value = "/quitar", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DividaClienteDTO> quitarDivida(@RequestBody QuitarDividaDTO quitarDividaDTO) {
        log.info("Request para quitar dívida do usuário: {}", quitarDividaDTO.getIdUsuarioCliente());
        return new ResponseEntity<>(dividaClienteService.quitarDivida(quitarDividaDTO), HttpStatus.OK);
    }
}
