package com.restaurante.web.rest;

import com.restaurante.service.ProdutoService;
import com.restaurante.service.dto.ProdutoDTO;
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
@RequestMapping(value = "/api/produto")
@RequiredArgsConstructor
@Slf4j
public class ProdutoResource {

    private final ProdutoService produtoService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProdutoDTO> salvar(@RequestBody ProdutoDTO produtoDTO) throws Exception {
        log.info("Request para salvar um produto: {}", produtoDTO.toString());
        return new ResponseEntity<>(produtoService.cadastrar(produtoDTO), HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdutoDTO>> listarProdutos() {
        log.info("Request para listar os produtos da empresa logada");
        return new ResponseEntity<>(produtoService.listarProdutos(), HttpStatus.OK);
    }

}
