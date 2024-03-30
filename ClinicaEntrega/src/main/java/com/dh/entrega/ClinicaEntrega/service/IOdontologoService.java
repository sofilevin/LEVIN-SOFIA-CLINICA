package com.dh.entrega.ClinicaEntrega.service;


import com.dh.entrega.ClinicaEntrega.entity.Odontologo;

import java.util.List;
import java.util.Optional;

public interface IOdontologoService {
    Odontologo guardar (Odontologo odontologo);
    List<Odontologo> listarTodos();
    Odontologo buscarPorId(Long id);
    void eliminar(Long id);
    void actualizar(Odontologo odontologo);
    Optional<Odontologo> findByMatricula(String matricula);


}
