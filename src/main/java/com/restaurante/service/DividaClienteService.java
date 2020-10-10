package com.restaurante.service;

import com.restaurante.domain.DividaCliente;
import com.restaurante.repository.DividaClienteRepository;
import com.restaurante.service.dto.ComprasClienteDTO;
import com.restaurante.service.mapper.DividaClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class DividaClienteService implements UsuarioBase {

    private final DividaClienteRepository dividaClienteRepository;

    private final DividaClienteMapper dividaClienteMapper;

    private final UsuarioService usuarioService;

    public void cadastrar(ComprasClienteDTO comprasClienteDTO) {
        DividaCliente dividaCliente = dividaClienteRepository.findByUsuarioClienteId(comprasClienteDTO.getIdUsuarioCliente());
        processamentoSalvar(dividaCliente, comprasClienteDTO);
    }

    private void processamentoSalvar(DividaCliente dividaCliente, ComprasClienteDTO comprasClienteDTO) {
        if (dividaCliente == null) {
            dividaCliente = dividaClienteMapper.toEntity(comprasClienteDTO);
        } else {
            dividaCliente.setTotal(dividaCliente.getTotal() + comprasClienteDTO.getValorCompra());
        }

        dividaCliente.setUsuarioEmpresa(usuarioService.findByLogin(getUsernameLogado()));

        dividaClienteRepository.save(dividaCliente);
    }

}
