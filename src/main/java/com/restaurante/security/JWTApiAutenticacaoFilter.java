package com.restaurante.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/** Filtro onde todas as requisições serão capturadas para autenticar */
public class JWTApiAutenticacaoFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /* Estabelece autenticação para requisição*/
        Authentication authentication = new JWTTokenAutenticacaoService().getAuthentication((HttpServletRequest) servletRequest);

        /* Coloca o processo de autenticação no spring security */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /* Continua o processo */
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
