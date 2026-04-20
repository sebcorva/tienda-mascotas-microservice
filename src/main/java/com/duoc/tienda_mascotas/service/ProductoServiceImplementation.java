package com.duoc.tienda_mascotas.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duoc.tienda_mascotas.model.Orden;
import com.duoc.tienda_mascotas.model.Producto;
import com.duoc.tienda_mascotas.repository.ProductoRepository;

@Service
public class ProductoServiceImplementation implements ProductoService{
    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> getAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id_pro) {
        if(id_pro!=null){
            return productoRepository.findById(id_pro);
        }else {
            throw new IllegalArgumentException("Producto no encontrado");
        } 
    }

    @Override
    public void reduceStock(Long id_pro, Integer cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a reducir debe ser mayor a cero");
        }
        Producto stockProducto = getProductoById(id_pro).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (stockProducto.getStock() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock. Disponible: " + stockProducto.getStock());
        }
        
        stockProducto.setStock(stockProducto.getStock() - cantidad);
        productoRepository.save(stockProducto);
    }

    @Override
    public void reStock(Long id_pro, Integer cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a aumentar debe ser mayor a cero");
        }

        Producto stockProducto = getProductoById(id_pro).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        stockProducto.setStock(stockProducto.getStock() + cantidad);
        productoRepository.save(stockProducto);
    }

    @Override
    public Producto createProducto(Producto producto) {
        if (producto.getPrecio() == null || producto.getPrecio() < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo o nulo");
        }

        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long id_pro, Producto producto) {
        Producto newProducto = getProductoById(id_pro).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        newProducto.setId_pro(id_pro);
        newProducto.setNombre_pro(producto.getNombre_pro());
        newProducto.setPrecio(producto.getPrecio());
        newProducto.setStock(producto.getStock());
        return productoRepository.save(newProducto);
    }

    @Override
    public void deleteProducto(Long id_pro) {
        if (!productoRepository.existsById(id_pro)) {
            throw new RuntimeException("No se puede eliminar: El producto con ID " + id_pro + " no existe");
        }

        productoRepository.deleteById(id_pro);
    }
}
