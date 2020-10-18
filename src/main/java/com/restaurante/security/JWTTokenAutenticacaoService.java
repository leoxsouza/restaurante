package com.restaurante.security;

import com.restaurante.domain.Usuario;
import com.restaurante.service.UsuarioService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Component
@RequiredArgsConstructor
public class JWTTokenAutenticacaoService {

    private static final String SECRET = "DeliciasRest";

    @Value("${security.jwt.chave-assinatura}")
    private String chaveAssinatura;

    @Value("${security.jwt.expiracao}")
    private String expiracao;

    private final UsuarioService usuarioService;


    private Claims obterClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean tokenValido(String token) {
        try {
            Claims claims = obterClaims(token);

            Date dataExpiracao = claims.getExpiration();
            LocalDateTime data = dataExpiracao.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

            return !LocalDateTime.now().isAfter(data);

        } catch (Exception e) {
            return false;
        }
    }

    public String obterLoginUsuario(String token) throws ExpiredJwtException {
        return (String) obterClaims(token).getSubject();
    }

    public String gerarToken(Usuario usuario) {
        long expString = Long.parseLong(expiracao);
        LocalDateTime dataHoraExpiracao = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dataHoraExpiracao.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);

//        List<UsuariosRole> usuariosRoles = usuarioService.getRolesByLogin(usuario.getLogin());

        usuario = usuarioService.findByLogin(usuario.getLogin());

        List<String> rolesList = usuarioService.getRolesByLogin(usuario.getLogin());


        Map<String, Object> roles = new HashMap<>();
        roles.put("ROLES", rolesList);

        return Jwts
                .builder()
                .setClaims(roles)
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, chaveAssinatura)
                .compact();
    }
}
