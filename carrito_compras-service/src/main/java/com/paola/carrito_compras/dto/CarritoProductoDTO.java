package com.paola.carrito_compras.dto;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CarritoProductoDTO {
    private Long productoId;
    private String nombre;
    private Double precio;
    private Integer cantidad;
}

