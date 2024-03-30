package com.dh.entrega.ClinicaEntrega.service;

import com.dh.entrega.ClinicaEntrega.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface IPacienteService {
    Paciente guardar(Paciente paciente);
    List<Paciente> listarTodos();
    Paciente buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(Paciente paciente);
}
