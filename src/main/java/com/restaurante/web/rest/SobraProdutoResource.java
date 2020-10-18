package com.restaurante.web.rest;

import com.restaurante.service.SobraProdutoService;
import com.restaurante.service.dto.SobraProdutoDTO;
import com.restaurante.service.dto.SobraProdutoListDTO;
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
@RequestMapping(value = "/api/sobra-produto")
@RequiredArgsConstructor
@Slf4j
public class SobraProdutoResource {

    private final SobraProdutoService sobraProdutoService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SobraProdutoDTO> salvar(@RequestBody SobraProdutoDTO sobraProdutoDTO) {
        log.info("Request para salvar uma sobra de produto: {}", sobraProdutoDTO.toString());
        return new ResponseEntity<>(sobraProdutoService.salvar(sobraProdutoDTO), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SobraProdutoListDTO>> listarSobraProdutos() {
        log.info("Request para listar as sobras produtos da empresa logada");
        return new ResponseEntity<>(sobraProdutoService.listarSobraProdutos(), HttpStatus.OK);
    }

}
