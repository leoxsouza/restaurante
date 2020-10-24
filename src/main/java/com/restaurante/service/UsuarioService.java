package com.restaurante.service;

import com.restaurante.domain.Usuario;
import com.restaurante.domain.UsuariosRole;
import com.restaurante.domain.UsuariosRolePK;
import com.restaurante.repository.UsuarioRepository;
import com.restaurante.repository.UsuariosRoleRepository;
import com.restaurante.service.dto.DropDownDTO;
import com.restaurante.service.dto.UsuarioDTO;
import com.restaurante.service.mapper.RolesMapper;
import com.restaurante.service.mapper.UsuarioMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class UsuarioService implements UsuarioBase {

    private final UsuarioRepository usuarioRepository;

    private final UsuarioMapper usuarioMapper;

    private final UsuariosRoleRepository usuariosRoleRepository;

    private final RolesMapper rolesMapper;

    @Transactional(readOnly = true)
    public List<UsuarioDTO> listarUsuarios() {
        return usuarioRepository.findAll().stream().map(usuarioMapper::toDto).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioDTO findOne(Long idUsuario) {
        return usuarioMapper.toDto(usuarioRepository.findById(idUsuario).get());
    }

    public void excluirUsuario(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    public Usuario cadastrar(UsuarioDTO usuarioDto) throws Exception {
        usuarioDto.setSenha(new BCryptPasswordEncoder().encode(usuarioDto.getSenha()));
        verificarUsuario(usuarioDto);

        Usuario usuario = usuarioMapper.toEntity(usuarioDto);

        if (usuarioDto.getPessoa().getEmpresa() == null) {
            usuario.getPessoa().setEmpresa(null);
        }

        usuario = usuarioRepository.save(usuario);
        salvarRoles(usuarioDto, usuario);

        return usuario;
    }

    private void salvarRoles(UsuarioDTO usuarioDto, Usuario usuario) {
        usuarioDto.getPessoa().getTipoPessoa().setRole(usuarioDto);

        UsuariosRole usuariosRole = new UsuariosRole();
        UsuariosRolePK usuariosRolePK = new UsuariosRolePK(usuario, rolesMapper.toEntityRoles(usuarioDto.getRole()));
        usuariosRole.setId(usuariosRolePK);
        usuariosRoleRepository.save(usuariosRole);
    }

    private void verificarUsuario(UsuarioDTO usuario) throws Exception {
        if (usuario.getId() == null && usuarioRepository.existsByLoginIgnoreCase(usuario.getLogin())) {
            throw new Exception("#iex Já existe um usuário com esse Login, favor escolher outro login! #fex");
        }

        // TODO verificar Cpf
    }

    public Usuario findByLogin(String login) {
        return usuarioRepository.findUsuarioByLogin(login);
    }

    public List<DropDownDTO> getClientesDropdown() {
        return usuarioRepository.getClientesDropdown();
    }

    public Long getIdByLogin(String login) {
        return usuarioRepository.getUsuarioByLogin(login);
    }

    public Long getIdEmpresaByLogin() {
        return usuarioRepository.getIdEmpresaByLogin(getUsernameLogado());
    }

    public List<String> getRolesByLogin(String login) {
       return usuariosRoleRepository.getRolesByLogin(login);
    }
}
