package com.restaurante.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public interface UsuarioBase {

    default String getUsernameLogado() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
