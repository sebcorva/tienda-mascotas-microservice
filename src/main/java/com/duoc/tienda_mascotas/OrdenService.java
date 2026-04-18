package com.duoc.tienda_mascotas;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public interface OrdenService {
    List<Orden> getAllOrden();
    Optional<Orden> getOrdenById(Long id);
    Orden createOrden(OrdenDtoCreate dto);
    void deleteOrden(Long num_orden);
    Orden updateOrden(Long num_orden, OrdenDtoUpdate dto);
    String checkEstado(Long num_orden);
    void changeState(Long num_orden, String estado);
    void completeOrden(Long num_orden);
}
