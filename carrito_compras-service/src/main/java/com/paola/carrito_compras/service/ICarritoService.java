package com.paola.carrito_compras.service;

import com.paola.carrito_compras.dto.CarritoDTO;
import com.paola.carrito_compras.dto.ProductoDTO;
import com.paola.carrito_compras.model.Carrito;

public interface ICarritoService {

    Carrito crearCarrito();

    // Ahora recibe ProductoDTO y cantidad, devuelve CarritoDTO
    CarritoDTO agregarProducto(Long carritoId, ProductoDTO productoDTO, int cantidad);

    // Quitar producto solo por ID, devuelve CarritoDTO
    CarritoDTO quitarProducto(Long carritoId, Long productoId);

    // Consultar carrito con productos
    CarritoDTO verCarritoConProductos(Long carritoId);
}


