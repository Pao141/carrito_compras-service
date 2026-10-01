package com.paola.carrito_compras.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarritoDTO {
    private Long id;
    private Double total;
    private List<CarritoProductoDTO> productos;
}
