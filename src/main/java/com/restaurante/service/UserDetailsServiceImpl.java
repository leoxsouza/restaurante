package com.restaurante.service;

import com.restaurante.domain.Usuario;
import com.restaurante.domain.UsuariosRole;
import com.restaurante.exception.SenhaInvalidaException;
import com.restaurante.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepository.findUsuarioByLogin(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuário não foi encontrado");
        }

        List<GrantedAuthority> listGrantAuthority = new ArrayList<>();
        checkGrantAuthorities(usuario, listGrantAuthority);

        return validateUser(username, listGrantAuthority, usuario);
    }

    private void checkGrantAuthorities(Usuario user, List<GrantedAuthority> listGrantAuthority) {
        if(user != null && user.getRoles() != null && !user.getRoles().isEmpty())
            for(UsuariosRole roleUser : user.getRoles()){
                final String PREFIX = "ROLE_";
                String role = PREFIX + roleUser.getAuthority();
                listGrantAuthority.add(new SimpleGrantedAuthority(role));
            }
    }

    private UserDetails validateUser(String login ,List<GrantedAuthority> listGrantAuthority, Usuario user) {
        UserDetails userDetails= null;
        if(user!=null){
            boolean accountNonLocked=true;
            boolean enabledUser=true;
            boolean accountNonExpired=true;
            boolean credentialsNonExpired=true;
            userDetails = new User(login, user.getPassword(), enabledUser, accountNonExpired, credentialsNonExpired, accountNonLocked, listGrantAuthority);
        }
        return userDetails;
    }

    public UserDetails autenticar(Usuario usuario) {
        UserDetails userDetails = loadUserByUsername(usuario.getLogin());
        boolean senhasBatem = new BCryptPasswordEncoder().matches(usuario.getSenha(), userDetails.getPassword());

        if(senhasBatem) {
            return userDetails;
        }

        throw new SenhaInvalidaException();
    }
}
