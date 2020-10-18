package com.restaurante.security;

import com.restaurante.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.filter.OncePerRequestFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebConfigSecurity extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;

    private final JWTTokenAutenticacaoService jwtTokenAutenticacaoService;

    @Bean
    public OncePerRequestFilter jwtFilter() {
        return new JwtAuthFilter(jwtTokenAutenticacaoService, userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues()).and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/api/usuario/login").permitAll()
                .antMatchers("/api/divida-cliente/**").hasAnyRole("CLIENTE", "ADMIN", "EMPRESA")
                .antMatchers("/api/compras-cliente/**").hasAnyRole("CLIENTE", "ADMIN", "EMPRESA")
                .antMatchers("/api/**").hasAnyRole("ADMIN", "EMPRESA")
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

}
