package com.restaurante.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SobraProdutoListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nomeProduto;

    private Double qtdPeso;

    private Long qtdUnidade;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataSobra;

}
