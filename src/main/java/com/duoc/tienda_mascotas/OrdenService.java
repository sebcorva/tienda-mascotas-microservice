package com.duoc.tienda_mascotas;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenService {
    
    @Autowired
    private ProductoService productoService;

    private List<Orden> ordenes = new ArrayList();

    public OrdenService() {
        ordenes.add(new Orden(1L, 1L, "Sebastian Corvalan", "+56944786517", 1, 3000, "CONFIRMADA"));
    }

    public List<Orden> getAll() {
        return ordenes;
    }

    public Orden searchById(Long num_orden) {
        for (Orden o : ordenes) {
            if (o.getNum_orden().equals(num_orden)) {
                return o;
            }
        }
        return null;
    }

    public String addOrden(Long id_pro ,String nombre_cliente, String celular, Integer cantidad) {

        if (nombre_cliente == null || celular == null || cantidad == null) {
            return "Los datos ingresados no son validos";
        }

        Producto producto = productoService.searchById(id_pro);
        if (producto == null) {
            return "El producto ingresado no existe";
        }

        if (producto.getStock() < cantidad) {
            return "Stock insuficiente, el Stock actual es: " + producto.getStock();
        }

        Long newId = (long) (ordenes.size() +1);
        Integer total = producto.getPrecio() * cantidad ;

        Orden newOrden = new Orden(newId, id_pro, nombre_cliente, celular, cantidad, total, "CONFIRMADA");
        ordenes.add(newOrden);

        productoService.reduceStock(id_pro, cantidad);


        return "Nueva orden de compra creada: N° " + newOrden.getNum_orden();
    }

    public String changeState(Long num_orden, String estado) {
        if ("ANULADA".equals(estado) || "ENVIADA".equals(estado)) {
            Orden o = searchById(num_orden);
            if (o == null) {
                return "No se ha podido cambiar el estado";
            }
            String newState = estado;
            o.setEstado(newState);
            
            return "El estado de la orden de compra N° " + o.getNum_orden() + " cambio a: " + o.getEstado();
            
        }
        return "Estado erroneo, tu estado debe ser 'ANULADA' O 'ENVIADA";
    }
    
    public String cancelOrden(Long num_orden) {
        Orden o = searchById(num_orden);
        if (o == null) {
            return "La orden de compra no se ha podido cancelar";
        }
        if ("ENVIADA".equals(o.getEstado())) {
            return "No puedes cancelar una orden de compra Enviada";
        }
        changeState(num_orden, "ANULADA");
        productoService.reStock(o.getId_pro(), o.getCantidad());
        return "La orden de compra N° " + o.getNum_orden() + " a sido anulada";
    }

    public String completeOrden(Long num_orden) {
        Orden o = searchById(num_orden);
        if (o == null) {
            return "La orden de compra no se ha podido completar";
        }
        if ("ANULADA".equals(o.getEstado())) {
            return "No puedes completar una orden de compra Anulada";
        }
        changeState(num_orden, "ENVIADA");
        return "La orden de compra N° " + o.getNum_orden() + " a sido enviada";
        
    }
    
    public String changeTelefono(Long num_orden, String celular) {
        Orden o = searchById(num_orden);
        if (o == null) {
            return "El celular no se ha podido modificar";
        }
        if (celular.equals(o.getCelular())) {
            return "El celular ingresado ya existe en su orden de compra";
        }
        String newTelefono = celular;
        o.setCelular(newTelefono);

        return "El celular a cambiado a: " + o.getCelular(); 
    }
}
