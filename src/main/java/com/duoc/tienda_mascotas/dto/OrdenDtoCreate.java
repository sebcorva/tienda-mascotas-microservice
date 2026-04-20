package com.duoc.tienda_mascotas.dto;

public record OrdenDtoCreate (
    Long id_pro,
    String nombre_cliente,
    String celular,
    Integer cantidad
){}
