package com.restaurante.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class QuitarDividaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long idUsuarioCliente;

    private Double valorQuitado;
}
