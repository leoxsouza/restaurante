package com.restaurante.service;

import com.restaurante.domain.ComprasCliente;
import com.restaurante.repository.ComprasClienteRepository;
import com.restaurante.service.dto.ComprasClienteDTO;
import com.restaurante.service.dto.ComprasClienteListDTO;
import com.restaurante.service.mapper.ComprasClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ComprasClienteService implements UsuarioBase {

    private final ComprasClienteRepository comprasClienteRepository;

    private final ComprasClienteMapper comprasClienteMapper;

    private final UsuarioService usuarioService;

    private final DividaClienteService dividaClienteService;

    public ComprasClienteDTO cadastrar(ComprasClienteDTO comprasClienteDTO) {
        ComprasCliente comprasCliente = comprasClienteMapper.toEntity(comprasClienteDTO);
        processamentoSalvar(comprasCliente);
        comprasClienteRepository.save(comprasCliente);

        dividaClienteService.cadastrar(comprasClienteDTO);

        return comprasClienteMapper.toDto(comprasCliente);
    }

    private void processamentoSalvar(ComprasCliente comprasCliente) {
        comprasCliente.setDataCompra(LocalDateTime.now());
        comprasCliente.setUsuarioEmpresa(usuarioService.findByLogin(getUsernameLogado()));
    }

    public List<ComprasClienteListDTO> getComprasPorEmpresa(Long idEmpresa) {
        return comprasClienteRepository.getComprasPorUsuario(getUsernameLogado(), idEmpresa);
    }
}
