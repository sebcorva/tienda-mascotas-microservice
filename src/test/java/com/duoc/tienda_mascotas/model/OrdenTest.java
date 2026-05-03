package com.duoc.tienda_mascotas.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class OrdenTest {
    @Test
    void testGettersAndSetters() {
        Orden orden = new Orden();

        orden.setNum_orden(1L);
        orden.setId_pro(6L);
        orden.setNombre_cliente("Juan Venegas");
        orden.setCelular("+56944553453");
        orden.setCantidad(2);
        orden.setTotal_compra(20580);
        orden.setEstado("CONFIRMADA");

        assertEquals(1L, orden.getNum_orden());
        assertEquals(6L, orden.getId_pro());
        assertEquals("Juan Venegas", orden.getNombre_cliente());
        assertEquals("+56944553453", orden.getCelular());
        assertEquals(2, orden.getCantidad());
        assertEquals(20580, orden.getTotal_compra());
        assertEquals("CONFIRMADA", orden.getEstado());
    }
}
