package com.restaurante.web.rest;

import com.restaurante.domain.Usuario;
import com.restaurante.exception.SenhaInvalidaException;
import com.restaurante.security.JWTTokenAutenticacaoService;
import com.restaurante.service.UserDetailsServiceImpl;
import com.restaurante.service.UsuarioService;
import com.restaurante.service.dto.CredenciaisDTO;
import com.restaurante.service.dto.DropDownDTO;
import com.restaurante.service.dto.TokenDTO;
import com.restaurante.service.dto.UsuarioDTO;
import com.restaurante.service.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
@RequiredArgsConstructor
@Slf4j
public class IndexResource {

    private final UsuarioService usuarioService;

    private final UserDetailsServiceImpl userDetailsService;

    private final UsuarioMapper usuarioMapper;

    private final JWTTokenAutenticacaoService jwtTokenAutenticacaoService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioDTO>> listarUsuarios() {
        log.info("Request para listar todos usuários");
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }

    @PostMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> cadastrar(@RequestBody UsuarioDTO usuario) throws Exception {
        //TODO NÃO ESTA SALVANDO EMPRESA
        log.info("Request para cadastrar usuário: {}", usuario.toString());
        return new ResponseEntity<>(usuarioService.cadastrar(usuario), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioDTO> findOne(@PathVariable Long id) {
        log.info("Request para buscar um usuário: {}", id);
        return new ResponseEntity<>(usuarioService.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/deletar/{id}")
    public ResponseEntity<Long> excluirUsuario(@PathVariable Long id) {
        log.info("Request para excluir um usuário: {}", id);
        usuarioService.excluirUsuario(id);

        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @PostMapping("/login")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciais) {
        log.info("Request para fazer o login");
        try {
            Usuario usuario = usuarioMapper.toEntity(credenciais);
            UserDetails usuarioAutenticado = userDetailsService.autenticar(usuario);
            String token = jwtTokenAutenticacaoService.gerarToken(usuario);
            return new TokenDTO(usuario.getLogin(), token);

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @GetMapping(value = "/clientes/dropdown", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<DropDownDTO>> getClientesDropdown() {
        log.info("Request para buscar dropdown de clientes");
        return new ResponseEntity<>(usuarioService.getClientesDropdown(), HttpStatus.OK);
    }
}
