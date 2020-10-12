package com.restaurante.service;

import com.restaurante.domain.DividaCliente;
import com.restaurante.repository.DividaClienteRepository;
import com.restaurante.service.dto.ComprasClienteDTO;
import com.restaurante.service.dto.DividaClienteDTO;
import com.restaurante.service.dto.DividaClienteListDTO;
import com.restaurante.service.dto.QuitarDividaDTO;
import com.restaurante.service.mapper.DividaClienteMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class DividaClienteService implements UsuarioBase {

    private final DividaClienteRepository dividaClienteRepository;

    private final DividaClienteMapper dividaClienteMapper;

    private final UsuarioService usuarioService;

    public void cadastrar(ComprasClienteDTO comprasClienteDTO) {
        DividaCliente dividaCliente = findByClienteId(comprasClienteDTO.getIdUsuarioCliente());
        processamentoSalvar(dividaCliente, comprasClienteDTO);
    }

    private void processamentoSalvar(DividaCliente dividaCliente, ComprasClienteDTO comprasClienteDTO) {
        if (dividaCliente == null) {
            dividaCliente = dividaClienteMapper.toEntityFromCompras(comprasClienteDTO);
        } else {
            dividaCliente.setTotal(dividaCliente.getTotal() + comprasClienteDTO.getValorCompra());
        }

        dividaCliente.setUsuarioEmpresa(usuarioService.findByLogin(getUsernameLogado()));

        dividaClienteRepository.save(dividaCliente);
    }

    public DividaClienteDTO quitarDivida(QuitarDividaDTO quitarDividaDTO) {
        DividaCliente dividaCliente = findByClienteId(quitarDividaDTO.getIdUsuarioCliente());

        if (dividaCliente != null) {
            verificaValor(dividaCliente, quitarDividaDTO);
            dividaCliente.setTotal(dividaCliente.getTotal() - quitarDividaDTO.getValorQuitado());
            dividaCliente.setDataUltimoPagamento(LocalDateTime.now());
            dividaClienteRepository.save(dividaCliente);
        }

        return dividaClienteMapper.toDto(dividaCliente);

    }

    private void verificaValor(DividaCliente dividaCliente, QuitarDividaDTO quitarDividaDTO) {
        if (quitarDividaDTO.getValorQuitado() > dividaCliente.getTotal()) {
            //TODO lançar uma exceção
        }
    }

    public DividaCliente findByClienteId(Long clienteId) {

        //TODO Buscar tbm pelo usuarioEmpresa

        return dividaClienteRepository.findByUsuarioClienteId(clienteId);
    }

    public List<DividaClienteListDTO> listarDividas() {
        Long idUsuarioLogado = usuarioService.getIdByLogin(getUsernameLogado());
        return dividaClienteRepository.listarDividas(idUsuarioLogado);
    }
}
