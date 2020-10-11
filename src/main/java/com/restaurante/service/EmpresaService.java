package com.restaurante.service;

import com.restaurante.repository.EmpresaRepository;
import com.restaurante.service.dto.DropDownDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public List<DropDownDTO> getEmpresasDropdown() {
        return empresaRepository.getEmpresasDropdown();
    }
}
