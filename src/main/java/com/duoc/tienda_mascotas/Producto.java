package com.duoc.tienda_mascotas;

import jakarta.persistence.*;
import lombok.*; 

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table (name = "producto")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_pro;
    private String nombre_pro;
    private Integer precio;
    private Integer stock;
}
