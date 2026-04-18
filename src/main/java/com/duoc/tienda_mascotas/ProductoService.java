package com.duoc.tienda_mascotas;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

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
