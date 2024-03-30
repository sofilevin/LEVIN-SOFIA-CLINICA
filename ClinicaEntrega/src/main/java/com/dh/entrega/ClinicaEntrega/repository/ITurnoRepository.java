package com.dh.entrega.ClinicaEntrega.repository;

import com.dh.entrega.ClinicaEntrega.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITurnoRepository extends JpaRepository<Turno, Long> {
}
