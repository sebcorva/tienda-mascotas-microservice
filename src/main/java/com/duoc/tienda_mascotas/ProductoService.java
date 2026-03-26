package com.duoc.tienda_mascotas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductoService {
    private List<Producto> productos = new ArrayList();

    public ProductoService() {
        productos.add(new Producto(1L, "Cortauñas gato", 3000, 20));
        productos.add(new Producto(2L, "Arena Aglutinante 5Kg", 10290, 10));
        productos.add(new Producto(3L, "Cepillo Desenredante", 4500, 15));
        productos.add(new Producto(4L, "Fuente Bebedera",9580, 5));
    }

    public List<Producto> getAll() {
        return productos;
    }

    public Producto searchById(Long id_pro) {
        for (Producto p : productos) {
            if(p.getId_pro().equals(id_pro)) {
                return p;
            }
        }
        return null;
    }

    public String reduceStock(Long id_pro, Integer cantidad) {
        Producto p = searchById(id_pro);
        if (p == null) {
            return "No se ha podido realizar la reduccion de stock";
        }
        Integer newStock = (p.getStock() - cantidad);
        p.setStock(newStock);
        return "Se realizo la reduccion de stock";
    }

    public String reStock(Long id_pro, Integer cantidad) {
        Producto p = searchById(id_pro);
        if (p == null) {
            return "No se ha podido realizar el aumento de stock";
        }
        Integer newStock = (p.getStock() + cantidad);
        p.setStock(newStock);
        return "Se realizo el aumento de stock";
    }
}
