package com.restaurante.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DividaClienteListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Double total;

    private String nomeCliente;
}
