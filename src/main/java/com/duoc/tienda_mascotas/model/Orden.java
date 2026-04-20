package com.duoc.tienda_mascotas.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(name = "num_orden")
    private Long num_orden;

    @NotNull(message = "El ID del producto es obligatorio")
    @Column(name = "id_pro")
    private Long id_pro;

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo debe contener letras")
    @Column(name = "nombre_cliente")
    private String nombre_cliente;

    @NotBlank(message = "El celular es obligatorio")
    @Column(name = "celular")
    private String celular;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    @Max(value = 1000, message = "La cantidad no puede exceder las 1000 unidades")
    @Column(name = "cantidad")
    private Integer cantidad;

    @Column(name = "total_compra")
    private Integer total_compra;

    @NotBlank(message = "El estado es obligatorio")
    @Column(name = "estado")
    private String estado;
}
