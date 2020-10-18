package com.restaurante.service;

import com.restaurante.domain.Empresa;
import com.restaurante.domain.Produto;
import com.restaurante.repository.ProdutoRepository;
import com.restaurante.service.dto.ProdutoDTO;
import com.restaurante.service.mapper.ProdutoMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    private final ProdutoMapper produtoMapper;

    private final UsuarioService usuarioService;

    public ProdutoDTO cadastrar(ProdutoDTO produtoDTO) throws Exception {
        Produto produto = produtoMapper.toEntity(produtoDTO);
        setEmpresa(produto);
        verificarDuplicidade(produto);

        return produtoMapper.toDto(produtoRepository.save(produto));
    }

    private void verificarDuplicidade(Produto produto) throws Exception {
        if (produtoRepository.existsByDescricaoIgnoreCaseAndAndEmpresaId(produto.getDescricao(), produto.getEmpresa().getId())) {
            throw new Exception("Produto já existe!");
        }
    }

    private void setEmpresa(Produto produto) {
        Empresa empresa = new Empresa();
        empresa.setId(usuarioService.getIdEmpresaByLogin());
        produto.setEmpresa(empresa);
    }

    public List<ProdutoDTO> listarProdutos() {
        return produtoRepository.getAllByEmpresaId(usuarioService.getIdEmpresaByLogin())
                .stream().map(produtoMapper::toDto).collect(Collectors.toList());
    }
}
