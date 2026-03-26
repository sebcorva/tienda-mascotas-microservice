package com.duoc.tienda_mascotas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    
    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> getAll() {
        return productoService.getAll();
    }

    // http://localhost:8080/productos/?id_pro=1
    @GetMapping("/")
    public Producto getById(@RequestParam Long id_pro) {
        return productoService.searchById(id_pro);
    }
    // http://localhost:8080/productos/stock/restar?id_pro=1&cantidad=2
    @GetMapping("/stock/restar")
    public String reduceStock(@RequestParam Long id_pro, @RequestParam Integer cantidad) {
        return productoService.reduceStock(id_pro, cantidad);
    }
    
    @GetMapping("/stock/recuperar")
    public String reStock(@RequestParam Long id_pro, @RequestParam Integer cantidad) {
        return productoService.reStock(id_pro, cantidad);
    }
}
