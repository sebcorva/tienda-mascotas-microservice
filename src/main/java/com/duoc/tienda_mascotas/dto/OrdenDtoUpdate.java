package com.duoc.tienda_mascotas.dto;

public record OrdenDtoUpdate(
    Long id_pro,
    String nombre_cliente,
    String celular,
    Integer cantidad,
    String estado
){}