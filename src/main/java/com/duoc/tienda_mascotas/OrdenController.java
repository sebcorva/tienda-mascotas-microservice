package com.duoc.tienda_mascotas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orden")
public class OrdenController {
    
    @Autowired
    private OrdenService ordenService;

    @GetMapping
    public List<Orden> getAll() {
        return ordenService.getAll();
    }

    // http://localhost:8080/orden/?num_orden=1
    @GetMapping("/")
    public Orden searchById(@RequestParam Long num_orden) {
        return ordenService.searchById(num_orden);
    }

    // http://localhost:8080/orden/crear?id_pro=4&nombre_cliente=Nataly Peña&celular=+56935789686&cantidad=2
    @GetMapping("/crear")
    public String add(
        @RequestParam Long id_pro,
        @RequestParam String nombre_cliente,
        @RequestParam String celular,
        @RequestParam Integer cantidad
        ) {
        return ordenService.addOrden(id_pro, nombre_cliente, celular, cantidad);
    }

    // http://localhost:8080/orden/cancelar?num_orden=1
    @GetMapping("/cancelar")
    public String cancel(@RequestParam Long num_orden) {
        return ordenService.cancelOrden(num_orden);
    }

    // http://localhost:8080/orden/completar?num_orden=1
    @GetMapping("/completar")
    public String complete(@RequestParam Long num_orden) {
        return ordenService.completeOrden(num_orden);
    }

    // http://localhost:8080/orden/editar/telefono?num_orden=1&celular=+56987654321
    @GetMapping("/editar/telefono")
    public String editTelefono(@RequestParam Long num_orden, @RequestParam String celular) {
        return ordenService.changeTelefono(num_orden, celular);
    }
}
