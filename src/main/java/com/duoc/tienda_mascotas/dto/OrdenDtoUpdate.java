package com.duoc.tienda_mascotas.dto;

import jakarta.validation.constraints.*;

public record OrdenDtoUpdate(
    @NotNull(message = "El ID del producto es obligatorio")
    Long id_pro,

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", message = "El nombre solo debe contener letras")
    String nombre_cliente,

    @NotBlank(message = "El celular es obligatorio")
    String celular,

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad mínima debe ser 1")
    @Max(value = 1000, message = "La cantidad no puede exceder las 1000 unidades")
    Integer cantidad,

    @NotBlank(message = "El estado es obligatorio")
    String estado
){}