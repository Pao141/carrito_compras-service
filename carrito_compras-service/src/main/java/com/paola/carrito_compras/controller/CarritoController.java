package com.paola.carrito_compras.controller;

import com.paola.carrito_compras.dto.CarritoDTO;
import com.paola.carrito_compras.dto.ProductoDTO;
import com.paola.carrito_compras.model.Carrito;
import com.paola.carrito_compras.service.CarritoService;
import com.paola.carrito_compras.service.ICarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private ICarritoService carritoService;

    // Crear carrito
    @PostMapping("/crear")
    public CarritoDTO crearCarrito() {
        Carrito carrito = carritoService.crearCarrito();
        return carritoService.verCarritoConProductos(carrito.getId());
    }

    // Agregar producto con cantidad (por defecto 1 si no se pasa)
    @PostMapping("/{carritoId}/agregar")
    public CarritoDTO agregarProducto(@PathVariable Long carritoId,
                                      @RequestBody ProductoDTO productoDTO,
                                      @RequestParam(defaultValue = "1") int cantidad) {
        return carritoService.agregarProducto(carritoId, productoDTO, cantidad);
    }

    // Quitar producto por ID
    @DeleteMapping("/{carritoId}/quitar/{productoId}")
    public CarritoDTO quitarProducto(@PathVariable Long carritoId,
                                     @PathVariable Long productoId) {
        return carritoService.quitarProducto(carritoId, productoId);
    }

    // Ver carrito con detalle de productos
    @GetMapping("/{carritoId}")
    public CarritoDTO verCarrito(@PathVariable Long carritoId) {
        return carritoService.verCarritoConProductos(carritoId);
    }

    // Endpoint opcional si quieres mantener /detallado
    @GetMapping("/{carritoId}/detallado")
    public CarritoDTO verCarritoDetallado(@PathVariable Long carritoId) {
        return carritoService.verCarritoConProductos(carritoId);
    }
}
