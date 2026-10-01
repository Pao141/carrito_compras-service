package com.paola.carrito_compras.repository;

import com.paola.carrito_compras.dto.ProductoDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos")
public interface IProductoAPI {
    @GetMapping("/api/productos/{id}")
    ProductoDTO obtenerProductoPorId(@PathVariable("id") Long id);

}
