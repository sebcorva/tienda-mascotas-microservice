package com.duoc.tienda_mascotas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.duoc.tienda_mascotas.model.Producto;
import com.duoc.tienda_mascotas.service.ProductoService;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    // http://localhost:8080/productos
    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAllProducto();
    }

    // http://localhost:8080/productos/5
    @GetMapping("/{id_pro}")
    public Producto getProductoById(@PathVariable Long id_pro) {
        return productoService.getProductoById(id_pro).orElse(null);
    }
    // http://localhost:8080/productos
    /* {
        "nombre_pro": "Cortauñas gato",
        "precio": 3000,
        "stock": 25
    } */
    @PostMapping()
    public Producto createProducto(@Valid @RequestBody Producto producto) {
        return productoService.createProducto(producto);
    }
    // http://localhost:8080/productos/5
    /* {
        "nombre_pro": "Cortauñas gato V2",
        "precio": 3000,
        "stock": 25
    } */
    @PutMapping("/{id_pro}")
    public Producto updateProducto(@Valid @PathVariable Long id_pro, @RequestBody Producto producto) {
        return productoService.updateProducto(id_pro, producto);
    }
    // http://localhost:8080/productos/5
    @DeleteMapping("/{id_pro}")
    public void deleteProducto(@PathVariable Long id_pro) {
        productoService.deleteProducto(id_pro);
    }
    // http://localhost:8080/productos/restar-stock/9/10
    @PutMapping("/restar-stock/{id_pro}/{cantidad}")
    public void reduceStock(@PathVariable Long id_pro, @PathVariable Integer cantidad) {
        productoService.reduceStock(id_pro, cantidad);
    }
    // http://localhost:8080/productos/recuperar-stock/9/30
    @PutMapping("/recuperar-stock/{id_pro}/{cantidad}")
    public void reStock(@PathVariable Long id_pro, @PathVariable Integer cantidad) {
        productoService.reStock(id_pro, cantidad);
    }
}
