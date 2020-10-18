package com.restaurante.service;

import com.restaurante.domain.SobraProduto;
import com.restaurante.repository.SobraProdutoRepository;
import com.restaurante.service.dto.SobraProdutoDTO;
import com.restaurante.service.dto.SobraProdutoListDTO;
import com.restaurante.service.mapper.SobraProdutoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class SobraProdutoService {

    private final SobraProdutoRepository sobraProdutoRepository;

    private final SobraProdutoMapper sobraProdutoMapper;

    private final UsuarioService usuarioService;

    public SobraProdutoDTO salvar(SobraProdutoDTO sobraProdutoDTO) {
        SobraProduto sobraProduto = sobraProdutoMapper.toEntity(sobraProdutoDTO);
        sobraProduto.setDataSobra(LocalDate.now());
        return sobraProdutoMapper.toDto(sobraProdutoRepository.save(sobraProduto));
    }

    public List<SobraProdutoListDTO> listarSobraProdutos() {
        return sobraProdutoRepository.listarPorEmpresa(usuarioService.getIdEmpresaByLogin());
    }

}
