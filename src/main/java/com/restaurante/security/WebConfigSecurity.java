package com.restaurante.security;

import com.restaurante.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) /* Ativando proteção contra usuário que não estão validados por token */
                .disable().authorizeRequests() /* Ativando permissão para página inicial do sistema */
                .antMatchers("/").permitAll() /* Ativando permissão para página inicial do sistema */
                .antMatchers("/index").permitAll() /* Ativando permissão para página inicial do sistema */
                .anyRequest().authenticated().and().logout().logoutSuccessUrl("/index") /* URL de logout - Redireciona após logout */
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")); /* mapeia Url de logout e invalida o usuario */



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
}
