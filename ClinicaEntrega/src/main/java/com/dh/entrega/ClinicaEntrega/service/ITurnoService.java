package com.dh.entrega.ClinicaEntrega.service;

import com.dh.entrega.ClinicaEntrega.entity.Turno;

import java.util.List;
import java.util.Optional;

public interface ITurnoService {
    Turno guardar(Turno turno);
    List<Turno> listarTodos();
    Optional<Turno> buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(Turno turno);
}
