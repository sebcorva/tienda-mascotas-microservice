package com.duoc.tienda_mascotas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            return null;
        } 
    }

    @Override
    public void reduceStock(Long id_pro, Integer cantidad) {
        Producto stockProducto = getProductoById(id_pro).orElse(null);
        stockProducto.setStock(stockProducto.getStock() - cantidad);
        productoRepository.save(stockProducto);
    }

    @Override
    public void reStock(Long id_pro, Integer cantidad) {
        Producto stockProducto = getProductoById(id_pro).orElse(null);
        stockProducto.setStock(stockProducto.getStock() + cantidad);
        productoRepository.save(stockProducto);
    }

    @Override
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Producto updateProducto(Long id_pro, Producto producto) {
        Producto newProducto = getProductoById(id_pro).orElse(null);
        newProducto.setId_pro(id_pro);
        newProducto.setNombre_pro(producto.getNombre_pro());
        newProducto.setPrecio(producto.getPrecio());
        newProducto.setStock(producto.getStock());
        return productoRepository.save(newProducto);
    }

    @Override
    public void deleteProducto(Long id_pro) {
        productoRepository.deleteById(id_pro);
    }
}
