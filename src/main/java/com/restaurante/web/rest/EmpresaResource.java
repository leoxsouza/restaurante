package com.restaurante.web.rest;

import com.restaurante.service.EmpresaService;
import com.restaurante.service.dto.DropDownDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/empresa")
@RequiredArgsConstructor
@Slf4j
public class EmpresaResource {

    private final EmpresaService empresaService;

    @GetMapping(value = "/drop-down", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DropDownDTO>> getEmpresasDropdown() {
        log.info("Request para buscar dropdown de empresas");
        return new ResponseEntity<>(empresaService.getEmpresasDropdown(), HttpStatus.OK);
    }
}
