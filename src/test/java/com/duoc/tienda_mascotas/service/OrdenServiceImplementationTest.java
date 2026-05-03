package com.duoc.tienda_mascotas.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;
import com.duoc.tienda_mascotas.dto.OrdenDtoCreate;
import com.duoc.tienda_mascotas.model.Orden;
import com.duoc.tienda_mascotas.model.Producto;
import com.duoc.tienda_mascotas.repository.OrdenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class OrdenServiceImplementationTest {
    @Mock
    private OrdenRepository ordenRepository;

    @Mock
    private ProductoService productoService;

    @InjectMocks
    private OrdenServiceImplementation ordenService;

    private Producto productoEjemplo;
    private Orden ordenEjemplo;

    @BeforeEach
    void setUp() {
        productoEjemplo = new Producto();
        productoEjemplo.setId_pro(6L);
        productoEjemplo.setPrecio(10290);
        productoEjemplo.setStock(10);

        ordenEjemplo = new Orden();
        ordenEjemplo.setNum_orden(1L);
        ordenEjemplo.setId_pro(6L);
        ordenEjemplo.setNombre_cliente("Juan Venegas");
        ordenEjemplo.setCelular("+56944553453");
        ordenEjemplo.setCantidad(2);
        ordenEjemplo.setTotal_compra(20580);
        ordenEjemplo.setEstado("CONFIRMADA");
    }

    @Test
    void testCreateOrden_Creado() {
        OrdenDtoCreate dto = new OrdenDtoCreate(6L, "Juan Venegas", "+56944553453", 2);
        
        when(productoService.getProductoById(6L)).thenReturn(Optional.of(productoEjemplo));
        when(ordenRepository.save(any(Orden.class))).thenReturn(ordenEjemplo);

        Orden resultado = ordenService.createOrden(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getNum_orden());
        assertEquals(6L, resultado.getId_pro());
        assertEquals("Juan Venegas", resultado.getNombre_cliente());
        assertEquals(20580, resultado.getTotal_compra()); // 10290 * 2
        
        verify(productoService, times(1)).reduceStock(6L, 2);
        verify(ordenRepository, times(1)).save(any(Orden.class));
    }

    @Test
    void testCreateOrden_StockInsuficiente() {
        //se intenta comprar 20 und pero solo hay 10 und
        OrdenDtoCreate dto = new OrdenDtoCreate(6L, "Juan", "123", 20);
        when(productoService.getProductoById(6L)).thenReturn(Optional.of(productoEjemplo));

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            ordenService.createOrden(dto);
        });

        assertEquals("Stock insuficiente", exception.getMessage());
        verify(ordenRepository, never()).save(any());
    }
}
