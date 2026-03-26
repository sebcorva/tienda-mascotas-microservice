package com.duoc.tienda_mascotas;

import lombok.*; 

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orden {
    private Long num_orden;
    private Long id_pro;
    private String nombre_cliente;
    private String celular;
    private Integer cantidad;
    private Integer total_compra;
    private String estado;
}
