package com.duoc.tienda_mascotas.model;

import jakarta.persistence.*;
import lombok.*; 

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "orden")
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num_orden;
    private Long id_pro;
    private String nombre_cliente;
    private String celular;
    private Integer cantidad;
    private Integer total_compra;
    private String estado;
}
