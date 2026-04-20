package com.duoc.tienda_mascotas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.duoc.tienda_mascotas.model.Producto;

@Service
public interface ProductoService {
    List<Producto> getAllProducto();
    Optional<Producto> getProductoById(Long id_pro);
    Producto createProducto(Producto producto);
    Producto updateProducto(Long id_pro, Producto producto);
    void deleteProducto(Long id_pro);
    void reduceStock(Long id_pro, Integer cantidad);
    void reStock(Long id_pro, Integer cantidad);
}
