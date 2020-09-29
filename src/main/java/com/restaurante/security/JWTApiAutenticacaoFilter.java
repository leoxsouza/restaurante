package com.restaurante.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/** Filtro onde todas as requisições serão capturadas para autenticar */
public class JWTApiAutenticacaoFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        liberaCors((HttpServletResponse) servletResponse);

        /* Estabelece autenticação para requisição*/
        Authentication authentication = new JWTTokenAutenticacaoService().getAuthentication((HttpServletRequest) servletRequest, (HttpServletResponse) servletResponse);

        /* Coloca o processo de autenticação no spring security */
        SecurityContextHolder.getContext().setAuthentication(authentication);

        /* Continua o processo */
        filterChain.doFilter(servletRequest, servletResponse);

    }

    private void liberaCors(HttpServletResponse servletResponse) {
        if(servletResponse.getHeader("Access-Control-Allow-Origin") == null)
        {
            servletResponse.addHeader("Access-Control-Allow-Origin", "*");
        }
        if(servletResponse.getHeader("Access-Control-Allow-Headers") == null)
        {
            servletResponse.addHeader("Access-Control-Allow-Headers", "*");
        }
        if(servletResponse.getHeader("Access-Control-Allow-Methods") == null)
        {
            servletResponse.addHeader("Access-Control-Allow-Methods", "GET, OPTIONS, HEAD, PUT, POST");
        }
        if(servletResponse.getHeader("Access-Control-Request-Headers") == null)
        {
            servletResponse.addHeader("Access-Control-Request-Headers", "*");
        }
    }
}
