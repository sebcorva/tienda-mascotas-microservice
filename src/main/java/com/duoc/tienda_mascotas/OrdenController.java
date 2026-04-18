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
    public List<Orden> getAllOrden() {
        return ordenService.getAllOrden();
    }
    // 
    @GetMapping("/{num_orden}")
    public Orden getOrdenById(@PathVariable Long num_orden) {
        return ordenService.getOrdenById(num_orden).orElse(null);
    }
    // http://localhost:8080/orden/check/4
    @GetMapping("/check/{num_orden}")
    public String checkEstado(@PathVariable Long num_orden) {
        return ordenService.checkEstado(num_orden);
    }
    // http://localhost:8080/orden
    /* {
        "id_pro": 6,
        "nombre_cliente": "Nancy Magaly",
        "celular": "+56944786515",
        "cantidad": 10
    } */
    @PostMapping
    public Orden createOrden(@RequestBody OrdenDtoCreate dto) {
        return ordenService.createOrden(dto);
    }
    // http://localhost:8080/orden/4
    @DeleteMapping("/{num_orden}")
    public void deleteOrden(@PathVariable Long num_orden) {
        ordenService.deleteOrden(num_orden);
    }
    // http://localhost:8080/orden/23
    /* {
        "id_pro": 6,
        "nombre_cliente": "Nancy Magaly Salazar",
        "celular": "+56944786515",
        "cantidad": 5,
        "estado": "RESERVADA"
    } */
    @PutMapping("/{num_orden}")
    public Orden updateOrden(@PathVariable Long num_orden, @RequestBody OrdenDtoUpdate dto) {
        return ordenService.updateOrden(num_orden, dto);
    }
    // http://localhost:8080/orden/completar/23
    @PutMapping("/completar/{num_orden}")
    public void completeOrden(@PathVariable Long num_orden) {
        ordenService.completeOrden(num_orden);
    }
    // http://localhost:8080/orden/estado/5
    // RESERVADA
    @PutMapping("/estado/{num_orden}")
    public void changeState(@PathVariable Long num_orden, @RequestBody String estado) {
        ordenService.changeState(num_orden, estado);
    }
}
