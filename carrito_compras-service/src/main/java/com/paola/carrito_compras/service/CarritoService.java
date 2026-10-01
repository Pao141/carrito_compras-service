package com.paola.carrito_compras.service;

import com.paola.carrito_compras.dto.CarritoDTO;
import com.paola.carrito_compras.dto.CarritoProductoDTO;
import com.paola.carrito_compras.dto.ProductoDTO;
import com.paola.carrito_compras.model.Carrito;
import com.paola.carrito_compras.repository.ICarritoRepository;
import com.paola.carrito_compras.repository.IProductoAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CarritoService implements ICarritoService {

    @Autowired
    private ICarritoRepository carritoRepo;

    @Autowired
    private IProductoAPI productosAPI;

    @Override
    public Carrito crearCarrito() {
        Carrito carrito = new Carrito();
        carrito.setTotal(0.0);
        carrito.setProductos(new ArrayList<>());
        return carritoRepo.save(carrito);
    }

    @Override
    public CarritoDTO agregarProducto(Long carritoId, ProductoDTO productoDTO, int cantidad) {
        Optional<Carrito> carritoOpt = carritoRepo.findById(carritoId);
        if (carritoOpt.isEmpty()) return null;

        Carrito carrito = carritoOpt.get();

        // Si nombre o precio son nulos o 0, completamos desde microservicio
        if (productoDTO.getNombre() == null || productoDTO.getPrecio_individual() == 0.0) {
            ProductoDTO datos = productosAPI.obtenerProductoPorId(productoDTO.getId());
            productoDTO.setNombre(datos.getNombre());
            productoDTO.setPrecio_individual(datos.getPrecio_individual());
        }


        CarritoProductoDTO item = new CarritoProductoDTO(
                productoDTO.getId(),
                productoDTO.getNombre(),
                productoDTO.getPrecio_individual(),
                cantidad
        );

        carrito.getProductos().add(item);

        // Recalcular total
        carrito.setTotal(carrito.getProductos()
                .stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum()
        );

        carritoRepo.save(carrito);

        return verCarritoConProductos(carritoId);
    }

    @Override
    public CarritoDTO quitarProducto(Long carritoId, Long productoId) {
        Optional<Carrito> carritoOpt = carritoRepo.findById(carritoId);
        if (carritoOpt.isEmpty()) return null;

        Carrito carrito = carritoOpt.get();

        carrito.getProductos().removeIf(p -> p.getProductoId().equals(productoId));

        // Recalcular total
        carrito.setTotal(carrito.getProductos()
                .stream()
                .mapToDouble(p -> p.getPrecio() * p.getCantidad())
                .sum()
        );

        carritoRepo.save(carrito);

        return verCarritoConProductos(carritoId);
    }

    @Override
    public CarritoDTO verCarritoConProductos(Long carritoId) {
        Optional<Carrito> carritoOpt = carritoRepo.findById(carritoId);
        if (carritoOpt.isEmpty()) return null;

        Carrito carrito = carritoOpt.get();

        List<CarritoProductoDTO> productosDTO = carrito.getProductos()
                .stream()
                .map(p -> new CarritoProductoDTO(
                        p.getProductoId(),
                        p.getNombre(),
                        p.getPrecio(),
                        p.getCantidad()
                ))
                .toList();

        return new CarritoDTO(carrito.getId(), carrito.getTotal(), productosDTO);
    }
}
