package com.paola.carrito_compras.model;

import com.paola.carrito_compras.dto.CarritoProductoDTO;
import com.paola.carrito_compras.dto.ProductoDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Guardamos solo los IDs de productos
    @ElementCollection
    @CollectionTable(name = "carrito_productos", joinColumns = @JoinColumn(name = "carrito_id"))


    private List<CarritoProductoDTO> productos = new ArrayList<>();

    private double total;
}
