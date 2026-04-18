package com.duoc.tienda_mascotas;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrdenServiceImplementation implements OrdenService{
    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoService productoService;

    @Override
    public List<Orden> getAllOrden() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> getOrdenById(Long num_orden) {
        if (num_orden!=null){
            return ordenRepository.findById(num_orden);
        } else {
            return null;
        }
    }

    @Override
    @Transactional
    public Orden createOrden(OrdenDtoCreate dto) {
        
        if (dto.nombre_cliente() == null || dto.celular() == null || dto.cantidad() == null) {
            throw new IllegalArgumentException("Los datos ingresados no son validos");
        }

        Producto producto = productoService.getProductoById(dto.id_pro()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (producto.getStock() < dto.cantidad()) {
            throw new IllegalArgumentException("Stock insuficiente");
        }
        
        productoService.reduceStock(dto.id_pro(), dto.cantidad());

        Integer total = producto.getPrecio() * dto.cantidad();
        Orden newOrden = new Orden();
        newOrden.setId_pro(dto.id_pro());
        newOrden.setNombre_cliente(dto.nombre_cliente());
        newOrden.setCelular(dto.celular());
        newOrden.setCantidad(dto.cantidad());
        newOrden.setTotal_compra(total);
        newOrden.setEstado("CONFIRMADA");
        return ordenRepository.save(newOrden);
    }

    @Override
    public void deleteOrden(Long num_orden) {
        Orden orden = getOrdenById(num_orden).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if ("CONFIRMADA".equals(orden.getEstado())) {
            productoService.reStock(orden.getId_pro(), orden.getCantidad());
        }
        ordenRepository.deleteById(num_orden);
    }

    @Override
    public Orden updateOrden(Long num_orden, OrdenDtoUpdate dto) {
        Orden newOrden = getOrdenById(num_orden).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        Producto producto = productoService.getProductoById(dto.id_pro()).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        Integer total = producto.getPrecio() * dto.cantidad();

        newOrden.setId_pro(dto.id_pro());
        newOrden.setNombre_cliente(dto.nombre_cliente());
        newOrden.setCelular(dto.celular());
        newOrden.setCantidad(dto.cantidad());
        newOrden.setTotal_compra(total);
        newOrden.setEstado(dto.estado());
        
        return ordenRepository.save(newOrden);
    }

    @Override
    public String checkEstado(Long num_orden) {
        Orden o = getOrdenById(num_orden).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        return o.getEstado();
    }

    @Override
    public void changeState(Long num_orden, String estado) {
        Orden o = getOrdenById(num_orden).orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        if ("ANULADA".equals(o.getEstado()) || "ENVIADA".equals(o.getEstado())) {
            throw new IllegalArgumentException("El estado no puede ser modificado");
        }
        o.setEstado(estado);
        ordenRepository.save(o);
    }

    @Override
    public void completeOrden(Long num_orden) {
        Orden o = getOrdenById(num_orden).orElseThrow(() -> new RuntimeException("Orden no encontrada"));
        if ("ANULADA".equals(o.getEstado())) {
            throw new IllegalArgumentException("No puedes completar una orden de compra Anulada");
        }
        changeState(num_orden, "ENVIADA");
    }
    
}
