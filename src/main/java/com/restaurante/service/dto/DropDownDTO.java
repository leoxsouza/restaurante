package com.restaurante.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DropDownDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long value;

    private String label;

}
