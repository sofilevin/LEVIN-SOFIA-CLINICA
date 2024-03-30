package com.dh.entrega.ClinicaEntrega.repository;

import com.dh.entrega.ClinicaEntrega.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPacienteRepository extends JpaRepository<Paciente, Long> {
}
