package com.duoc.tienda_mascotas;

import lombok.*; 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto {
    private Long id_pro;
    private String nombre_pro;
    private Integer precio;
    private Integer stock;
}
